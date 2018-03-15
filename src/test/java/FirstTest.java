
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage;
import talkable.talkableSite.headerFrame.Header;
import util.DriverConfig;

import static talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage.LocalizationMode.COPY;


public class FirstTest {

    private WebDriver driver;

    private String siteName = "automationSite";

    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://void.talkable.com");
    }

    @Test
    public void test1_login(){
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");

        driver.navigate().to("https://admin.void.talkable.com/sites/simple-test/campaigns/45595/editor#/view_setups/150283/preset_slug/default-preset");
    }

    @Test
    public void test2() {
        EditorPage editorPage = new EditorPage();
        System.out.println(editorPage.localizationSidebar.getRecord(COPY, "Advocate pages overlay opacity#").getNameText());
        System.out.println(editorPage.localizationSidebar.getRecord(COPY, "Advocate pages overlay opacity#").getValueText());
        editorPage.updateLocalization(COPY,"Advocate share page description#", "Updated");
    }

    @Test
    public void test3(){
        EditorPage editorPage = new EditorPage();
        editorPage = editorPage.switchViewByName("Advocate social sharing");
    }

}
