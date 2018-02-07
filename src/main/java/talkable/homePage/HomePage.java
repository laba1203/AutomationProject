package talkable.homePage;

import abstractObjects.AbstractElementsContainer;
import talkable.homePage.elements.LoginButton;
import talkable.loginPage.LoginPage;

public class HomePage extends AbstractElementsContainer{

    private static final String title = "Talkable: Referral Marketing Made Easy | Refer-A-Friend programs, Referral Platform";

    private LoginButton loginButton;

    public HomePage(){
//        isPageOpened(title);

        loginButton = new LoginButton();
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return new LoginPage();
    }


}
