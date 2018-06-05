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
    private static final By elmntSaveButton = By.xpath("//button[contains(@class,'ac-site-save')]");
    private static final By elmntSitePlatform = By.xpath("//*[@name = 'platform']");
    //
    public SiteSettingsBasicTab(){
        setVisibleElements();
    }

    private void setVisibleElements(){
        new Element(elmntSiteId);
        new Element(elmntSiteName);
        new Element(elmntSiteURL);
    }

    public String getSiteID(){
//        return new ElmntSiteID().getAttribute("value");
        return new Element(elmntSiteId).getAttribute("value");
    }

    public void saveChanges(){
        new Element(elmntSaveButton).click();
        waitSaving();
    }

   public SiteSettingsBasicTab editMandatoryFields(String siteName, String siteId, String siteURL ){

        populate(siteName, siteId, siteURL);

        saveChanges();
        return new SiteSettingsBasicTab();
    }

    private void populate(String siteName, String siteId, String siteURL ){
        new Element(elmntSiteName, "Site Name field").clearAndSendKeys(siteName);
        new Element(elmntSiteId, "Site ID field").clearAndSendKeys(siteId);
        new Element(elmntSiteURL, "Site URL field").clearAndSendKeys(siteURL);
    }

    public SiteSettingsBasicTab updateAll(String siteName, String siteId, String siteURL, String platform ){
        populate(siteName, siteId, siteURL);
        new SelectElement(elmntSitePlatform).searchAndSelect(platform);
        saveChanges();
        return new SiteSettingsBasicTab();
    }

}
