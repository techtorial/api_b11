package put;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PetPojo;
import post.Slack;

import java.util.Map;

import static post.Slack.APPLICATION_JSON;

public class Pet {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
    }

    @Test
    public void updatePetTest() {
        PetPojo pet = new PetPojo();
        pet.setName("pet from java");
        pet.setId(3738192);
        pet.setStatus("funny");


        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(pet)
                .when().put()
                .then().statusCode(200).extract().response();


        Map<String, Object> deserializedResp = response.as(new TypeRef<Map<String, Object>>() {
        });

        String name = String.valueOf(deserializedResp.get("name"));
        Assert.assertEquals("pet from java", name);
        int id = (int) deserializedResp.get("id");
        Assert.assertEquals(3738192, id);

    }


}
