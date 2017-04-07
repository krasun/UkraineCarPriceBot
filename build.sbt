name := "UkraineCarPriceBot"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("snapshots")

// for Telegram
libraryDependencies += "info.mukel" %% "telegrambot4s" % "2.1.0-SNAPSHOT"
// for HTTP requests to API
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
// for parsing/generating JSON to/from HTTP requests/responses
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.5.0"
// for caching HTTP responses
libraryDependencies += "com.github.cb372" %% "scalacache-memcached" % "0.9.3"
// for testing purposes
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test"
// for mocking in tests
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.4.2" % Test
