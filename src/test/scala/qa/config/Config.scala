package test.scala.qa.config

import java.nio.charset.StandardCharsets

object Config {
  val environment: String = System.getProperty("INFLUX_ENV", "dev")
  val influxUser: String = System.getProperty("INFLUX_USER", "mmdev")
  val influxPass: String = System.getProperty("INFLUX_PASS")
  val influxDB: String = System.getProperty("INFLUX_DB", "dummy")
  var influxToken: String = java.util.Base64.getEncoder.encodeToString((influxUser + ":" + influxPass)
    .getBytes(StandardCharsets.UTF_8))
  println("encoded==> " + influxToken)
  val influxHost: String = "http://influxdb-" + environment + ".com"
  val influxQueryUrl: String = influxHost + "/query?db=" + influxDB + "&q=" +
    System.getProperty("INFLUX_QUERY", "select * from table where time > now() - 1h limit 10")
      .replaceAll("\"","").replaceAll("#","'")
      .replaceAll("ALL", "")
  println("URL==> " + influxQueryUrl)
}