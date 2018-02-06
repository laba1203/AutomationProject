package customerSite.talkableFrame.floatingWidgete.advocateSharePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntShareByLinkButton extends AbstractElement{

    private static  final By locator = By.cssSelector(".ac-share-via-link");

    ElmntShareByLinkButton(){
        setWebElement(locator);
    }
}
