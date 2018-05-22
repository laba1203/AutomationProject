package api;

import api.objects.Site;
import api.objects.advocateOffers.AdvocateOffers;
import api.objects.origin.Origin;
import api.objects.shares.channel.email.SharesEmail;
import api.objects.shares.channel.social.SharesSocial;
import api.scenarios.ViaAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import util.TestDataGenerator;

import java.util.ArrayList;

import static io.restassured.http.ContentType.JSON;

public class API_Test {
    private static final String myUserAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
    private Site site = new Site().setData(
            /*void data*/
            "test2003",
            "mtmTMlAPrzJ6XdEAj1c"

            /*prod data*/
//            "test2203",
//            "Th09JOgxeDycS1M2zBBl"
    );


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

        //emails test data:
        ArrayList <String>emails = new ArrayList<>();
        emails.add("maxim.laba+api.test1@talkable.com");
        emails.add("maxim.laba+api.test2@talkable.com");
        emails.add("maxim.laba+api.test3@talkable.com");
        emails.add("maxim.laba+api.test4@talkable.com");
        emails.add("maxim.laba+api.test5@talkable.com");
        emails.add("maxim.laba+api.test5@talkable.com");
        emails.add("maxim.laba+api.test6@talkable.com");
        emails.add("maxim.laba+api.test7@talkable.com");
        emails.add("maxim.laba+api.test8@talkable.com");
        emails.add("maxim.laba+api.test9@talkable.com");
        emails.add("maxim.laba+api.test10@talkable.com");
        emails.add("maxim.laba+api.test11@talkable.com");
        emails.add("maxim.laba+api.test12@talkable.com");
        //

//        resp = new Shares().postEmailShare(site, shortUrlCode, emails);
        resp = new SharesEmail().postEmailShare(site, shortUrlCode, emails);
        new SharesSocial().postFacebookShare(site, shortUrlCode);
//        String shareUrl = resp
//                .then()
//                    .contentType(JSON)
//                .extract().path("result.share.short_url");
//
//        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("User-Agent", myUserAgent).build();
//        resp = RestAssured.get(shareUrl);
//
//
//        String uuid = resp.getCookie("uuid");
//
//
//        System.out.println("LOG: UUID = " + uuid);
//
//        resp = new Origin().postOriginPurchaseWithUUID(site, "friend+new5@t.com" , uuid);


    }

    @Test
    public void temp(){
        String url = "https://void.talkable.com/api/v2/offers/t547bV/shares/email";

        UtilAPI.setBearerAuthorisationHeader(site.getApiKey());
        Response response = RestAssured.given()
                .body("{\"channel\":\"email\"," +
                        "\"recipients\":\"maxim.laba+test11@talkable.com, maxim.laba+12@talkable.com\"," +
//                        "\"email\":{" +
                        "\"subject\":\"AutomationTest_EmailShare_ViaAPI\"," +
                        "\"body\":\"Email body test\"," +
                        "\"reminder\":\"test\"" +
//                            "}" +
                        "}"
                )
                .contentType("application/json")
                .when()
                .post(url)
                ;

        System.out.println(response.statusLine());
        response.body().print();

    }

    @Test
    public void test3(){
        ViaAPI.createReferral(
                site,
                "ad" + TestDataGenerator.getRandomId() + "@gmail.com",
                "friend" + TestDataGenerator.getRandomId() + "@gmail.com");
    }


    @Test
    public void test(){
        ViaAPI.createReferral(site, "ad2@t.com", "fr3@t.com");
    }


}
