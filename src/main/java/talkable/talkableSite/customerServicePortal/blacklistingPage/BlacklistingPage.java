package talkable.talkableSite.customerServicePortal.blacklistingPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.customerServicePortal.AbstractCustomerServicePortalPage;

public class BlacklistingPage extends AbstractCustomerServicePortalPage{

    private static final By blacklistedEmailsLctr = By.xpath("//textarea[@name='blocked_emails']");
    private static final By saveChangesBtnLctr = By.xpath("//button[@value='Save Changes']");


    private Element blacklistedEmailsField = new Element(blacklistedEmailsLctr, "Blacklisted emails list");


    public BlacklistingPage updateBlacklistedEmailList(String value){
        blacklistedEmailsField.sendKeys("\r\n" + value);
        return saveChanges();
    }

    public String getBlacklistedEmailsList(){
        return blacklistedEmailsField.getText();
    }

    private BlacklistingPage saveChanges(){
        new Element(saveChangesBtnLctr, "'Save Changes' button").click();
        waitSaving();
        return new BlacklistingPage();
    }

}
