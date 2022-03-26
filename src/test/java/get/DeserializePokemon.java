package get;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class DeserializePokemon {
    // For windows -> control + n
    // for mac -> command + n
    @Test
    public void pokemonGet() {
     Response response= given().
                when().get("https://pokeapi.co/api/v2/pokemon").
                then().extract().response();

        Assert.assertTrue
                ("Status code is not successful",response.statusCode()==201);
        Map<String,Object> pokemonResponse =
                response.as(new TypeRef<Map<String, Object>>() {
                });
        try {


Assert.assertTrue(pokemonResponse.containsKey("next"));}
        catch (AssertionError e){
            e.printStackTrace();
        }
     //   System.out.println(pokemonResponse);
        List<Map<String,Object>> results =
                (List<Map<String, Object>>) pokemonResponse.get("results");
       boolean isThereBulbasaur =  false;
        for (Map<String,Object> info:results) {
          if (info.get("name").toString().equals("bulbasaur")){
              isThereBulbasaur=true;
              break;
          }

        }
       Assert.assertTrue("There is no bulbasaur",isThereBulbasaur);

    }
}
