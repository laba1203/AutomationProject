package talkable.talkableSite.siteDashboardPage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;

public class SiteDashboardPage extends AbstractTalkableSitePage{
    private ElmntSiteNameField siteName;
//    public Header header;

    public SiteDashboardPage(){
        beforeSupperActions();
//        header = new Header();
        siteName = new ElmntSiteNameField();
    }

    @Override
    protected void beforeSupperActions(){
        siteName = new ElmntSiteNameField();
    }

    public SiteDashboardPage verifySiteName(String expectedSiteName){
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(siteName.getWebElement(), expectedSiteName));
            return new SiteDashboardPage();
        }catch (TimeoutException e) {
            Assert.assertEquals(siteName.getText(), expectedSiteName);
            return null;
        }
    }


}
