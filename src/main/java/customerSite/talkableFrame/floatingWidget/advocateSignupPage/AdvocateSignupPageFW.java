package customerSite.talkableFrame.floatingWidget.advocateSignupPage;

import customerSite.talkableFrame.commonPages.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidget.advocateSharePage.AdvocateSharePageFW;
import org.openqa.selenium.By;

public class AdvocateSignupPageFW extends AdvocateSignupPage{
    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe-overlay']");

//    private FirstName firstNameInput;
//    private Email email;
//    private InviteFriendsButton inviteFriendsButton;
//    private ClosePopupButton closePopup;

    public AdvocateSignupPageFW(){
        super(frameLocator);
    }

//    public AdvocateSignupPageFW(){
//        setWebElement(frameLocator);
//        switchToThisFrame();
//
//        //elements inside frame:
//        firstNameInput = new FirstName();
//        email = new Email();
//        inviteFriendsButton = new InviteFriendsButton();
//        closePopup = new ClosePopupButton();
//    }
//
//    public AdvocateSharePageFW submitForm(String firstName, String email){
//        firstNameInput.sendKeys(firstName);
//        this.email.sendKeys(email);
//        inviteFriendsButton.click();
//        switchToParentFrame();
//        return new AdvocateSharePageFW();
//
//    }
//
//    public void closePopup(){
//        closePopup.click();
//        switchToParentFrame();
//    }




}
