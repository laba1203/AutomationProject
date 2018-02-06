package customerSite.talkableFrame.floatingWidgete.advocateSignupPage;

import abstractObjects.AbstractTalkableFrame;
import customerSite.talkableFrame.floatingWidgete.advocateSharePage.AdvocateSharePage;
import org.openqa.selenium.By;

public class AdvocateSignupPage extends AbstractTalkableFrame{
    private By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe-overlay']");

    private FirstName firstNameInput;
    private Email email;
    private InviteFriendsButton inviteFriendsButton;
    private ClosePopupButton closePopup;

    public AdvocateSignupPage(){
        setWebElement(frameLocator);
        switchToThisFrame();

        firstNameInput = new FirstName();
        email = new Email();
        inviteFriendsButton = new InviteFriendsButton();
        closePopup = new ClosePopupButton();
    }

    public AdvocateSharePage submitForm(String firstName, String email){
        firstNameInput.sendKeys(firstName);
        this.email.sendKeys(email);
        inviteFriendsButton.click();
        switchToParentFrame();
        return new AdvocateSharePage();

    }

    public void closePopup(){
        closePopup.click();
        switchToParentFrame();
    }




}
