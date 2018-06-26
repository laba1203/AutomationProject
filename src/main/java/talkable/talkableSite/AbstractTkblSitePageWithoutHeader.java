package talkable.talkableSite;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbstractTkblSitePageWithoutHeader extends AbstractElementsContainer{
    /*Class for Talkable pages without Header(). Example SimpleEditorPage() */


    public void waitSaving(){
        ElmntChangesSavedNotification notification = new ElmntChangesSavedNotification();
        try {
            wait.until(ExpectedConditions.invisibilityOf(notification.getWebElement()));
        }catch (TimeoutException e){
            System.out.println("LOG: Saving message is not disappeared for 15 seconds. Test execution is continued.\r\n" + e.getLocalizedMessage());
//            Assert.fail("FAILED: Warning message is not disappeared.\r\n" + e.getLocalizedMessage());
        }
    }
}
