package talkable.talkableSite.siteDashboardPage;

import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;

public class SiteDashboardPage extends AbstractTalkableSitePage{
    private ElmntSiteNameField siteName;
//    public Header header;

    public SiteDashboardPage(){
//        header = new Header();
        siteName = new ElmntSiteNameField();
    }

    public void verifySiteName(String expectedSiteName){
        Assert.assertEquals(siteName.getText(), expectedSiteName);
    }


}
