package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
//import abstractObjects.Element;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class IncentiveTile extends AbstractElementsContainer {

    private PageCampaignRules.IncentiveType incentiveType;
    private WebElement containerElement;

    private Element valueField;
//    private Element couponCodeField;
    private Element identifierField;
    private Element editButton;
    private Element deleteButton;


//    private String value;
//    private String couponCode;
//    private String identifier;
//    private ArrayList<String> rewardCriteria;



    IncentiveTile(WebElement incentiveContainerElement, PageCampaignRules.IncentiveType incentiveType){
        this.incentiveType = incentiveType;
        containerElement = incentiveContainerElement;

        valueField = new Element(incentiveContainerElement.findElement(By.xpath(".//*[text()='Value:']/following-sibling::div")));
        identifierField = new Element(incentiveContainerElement.findElement(By.xpath(".//*[text()='Identifier:']/following-sibling::div")));
        editButton = new Element(incentiveContainerElement.findElement(By.xpath(".//a[text()='Edit']")));
    }

    public String getValue(){
        return valueField.getText();
    }

    public String getIdentifier(){
        return identifierField.getText();
    }

    public PopupIncentiveFactory edit(){
        editButton.click();
        return PageCampaignRules.getIncentivePopup(incentiveType);
    }

    public PageCampaignRules delete(){
        try{
            deleteButton = new Element(containerElement.findElement(By.xpath(".//*[text()='Delete']")));
        }
        catch (NoSuchElementException e){
            System.out.println("FAILED: " + e.getMessage());
            Assert.fail("FAILED Assert:" + e.getMessage());
        }

        deleteButton.click();
        PopupWarning warningPopup = new PopupWarning();

        return warningPopup.ok();

    }



}
