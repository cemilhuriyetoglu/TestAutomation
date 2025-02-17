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
public class UpdatePet extends BaseTest {

    String jsonPet = ".//src/test/resources/pet.json";

    @Test
    public void T01_UpdatePet_Successful_200() throws IOException, ParseException {

        logger.info("Add new pet to the store successful");

        Object jsonObj = new JSONParser().parse(new FileReader(jsonPet));
        String oldPetID = "petID";
        String newPetID = String.valueOf(Helper.numberGenerator(100,10000));
        jsonObj = jsonObj.toString().replace(oldPetID, newPetID);
        String jsonStrPost = jsonObj.toString();
        System.out.println(jsonStrPost);

        given().contentType(ContentType.JSON)
                .body(jsonStrPost)
                .when()
                .post(ConfigurationReader.get("baseURI") + ConfigurationReader.get("POST_ENDPOINT"))
                .then()
                .statusCode(200)
                .assertThat().body("status", equalTo("available"))
                .log().all();

        logger.info("Update existing pet successful");


        String oldName = "Tom";
        String newName = "Paskal";
        jsonObj = jsonObj.toString().replace(oldName, newName);
        String jsonStrPut = jsonObj.toString();


        given().contentType(ContentType.JSON)
                .body(jsonStrPut)
                .when()
                .put(ConfigurationReader.get("baseURI") + ConfigurationReader.get("PUT_ENDPOINT"))
                .then()
                .statusCode(200)
                .assertThat().body("status", equalTo("available"))
                .assertThat().body("category.name", equalTo("golden"))
                .assertThat().body("name", equalTo("Paskal"))
                .log().all();

    }

}
