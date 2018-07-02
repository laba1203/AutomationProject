package talkable.talkableSite.siteSettings;

import abstractObjects.Element;
import org.openqa.selenium.By;

public class ElmntUnsavedChangesPopup extends SiteSettingsPage {

    private static final By elmntSaveChanges = By.xpath("//button[contains(@class,'base-btn first js-alert-close is-success')]");
    private static final By elmntCancelChanges = By.xpath("//div[contains(@class,'base-btn js-alert-close')]");
    private static final By elmntDiscardChanges = By.xpath("//a[contains(@class,'base-btn js-alert-close js-alert-action is-error')]");

    public void saveChanges(){ new Element(elmntSaveChanges).click();}
    public void cancelChanges(){ new Element(elmntCancelChanges).click();}
    public void discardChanges(){ new Element(elmntDiscardChanges).click();}
}
