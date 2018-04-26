package api.objects.advocateOffers;

import api.objects.Site;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.EnvFactory;

public class AdvocateOffers {
    private static final String URL = EnvFactory.getApiUrl() + "/offers/";


    public Response getAdvocateOffer(Site site, int offerId){
        String url = URL + offerId + "?site_slug=" + site.getSiteSlug() + "&api_key=" + site.getApiKey();
        Response resp = RestAssured.get(url);
        System.out.println("LOG: Response for GET /offers/<id> (Advocate Offers) :");
        resp.body().print();
        return resp;
    }
}
