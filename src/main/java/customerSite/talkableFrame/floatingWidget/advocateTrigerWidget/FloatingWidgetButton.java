package customerSite.talkableFrame.floatingWidget.advocateTrigerWidget;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class FloatingWidgetButton extends AbstractElement{
    private By locator = By.cssSelector(".trigger-body");

    FloatingWidgetButton(){
        setWebElement(locator);
    }
}
