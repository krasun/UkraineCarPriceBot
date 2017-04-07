package analyzer

/**
  * Query to analyze car price.
  *
  * Brand, models and cities are normalized (just in lowercase).
  */
case class CarPriceQuery(model: (Option[String], Option[String]), year: Option[Int], city: Option[String])
