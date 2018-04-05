package customerSite.talkableFrame.commonPages.advocateSharePage.advocateDashboard;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEmailShareButton extends AbstractElement{
    private static final By locator = By.cssSelector("[data-toggle='email-share']");

    ElmntEmailShareButton(){
        setWebElement(locator);
    }
}
