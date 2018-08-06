package customerSite.talkableFrame.commonPages.advocateSharePage.invite;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

@Deprecated
class ElmntShareViaEmailButton extends AbstractElement{

    private static  final By locator = By.cssSelector(".ac-share-via-email");

    ElmntShareViaEmailButton(){
        setWebElement(locator);
    }
}
