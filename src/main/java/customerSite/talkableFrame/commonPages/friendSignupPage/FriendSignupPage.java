package customerSite.talkableFrame.commonPages.friendSignupPage;

import abstractObjects.AbstractTkblFrame;
import abstractObjects.Element;
import org.openqa.selenium.By;

public class FriendSignupPage extends AbstractTkblFrame {

    private static final By emailGatingLctr = By.xpath("//input[@name='person[email]']");
    private static final By getMyDiscountBtn = By.xpath("//button[@type='submit']");
    private static final By couponCodeLctr = By.xpath("//*[@class='promo-code']");

    public FriendSignupPage signup(String email){
        new Element(emailGatingLctr, "Friend Email").sendKeys(email);
        new Element(getMyDiscountBtn, "Get my discount button").click();
        return new FriendSignupPage();
    }

    public String getCouponCode(){
        return new Element(couponCodeLctr).getText();
    }

}
