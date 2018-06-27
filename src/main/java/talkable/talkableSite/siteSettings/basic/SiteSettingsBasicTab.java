package talkable.talkableSite.siteSettings.basic;

import abstractObjects.Element;
import abstractObjects.SelectElement;
import org.openqa.selenium.By;
import talkable.talkableSite.siteSettings.SiteSettingsPage;

public class SiteSettingsBasicTab extends SiteSettingsPage{
    //ELEMENTS

    private static final By elmntSiteId = By.xpath("//*[@name = 'cached_slug']");
    private static final By elmntSiteName = By.xpath("//*[@name='name']");
    private static final By elmntSiteURL = By.xpath("//*[@name='url']");
    private static final By elmntSitePlatform = By.xpath("//*[@name = 'platform']");
    private static final By elmntSiteCurrency = By.xpath("//*[@name = 'currency_code']");
    private static final By elmntErrorMsg = By.xpath("//*[@class = 'form-validation']");


    //
    public SiteSettingsBasicTab(){
        setVisibleElements();
    }

    private void setVisibleElements(){
        new Element(elmntSiteId);
        new Element(elmntSiteName);
        new Element(elmntSiteURL);
    }
//geters
    public String getSiteName(){
        return new Element(elmntSiteName).getAttribute("value");
    }
    public String getSiteID(){
        return new Element(elmntSiteId).getAttribute("value");
    }
    public String getSiteURL(){
        return new Element(elmntSiteURL).getAttribute("value");
    }
    public String getErrorMsg(){
        /*Log.debagRecord("Start");
        for (WebElement e :
                driver.findElements(elmntErrorMsg)
                ) {
            System.out.println("  *** - " + e.getText());
        }

        Log.debagRecord("End");*/
        return new Element(elmntErrorMsg).getText();
    }




    public void populate(String siteName, String siteId, String siteURL ){
        new Element(elmntSiteName, "Site Name field").clearAndSendKeys(siteName);
        new Element(elmntSiteId, "Site ID field").clearAndSendKeys(siteId);
        new Element(elmntSiteURL, "Site URL field").clearAndSendKeys(siteURL);
    }
    public void  selectPlatform(String platform){
        new SelectElement(elmntSitePlatform).searchAndSelect(platform);
    }
    public SiteSettingsBasicTab updateAll(String siteName, String siteId, String siteURL, String platform, String currency ){
        //debag:
        //new Element(elmntSaveButton).moveMouseOver();
        //end
        populate(siteName, siteId, siteURL);
        new SelectElement(elmntSitePlatform).searchAndSelect(platform);
        new SelectElement(elmntSiteCurrency).selectByVisibleText(currency);
        updateChanges();
        return new SiteSettingsBasicTab();
    }

}
