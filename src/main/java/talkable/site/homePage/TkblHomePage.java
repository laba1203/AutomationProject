package talkable.site.homePage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.loginPage.LoginPage;
import talkable.site.TlkblSiteFooter;

public class TkblHomePage extends AbstractElementsContainer implements TlkblSiteFooter{

    private static final By loginBtnLctr = By.xpath("//a[text()='Login']");

    private Element loginBtn = new Element(loginBtnLctr, "Login button");


    public LoginPage clickLoginButton(){
        loginBtn.click();
        return new LoginPage();
    }


}
