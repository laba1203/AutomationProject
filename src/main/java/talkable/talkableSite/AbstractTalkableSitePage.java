package talkable.talkableSite;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.talkableSite.headerFrame.Header;

public abstract class AbstractTalkableSitePage extends AbstractElementsContainer{

    public Header header;

    public AbstractTalkableSitePage(){
        header = new Header();
    }

    public void waitSaving(){
        ElmntChangesSavedNotification notification = new ElmntChangesSavedNotification();
        wait.until(ExpectedConditions.visibilityOf(notification.getWebElement()));
        wait.until(ExpectedConditions.invisibilityOf(notification.getWebElement()));
    }
}
