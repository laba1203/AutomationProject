package talkable.talkableSite.integrationPage;

import abstractObjects.Element;
import external.shopify.shop.ShopifyShopLoginPage;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;

public class IntegrationPage extends AbstractTalkableSitePage{
    private static final By goBackBtnLctr = By.xpath("//a[text()='Go back']");
    private static final By selectedPlatformLctr = By.xpath("//div[contains(@class, 'platform-select')]//a[@data-toggle='dropdown']");
    private static final By welcomeMessage = By.xpath("//*[@class='Welcome-data']//h2");
    private static final By installShopifyAppBtnLctr = By.xpath("//div[contains(@class, 'Welcome-controls')]/a[text()='Install Shopify App']");
    private static final By scriptSourceLinkLctr = By.xpath("//span[@class='hljs-tag'][3]");

    private Element goBackBtn = new Element(goBackBtnLctr, "Go Back button");
//    private Element selectedPlatformBtn = new Element(selectedPlatformLctr, "Selected Platform");


    public ShopifyShopLoginPage installShopifyApp(){
        new Element(installShopifyAppBtnLctr, "Install Shopify App button").click();
        return new ShopifyShopLoginPage();
    }

    public String getWelcomeMsg(){
        return new Element(welcomeMessage).getText();
    }

    public String getScriptSourceFromGeneralIntegration(){
        return new Element(scriptSourceLinkLctr).getText() + "</script> \n";
    }


}
