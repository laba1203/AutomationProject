package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateNewIncentiveButton extends AbstractElement{

//    private static final By locator = By.xpath("//div[@data-toggle]");
    private static final By locator = By.xpath("//div[text() = 'Create New Incentive']");

    ElmntCreateNewIncentiveButton(){
        setWebElement(locator);
    }

    public static By getWebElementLocator(){
        return locator;
    }

}
