package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.recordViewContainer.ViewRecord;

import java.util.ArrayList;

public class ViewRecordsContainer extends AbstractElement{

    private String locator = ".ac-editor-widget-navigation.js-editor-widget-shown > div > li";
    private ArrayList<ViewRecord> records;

    public ViewRecordsContainer(){
        setWebElement(By.cssSelector(locator));
        records = getViewRecords(By.cssSelector(locator));
    }

    private ArrayList<ViewRecord> getViewRecords(By locator){
        int count = getWebElements(locator).size();
        ArrayList<ViewRecord> records = new ArrayList<>();

        for (int i = 1; i <= count; i++){
            records.add(new ViewRecord().getRecordByIndex(i));
        }
        return records;
    }

    public void selectViewByIndex(int index){
        records.get(index).click();
    }

}
