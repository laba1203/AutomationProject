
package talkable.talkableSite.campaignsPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage;
import util.WaitFactory;
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

    private Table getLiveCampaignsTable() {
        return new Table(LIVE);
    }

    public Table getTestCampaignsTable() {
        return new Table(TEST);
    }

    public Table getDisabledCampaignsTable() {
        return new Table(DISABLED);
    }

    public CampaignDetailsPage createNewCampaign(CampaignType type, CampaignPlacement placement) {
        this.createNewCampaignButton.click();
        new ElmntNewCampaignDropdownFields(type, placement).click();
        return new CampaignDetailsPage();
    }


    public CampaignDetailsPage openCampaignByName(String campaignName, Table.Status status) {
        Table table = new Table(status);
        Table.Row campaignRow = table.getRowByCampaignName(campaignName);
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

    public PageCampaigns deleteAllCampaignsWithStatus(Table.Status status){
        if(isTablePresent(status)){
            deleteAllCampaigns(new Table(status));
//            int count = getTestCampaignsTable().getAllRows().size();
//            System.out.println("DEBAG: Found " + count + " Test campaign for deletion");
//            for(int i = 0; i < count; i++){
//                Table.Row row = getTestCampaignsTable().getAllRows().get(0);
//                String campaignName = row.name.getText();
//                row.delete();
//                waitDeletion();
//                Log.campaignDeleted(campaignName);
//            }
        }
        return new PageCampaigns();
    }

//    public PageCampaigns deleteAllLiveCampaigns(){
//        if(isTablePresent(LIVE)){
//            deleteAllCampaigns(getLiveCampaignsTable());
//        }
//        return new PageCampaigns();
//    }

//    public PageCampaigns deleteAllDisabledCampaigns(){
//        Table.Status status = DISABLED;
//        if(isTablePresent(status)){
//            deleteAllCampaigns(getDisabledCampaignsTable(), status);
//        }
//        return new PageCampaigns();
//    }

    private void deleteAllCampaigns(Table table){
        Table.Status status = table.getStatus();
        int count = table.getAllRows().size();
        System.out.println("DEBAG: Found " + count + " campaign for deletion");
        for(int i = 0; i < count; i++){
            Table.Row row = table.getAllRows().get(0);
            String campaignName = row.name.getText();
            row.delete();
            waitDeletion();
            Log.campaignDeleted(campaignName);

            if(isTablePresent(status)) {
                table = new Table(status); //to be tested
            }
        }
    }



    private boolean isTablePresent(Table.Status status){
        try{

            new Table(status);
            return true;
        }catch (/*NoSuchElementException | TimeoutException |*/ AssertionError e){
           Log.tableIsMissed(status.toString());
           return false;
        }
    }

    private void waitDeactivation(){
        MsgCampaignDeactivated campaignDeactivated = new MsgCampaignDeactivated();
        wait.until(ExpectedConditions.invisibilityOf(campaignDeactivated.getWebElement()));
    }

    private void waitDeletion(){
        MsgCampaignDeleted msg = new MsgCampaignDeleted();
        wait.until(ExpectedConditions.invisibilityOf(msg.getWebElement()));
    }
}

