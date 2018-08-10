package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.ContentValueRecord;

class ContentColorInput extends AbstractAbTestContent implements ContentValueRecord{
    private static final By colorFieldInputLctr = By.xpath(".//*[contains(@class, 'asColorPicker-input')]");


    public ContentColorInput(WebElement element){
        setParentElement(element);
    }

    @Override
    public void update(String text){
        getColorInput().clear();
        getColorInput().sendKeys(text);
    }

    @Override
    public String getText(){
        return getColorInput().getAttribute("value");
    }

    private Element getColorInput(){
        return new Element(getParentElement(), colorFieldInputLctr, "Color input");
    }


}
