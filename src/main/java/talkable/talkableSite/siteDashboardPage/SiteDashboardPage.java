package talkable.talkableSite.siteDashboardPage;

import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.siteDashboardPage.elements.SiteNameField;

public class SiteDashboardPage extends AbstractTalkableSitePage{
    private SiteNameField siteName;
//    public Header header;

    public SiteDashboardPage(){
//        header = new Header();
        siteName = new SiteNameField();
    }

    public void verifySiteName(String expectedSiteName){
        Assert.assertEquals(siteName.getText(), expectedSiteName);
    }


}
