package talkable.talkableSite.campaignsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import talkable.common.CommonMethods;

class ElmntNewCampaignDropdownFields extends AbstractElement{

    ElmntNewCampaignDropdownFields(CampaignType type, CampaignPlacement placement){
        String xpath = "//input[contains(@value, '"+ CommonMethods.getCampaignTypeString(type) + " " + CommonMethods.getCampaignPlacementString(placement) + "')]";
        setWebElement(By.xpath(xpath));
    }
}
