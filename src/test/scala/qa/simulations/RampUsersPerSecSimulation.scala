package test.scala.qa.simulations

import test.scala.qa.scenarios.INFLUX.selectQueryScenario
import io.gatling.core.Predef._


class RampUsersPerSecSimulation extends Simulation {
  if (System.getProperty("random", "false").toBoolean) {
    setUp(selectQueryScenario.inject(rampUsersPerSec(Integer.getInteger("users", 1).toDouble)
          .to(Integer.getInteger("usersTarget", 3).toDouble)
          .during(java.lang.Long.getLong("duration", 1L).minutes).randomized))
      .assertions(
        details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
        details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
          .toDouble))
  } else {
    setUp(selectQueryScenario.inject(rampUsersPerSec(Integer.getInteger("users", 1).toDouble)
          .to(Integer.getInteger("usersTarget", 3).toDouble)
          .during(java.lang.Long.getLong("duration", 1L).minutes)))
      .assertions(
        details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
        details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
          .toDouble))
  }
}