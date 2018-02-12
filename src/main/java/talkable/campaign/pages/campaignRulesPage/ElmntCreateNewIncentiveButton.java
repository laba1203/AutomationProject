package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateNewIncentiveButton extends AbstractElement{

    private static final By locator = By.xpath("//div[@data-toggle]");

    ElmntCreateNewIncentiveButton(){
        setWebElement(locator);
    }

}
