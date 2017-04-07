package analyzer

import analyzer.autoria.models.{CarBrand, Province, Query}

/**
  * Translates application query to AUTO.RIA query.
  */
class QueryTranslator(brands: List[CarBrand], provinces: List[Province]) {

  /**
    * Translates application query.
    *
    * @param query
    * @return
    */
  def translate(query: CarPriceQuery): Query = {
    val brand = query.model match {
      case (Some(b), _) => brands.find(brand => brand.name.toLowerCase == b.toLowerCase)
      case _ => None
    }
    val model = (query.model, brand) match  {
      case ((_, Some(m)), Some(b)) => b.models.find(model => model.name.toLowerCase == m.toLowerCase)
      case _ => None
    }
    val (province, city) = query.city match {
      case Some(c) => {
        val province = provinces.find(p => p.cities.exists(city => city.name.toLowerCase == c.toLowerCase))
        (province.map(_.id), province.map(p => p.cities.find(city => city.name.toLowerCase == c.toLowerCase).get.id))
      }
      case _ => (None, None)
    }

    Query(brand.map(_.id), model.map(_.id), query.year, province, city)
  }
}
