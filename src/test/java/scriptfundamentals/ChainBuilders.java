package scriptfundamentals;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class ChainBuilders {
    public static ChainBuilder allGames =
            exec(http("get 1st game")
                    .get("/videogame"));
}
