package talkable.talkableSite.siteDashboardPage;

import abstractObjects.AbstractElementsContainer;
import org.testng.Assert;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.siteDashboardPage.elements.SiteNameField;

public class SiteDashboardPage extends AbstractElementsContainer{
    private SiteNameField siteName;
    public Header header;

    public SiteDashboardPage(){
        header = new Header();
        siteName = new SiteNameField();
    }

    public void verifySiteName(String expectedSiteName){
        Assert.assertEquals(siteName.getText(), expectedSiteName);
    }


}
