package customerSite.talkableFrame.commonPages.advocateSignupPage;

import abstractObjects.AbstractTalkableFrame;
import abstractObjects.Element;
import customerSite.talkableFrame.floatingWidget.advocateSharePage.AdvocateSharePageFW;
import org.openqa.selenium.By;

public class AdvocateSignupPage extends AbstractTalkableFrame{
//    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe-popup']");
    private static final By frameLocator = By.xpath("//iframe[contains(@name,'talkable-offer-iframe')]");
    private static final By firstNameLctr = By.xpath("//input[@name='affiliate_member[first_name]']");

//    private FirstName firstNameInput;
    private Email email;
    private InviteFriendsButton inviteFriendsButton;
//    private ClosePopupButton closePopup;

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
//        firstNameInput = new FirstName();
        email = new Email();
        inviteFriendsButton = new InviteFriendsButton();
//        closePopup = new ClosePopupButton();
    }

    public AdvocateSharePageFW submitForm(String firstName, String email){
        new Element(firstNameLctr, "First Name field").sendKeys(firstName);
        this.email.sendKeys(email);
        inviteFriendsButton.click();
        switchToParentFrame();
        return new AdvocateSharePageFW();

    }

//    public void closePopup(){
//        closePopup.click();
//        switchToParentFrame();
//    }

    public static By getFrameLocator(){
        return frameLocator;
    }




}
