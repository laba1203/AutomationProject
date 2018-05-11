package api.objects.shares;

import api.UtilAPI;
import api.objects.Site;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.EnvFactory;

import java.util.ArrayList;

/*
End point will be unavailable after release of https://talkable.atlassian.net/browse/PR-8973.
It is separated as per channels: social, email
//TODO: remove class usage after release
* */


@Deprecated
public class Shares {

    private static final String URL = EnvFactory.getApiUrl() + "/offers";

    public Response postFacebookShare(Site site, String shortUrlCode){
        String url = getSharesURL(shortUrlCode);

        Response response = RestAssured
                .given()
                    .contentType("application/json")
                    .body("{\"api_key\":\"" + site.getApiKey() +"\"," +
                          " \"site_slug\":\"" + site.getSiteSlug() +"\"," +
                          "\"channel\":\"facebook\"}"
                    )
                .when()
                    .post(url);

        System.out.println("LOG: Response for POST /offers/<short_url_code>/shares :");
        UtilAPI.logResponse(response);
//        response.body().print();
        return response;
    }

    public Response postEmailShare(Site site, String shortUrlCode, ArrayList<String> recipients){
        System.out.println("DEBAG: URL " + getSharesURL(shortUrlCode));
        String emails = arrayToString(recipients);

        String requestBody =
                "{\"site_slug\":\"" + site.getSiteSlug() + "\"," +
                "\"channel\":\"email\"," +
                "\"recipients\":\""+ emails +"\"," +
                "\"email\":{\"subject\":\"AutomationTest_EmailShare_ViaAPI\"}}"
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



    private String getSharesURL(String shortUrlCode){
        return URL + "/" + shortUrlCode + "/shares";
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


}
