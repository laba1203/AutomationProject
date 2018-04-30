package talkable.talkableSite.siteSettings.basic;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.siteSettings.SiteSettingsPage;

public class SiteSettingsBasicTab extends SiteSettingsPage{

    public SiteSettingsBasicTab(){
        setVisibleElements();
    }

    private void setVisibleElements(){
        new ElmntSiteID();
    }

    public String getSiteID(){
        return new ElmntSiteID().getAttribute("value");
    }



}
