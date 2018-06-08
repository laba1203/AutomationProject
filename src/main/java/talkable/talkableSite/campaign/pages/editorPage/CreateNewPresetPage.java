package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import util.DriverConfig;

class CreateNewPresetPage extends AbstractTalkableSitePage{
    private static final By nameLctr = By.xpath("//*[@name = 'view_preset[name]']");
    private static final By jsonVariablesFieldLctr = By.xpath("//*[@name = 'view_preset[json_interpolation_variables]']");
    private static final By createBtnLctr = By.xpath("//input[@name = 'commit']");

    private String parentHandle;
    private String ownHandle;


    CreateNewPresetPage(){
        parentHandle = driver.getWindowHandle();
        ownHandle = DriverConfig.switchToUnknownWindow(parentHandle);
    }

    private void closeParentWindow(){
        ownHandle = driver.getWindowHandle();
        DriverConfig.switchToWindow(parentHandle);
        driver.close();
        DriverConfig.switchToWindow(ownHandle);
    }

    HtmlEditorPage closeParentWindowAndCreateNewPreview(String presetName, String jsonVariables){
        closeParentWindow();
        new Element(nameLctr, "'Preset Name' field").sendKeys(presetName);
        new Element(jsonVariablesFieldLctr, "'Json interpolation variables' field").sendKeys(jsonVariables);
        new Element(createBtnLctr, "'Create' button");
        return new HtmlEditorPage();
    }

}
