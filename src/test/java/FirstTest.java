
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.camapignPlacements.PlacementRowElement;
import util.DriverConfig;

import java.util.ArrayList;


public class FirstTest {

    WebDriver driver;

    CommonScenarios commonScenarios = new CommonScenarios();

    private String siteName = "automationSite";

    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://void.talkable.com");
    }

    @Test
    public void test1_login(){
        commonScenarios.login("maxim.laba@talkable.com", "Password@1");
        driver.navigate().to("https://admin.void.talkable.com/sites/email-test/placements");
    }

    @Test
    public void test2(){
        PageCampaignPlacements campaignPlacements = new PageCampaignPlacements();
        ArrayList<PlacementRowElement> shownOn = campaignPlacements.standaloneSection.getShownOnList();
        for (PlacementRowElement el :
                shownOn) {
            System.out.println(el.getText());
        }
        System.out.println("***************");

        ArrayList<PlacementRowElement> hiddenOn = campaignPlacements.standaloneSection.getHiddenOnList();
        for (PlacementRowElement el :
                hiddenOn) {
            System.out.println(el.getText());
        }
        campaignPlacements = campaignPlacements.standaloneSection.addInclusion(false, "autotest.html");
        campaignPlacements.floatingWidgetSection.addExclusion(true, "/test/autotest.html");

    }



}
