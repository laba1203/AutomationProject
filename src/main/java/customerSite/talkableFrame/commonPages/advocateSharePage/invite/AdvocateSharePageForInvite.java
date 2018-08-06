package customerSite.talkableFrame.commonPages.advocateSharePage.invite;

import abstractObjects.AbstractTalkableFrame;
import abstractObjects.Element;
import org.openqa.selenium.By;
import util.WaitFactory;

public class AdvocateSharePageForInvite extends AbstractTalkableFrame{

    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");
    private static final By shareViaEmailBtn = By.cssSelector(".ac-share-via-email");
    private static  final By shareViaLinkBtn = By.cssSelector(".ac-share-via-link");
    private static  final By shareLinkLctr = By.cssSelector(".share-link");


    public AdvocateSharePageForInvite(){
        waitFactory().waitUntilVisibilityOfElementLocated(AdvocateSharePageForInvite.getFrameLocator(), 5);
        setWebElement(frameLocator);
        switchToThisFrame();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public AdvocateSharePageForInvite(By frameLocator){
        waitFactory().waitUntilVisibilityOfElementLocated(AdvocateSharePageForInvite.getFrameLocator(), 5);
        setWebElement(frameLocator);
        switchToThisFrame();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public String getShareLink(){
        new Element(shareViaLinkBtn, "Share by link").click();
        return new Element(shareLinkLctr).getText();
    }

    public static By getFrameLocator(){
        return frameLocator;
    }

}
