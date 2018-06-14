package customerSite.talkableFrame.commonPages.advocateSignupPage;

import abstractObjects.AbstractTalkableFrame;
import customerSite.talkableFrame.floatingWidget.advocateSharePage.AdvocateSharePageFW;
import org.openqa.selenium.By;
import util.WaitFactory;

public class AdvocateSignupPage extends AbstractTalkableFrame{
    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");

    private FirstName firstNameInput;
    private Email email;
    private InviteFriendsButton inviteFriendsButton;
    private ClosePopupButton closePopup;

    public AdvocateSignupPage(){
        waitFactory().waitUntilVisibilityOfElementLocated(frameLocator, 5);
        setWebElement(frameLocator);
        switchToThisFrame();

        //elements inside frame:
        setElementsInFrame();
    }

    public AdvocateSignupPage(By frameLocator){
        waitFactory().waitUntilVisibilityOfElementLocated(frameLocator, 5);
        setWebElement(frameLocator);
        switchToThisFrame();

        //elements inside frame:
        setElementsInFrame();
    }

    private void setElementsInFrame(){
        firstNameInput = new FirstName();
        email = new Email();
        inviteFriendsButton = new InviteFriendsButton();
        closePopup = new ClosePopupButton();
    }

    public AdvocateSharePageFW submitForm(String firstName, String email){
        firstNameInput.sendKeys(firstName);
        this.email.sendKeys(email);
        inviteFriendsButton.click();
        switchToParentFrame();
        return new AdvocateSharePageFW();

    }

    public void closePopup(){
        closePopup.click();
        switchToParentFrame();
    }

//    public static By getFrameLocator(){
//        return frameLocator;
//    }




}
