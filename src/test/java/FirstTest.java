
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.camapignPlacements.PlacementRowElement;
import talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage;
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
        driver.navigate().to("https://admin.void.talkable.com/sites/simple-test/campaigns/45595/editor#/view_setups/150283/preset_slug/default-preset");
    }

    @Test
    public void test2() {
        EditorPage editorPage = new EditorPage();
        System.out.println(editorPage.localizationSidebar.getRecordByName("Advocate pages overlay opacity#").getName().getText());
        System.out.println(editorPage.localizationSidebar.getRecordByName("Advocate pages overlay opacity#").getValue().getText());
    }

    @Test
    public void test3(){
        EditorPage editorPage = new EditorPage();
        editorPage = editorPage.switchViewByName("Advocate social sharing");
    }



}
