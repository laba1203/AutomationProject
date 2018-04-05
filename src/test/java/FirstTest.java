
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import util.DriverConfig;
import util.Util;

import static talkable.common.CampaignPlacement.FloatingWidget;
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
        driver.navigate().to("https://void.talkable.com");
    }

    @Test
    public void test1_login(){
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");

        driver.navigate().to("https://admin.void.talkable.com/sites/test14123434/campaigns");
    }

    @Test
    public void test2() {
        PageCampaigns campaigns = new PageCampaigns();
        campaigns.createNewCampaign(AdvocateDashboard, FloatingWidget);
    }

}
