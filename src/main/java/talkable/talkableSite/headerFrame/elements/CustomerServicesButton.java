package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CustomerServicesButton extends AbstractElement {
    private static  final By customerServicesButtonLocator = By.linkText("Customer Service");

    public CustomerServicesButton(){
        setWebElement(customerServicesButtonLocator);
    }
}
