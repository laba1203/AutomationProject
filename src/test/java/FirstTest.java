
import abstractObjects.Element;
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.IntegrationInstructionPage.IntegrationInstructionPage;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.IncentiveTile;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.siteDashboardPage.SiteDashboardPage;
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
        ArrayList<Element> shownOn = campaignPlacements.standaloneSection.getShownOn();
        for (Element el :
                shownOn) {
            System.out.println(el.getText());
        }
        System.out.println("***************");

        ArrayList<Element> hiddenOn = campaignPlacements.standaloneSection.getHiddenOn();
        for (Element el :
                hiddenOn) {
            System.out.println(el.getText());
        }

    }





}
