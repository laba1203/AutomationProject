package api.scenarios;

import api.objects.Site;
import api.objects.advocateOffers.AdvocateOffers;
import api.objects.origin.Origin;
import api.objects.shares.channel.email.SharesEmail;
import api.objects.shares.channel.social.SharesSocial;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.http.ContentType.JSON;

public class ViaAPI {
    private static final String myUserAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";

    public static void createReferral(Site site, String adEmail, String frEmail ){
        String advocateUUID = getRandomUUID();
        int offerID = getOfferIdFromOrigin(site, adEmail, advocateUUID);
        String shortUrlCode = getShortUrlCodeFromAdvocateOffer(site, offerID);
        String shortURL = getFacebookShareShortURL(site, shortUrlCode);
        String friendUUID = getUuidAfterVisitToShortURL(shortURL);
        new Origin().postOriginPurchaseWithUUID(site, frEmail , friendUUID);
    }

    public static void createReferral(Site site, String adEmail, String frEmail, String advocateUUID, String advocateIP, String friendIp ){
        int offerID = getOfferIdFromOrigin(site, adEmail, advocateUUID, advocateIP);
        String shortUrlCode = getShortUrlCodeFromAdvocateOffer(site, offerID);
        String shortURL = getFacebookShareShortURL(site, shortUrlCode);
        String friendUUID = getUuidAfterVisitToShortURL(shortURL);
        new Origin().postOriginPurchase(site, frEmail , friendUUID, friendIp);
    }

    private static int getOfferIdFromOrigin(Site site, String advocateEmail, String advocateUUID){
        Response resp = new Origin().postOriginPurchaseWithUUID(site, advocateEmail, advocateUUID);
        return getOfferIdFromResponse(resp);
    }

    private static int getOfferIdFromOrigin(Site site, String advocateEmail, String advocateUUID, String ipAddress){
        Response resp = new Origin().postOriginPurchase(site, advocateEmail, advocateUUID, ipAddress);
        return getOfferIdFromResponse(resp);
    }

    private static int getOfferIdFromResponse(Response originResponse){
        return originResponse
                .then()
                .contentType(JSON)
                .extract()
                .path("result.offer.id");
    }

    public static String getRandomUUID(){
        return "b3567d87-3e7f-44bc-92b6-" +
                String.valueOf(System.currentTimeMillis()).substring(5) +
                "cd6a";
    }

    private static String getShortUrlCodeFromAdvocateOffer(Site site, int offerId){
        Response resp = new AdvocateOffers().getAdvocateOffer(site, offerId);
        return resp
                .then()
                .contentType(JSON)
                .extract().path("result.offer.short_url_code");
    }

    private static String getFacebookShareShortURL(Site site, String shortUrlCode){
        Response resp = new SharesSocial().postFacebookShare(site, shortUrlCode);

        return resp
                .then()
                .contentType(JSON)
                .extract().path("result.share.short_url");
    }

    public static void makEmailShares(Site site, String shortUrlCode, ArrayList<String> recipients){
        Response resp = new SharesEmail().postEmailShare(site, shortUrlCode, recipients);

    }

    private static String getUuidAfterVisitToShortURL(String shareUrl){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("User-Agent", myUserAgent)
                .build();
        return RestAssured
                .get(shareUrl)
                .getCookie("uuid");
    }
}
