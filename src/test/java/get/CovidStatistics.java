package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CovidStatistics {


    // GET https://corona.lmao.ninja/v2/all
    // Validate status code = 200
    // Deserialize the response
    // Validate affectedCountries = 227

    @Test
    public void allCountriesTest() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get("https://corona.lmao.ninja/v2/all")
                .then()
                .statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        int affectedCountries = (int) deserializedResponse.get("affectedCountries");
        Assert.assertEquals(227, affectedCountries);

    }


}
