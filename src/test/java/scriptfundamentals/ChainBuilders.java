package scriptfundamentals;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class ChainBuilders {
    public static ChainBuilder allGames =
            exec(http("get 1st game")
                    .get("/videogame"));

    public static ChainBuilder allGamesThreeTimes =
            repeat(3).on( // using repeat to loop http call
                    exec(http("get 1st game")
                            .get("/videogame/1"))
            );

    public static ChainBuilder getAuthToken = exec(http("get authentication token")
            .post("/authenticate")
            .body(StringBody("" +
                    "{\n" +
                    "  \"password\": \"admin\",\n" +
                    "  \"username\": \"admin\"\n" +
                    "}"))
            .check(jmesPath("token").saveAs("jwtToken"))); // save token in response as variable
}
