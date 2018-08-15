package talkable.site.handbook;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

public class DownloadHandbookPage extends AbstractElementsContainer{
    private static final By messageHeaderLctr = By.xpath("//h1");
    private static final By messageTextLctr = By.xpath("//div/p");
    private static final By accessHandbookBtnLctr = By.xpath("//a[contains(@class, 'primary')]");

    private Element accessHandbookBtn = new Element(accessHandbookBtnLctr, "Access Handbook");

    public String getMessageHeader(){
        return new Element(messageHeaderLctr, "Message Header").getText();
    }

    public String getMessageText(){
        return new Element(messageTextLctr, "Message Text").getText();
    }

    public HandbookFilePage clickAccessHandbook(){
        accessHandbookBtn.click();
        return new HandbookFilePage();
    }
}
