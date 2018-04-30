package talkable.talkableSite.siteSettings.integrationSettings;

import talkable.talkableSite.siteSettings.SiteSettingsPage;

public class SiteSettingIntegrationTab extends SiteSettingsPage{

    public SiteSettingIntegrationTab(){
        setVisibleElements();
    }

    private void setVisibleElements(){
        new ElmntApiKey();
    }

    public String getApiKey(){
        return new ElmntApiKey().getAttribute("value");
    }
}
