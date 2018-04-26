package api.objects.shares;

import api.objects.Site;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.EnvFactory;

public class Shares {

    private static final String URL = EnvFactory.getApiUrl() + "/offers";

    public Response postFacebookShare(Site site, String shortUrlCode){
        String url = URL + "/" + shortUrlCode + "/shares";
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
        response.body().print();
        return response;
    }
}
