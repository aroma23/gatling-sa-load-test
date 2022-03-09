package test.scala.qa.requests

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import test.scala.qa.config.Config.influxToken
import test.scala.qa.config.Config.influxQueryUrl

object GetQueryRequest {

  private val auth0Headers = Map("Authorization" -> ("Basic " + influxToken))

  val GetQuery: ChainBuilder = exec(http("Get Query")
    .get(influxQueryUrl)
    .headers(auth0Headers)
    .check(status.is(200))
    .check(jsonPath("$.results[0].series").exists))
}