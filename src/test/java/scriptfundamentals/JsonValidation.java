package scriptfundamentals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.io.InputStream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * Gatling step def should contains 3 sections:
 * 1. HTTP Configuration
 * 2. Scenario Definition
 * 3. Load Simulation
 */
public class JsonValidation extends Simulation {

    private JsonNode expectedJson;
    ObjectMapper mapper;

    public JsonValidation() {
        this.mapper = new ObjectMapper();
        try {
            expectedJson = readResourceFileAsString("Resident_Evil_4.json");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

    private JsonNode readResourceFileAsString(String fileName) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        return mapper.readTree(inputStream);
    }

    // 1. HTTP Configuration
    private HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl("https://www.videogamedb.uk/api")
            .acceptHeader("application/json");

    // 2. Scenario Definition
    private ScenarioBuilder scenarioBuilder = scenario("First Test")
            .exec( // chain of calls to make
                    http("Get all games - 1st call") // this the name of this request, can be anything
                            .get("/videogame") // this the request based on baseUrl, change the method to post(), put() etc as needed
                            .check(status().is(200)), // verify the status code is 200
                    pause(2), // pause for 2 sec
                    http("Get specific game")
                            .get("/videogame/1")
                            .check(bodyString().transform(response -> {
                                        try {
                                            JsonNode actualJson = mapper.readTree(response);
                                            return actualJson.equals(expectedJson);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            return false;  // Handle parse errors or transformation issues
                                        }
                                    })
                                    .is(true)),
                    pause(1, 5), // randomly pause from 1 to 5 sec
                    http("Get specific game again")
                            .get("/videogame/2")
                            .check(status().not(400))

            );

    // 3. Load Simulation
    {
        setUp(
                scenarioBuilder.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocolBuilder);
    }

}
