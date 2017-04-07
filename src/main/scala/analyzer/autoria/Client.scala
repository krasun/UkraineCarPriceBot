package analyzer.autoria

import analyzer.autoria.models._
import play.api.libs.json._

import scalaj.http.Http
import scalacache._
import memoization._
import scalacache.memcached.MemcachedCache

import play.api.libs.json._
import play.api.libs.functional.syntax._


/**
  * Client for AUTO.RIA API
  */
class Client(memcachedCache: MemcachedCache) {

  implicit val scalaCache = ScalaCache(memcachedCache)

  implicit val resultReads: Reads[Result] = (
      (JsPath \ "total").read[Int] and
      (JsPath \ "arithmeticMean").read[Double] and
      (JsPath \ "interQuartileMean").read[Double] and
      (JsPath \ "classifieds").read[List[Int]]
    ) (Result.apply _)

  /**
    * Computes average price by specified query.
    *
    * @todo should be feature or async
    */
  def averagePrice(query: Query): Option[Result] = {
    val parameters = (Seq(
      ("custom" -> "0"),
      ("damage" -> "0"),
      ("under_credit" -> "0"),
      ("confiscated_car" -> "0"),
      ("onRepairParts" -> "0"),
      ("marka_id" -> query.brandId.toString),
      ("yers" -> query.year.toString)
    ) ++ (query.provinceId match { case None => Seq(); case Some(provinceId) => Seq(("state_id" -> provinceId.toString))})) ++
     (query.cityId match { case None => Seq(); case Some(cityId) => Seq(("city_id" -> cityId.toString))}) ++
     (query.modelId match { case None => Seq(); case Some(modelId) => Seq(("model_id" -> modelId.toString))})

    Json.parse(get("http://api.auto.ria.com/average", parameters)).validate[Result] match {
      case JsSuccess(value, _) => Some(value)
      case _ => None
    }
  }

  /**
    * List of available brands.
    */
  lazy val brands: List[CarBrand] = {
    val result = Json.parse(get("http://api.auto.ria.com/categories/1/marks")).as[List[JsValue]]
    for (item <- result) yield {
      val brandId = (item \ "value").as[Int]
      val brandName = (item \ "name").as[String]

      val result = Json.parse(get("http://api.auto.ria.com/categories/1/marks/" + brandId + "/models")).as[List[JsValue]]
      val models = for (item <- result) yield {
        CarModel((item \ "value").as[Int], (item \ "name").as[String])
      }

      CarBrand(brandId, brandName, models)
    }
  }

  /**
    * List of available provinces.
    */
  lazy val provinces: List[Province] = {
    val result = Json.parse(get("http://api.auto.ria.com/states")).as[List[JsValue]]
    for (item <- result) yield {
      val provinceId = (item \ "value").as[Int]
      val provinceName = (item \ "name").as[String]

      val result = Json.parse(get("http://api.auto.ria.com/states/" + provinceId + "/cities")).as[List[JsValue]]
      val cities = for (item <- result) yield {
        City((item \ "value").as[Int], (item \ "name").as[String])
      }

      Province(provinceId, provinceName, cities)
    }
  }

  private def get(url: String, parameters: Seq[(String, String)] = List()): String = memoizeSync {
    Http(url).params(parameters).asString.body
  }
}
