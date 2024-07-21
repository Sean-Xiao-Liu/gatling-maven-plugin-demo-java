package scriptfundamentals;


import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UseFeeders extends Simulation {
    private HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("https://www.videogamedb.uk/api")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    private static FeederBuilder.FileBased<String> feederBuilder = csv("games.csv").random(); // csv() method will look up file in resources, random() will feed record randomly

    // ScenarioBuilder with CSV feeder
    private ScenarioBuilder scenarioBuilder = scenario("use CSV feeder to get game")
            .feed(feederBuilder)
            .exec(
                   ChainBuilders.getSpecificGame
            );

    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocolBuilder);
    }
}
