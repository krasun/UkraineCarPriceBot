package analyzer

import org.scalatest._
import org.scalamock.scalatest.MockFactory

class AnalyzerSpec extends FlatSpec with MockFactory {
//  trait AnalyzeFixture {
//    val queryParser = stub[CarPriceQueryParser]
//    val carPriceAnalyzer = stub[CarPriceAnalyzer]
//
//    val analyzer = new Analyzer(queryParser, carPriceAnalyzer)
//  }
//
//  "An Analyzer" should "use query parser can car price analyze for car price analysis" in new AnalyzeFixture {
//    (queryParser parse _).when("BMW").returns(CarPriceQuery((Some("BMW"), None), None, None))
//    (carPriceAnalyzer analyze _).when(CarPriceQuery((Some("BMW"), None), None, None)).returns(42)
//
//    val actualPrice = analyzer.analyzeCarPrice("BMW")
//
//    assert(42 === actualPrice)
//  }
}
