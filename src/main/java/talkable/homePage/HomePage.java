package talkable.homePage;

import abstractObjects.AbstractElementsContainer;
import talkable.homePage.elements.LoginButton;

public class HomePage extends AbstractElementsContainer{

    private static final String title = "Talkable: Referral Marketing Made Easy | Refer-A-Friend programs, Referral Platform";

    public LoginButton loginButton;

    public HomePage(){
//        isPageOpened(title);

        loginButton = new LoginButton();
    }


}
