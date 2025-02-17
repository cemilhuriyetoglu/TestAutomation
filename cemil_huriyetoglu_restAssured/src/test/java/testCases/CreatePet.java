package testCases;

import base.BaseTest;
import base.ConfigurationReader;
import base.Helper;
import io.restassured.http.ContentType;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners({base.Listeners.class})
public class CreatePet extends BaseTest {

    String jsonPet = ".//src/test/resources/pet.json";

    @Test
    public void T01_CreatePet_Successful_200() throws IOException, ParseException {

        logger.info("Add new pet to the store successful");

        Object jsonObj = new JSONParser().parse(new FileReader(jsonPet));
        String oldPetID = "petID";
        String newPetID = String.valueOf(Helper.numberGenerator(100, 10000));
        jsonObj = jsonObj.toString().replace(oldPetID, newPetID);
        String jsonStr = jsonObj.toString();
        System.out.println(jsonStr);

        given().contentType(ContentType.JSON)
                .body(jsonStr)
                .when()
                .post(ConfigurationReader.get("baseURI") + ConfigurationReader.get("POST_ENDPOINT"))
                .then()
                .statusCode(200)
                .assertThat().body("status", equalTo("available"))
                .assertThat().body("category.name", equalTo("golden"))
                .assertThat().body("name", equalTo("Tom"))
                .log().all();
    }

    @Test
    public void T02_CreatePet_WithInvalidPetID_500() throws IOException, ParseException {

        logger.info("Add new pet to the store with invalid petId");

        Object jsonObj = new JSONParser().parse(new FileReader(jsonPet));
        String oldPetID = "petID";
        String newPetID = Helper.stringGenerator();
        jsonObj = jsonObj.toString().replace(oldPetID, newPetID);
        String jsonStr = jsonObj.toString();
        System.out.println(jsonStr);

        given().contentType(ContentType.JSON)
                .body(jsonStr)
                .when()
                .post(ConfigurationReader.get("baseURI") + ConfigurationReader.get("POST_ENDPOINT"))
                .then()
                .statusCode(500)
                .assertThat().body("type", equalTo("unknown"))
                .body("message", equalTo("something bad happened"))
                .log().all();
    }

    @Test
    public void T03_CreatePet_WithEmptyBody_405() throws IOException, ParseException {

        logger.info("Add new pet to the store with empty body");

        given().contentType(ContentType.JSON)
                .body("")
                .when()
                .post(ConfigurationReader.get("baseURI") + ConfigurationReader.get("POST_ENDPOINT"))
                .then()
                .statusCode(405)
                .assertThat().body("message", equalTo("no data"))
                .log().all();
    }

}
