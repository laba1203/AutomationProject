package talkable.talkableSite.siteSettings;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.siteSettings.basic.SiteSettingsBasicTab;
import talkable.talkableSite.siteSettings.integrationSettings.SiteSettingIntegrationTab;

public class SiteSettingsPage extends AbstractTalkableSitePage{

    public SiteSettingsBasicTab openBasicTab(){
        new ElmntBasicButton().click();
        return new SiteSettingsBasicTab();
    }

    public SiteSettingIntegrationTab openIntegrationSettingsTab(){
        new ElmntIntegrationSettingsButton().click();
        return new SiteSettingIntegrationTab();
    }
}
