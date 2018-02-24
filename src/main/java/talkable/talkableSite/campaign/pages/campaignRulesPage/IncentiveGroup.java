package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class IncentiveGroup extends AbstractElementsContainer{
    private WebElement groupContainer;
    private ArrayList<IncentiveTile> incentives = new ArrayList<>();

    IncentiveGroup(String incentiveType) {
        groupContainer = driver.findElement(By.xpath(".//*[text()='" + incentiveType + "']/following-sibling::div"));
        setIncentiveTilesList();

    }



    private ArrayList<IncentiveTile> setIncentiveTilesList(){
//        ArrayList<IncentiveTile> incentives = new ArrayList<>();

        List<WebElement> webElements = groupContainer.findElements(By.xpath(".//div[@class = 'incentive-container']"));
        for (WebElement webElement :
                webElements) {
            incentives.add(new IncentiveTile(webElement));
        }
        return incentives;
    }


    //To be completed
    public IncentiveTile getIncentive(String value, String couponCode){
        for (IncentiveTile incentive :
                incentives) {
            
        }

        return null;

    }


}
