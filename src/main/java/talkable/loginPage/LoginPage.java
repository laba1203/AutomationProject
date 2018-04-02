package talkable.loginPage;


import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.headerFrame.Header;


public class LoginPage extends AbstractElementsContainer {


    private static final String title = "Login | Talkable";

    //Elements

    private ElmntEmailInput emailInput;
    private ElmntPasswordInput passwordInput;
    private ElmntLoginButton loginButton;



    public LoginPage(){
        //initiate web containers
        emailInput = new ElmntEmailInput();
        passwordInput = new ElmntPasswordInput();
        loginButton = new ElmntLoginButton();

    }



    public Header submitLoginForm(String email, String password){
        //fill Email
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new Header();
    }





}
