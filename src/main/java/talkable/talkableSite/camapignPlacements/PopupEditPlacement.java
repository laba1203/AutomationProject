package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class PopupEditPlacement extends AbstractElementsContainer{

    ElmntAddInclusionUrl addInclusionUrlButton = new ElmntAddInclusionUrl();
    ElmntAddExclusionUrl addExclusionUrlButton = new ElmntAddExclusionUrl();
    ElmntSaveChangesButton saveChangesButton = new ElmntSaveChangesButton();
    Element shownOnSection = new Element(By.xpath("//*[contains(@class, 'is-edit')]//div[@class = 'Routes-form'][1]"));
    Element hiddenOnSection = new Element(By.xpath("//*[contains(@class, 'is-edit')]//div[@class = 'Routes-form'][2]"));
    ArrayList<PlacementRow> inclusionRows;
    ArrayList<PlacementRow> exclusionRows;



    PopupEditPlacement(){
        inclusionRows = addRows(shownOnSection);
        exclusionRows = addRows(hiddenOnSection);
    }

    PageCampaignPlacements add(boolean isInclusion, boolean regex, String value){

       // to be reworked:
        if(isInclusion) {
            addInclusionUrlButton.click();
        }
        else {
            addExclusionUrlButton.click();
        }
        ///
        ArrayList<PlacementRow> rows = getRows(isInclusion);

        int lastRow = rows.size() - 1;
        PlacementRow newRow = rows.get(lastRow);
        if(regex == true){
            newRow.regexCheckbox.click();
        }
        newRow.textInput.clear();
        newRow.textInput.sendKeys(value);
        saveChangesButton.click();
        new PageCampaignPlacements().waitSaving();

        return new PageCampaignPlacements();
    }

    private ArrayList<PlacementRow> getRows(boolean isInclusion){
        if(isInclusion){
            return inclusionRows;
        }
        else {
            return exclusionRows;
        }
    }





    private ArrayList<PlacementRow> addRows(Element parentSection){
        ArrayList<PlacementRow> rows = new ArrayList<>();
        List<WebElement> webElements = parentSection.getWebElement().findElements(By.xpath(".//*[@type = 'checkbox']/../../.."));
        for (WebElement webElement :
                webElements) {
            rows.add(new PlacementRow(webElement));
        }
        return rows;
    }


    class PlacementRow
    {
        Element regexCheckbox;
        Element textInput;
        Element addNewButton;

        PlacementRow(WebElement rowElement){
            regexCheckbox = new Element(rowElement.findElement(By.xpath(".//input[@type = 'checkbox']")));
            textInput = new Element(rowElement.findElement(By.xpath(".//input[@type = 'text']")));

        }


    }

}
