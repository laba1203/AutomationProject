package api.objects.shares.channel.social;


import api.UtilAPI;
import api.objects.Site;
import api.objects.shares.AbstractShares;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class SharesSocial extends AbstractShares{

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

        System.out.println("LOG: Response for POST "+url+" :");
        UtilAPI.logResponse(response);
        return response;
    }


    private String getSharesURL(String shortUrlCode){
        return getOffersUrl() + "/" + shortUrlCode + "/shares/social";
    }


}
