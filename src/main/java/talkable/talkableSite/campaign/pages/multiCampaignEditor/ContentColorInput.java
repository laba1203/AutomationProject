package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class ContentColorInput extends AbstractElementsContainer implements ContentValueRecord{

    private Element colorValueInput;

    ContentColorInput(){
        colorValueInput = new Element(driver.findElement(By.xpath("//*[contains(text(), 'New content value')]/..//input")));
    }

    @Override
    public void update(String text){
        colorValueInput.clear();
        colorValueInput.sendKeys(text);
    }

    @Override
    public String getText(){
        return colorValueInput.getAttribute("value");
    }


}
