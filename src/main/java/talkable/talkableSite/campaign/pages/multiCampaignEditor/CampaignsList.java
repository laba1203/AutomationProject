package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;

public class CampaignsList extends AbstractElementsContainer
{
    private static final String selectedCampaignsXpath = "//strong[contains(text(), 'Selected campaigns')]/../../../..";
    private static final String unselectedCampaignsXpath = "//strong[contains(text(), 'Unselected campaigns')]/../../../..";
    private static final String ineligibleCampaignsXpath = "//strong[contains(text(), 'Ineligible campaigns')]/../../../..";
    private static final String campaignRecordXpath = "./li[@class = 'is-editMandatoryFields']";

    private WebElement ownElement;
    private ArrayList<CampaignRecord> campaigns = new ArrayList<>();
    private boolean eligible;
    private Element count;


    enum State {SELECTED, UNSELECTED, INELIGIBLE}

    public CampaignsList(State state){
        ownElement = getWebElement(state);
        count = new Element(ownElement.findElement(By.xpath(".//h4/span/span")));
        setCampaignRecords();
    }

    private WebElement getWebElement(State state){
        switch (state){
            case SELECTED:
                eligible = true;
                return driver.findElement(By.xpath(selectedCampaignsXpath));
            case INELIGIBLE:
                eligible = false;
                return driver.findElement(By.xpath(ineligibleCampaignsXpath));
            case UNSELECTED:
                eligible = true;
                return driver.findElement(By.xpath(unselectedCampaignsXpath));
            default:
                Assert.fail("FAILED: Unknown campaign state: <" + state.toString() + ">");
                return null;
        }
    }

    public String getCampaignCount(){
        return count.getText();
    }

    private void setCampaignRecords(){
        if(!isCountZero()) {
            List<WebElement> records = ownElement.findElements(By.xpath(campaignRecordXpath));
            for (WebElement el :
                    records) {
                campaigns.add(new CampaignRecord(el, eligible));
            }
        }
    }

    private boolean isCountZero(){
        return count.getText().equals("0");
    }

    public ArrayList<CampaignRecord> getCampaignsList(){
        return campaigns;
    }

    CampaignRecord findCampaign(String campaignName){
        for (CampaignRecord campaign :
                campaigns) {
            if (campaign.getName().equals(campaignName)){
                return campaign;
            }
        }
        Assert.fail("FAILED: Campaign with name: <" + campaignName + "> is not found");
        return null;
    }



}
