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
    private static final String campaignRecordXpath = "./li[@class = 'is-edit']";
    private static final By countLctr = By.xpath(".//h4/span/span");

    private WebElement ownElement;
    private boolean eligible;


    enum State {SELECTED, UNSELECTED, INELIGIBLE}

    CampaignsList(State state){
        ownElement = getWebElement(state);
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
        return new Element(ownElement.findElement(countLctr)).getText();
    }


    private boolean countIsNotZero(){
        return !getCampaignCount().equals("0");
    }

    private ArrayList<CampaignRecord> getCampaignsList(){
        ArrayList<CampaignRecord> cRecords = new ArrayList<>();
        if(countIsNotZero()) {
            List<WebElement> records = ownElement.findElements(By.xpath(campaignRecordXpath));
            for (WebElement el :
                    records) {
                cRecords.add(new CampaignRecord(el, eligible));
            }
        }
        return cRecords;
    }

    CampaignRecord findCampaign(String campaignName){
        ArrayList<String> availableCampaigns = new ArrayList<>();
        for (CampaignRecord campaign :
                getCampaignsList()) {
            String cName = campaign.getName();
            availableCampaigns.add(cName);
            if (cName.equals(campaignName)){
                return campaign;
            }
        }
        Assert.fail("FAILED: Campaign with name: <" + campaignName + "> is not found. List of available campaigns: \n" + availableCampaigns.toString());
        return null;
    }



}
