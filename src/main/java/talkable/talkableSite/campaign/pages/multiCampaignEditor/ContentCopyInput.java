package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

class ContentCopyInput extends AbstractElementsContainer implements ContentValueRecord{

    private Element value;
    private Element text;

    ContentCopyInput(){
        value = new Element(driver.findElement(By.xpath("//textarea")));
        text = new Element(driver.findElement(By.xpath("//div[@class = 'code-area']")));
    }

    @Override
    public void update(String text){
//        value = new Element(wait.until(ExpectedConditions.elementToBeClickable(value.getWebElement())));
//        value.click();
//        value.clear();
//        value.sendKeys(text);
        this.text = new Element(wait.until(ExpectedConditions.elementToBeClickable(this.text.getWebElement())));
        this.text.click();
        value = new Element(driver.findElement(By.xpath("//textarea")));
        value.clear();
        value.sendKeys(text);
    }

    @Override
    public String getText(){
        return text.getText();
    }


}
