package api;

import api.objects.Site;
import api.objects.advocateOffers.AdvocateOffers;
import api.objects.origin.Origin;
import api.objects.shares.Shares;
import api.scenarios.ViaAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;

public class API_Test {
    private static final String myUserAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
    private Site site = new Site().setData("testmax-shard2", "hCrTR9VmPolibfPHWCvO");

    @Test
    public void test1(){
        String advocateEmail = "advocate001@t.com";
        String advocateUUID = "b1447r36-4e7f-46bc-92b3-57344357cd6a";
        Response resp = new Origin().postOriginPurchaseWithUUID(site, advocateEmail, advocateUUID);
        int offerId = resp
                .then()
                    .contentType(JSON)
                .extract()
                    .path("result.offer.id");
        System.out.println("\r\nID: <" + offerId + ">\n");

        resp = new AdvocateOffers().getAdvocateOffer(site, offerId);

        String shortUrlCode = resp
                .then()
                    .contentType(JSON)
                .extract().path("result.offer.short_url_code");

        resp = new Shares().postFacebookShare(site, shortUrlCode);

        String shareUrl = resp
                .then()
                    .contentType(JSON)
                .extract().path("result.share.short_url");

        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("User-Agent", myUserAgent).build();
        resp = RestAssured.get(shareUrl);


        String uuid = resp.getCookie("uuid");


        System.out.println("LOG: UUID = " + uuid);

        resp = new Origin().postOriginPurchaseWithUUID(site, "friend+new5@t.com" , uuid);


    }

    @Test
    public void test(){
        ViaAPI.createReferral(site, "ad2@t.com", "fr3@t.com");
    }
}
