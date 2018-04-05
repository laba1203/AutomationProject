package customerSite.talkableFrame.commonPages.advocateSharePage.invite;

import abstractObjects.AbstractTalkableFrame;
import org.openqa.selenium.By;

public class AdvocateSharePageForInvite extends AbstractTalkableFrame{

    private By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");

//    private ElmntClosePopupButton elmntClosePopupButton;
    private ElmntShareViaEmailButton shareViaEmailButton;
    private ElmntShareByLinkButton shareByLinkButton;
    private ElmntShareViaFacebookButton shareViaFacebookButton;
    //Not visible containers:
    private ElmntShareLink shareLinkField;
    private ElmntFriendEmailInput friendEmailInput;
    private ElmntSubmitEmailButton submitEmailButton;

    public AdvocateSharePageForInvite(){

        setWebElement(frameLocator);
        switchToThisFrame();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setElementsInFrame();

    }

    public AdvocateSharePageForInvite(By frameLocator){
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
        shareViaEmailButton = new ElmntShareViaEmailButton();
        shareByLinkButton = new ElmntShareByLinkButton();
        shareViaFacebookButton = new ElmntShareViaFacebookButton();
    }

    public String getShareLink(){
        shareByLinkButton.click();
        shareLinkField = new ElmntShareLink();
        return shareLinkField.getText();
    }

    public void shareViaEmail(String friendEmail){
        shareByLinkButton.click();
        friendEmailInput = new ElmntFriendEmailInput();
        friendEmailInput.sendKeys(friendEmail);
        submitEmailButton = new ElmntSubmitEmailButton();
        submitEmailButton.click();
    }

}
