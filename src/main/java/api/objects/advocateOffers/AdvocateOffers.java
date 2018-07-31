package api.objects.advocateOffers;

import api.UtilAPI;
import api.objects.Site;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import util.EnvFactory;
import util.logging.Log;

public class AdvocateOffers {
    private static final String URL = EnvFactory.getApiUrl() + "/offers/";


    public Response getAdvocateOffer(Site site, int offerId){
        String url = URL + offerId + "?site_slug=" + site.getSiteSlug() + "&api_key=" + site.getApiKey();
        Log.logRecord("URL for GET: " + url);

        Response resp = RestAssured.get(url);
        System.out.println("LOG: Response for GET </offers/"+offerId+"> (Advocate Offers) :");
        UtilAPI.logResponse(resp);
        Assert.assertEquals(UtilAPI.isResultOk(resp), true, "Response result is not ok.");

        return resp;
    }
}
