package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.common.CampaignPlacement;
import talkable.common.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class PlacementTile extends AbstractElementsContainer{


    private Element tileElmnt;
    private Element header;
    private static final String showOnXpath = ".//*[contains(@class, 'is-on')]/following-sibling::div//div[contains(@class, 'Routes-form-row')]";
    private static final String hiddenOnXpath = ".//*[contains(@class, 'is-off')]/following-sibling::div//div[contains(@class, 'Routes-form-row')]";
    private ArrayList<PlacementRowElement> shownOn;
    private ArrayList<PlacementRowElement> hiddenOn;
    private Element actionButton;

    PlacementTile(CampaignPlacement placement){
        header = new Element(driver.findElement(By.xpath("//h1[contains(text(), '" + getPlacementName(placement) + "')]")));

        tileElmnt = new Element(header.getWebElement().findElement(By.xpath("./../../..")));
        actionButton = new Element(tileElmnt.getWebElement().findElement(By.xpath(".//*[contains(@data-toggle, 'dropdown')]")));
        shownOn = setList(showOnXpath);
        hiddenOn = setList(hiddenOnXpath);
    }

    public ArrayList<String> getHiddenOnList() {
        return getPlacementsList(hiddenOn);
    }

    public ArrayList<String> getShownOnList() {
        return getPlacementsList(shownOn);
    }

    private ArrayList<String> getPlacementsList(ArrayList<PlacementRowElement> placementsList){
        ArrayList<String> out = new ArrayList<>();
        for (PlacementRowElement row :
                placementsList) {
            out.add(row.getText());
        }
        return out;
    }

    public PageCampaignPlacements addExclusion(boolean regex, String exclusionText){
        return edit().add(false, regex, exclusionText);
    }

    public PageCampaignPlacements addInclusion(boolean regex, String inclusionText){
        return edit().add(true, regex, inclusionText);
    }

    public PageCampaignPlacements removePlacement(boolean isInclusion, String name){
        return edit().remove(isInclusion, name);
    }

    public PageCampaignPlacements updateFirstPlacement(boolean isInclusion, String newValue){
        return edit().updateFirstPlacement(isInclusion, newValue);
    }

    private PopupEditPlacement edit(){
        actionButton.click();
        new ActionMenu(tileElmnt).editBtn.click();
        return new PopupEditPlacement();
    }



    public PageCampaignPlacements deleteTile(){
        actionButton.click();
        new ActionMenu(tileElmnt).deleteTile();
        new PageCampaignPlacements();

        return new PageCampaignPlacements();
    }


    private ArrayList<PlacementRowElement> setList(String xpath){
        ArrayList<PlacementRowElement> elements = new ArrayList<>();
        List<WebElement> webElements = tileElmnt.getWebElement().findElements(By.xpath(xpath));
        for (WebElement webElement :
                    webElements) {
                elements.add(new PlacementRowElement(webElement));
        }
        return elements;
    }


    private String getPlacementName(CampaignPlacement placement)
    {
        return CommonMethods.getCampaignPlacementString(placement);
    }


}
