package api.objects.shares.channel.email;

import api.UtilAPI;
import api.objects.Site;
import api.objects.shares.AbstractShares;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SharesEmail extends AbstractShares {


    public Response postEmailShare(Site site, String shortUrlCode, ArrayList<String> recipients){
        System.out.println("DEBAG: URL " + getSharesURL(shortUrlCode));
        String emails = arrayToString(recipients);

        String requestBody =
                "{\"channel\":\"email\"," +
                "\"recipients\":\"" + emails + "\"," +
                "\"subject\":\"AutomationTest_EmailShare_ViaAPI\"," +
                "\"body\":\"Email body test\"," +
                "\"reminder\":\"false\"" +
                "}"
                ;

        System.out.println("DEBAG: request body:");
        System.out.println(requestBody);

        UtilAPI.setBearerAuthorisationHeader(site.getApiKey());

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(getSharesURL(shortUrlCode));
        UtilAPI.logResponse(response);
        return response;
    }



    private String arrayToString(ArrayList<String> arr){
        StringBuilder out = new StringBuilder();
        for (String str :
                arr) {
            out.append(str).append(",");
        }
        //to remove the last coma ','
        int size = out.toString().toCharArray().length;
        return out.substring(0, size-1);
    }

    private String getSharesURL(String shortUrlCode){
        return getOffersUrl() + "/" + shortUrlCode + "/shares/email";
    }
}
