
package talkable.talkableSite.campaignsPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage;
import util.logging.Log;

public class PageCampaigns extends AbstractTalkableSitePage {
    private static final String liveCampaignsTableXpath = "//h2[contains(text(), 'Live')]/following::table[1]";
    private static final String testCampaignsTableXpath = "//h2[contains(text(), 'Test')]/following::table[1]";
    private static final String disabledCampaignsTableXpath = "//h2[contains(text(), 'Disabled')]/following::table[1]";
    private ElmntCreateNewCampaignButton createNewCampaignButton = new ElmntCreateNewCampaignButton();
    private Table liveCampaigns;
    private Table testCampaigns;
    private Table disabledCampaigns;

    public PageCampaigns() {
    }

    public Table getLiveCampaignsTable() {
        return new Table(By.xpath("//h2[contains(text(), 'Live')]/following::table[1]"));
    }

    public Table getTestCampaignsTable() {
        return new Table(By.xpath("//h2[contains(text(), 'Test')]/following::table[1]"));
    }

    public Table getDisabledCampaignsTable() {
        return new Table(By.xpath("//h2[contains(text(), 'Disabled')]/following::table[1]"));
    }

    public CreateNewCampaignPage clickCreateNewCampaign() {
        this.createNewCampaignButton.click();
        return new CreateNewCampaignPage();
    }

    public CampaignDetailsPage clickCampaignByName(String campaignName) {
        ArrayList<Element> row = this.getLiveCampaignsTable().getRowByCampaignName(campaignName);
        if (row == null) {
            row = this.getTestCampaignsTable().getRowByCampaignName(campaignName);
            if (row == null) {
                row = this.getDisabledCampaignsTable().getRowByCampaignName(campaignName);
            }
        }

        if (row == null) {
            Assert.fail(Log.rowIsNotFound(campaignName));
        }

        ((Element)row.get(1)).click();
        return new CampaignDetailsPage();
    }
}

