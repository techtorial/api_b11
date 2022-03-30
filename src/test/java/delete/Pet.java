package delete;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import utils.PayloadUtil;

public class Pet {

    int id = 87483938;
    String name = "hatiko";
    String status = "waiting";

    @Test
    public void deletePetTest(){
        // DELETE: https://petstore.swagger.io/v2/pet/{petId}
        RestAssured.given()
                .accept("application/json")
                .when().delete(String.valueOf(id))
                .then().statusCode(200);
    }

    @Before //hook
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(PayloadUtil.getPetPayload(id, name, status))
                .when().post()
                .then().statusCode(200);

        // DELETE: https://petstore.swagger.io/v2/pet/134847839072
        //POST: https://petstore.swagger.io/v2/pet


    }






}
