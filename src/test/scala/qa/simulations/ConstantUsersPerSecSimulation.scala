package test.scala.qa.simulations

import io.gatling.core.Predef._
import test.scala.qa.scenarios.INFLUX.selectQueryScenario

class ConstantUsersPerSecSimulation extends Simulation {
  if (System.getProperty("random", "false").toBoolean) {
    setUp(selectQueryScenario.inject(constantUsersPerSec(Integer.getInteger("users", 2).toDouble)
          .during(java.lang.Long.getLong("duration", 10L).minutes).randomized))
      .assertions(
        details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
        details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
          .toDouble))
  } else {
    setUp(selectQueryScenario.inject(constantUsersPerSec(Integer.getInteger("users", 2).toDouble)
          .during(java.lang.Long.getLong("duration", 5L).minutes)))
      .assertions(
        details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
        details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
          .toDouble))
  }
}