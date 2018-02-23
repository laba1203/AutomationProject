package talkable.talkableSite.campaign.pages.campaignDetailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntDeleteButton extends AbstractElement{
    private static final By locator = By.linkText("Delete");

    ElmntDeleteButton(){
        setWebElement(locator);
    }

}
