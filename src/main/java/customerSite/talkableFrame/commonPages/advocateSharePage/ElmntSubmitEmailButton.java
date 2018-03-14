package customerSite.talkableFrame.commonPages.advocateSharePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSubmitEmailButton extends AbstractElement{

    private static  final By locator = By.cssSelector("[type = 'submit']");

    ElmntSubmitEmailButton(){
        setWebElement(locator);
    }
}
