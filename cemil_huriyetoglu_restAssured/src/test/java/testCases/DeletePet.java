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
public class DeletePet extends BaseTest {

    String jsonPet = ".//src/test/resources/pet.json";

    @Test
    public void T01_DeletePet_Successful_200() throws IOException, ParseException {

        logger.info("Add new pet to the store successful");

        int pet_id = Helper.numberGenerator(100, 10000);

        Object jsonObj = new JSONParser().parse(new FileReader(jsonPet));
        String oldPetID = "petID";
        String newPetID = String.valueOf(pet_id);
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

        logger.info("Delete existing pet");

        given().contentType(ContentType.JSON)
                .when()
                .delete(ConfigurationReader.get("baseURI") + ConfigurationReader.get("DELETE_ENDPOINT") + pet_id)
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void T02_DeletePet_WithInvalidPetId_404() throws IOException, ParseException {
        logger.info("Delete pet with invalid pet id");

        given().contentType(ContentType.JSON)
                .when()
                .delete(ConfigurationReader.get("baseURI") + ConfigurationReader.get("DELETE_ENDPOINT") + 00000)
                .then()
                .statusCode(404)
                .log().all();

    }

}
