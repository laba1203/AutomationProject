package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import util.logging.Log;

import java.util.Arrays;

import static io.restassured.http.ContentType.JSON;

public class UtilAPI {

    public static void setBearerAuthorisationHeader(String apiKey){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + apiKey).build();
    }

    public static void logResponse(Response resp){
        System.out.println("LOG: API Response:");
        System.out.println(resp.getStatusLine());
        System.out.print("Headers:");
        System.out.println(Arrays.toString(resp.getHeaders().asList().toArray()));
        System.out.println("Body:");
        resp.body().prettyPrint();
        System.out.println("  * * * * End response  * * *  ");
    }

    public static boolean isResultOk(Response response){
        boolean result = response.then()
                .contentType(JSON)
                .extract()
                .path("ok");
        Log.logRecord("Is response successful({\"ok\"}): " + result);
        return result;
    }


}
