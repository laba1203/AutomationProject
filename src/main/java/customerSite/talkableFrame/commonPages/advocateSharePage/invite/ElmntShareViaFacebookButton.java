package customerSite.talkableFrame.commonPages.advocateSharePage.invite;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntShareViaFacebookButton extends AbstractElement{

    private static  final By locator = By.cssSelector(".js-share-offer-via-facebook");

    ElmntShareViaFacebookButton(){
        setWebElement(locator);
    }
}
