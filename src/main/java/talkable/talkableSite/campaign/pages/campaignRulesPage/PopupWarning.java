package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class PopupWarning extends AbstractElementsContainer{

    private static final String okButtonXpath = "//*[contains(@class, 'alert')]//*[contains(@class, 'alert-title')]//following-sibling::div//div[text()='Ok']";
    private static final String cancelButtonXpath = "//*[contains(@class, 'alert')]//*[contains(@class, 'alert-title')]//following-sibling::div//div[text()='Cancel']";

    private Element okButton = new Element(By.xpath(okButtonXpath));
    private Element cancelButton = new Element(By.xpath(cancelButtonXpath));

    PageCampaignRules ok(){
        okButton.click();
        return new PageCampaignRules();
    }

    PageCampaignRules cancel(){
        cancelButton.click();
        return new PageCampaignRules();
    }

}
