package utils;

public class PayloadUtil {

    public static String getPetPayload(int id, String petName, String petStatus) {
        String petPayload = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 123,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + petStatus + "\"\n" +
                "}";

        return petPayload;
    }

    public static String getSlackMessagePayload(String message) {
        String payload = "{\n" +
                "    \"channel\": \"C0397J4JY3T\",\n" +
                "    \"text\": \"Temirlan: " + message + "\"\n" +
                "}";

        return payload;
    }


}
