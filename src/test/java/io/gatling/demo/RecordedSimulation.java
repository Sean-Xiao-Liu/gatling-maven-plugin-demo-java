package io.gatling.demo;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://www.videogamedb.uk")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,zh-TW;q=0.6")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("sec-ch-ua", "Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "macOS")
  );
  
  private Map<CharSequence, String> headers_2 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://www.videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("sec-ch-ua", "Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "macOS")
  );
  
  private Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://www.videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMDM2NTgxOSwiZXhwIjoxNzIwMzY5NDE5fQ.lf9tR_uFRV_pdg7eoeK5maJ-fFpW8PvhwyotlAFhT1E"),
    Map.entry("sec-ch-ua", "Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "macOS")
  );


  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get("/api/videogame")
        .headers(headers_0),
      pause(27),
      http("request_1")
        .get("/api/videogame/10")
        .headers(headers_0),
      pause(10),
      http("request_2")
        .post("/api/authenticate")
        .headers(headers_2)
        .body(RawFileBody("io/gatling/demo/recordedsimulation/0002_request.json")),
      pause(6),
      http("request_3")
        .post("/api/authenticate")
        .headers(headers_2)
        .body(RawFileBody("io/gatling/demo/recordedsimulation/0003_request.json")),
      pause(50),
      http("request_4")
        .post("/api/videogame")
        .headers(headers_4)
        .body(RawFileBody("io/gatling/demo/recordedsimulation/0004_request.json"))
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
