package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class PopupEditPlacement extends AbstractElementsContainer{

    private Element shownOnSection = new Element(By.xpath("//*[contains(@class, 'is-editMandatoryFields')]//div[@class = 'Routes-form'][1]"));
    private Element hiddenOnSection = new Element(By.xpath("//*[contains(@class, 'is-editMandatoryFields')]//div[@class = 'Routes-form'][2]"));
    private ElmntSaveChangesButton saveChangesButton = new ElmntSaveChangesButton();

    private Section inclusionSection = new Section(shownOnSection);
    private Section exclusionSection = new Section(hiddenOnSection);



    PopupEditPlacement(){

    }

    PageCampaignPlacements add(boolean isInclusion, boolean regexMode, String value){
        Section section = getSection(isInclusion);
        PopupEditPlacement updatedPopup = section.add(regexMode, value);
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

}
