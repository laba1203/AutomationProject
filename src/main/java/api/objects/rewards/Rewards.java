package api.objects.rewards;

import api.UtilAPI;
import api.objects.Site;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.EnvFactory;
import util.logging.Log;

public class Rewards {
    private static final String RESOURCE_URL = EnvFactory.getApiUrl() + "/rewards";

    public Response getPaidRewards(Site site, String visitorUuid){
        String url = RESOURCE_URL +
                "?site_slug=" + site.getSiteSlug() +
                "&visitor_uuid=" + visitorUuid;

        return getRequestToRewards(site, url);
    }


    /** Possible statuses: Paid, Unpaid, Voided, All
     * */
    public Response getRewards(Site site, String visitorUuid, String status){
        String url = RESOURCE_URL +
                "?site_slug=" + site.getSiteSlug() +
                "&visitor_uuid=" + visitorUuid +
                "&status=" + status
                ;
        return getRequestToRewards(site, url);
    }

    private Response getRequestToRewards(Site site, String url){
        UtilAPI.setBearerAuthorisationHeader(site.getApiKey());
        Log.logRecord("GET /rewards for site <" + site.getSiteSlug() + ">. URL: " + url);
        Response resp = RestAssured.get(url);
        Log.logRecord("Response status line for GET: " + resp.getStatusLine());
        Log.logRecord("Response body for GET: ");
        resp.body().prettyPrint();
        return resp;
    }
}
