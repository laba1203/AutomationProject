package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

class ContentCopyInput extends AbstractElementsContainer implements ContentValueRecord{

    private static final By valueLctr = By.xpath("//textarea");
    private static final By textLctr = By.xpath("//div[@class = 'code-area']");

    private Element value = new Element(valueLctr);
    private Element text = new Element(textLctr);

    @Override
    public void update(String text){
        this.text = new Element(wait.until(ExpectedConditions.elementToBeClickable(this.text.getWebElement())));
        this.text.click();
        value.clear();
        value.sendKeys(text);
    }

    @Override
    public String getText(){
        return text.getText();
    }


}
