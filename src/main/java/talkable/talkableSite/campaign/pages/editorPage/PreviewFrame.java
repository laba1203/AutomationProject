package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractTalkableFrame;
import abstractObjects.Element;
import org.openqa.selenium.By;
import util.logging.Log;

class PreviewFrame extends AbstractTalkableFrame{

    private static final By frameLocator = By.xpath("//iframe");
    private int frameLevel = 0;

    PreviewFrame(){
        setWebElement(frameLocator);
        switchToThisFrame();
        switchToParentFrame();
    }

    private void switchToLowestChildFrame(){
        while(driver.findElements(frameLocator).size() > 0){
            switchToThisFrame();
            frameLevel++;
            if(frameLevel == 10){
                Log.debagRecord("Frame level is 10. Stopping the while cycle manually...");
                break;
            }
        }
    }

    private void switchToHighestParentFrame(){
        while (frameLevel > 0){
            switchToParentFrame();
            frameLevel--;
        }
    }


    public String getElementText(By locator){
        switchToLowestChildFrame();
        String out = new Element(locator).getText();
        switchToHighestParentFrame();
        return out;
    }


}
