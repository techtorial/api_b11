package post;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pojo.SlackMessagePojo;
import utils.PayloadUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Slack {

    public static final String APPLICATION_JSON = "application/json";
    private static final String TOKEN = "UPDATE_TOKEN";

    @Before  //hook
    public void setup() {
        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";
    }

    @Test
    public void slackMessageTest() {
        RestAssured.given()
                .accept(APPLICATION_JSON)  //alternative for .header("Accept", "application/json")
                .contentType(APPLICATION_JSON) //alternative for .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(PayloadUtil.getSlackMessagePayload("hello channel from Java"))
                .when().post()
                .then().statusCode(200)
                .and()
                .body("ok", Matchers.is(true));

    }


    @Test
    public void sendMessageTest() {

        Map<String, String> slackMessageMap = new HashMap<>();
        slackMessageMap.put("channel", "C0397J4JY3T");
        slackMessageMap.put("text", "Temirlan: Hello from java(diff serializatoin");

        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2(TOKEN)
                .body(slackMessageMap)
                .when().post()
                .then().statusCode(200)
                .and()
                .body("ok", Matchers.equalTo(true));

    }

    @Test
    public void sendMessageTest2() {

        File slackMessageFile = new File("src/test/resources/SlackMessage.json");

        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2(TOKEN)
                .body(slackMessageFile)
                .when().post()
                .then().statusCode(200)
                .and().body("ok", Matchers.is(true));

    }


    @Test
    public void sendMessageWithPojoTest(){

        SlackMessagePojo slackMessage = new SlackMessagePojo();

        slackMessage.setChannel("C0397J4JY3T");
        slackMessage.setText("Temirlan: Hello from java Pojo");


        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2("xoxb-2694972852931-3301004561938-aacdTvY8jFOXXe6YvdGZ7efn")
                .body(slackMessage)
                .when().post()
                .then().statusCode(200)
                .body("ok", Matchers.is(true));







    }


}
