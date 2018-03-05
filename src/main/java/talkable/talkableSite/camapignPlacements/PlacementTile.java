package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.CampaignPlacement;

import java.util.ArrayList;
import java.util.List;

public class PlacementTile extends AbstractElementsContainer{

    private Element tileElmnt;
    private Element header;
    private ArrayList<Element> shownOn;
    private ArrayList<Element> hiddenOn;
    private Element actionButton;

    PlacementTile(CampaignPlacement placement){

        header = new Element(driver.findElement(By.xpath("//h1[contains(text(), '" + getPlacementName(placement) + "')]")));

        tileElmnt = new Element(header.getWebElement().findElement(By.xpath("./../../..")));
        actionButton = new Element(tileElmnt.getWebElement().findElement(By.xpath(".//*[contains(@data-toggle, 'dropdown')]")));
        shownOn = setList(".//*[contains(@class, 'is-on')]/following-sibling::div//div[contains(@class, 'Routes-form-row')]");
        hiddenOn = setList(".//*[contains(@class, 'is-off')]/following-sibling::div//div[contains(@class, 'Routes-form-row')]");

    }

    public ArrayList<Element> getShownOn() {
        return shownOn;
    }

    public ArrayList<Element> getHiddenOn() {
        return hiddenOn;
    }

//    PageCampaignPlacements addInclusion(boolean isInclusion, boolean regex, String text){
//        isI
//
//    }


    public PopupEditPlacement edit(){
        actionButton.click();
        new ActionMenu(tileElmnt).editBtn.click();
        return new PopupEditPlacement();
    }

    public PageCampaignPlacements delete(){
        actionButton.click();
        new ActionMenu(tileElmnt).deleteBtn.click();
        new PageCampaignPlacements().waitSaving();

        return new PageCampaignPlacements();
    }



    private ArrayList<Element> setList(String xpath){
        ArrayList<Element> elements = new ArrayList<>();
        List<WebElement> webElements = tileElmnt.getWebElement().findElements(By.xpath(xpath));

        for (WebElement webElement :
                webElements) {
            elements.add(new Element(webElement));
        }
        return elements;
    }


    private String getPlacementName(CampaignPlacement placement)
    {
        String placementName;
        switch (placement){
            case PostPurchase:
                placementName = "Post Purchase";
                break;
            case Standalone:
                placementName = "Standalone";
                break;
            case Gleam:
                placementName = "Gleam";
                break;
            case FloatingWidget:
                placementName = "Floating Widget";
                break;
            default:
                placementName = null;
                Assert.fail("FAILED: Unknown placement type: " + placement.toString());
        }
        return placementName;

    }


}
