package talkable.talkableSite.siteSettings;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.siteSettings.basic.SiteSettingsBasicTab;
import talkable.talkableSite.siteSettings.contacts.SiteSettingsContactsTab;
import talkable.talkableSite.siteSettings.integrationSettings.SiteSettingIntegrationTab;

public class SiteSettingsPage extends AbstractTalkableSitePage{

    //Elements

    private static final By elmntBasicButton = By.xpath("//a[contains(@class, 'ac-site-navigation-basic')]");
    private static final By elmntIntegrationSettingsButton = By.xpath("//a[contains(@class, 'ac-site-navigation-integration')]");
    private static final By elmntContactsButton = By.xpath("//a[contains(@class, 'ac-site-navigation-contacts')]");
    private static final By elmntSaveButton = By.xpath("//button[contains(@class,'ac-site-save')]");
    private static final By elmntCancelButton = By.xpath("//div[contains(@class,'base-form-inline-link')]");



    public SiteSettingsBasicTab openBasicTab(){
        new Element(elmntBasicButton).click();
        return new SiteSettingsBasicTab();
    }
    public SiteSettingIntegrationTab openContactsTab(){
        new Element(elmntContactsButton).click();
        return new SiteSettingsContactsTab();
    }
    public SiteSettingIntegrationTab openIntegrationSettingsTab(){
        new Element(elmntIntegrationSettingsButton).click();
        return new SiteSettingIntegrationTab();
    }

    public void saveChanges(){
        clickSaveChanges();
        waitSaving();
    }
    public void clickSaveChanges(){ new Element(elmntSaveButton).click();}
    public void clickCancel(){ new Element(elmntCancelButton).click();}

    // Alert popup "Do you want to save changes?" todo:click tab unsaved changes 
    private static final By elmntSaveChanges = By.xpath("//button[contains(@class,'base-btn first js-alert-close is-success')]");
    private static final By elmntCancelChanges = By.xpath("//div[contains(@class,'base-form-inline-link')]");
    private static final By elmntDiscardChanges = By.xpath("//button[contains(@class,'ac-site-save')]");
}
