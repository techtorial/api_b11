package post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtil;

public class Pet {


    String petName = "hatiko";
    int petId = 38247289;
    String petStatus = "waiting";

    @Test
    public void createPetTest() {


        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(PayloadUtil.getPetPayload(petId, petName, petStatus))
                .when().post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().response();


        PetPojo parsedResponse = response.as(PetPojo.class);

        Assert.assertEquals(petId, parsedResponse.getId());

        Assert.assertEquals(petName, parsedResponse.getName());

        Assert.assertEquals(petStatus, parsedResponse.getStatus());


        /**
         * Add GET https://petstore.swagger.io/v2/pet/{yourPetId}
         * Validate name, id, status are still same
         */


        /**
         * 1. POST https://petstore.swagger.io/v2/pet
         * 2. Validate the status code and response body(name, id, status)
         * 3. GET https://petstore.swagger.io/v2/pet/{yourPetId}
         * 4. Validate the status code and response body(name, id, status)
         */

        RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/pet/"+ petId)
                .then().statusCode(200)
                .and()
                .body("id", Matchers.is(petId))
                .and()
                .body("name", Matchers.equalTo(petName))
                .body("status", Matchers.equalTo(petStatus))
                .body("category.id", Matchers.is(123));

    }


}
