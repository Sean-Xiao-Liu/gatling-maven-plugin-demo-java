package scriptfundamentals;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class SaveVariable extends Simulation {

    private HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("https://www.videogamedb.uk/api")
            .acceptHeader("application/json");

    private ScenarioBuilder scenarioBuilder = scenario("Save variable from response")
            .exec(
                    http("get 1st game")
                            .get("/videogame")
                            .check(status().is(200))
                            .check(jmesPath("[1].id").saveAs("gameId")))

            .pause(2)
            .exec(
                    http("get game id #{gameId}")
                            .get("/videogame/#{gameId}")
                            .check(status().is(200))
                            .check(jmesPath("id").is("2")))
            .exec(
                    session -> {
                        System.out.println("Printing Sessions:");
                        System.out.println(session);
                        System.out.println("gameId is set to: " + session.getString("gameId"));
                        return session;
                    });

    // 3. Load Simulation
    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocolBuilder);
    }
}
