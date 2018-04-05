package customerSite.talkableFrame.floatingWidget.advocateSharePage;

import customerSite.talkableFrame.commonPages.advocateSharePage.invite.AdvocateSharePageForInvite;
import org.openqa.selenium.By;

public class AdvocateSharePageFW extends AdvocateSharePageForInvite {

    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe-overlay']");

////    private ElmntClosePopupButton elmntClosePopupButton;
//    private ElmntShareViaEmailButton shareViaEmailButton;
//    private ElmntShareByLinkButton shareByLinkButton;
//    private ElmntShareViaFacebookButton shareViaFacebookButton;
//    //Not visible containers:
//    private ElmntShareLink shareLinkField;
//    private ElmntFriendEmailInput friendEmailInput;
//    private ElmntSubmitEmailButton submitEmailButton;

    public AdvocateSharePageFW(){
        super(frameLocator);
    }
//
//    public AdvocateSharePageFW(){
//
//        setWebElement(frameLocator);
//        switchToThisFrame();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        shareViaEmailButton = new ElmntShareViaEmailButton();
//        shareByLinkButton = new ElmntShareByLinkButton();
//        shareViaFacebookButton = new ElmntShareViaFacebookButton();
//
//    }
//
//    public String getShareLink(){
//        shareByLinkButton.click();
//        shareLinkField = new ElmntShareLink();
//        return shareLinkField.getText();
//    }
//
//    public void shareViaEmail(String friendEmail){
//        shareByLinkButton.click();
//        friendEmailInput = new ElmntFriendEmailInput();
//        friendEmailInput.sendKeys(friendEmail);
//        submitEmailButton = new ElmntSubmitEmailButton();
//        submitEmailButton.click();
//    }

}
