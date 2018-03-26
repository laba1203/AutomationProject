
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import util.DriverConfig;
import util.Util;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.CONFIGURATION;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.IMAGES;


public class FirstTest {

    private WebDriver driver;

    private String siteName = "automationSite";

//    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://void.talkable.com");
    }

    @Test
    public void test1_login(){
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");

        driver.navigate().to("https://admin.void.talkable.com/sites/custom2501/campaigns/45546/multiple_campaign_editor?locale_entry_key=advocate_signup_page_background&view_setup_id=150008&view_preset_cached_slug=default-preset");
    }

    @Test
    public void test2() {
        PageMultiCampaignEditor mcePage = new PageMultiCampaignEditor(IMAGES);
        mcePage.updateContent("Purchases.png");
        Assert.assertEquals(mcePage.getNewContentValue(), "Purchases.png");
    }

}
