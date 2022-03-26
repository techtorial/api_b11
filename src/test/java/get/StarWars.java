package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.StarWarsCharactersPojo;
import pojo.StarWarsPojo;

import java.util.List;
import java.util.Map;

public class StarWars {


    /**
     * GET https://swapi.dev/api/people
     * Validate we have more than 0 female character in the response
     * on page 1
     */

    @Test
    public void femaleCountTest() {
        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> listOfCharacters =
                (List<Map<String, Object>>) parsedResponse.get("results");

        int count = 0;
        for (int i = 0; i < listOfCharacters.size(); i++) {
            //getting every character
            Map<String, Object> charMap = listOfCharacters.get(i);
            //getting gender of every character
            if (charMap.get("gender").equals("female")) {
                ++count;
            }
        }

        Assert.assertTrue(count > 0);


    }

    @Test
    public void deserializeWithPojo() {

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();

        StarWarsPojo deserializedResp = response.as(StarWarsPojo.class);
        Assert.assertEquals(82, deserializedResp.getCount());


//        List<Map<String, Object>> results = deserializedResp.getResults();
//
//        for (Map<String, Object> map : results) {
//            System.out.println(map.get("name"));
//        }


        List<StarWarsCharactersPojo> results = deserializedResp.getResults();

        System.out.println(results.get(0).getName());

        for (StarWarsCharactersPojo character : results) {
            System.out.println(character.getName());
        }

    }


}
