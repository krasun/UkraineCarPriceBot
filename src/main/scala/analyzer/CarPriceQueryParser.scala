package analyzer

/**
  * Responsible for parsing query as sentence provided by human
  * and creating Query object compatible with analyzer API.
  */
class CarPriceQueryParser(brands: List[(String, List[String])], cities: List[String]) {

  /**
    * Brand and model names in lowercase.
    */
  lazy val normalizedBrands = brands map { case (brand, models) => (normalize(brand), models map normalize)}

  /**
    * City names in lowercase.
    */
  lazy val normalizedCities = cities map normalize

  /**
    * Parses raw sentence and creates Query.
    *
    * It also can perform normalization, e.g. transliterate car brands and model from different languages.
    *
    * @param raw Raw sentence as Query
    * @return Query compatible with analyzer API
    */
  def parse(raw: String): CarPriceQuery = {
      val tokens = tokenize(raw)

      CarPriceQuery(matchBrandModel(tokens), matchYear(tokens), matchCity(tokens))
  }

  /**
    * Tokenizes string.
    *
    * @param string String
    *
    * @return Tokens
    */
  private def tokenize(string: String) = string.trim.split(" ") map normalize

  /**
    * Normalizes string.
    *
    * @param string String to normalize
    *
    * @return String in lowercase
    */
  private def normalize(string: String) = string.toLowerCase.trim

  /**
    * Tries to match brand and corresponding model.
    *
    * @param tokens
    *
    * @return Brand and model
    */
  private def matchBrandModel(tokens: Array[String]) = {
    def matchBrand(tokens: Array[String]) = normalizedBrands find { case (brand, _) => tokens.contains(brand)}

    matchBrand(tokens) match {
      case Some((brand, models)) => (Some(brand), models.find(tokens.contains))
      case _ => (None, None)
    }
  }

  /**
    * Tries to match city.
    *
    * @param tokens Query tokens (words)
    *
    * @return City
    */
  private def matchCity(tokens: Array[String]) = tokens.find(normalizedCities.contains)

  /**
    * Tries to match year.
    *
    * @param tokens Query tokens (words)
    *
    * @return Year
    */
  private def matchYear(tokens: Array[String]) = tokens.find(_.matches("\\d{4}")).map(_.toInt)
}
