package test.scala.qa.simulations

import io.gatling.core.Predef._
import test.scala.qa.scenarios.INFLUX.selectQueryScenario

class AtOnceUsersSimulation extends Simulation {
  setUp(
    selectQueryScenario.inject(atOnceUsers(Integer.getInteger("users", 2))))
    .assertions(
      details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
      details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
        .toDouble))
}