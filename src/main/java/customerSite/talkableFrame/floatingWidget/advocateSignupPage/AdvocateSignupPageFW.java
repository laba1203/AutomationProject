package customerSite.talkableFrame.floatingWidget.advocateSignupPage;

import customerSite.talkableFrame.commonPages.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidget.advocateSharePage.AdvocateSharePageFW;
import org.openqa.selenium.By;

public class AdvocateSignupPageFW extends AdvocateSignupPage{
    private static final By frameLocator = By.xpath("//iframe[contains(@name,'talkable-offer-iframe-popup')]");

//    private FirstName firstNameInput;
//    private Email email;
//    private InviteFriendsButton inviteFriendsButton;
//    private ClosePopupButton closePopup;

    public AdvocateSignupPageFW(){
        super(frameLocator);
    }





}
