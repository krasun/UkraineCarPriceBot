package analyzer

import org.scalatest._

class CarQueryParserSpec extends FlatSpec {

  trait ParserFixture {
    val brands = List(("BMW", List("M3", "5")), ("Audi", List("A4")))
    val cities = List("Kiev", "Odessa")

    val parser = new CarPriceQueryParser(brands, cities)
  }

  "A Car Price Query Parser" should "return \"empty\" Query for \"empty\" string" in new ParserFixture {
    val query = parser.parse("")

    assert(CarPriceQuery((None, None), None, None) === query)
  }

  it should "return Query with year for string with 4 digits, e.g. \"1980\"" in new ParserFixture {
    val query = parser.parse("1980")

    assert(CarPriceQuery((None, None), Some(1980), None) === query)
  }

  it should "return Query with city for string with city, e.g. \"Odessa\"" in new ParserFixture {
    val query = parser.parse("Odessa")

    assert(CarPriceQuery((None, None), None, Some("odessa")) === query)
  }

  it should "return Query with city and year for string with city and year, e.g. \"Odessa 2012\"" in new ParserFixture {
    val query = parser.parse("Odessa 2012")

    assert(CarPriceQuery((None, None), Some(2012), Some("odessa")) === query)
  }

  it should "return Query with brand for string with brand, e.g. \"Audi\"" in new ParserFixture {
    val query = parser.parse("Audi")

    assert(CarPriceQuery((Some("audi"), None), None, None) === query)
  }

  it should "return Query with brand and model for string with brand and model, e.g. \"Audi A4\"" in new ParserFixture {
    val query = parser.parse("Audi A4")

    assert(CarPriceQuery((Some("audi"), Some("a4")), None, None) === query)
  }

  it should "return Query with brand, model, year and city for string with brand, model, year and city, e.g. \"Audi A4 2012 Odessa\"" in new ParserFixture {
    val query = parser.parse("Audi A4 2012 Odessa")

    assert(CarPriceQuery((Some("audi"), Some("a4")), Some(2012), Some("odessa")) === query)
  }
}
