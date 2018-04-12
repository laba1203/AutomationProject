
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import util.DriverConfig;
import util.Util;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.AdvocateDashboard;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.CONFIGURATION;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.IMAGES;


public class FirstTest {

    private WebDriver driver;

    private String siteName = "automationSite";

    @BeforeSuite
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://talkable.com");
    }

    @Test
    public void test1_login(){
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.navigate().to("https://admin.talkable.com/sites/custom/placements");
    }

    @Test
    public void test2() {
        PageCampaignPlacements campaignPlacements = new PageCampaignPlacements();
        campaignPlacements = campaignPlacements.addInclusion(FloatingWidget, false, "/test.2.2.html");
        campaignPlacements = campaignPlacements.addExclusion(Standalone, true, "test2");
        campaignPlacements.waitTillChangesApplied();
        System.out.println("PASSED: Changes are applied !!!");
    }

    @Test
    public void test3(){
        new PageCampaignPlacements().assertPlacement(FloatingWidget, true, "/test.2.2.html");
    }

    @Test
    public void test4(){
        new PageCampaignPlacements().assertPlacement(Standalone, true, "2312121");
    }

}
