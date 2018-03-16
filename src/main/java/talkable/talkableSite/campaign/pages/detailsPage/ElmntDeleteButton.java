package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntDeleteButton extends AbstractElement{
    private static final By locator = By.xpath("//input[@value='Delete']");

    ElmntDeleteButton(){
        setWebElement(locator);
    }

}
