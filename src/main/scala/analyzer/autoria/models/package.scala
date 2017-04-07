package analyzer.autoria

/**
  * Models.
  */
package object models {

  /**
    * Model.
    *
    * E.g. BMW or Honda.
    */
  case class CarBrand(id: Int, name: String, models: List[CarModel])

  /**
    * Model.
    *
    * E.g. A4 (Audi), M3 (BMW) or Rapid (Skoda).
    */
  case class CarModel(id: Int, name: String)

  /**
    * Province.
    *
    * E.g. Odessa Oblast.
    */
  case class Province(id: Int, name: String, cities: List[City])

  /**
    * City.
    *
    * E.g. Kyiv.
    */
  case class City(id: Int, name: String)

  /**
    * analyzer.carprice.Query to calculate price.
    */
  case class Query(brandId: Option[Int], modelId: Option[Int], year: Option[Int], provinceId: Option[Int], cityId: Option[Int])

  /**
    * Result.
    */
  case class Result(total: Int, arithmeticMean: Double, interQuartileMean: Double, classifieds: List[Int])
}
