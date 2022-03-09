package test.scala.qa.scenarios

import test.scala.qa.requests.GetQueryRequest
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object INFLUX {

  val selectQueryScenario: ScenarioBuilder = scenario("Get Query")
    .exec(GetQueryRequest.GetQuery)
}