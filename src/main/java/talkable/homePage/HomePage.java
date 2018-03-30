package talkable.homePage;

import abstractObjects.AbstractElementsContainer;
import talkable.loginPage.LoginPage;

public class HomePage extends AbstractElementsContainer{

    private static final String title = "Talkable: Referral Marketing Made Easy | Refer-A-Friend programs, Referral Platform";

    private ElmntLoginButton loginButton;

    public HomePage(){
//        isPageOpened(title);

        loginButton = new ElmntLoginButton();
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return new LoginPage();
    }


}
