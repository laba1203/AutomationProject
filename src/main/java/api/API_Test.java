package api;

import api.objects.Site;
import api.objects.advocateOffers.AdvocateOffers;
import api.objects.origin.Origin;
import api.objects.rewards.Rewards;
import api.objects.shares.channel.email.SharesEmail;
import api.objects.shares.channel.social.SharesSocial;
import api.scenarios.ViaAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.TestDataGenerator;

import java.util.ArrayList;

import static io.restassured.http.ContentType.JSON;

public class API_Test {
    private static final String myUserAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
    private Site site = new Site().setData(
            /*void data*/
            "testmax-shard2",
            "V4wBaKBrI8kywuaEG3b"

            /*prod data*/
//            "test2203",
//            "Th09JOgxeDycS1M2zBBl"
    );


    @Test
    public void test(){
        new Rewards().getRewards(
                site,
                "67830af9-1d3f-4481-920b-5aa960aece80",
                "All");
    }

}
