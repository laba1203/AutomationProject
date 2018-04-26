package customerSite.talkableFrame.commonPages.advocateSharePage.advocateDashboard;

import abstractObjects.AbstractTalkableFrame;
import org.openqa.selenium.By;

public class AdSharePageForAdDashboard extends AbstractTalkableFrame {
    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");

    //Elements:
    ElmntEmailShareButton emailShareButton;

    public AdSharePageForAdDashboard(){
        setWebElement(frameLocator);
        switchToThisFrame();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setElementsInFrame();
    }

    private void setElementsInFrame(){
        emailShareButton = new ElmntEmailShareButton();
    }

    public static By getFrameLocator(){
        return frameLocator;
    }
}
