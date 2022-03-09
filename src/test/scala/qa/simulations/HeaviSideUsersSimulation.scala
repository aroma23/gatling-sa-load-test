package test.scala.qa.simulations

import io.gatling.core.Predef._
import test.scala.qa.scenarios.INFLUX.selectQueryScenario


class HeaviSideUsersSimulation extends Simulation {
  setUp(
    selectQueryScenario.inject(heavisideUsers(Integer.getInteger("users", 10))
      .during(java.lang.Long.getLong("duration", 20L).seconds)),
  ).assertions(details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
    details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
      .toDouble))
}