package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.SelectElement;
import org.openqa.selenium.By;

class ContentConfigInput extends AbstractElementsContainer implements ContentValueRecord{

    private SelectElement dropdown;

    ContentConfigInput(){
        dropdown = new SelectElement(driver.findElement(By.xpath("//*[contains(text(), 'New content value')]/..//select")));
    }

    @Override
    public void update(String text){
        dropdown.selectByVisibleText(text);
    }

    @Override
    public String getText(){
        return dropdown.getSelectedItemText();
    }


}
