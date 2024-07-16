package scriptfundamentals;


import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Authentication extends Simulation {

    private HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("https://www.videogamedb.uk/api")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");
    //
    private ScenarioBuilder scenarioBuilder = scenario("save a game")
            .exec(ChainBuilders.getAuthToken) // call authentication first where the token is save in jwtToken variable
            .pause(2)
            .exec(http("send post request to save game")
                    .post("/videogame")
                    .header("Authorization", "Bearer #{jwtToken}") // add Authorization as header
                    .body(StringBody("{\n" +
                            "  \"category\": \"Platform\",\n" +
                            "  \"name\": \"Mario\",\n" +
                            "  \"rating\": \"Mature\",\n" +
                            "  \"releaseDate\": \"2012-05-04\",\n" +
                            "  \"reviewScore\": 86\n" +
                            "}")));


    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocolBuilder);
    }
}
