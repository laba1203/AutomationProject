package talkable.talkableSite;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbstractTkblSitePageWithoutHeader extends AbstractElementsContainer{
    /*Class for Talkable pages without Header(). Example EditorPage() */


    public void waitSaving(){
        ElmntChangesSavedNotification notification = new ElmntChangesSavedNotification();
//        wait.until(ExpectedConditions.visibilityOf(notification.getWebElement()));
        wait.until(ExpectedConditions.invisibilityOf(notification.getWebElement()));
    }
}
