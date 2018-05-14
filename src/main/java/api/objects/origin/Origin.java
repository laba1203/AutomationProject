package api.objects.origin;

import api.objects.Site;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.EnvFactory;


public class Origin{

    private static final String URL = EnvFactory.getApiUrl() + "/origins";

    public Response postOriginPurchaseWithUUID(Site site, String email, String uuid){
        String postBody =
                "{\n" +
                        "  \"api_key\": \"" + site.getApiKey() + "\",\n" +
                        "  \"site_slug\": \"" + site.getSiteSlug() + "\",\n" +
                        "  \"type\": \"Purchase\",\n" +
                        "  \"data\": {\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"order_number\": " + String.valueOf(System.currentTimeMillis()).substring(7) + ",\n" +
                        "    \"subtotal\": 100,\n" +
                        "    \"uuid\": \"" + uuid + "\",\n" +
                        "    \"campaign_tags\": \"api\",\n" +
                        "    \"items\": [\n" +
                        "      {\n" +
                        "        \"price\": 25,\n" +
                        "        \"quantity\": 4,\n" +
                        "        \"product_id\": \"TSHIRT\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"
                ;
        System.out.println("DEBAG: " + postBody);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(postBody)
                .when()
                .post(URL);
        System.out.println("LOG: Response body for POST to /origin :");
        response.body().print();
        return response;
    }


    public Response postOriginPurchase(Site site){
        String defaultUUID = "b3967d36-4e7f-46bc-92b3-57344347cd6a";
        String defaultEmail = "api.email@test.com";
        return postOriginPurchaseWithUUID(site, defaultEmail, defaultUUID);
    }






}
