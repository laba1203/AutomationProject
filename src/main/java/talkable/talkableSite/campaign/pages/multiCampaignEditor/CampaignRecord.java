package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class CampaignRecord extends AbstractElementsContainer{
    private Element name;
    private Element id;
    private Element checkbox;
    private Element status;
    private boolean eligible;

    public CampaignRecord(WebElement element, boolean eligible){
        this.eligible = eligible;
        setElements(element);
    }

    private void setElements(WebElement element){
        name = new Element(element.findElement(By.xpath(".//a")));
        id = new Element(element.findElement(By.xpath(".//div[@class = 'campaignfilter-campaign-id']")));
        status = new Element(element.findElement(By.xpath(".//i")));
        if(eligible){
            checkbox = new Element(element.findElement(By.xpath(".//input/..")));
        }
    }

    public void select(){
        checkEligibility();
        checkbox.click();
    }

    private void checkEligibility(){
        if(!eligible){
            Assert.fail("FAILED: Unable to select ineligible campaign");
        }
    }

    public String getName(){
        return name.getText();
    }

    public String getId(){
        return id.getText().substring(4);
    }

    public String getStatus(){
        return status.getText();
    }
}
