package test.scala.qa.simulations

import test.scala.qa.scenarios.INFLUX.selectQueryScenario
import io.gatling.core.Predef._

class RampUsersSimulation extends Simulation {
  setUp(
    selectQueryScenario.inject(rampUsers(Integer.getInteger("users", 1))
        .during(java.lang.Long.getLong("duration", 1L).minutes)))
    .assertions(
      details("Get Query").responseTime.max.lte(Integer.getInteger("maxRespTime", 5000)),
      details("Get Query").responseTime.percentile1.lte(Integer.getInteger("percentile1Time", 5000)),
      details("Get Query").responseTime.percentile2.lte(Integer.getInteger("percentile2Time", 5000)),
      details("Get Query").responseTime.percentile3.lte(Integer.getInteger("percentile3Time", 5000)),
      details("Get Query").responseTime.percentile4.lte(Integer.getInteger("percentile4Time", 5000)),
      details("Get Query").successfulRequests.percent.gte(Integer.getInteger("successRate", 100)
        .toDouble))
}