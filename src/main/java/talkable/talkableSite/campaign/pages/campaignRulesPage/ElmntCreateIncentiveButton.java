package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateIncentiveButton extends AbstractElement{
    private static final  By locator = By.cssSelector(".incentive-form-footer > .is-success");

    ElmntCreateIncentiveButton(){
        setWebElement(locator);
    }
}
