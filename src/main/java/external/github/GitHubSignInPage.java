package external.github;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

public class GitHubSignInPage extends AbstractElementsContainer{
    private static final By loginLctr = By.xpath("//*[@name='login']");
    private static final By pswrdLctr = By.xpath("//*[@name='password']");
    private static final By submitLctr = By.xpath("//*[@name='commit']");

    public void login(String userName, String password){
        new Element(loginLctr, "Login").sendKeys(userName);
        new Element(pswrdLctr, "Password").sendKeys(password);
        new Element(submitLctr, "Sign in").click();
    }
}
