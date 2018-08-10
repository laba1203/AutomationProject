package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.ContentValueRecord;

class ContentCopyInput extends AbstractAbTestContent implements ContentValueRecord{

    private static final By valueLctr = By.xpath(".//textarea");
    private static final By textLctr = By.xpath(".//div[contains(@class,'code-area')]/div");


    ContentCopyInput(WebElement parent){
        setParentElement(parent);
    }

    @Override
    public void update(String text){
        Element textDiv = new Element(
                wait.until(ExpectedConditions.elementToBeClickable(getParentElement().findElement(textLctr))),
                "COPY input");
        textDiv.click();
        getTextArea().clear();
        getTextArea().sendKeys(text);
    }

    @Override
    public String getText(){
        return new Element(getParentElement(), textLctr, "Copy")
                .getText();
    }

    private Element getTextArea(){
        return  new Element(getParentElement(), valueLctr, "text area");
    }


}
