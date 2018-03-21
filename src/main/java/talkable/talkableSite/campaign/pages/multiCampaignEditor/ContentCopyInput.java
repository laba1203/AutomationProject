package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class ContentCopyInput extends AbstractElementsContainer implements ContentValueRecord{

    private Element value;
    private Element text;

    ContentCopyInput(){
        value = new Element(driver.findElement(By.xpath("//textarea")));
        text = new Element(driver.findElement(By.xpath("//div[@class = 'code-area']")));
    }

    @Override
    public void update(String text){
        value.sendKeys(text);
    }

    @Override
    public String getText(){
        return text.getText();
    }


}
