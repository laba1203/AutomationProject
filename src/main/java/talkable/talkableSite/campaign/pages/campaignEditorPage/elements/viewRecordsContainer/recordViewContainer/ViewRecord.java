package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.recordViewContainer;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ViewRecord extends AbstractElement{

    private static final String parentLocator = ".ac-editor-widget-navigation.js-editor-widget-shown";

//    private static final String viewNameLocator = ".editor-menu-text";
    private static final String viewNameLocator = ">div>.editor-menu-text";

    private static final String viewVisibleLocator = ".editor-menu-state";

    ViewName nameColumn;
    ViewVisible visibleColumn;

    public ViewRecord(){

    }

    public ViewRecord getRecordByIndex(int index){
        String recordLocator = parentLocator + ">li:nth-of-type(" + index + ")";
        setWebElement(By.cssSelector(recordLocator));

        //To improve:
//        nameColumn = new ViewName(By.cssSelector(recordLocator + viewNameLocator));
//        visibleColumn = new ViewVisible(By.cssSelector(recordLocator + " " + viewVisibleLocator));
        return this;
    }


    public void switchVisibleCheckbox(){
        if(visibleColumn == null){
            Assert.fail(visibleColumn.getClass().getName() + " is null");
        }
        visibleColumn.click();
    }




}
