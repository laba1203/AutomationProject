package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class PopupEditPlacement extends AbstractElementsContainer{

    private Element shownOnSection = new Element(By.xpath("//*[contains(@class, 'is-edit')]//div[@class = 'Routes-form'][1]"));
    private Element hiddenOnSection = new Element(By.xpath("//*[contains(@class, 'is-edit')]//div[@class = 'Routes-form'][2]"));
    private ElmntSaveChangesButton saveChangesButton = new ElmntSaveChangesButton();

    Section inclusionSection = new Section(shownOnSection);
    Section exclusionSection = new Section(hiddenOnSection);



    PopupEditPlacement(){
//        inclusionRows = addRows(shownOnSection);
//        exclusionRows = addRows(hiddenOnSection);
    }

    PageCampaignPlacements add(boolean isInclusion, boolean regexMode, String value){
        Section section = getSection(isInclusion);
        PopupEditPlacement updatedPopup = section.add(regexMode, value);
//        if(isInclusion){
//            updatedPopup = inclusionSection.add(regexMode, value);
//        }
//        else {
//            updatedPopup = exclusionSection.add(regexMode, value);
//        }
        updatedPopup.saveChangesButton.click();

        return new PageCampaignPlacements();
    }

    PageCampaignPlacements remove(boolean isInclusion, String name){
        Section section = getSection(isInclusion);
        PopupEditPlacement updatedPopup = section.remove(name);
        updatedPopup.saveChangesButton.click();
        return new PageCampaignPlacements();
    }

    PageCampaignPlacements updateFirstPlacement(boolean isInclusion, String newValue){
            Section section = getSection(isInclusion);
            PopupEditPlacement editPlacementPopup = section.updateFirstRow(newValue);
            editPlacementPopup.saveChangesButton.click();
            return new PageCampaignPlacements();
    }

    private Section getSection(boolean isInclusion){
        if(isInclusion){
            return inclusionSection;
        }
        else {
            return exclusionSection;
        }
    }






//    private ArrayList<PlacementRow> getRows(boolean isInclusion){
//        if(isInclusion){
//            return inclusionRows;
//        }
//        else {
//            return exclusionRows;
//        }
//    }





//    private ArrayList<Section.PlacementRow> addRows(Element parentSection){
//        ArrayList<Section.PlacementRow> rows = new ArrayList<>();
//        List<WebElement> webElements = parentSection.getWebElement().findElements(By.xpath(".//*[@type = 'checkbox']/../../.."));
//        for (WebElement webElement :
//                webElements) {
//            rows.add(new Section.PlacementRow(webElement));
//        }
//        return rows;
//    }






}
