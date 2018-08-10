package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.SelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.ContentValueRecord;

class ContentConfigInput extends AbstractAbTestContent implements ContentValueRecord{
    private static final By dropdownLctr = By.xpath(".//select");

    ContentConfigInput(WebElement parent){
        setParentElement(parent);
    }

    @Override
    public void update(String text){
        getSelect().selectByVisibleText(text);
    }

    @Override
    public String getText(){
        return getSelect().getSelectedItemText();
    }

    private SelectElement getSelect(){
        return new SelectElement(getParentElement().findElement(dropdownLctr));
    }


}
