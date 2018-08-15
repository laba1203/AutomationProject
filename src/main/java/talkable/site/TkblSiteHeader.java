package talkable.site;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.loginPage.LoginPage;
import talkable.site.resourcesPage.TkblSiteResourcesPage;

public interface TkblSiteHeader {

    By loginBtnLctr = By.xpath("//a[text()='Login']");
    By resourcesLctr = By.xpath("//a[text()='Resources']");

    default LoginPage clickLoginButton(){
        new Element(loginBtnLctr, "Login button").click();
        return new LoginPage();
    }

    default TkblSiteResourcesPage openResourcesPage(){
        new Element(resourcesLctr, "Resources").click();
        return new TkblSiteResourcesPage();
    }
}
