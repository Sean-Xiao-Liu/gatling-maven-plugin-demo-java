package scriptfundamentals;

import io.gatling.javaapi.core.Simulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UseChainBuilder extends Simulation {
    private HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("https://www.videogamedb.uk/api")
            .acceptHeader("application/json");
    //
    private ScenarioBuilder scenarioBuilder = scenario("Use ChainBuilders")
            .exec(ChainBuilders.allGames);

    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocolBuilder);
    }
}


