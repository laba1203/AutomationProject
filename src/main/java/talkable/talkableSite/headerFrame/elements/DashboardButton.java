package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class DashboardButton extends AbstractElement {
    private static  final By dashboardButtonLocator = By.linkText("Dashboard");

    public DashboardButton(){
        setWebElement(dashboardButtonLocator);
    }
}
