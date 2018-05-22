package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.Arrays;

public class UtilAPI {

    public static void setBearerAuthorisationHeader(String apiKey){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + apiKey).build();
    }

    public static void logResponse(Response resp){
        System.out.println("LOG: API Response:");
        System.out.println(resp.getStatusLine());
        System.out.println("Headers:");
        System.out.print(Arrays.toString(resp.getHeaders().asList().toArray()));
        System.out.println("Body:");
        resp.body().prettyPrint();
        System.out.println("  *******  ");
    }
}
