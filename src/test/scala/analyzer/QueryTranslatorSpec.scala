package analyzer

import analyzer.autoria.models._
import org.scalatest._

class QueryTranslatorSpec extends FlatSpec {
  trait TranslatorFixture {
    val brands = List(CarBrand(42, "BMW", List(CarModel(2, "M3"), CarModel(3, "X5"))))
    val provinces = List(Province(1, "Kyivska", List(City(1, "Kyiv"), City(2, "Irpen"))))

    val translator = new QueryTranslator(brands, provinces)
  }

  "A Query Translator" should "map query strings to identifiers" in new TranslatorFixture {
    val query = translator.translate(CarPriceQuery((Some("bmw"), Some("m3")), Some(2005), Some("irpen")))

    assert(query === Query(Some(42), Some(2), Some(2005), Some(1), Some(2)))
  }
}
