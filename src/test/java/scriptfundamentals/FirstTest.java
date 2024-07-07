package scriptfundamentals;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * Gatling step def should contains 3 sections:
 * 1. HTTP Configuration
 * 2. Scenario Definition
 * 3. Load Simulation
 */
public class FirstTest extends Simulation{

    // 1. HTTP Configuration
    private HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("https://www.videogamedb.uk/api")
            .acceptHeader("application/json");

    // 2. Scenario Definition
    private ScenarioBuilder scenarioBuilder = scenario("First Test")
            .exec( // chain of calls to make
                    http("Get all games - 1st call") // this the name of this request, can be anything
                            .get("/videogame"), // this the request based on baseUrl, change the method to post(), put() etc as needed
                    pause(2), // pause for 2 sec
                    http("Get specific game")
                            .get("/videogame/1"),
                    pause(1,5), // randomly pause from 1 to 5 sec
                    http("Get specific game again")
                            .get("/videogame/2")

            )
            .exec( // chain of calls to make
                    http("Get all games - 1st call") // this the name of this request, can be anything
                            .get("/videogame"), // this the request based on baseUrl, change the method to post(), put() etc as needed
                    pause(2), // pause for 2 sec
                    http("Get specific game")
                            .get("/videogame/1"),
                    pause(1,5), // randomly pause from 1 to 5 sec
                    http("Get specific game again")
                            .get("/videogame/2")
            );

    // 3. Load Simulation
    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocolBuilder);
    }

}
