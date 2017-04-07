package analyzer

import analyzer.autoria.Client

/**
  * Car Price analyzer based on AUTO.RIA API.
  *
  * @param client
  */
class CarPriceAnalyzer(client: Client, queryTranslator: QueryTranslator) {

  /**
    * Analyzes car price using AUTO.RIA client.
    *
    * @param query
    *
    * @return
    */
  def analyze(query: CarPriceQuery): Double = client.averagePrice(queryTranslator.translate(query)).get.interQuartileMean
}
