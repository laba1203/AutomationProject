package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.recordViewContainer.RecordViewContainer;

import java.util.ArrayList;

public class ViewRecordsContainer extends AbstractElement{

    private String locator = ".ac-editor-widget-navigation.js-editor-widget-shown li";
    private ArrayList<RecordViewContainer> records;

    public ViewRecordsContainer(){
        setWebElement(By.cssSelector(locator));
        records = getViewRecords(By.cssSelector(locator));
    }

    private ArrayList<RecordViewContainer> getViewRecords(By locator){
        int count = getWebElements(locator).size();
        ArrayList<RecordViewContainer> records = new ArrayList<>();

        for (int i = 1; i <= count; i++){
            records.add(new RecordViewContainer().getRecordByIndex(i));
        }
        return records;
    }

    public void selectViewByIndex(int index){
        records.get(index).click();
    }

    //Not work:
    public void selectViewByName(String name){
        new RecordViewContainer().selectViewByLinkText(name);
    }

}
