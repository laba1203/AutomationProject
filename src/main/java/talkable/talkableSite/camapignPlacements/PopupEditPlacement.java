package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.common.CampaignPlacement;

class PopupEditPlacement extends AbstractElementsContainer{

    private Element shownOnSection = new Element(By.xpath("//*[contains(@class, 'is-editMandatoryFields')]//div[@class = 'Routes-form'][1]"));
    private Element hiddenOnSection = new Element(By.xpath("//*[contains(@class, 'is-editMandatoryFields')]//div[@class = 'Routes-form'][2]"));
    private ElmntSaveChangesButton saveChangesButton = new ElmntSaveChangesButton();

    private Section inclusionSection = new Section(shownOnSection);
    private Section exclusionSection = new Section(hiddenOnSection);



    PopupEditPlacement(){
        new ElmntPlacementName();
    }

    PageCampaignPlacements add(boolean isInclusion, boolean regexMode, String value){
        Section section = getSection(isInclusion);
        PopupEditPlacement updatedPopup = section.add(regexMode, value);
        updatedPopup.saveChangesButton.click();

        return new PageCampaignPlacements();
    }

    PageCampaignPlacements removeAndSave(boolean isInclusion, String name){
        PopupEditPlacement updatedPopup = removePlacement(isInclusion, name);
        updatedPopup.saveChangesButton.click();
        return new PageCampaignPlacements();
    }


    PopupEditPlacement removePlacement(boolean isInclusion, String name){
        Section section = getSection(isInclusion);
        return section.remove(name);
    }

    PageCampaignPlacements updateFirstPlacement(boolean isInclusion, String newValue){
            Section section = getSection(isInclusion);
            PopupEditPlacement editPlacementPopup = section.updateFirstRow(newValue);
            editPlacementPopup.saveChangesButton.click();
            return new PageCampaignPlacements();
    }

    public PageCampaignPlacements deleteAllPlacements(){
        getSection(true).removeAll(true);
        getSection(false).removeAll(false);
        saveChangesButton.click();
        return new PageCampaignPlacements();
    }

//    void changePlacementName(String newName){
//        ElmntPlacementName placementName = new ElmntPlacementName();
//        placementName.clear();
//        placementName.sendKeys(newName);
//    }

    private Section getSection(boolean isInclusion){
        if(isInclusion){
            return new Section(shownOnSection);
        }
        else {
            return new Section(hiddenOnSection);
        }
    }



}
