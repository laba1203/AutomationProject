
package talkable.talkableSite.campaignsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage;
import util.logging.Log;

import java.util.ArrayList;

import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;
import static talkable.talkableSite.campaignsPage.Table.Status.LIVE;
import static talkable.talkableSite.campaignsPage.Table.Status.TEST;

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
//        return new Table(By.xpath("//h2[contains(text(), 'Live')]/following::table[1]"));
        return new Table(LIVE);
    }

    public Table getTestCampaignsTable() {
//        return new Table(By.xpath("//h2[contains(text(), 'Test')]/following::table[1]"));
        return new Table(TEST);
    }

    public Table getDisabledCampaignsTable() {
//        return new Table(By.xpath("//h2[contains(text(), 'Disabled')]/following::table[1]"));
        return new Table(DISABLED);
    }

    public CreateNewCampaignPage clickCreateNewCampaign() {
        this.createNewCampaignButton.click();
        return new CreateNewCampaignPage();
    }

    public CampaignDetailsPage clickCampaignByName(String campaignName) {
        Table liveCampaigns = getLiveCampaignsTable();
        Table.Row campaignRow = liveCampaigns.getRowByCampaignName(campaignName);
        campaignRow.name.click();
        return new CampaignDetailsPage();
    }

    public PageCampaigns deactivateAllLiveCampaigns(){
        if(isTablePresent(LIVE)) {
            ArrayList<Table.Row> campaigns = getLiveCampaignsTable().getAllRows();
            for (Table.Row row :
                    campaigns) {
                String campaignName = row.name.getText();
                row.deactivate();
                waitDeactivation();
                Log.campaignDeactivated(campaignName);
            }
        }
        return new PageCampaigns();
    }

    private boolean isTablePresent(Table.Status status){
        try{
            Table table = new Table(status);
            return true;
        }catch (NoSuchElementException e){
           Log.tableIsMissed(status.toString());
           return false;
        }
    }

    private void waitDeactivation(){
        MsgCampaignDeactivated campaignDeactivated = new MsgCampaignDeactivated();
        wait.until(ExpectedConditions.invisibilityOf(campaignDeactivated.getWebElement()));
    }
}

