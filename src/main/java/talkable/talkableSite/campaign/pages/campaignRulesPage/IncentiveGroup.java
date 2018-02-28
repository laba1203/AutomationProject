package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

class IncentiveGroup extends AbstractElementsContainer{
    private WebElement groupContainer;
    private ArrayList<IncentiveTile> incentives = new ArrayList<>();
    private PageCampaignRules.IncentiveType incentiveType;

    IncentiveGroup(PageCampaignRules.IncentiveType incentiveType) {
        this.incentiveType = incentiveType;
        groupContainer = driver.findElement(By.xpath(".//*[text()='" + PageCampaignRules.getIncentiveTypeName(incentiveType) + "']/following-sibling::div"));
        setIncentiveTilesList();

    }



    private ArrayList<IncentiveTile> setIncentiveTilesList()
    {
        List<WebElement> webElements = groupContainer.findElements(By.xpath(".//div[@class = 'incentive-container']"));
        for (WebElement webElement :
                webElements) {
            incentives.add(new IncentiveTile(webElement, incentiveType));
        }
        return incentives;
    }


    public IncentiveTile getIncentive(String value){
        for (IncentiveTile incentive :
                incentives) {
            if(value.equals(incentive.getValue())){
                return incentive;
            }
        }
        Assert.fail("Incentive with value: <" + value + "> is not found");
        return null;
    }




}
