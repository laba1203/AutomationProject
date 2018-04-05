package customerSite.talkableFrame.commonPages.advocateSharePage.invite;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntShareByLinkButton extends AbstractElement{

    private static  final By locator = By.cssSelector(".ac-share-via-link");

    ElmntShareByLinkButton(){
        setWebElement(locator);
    }
}
