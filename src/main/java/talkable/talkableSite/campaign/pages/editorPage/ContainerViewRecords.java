package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ContainerViewRecords extends AbstractElementsContainer{

    private static final By containerElmntLctr = By.xpath("//ul[contains(@class, 'ac-editor-widget-navigation')]");
    private static final By recordsLctr = By.xpath("./div/li");
    private static final By createNewViewLctr = By.xpath("//a[contains(@class, 'create-new-view')]");

    private Element containerElement = new Element(containerElmntLctr);
    private ArrayList<ElmntViewRecord> records = new ArrayList<>();


    ContainerViewRecords(){
        setRecords();
    }

    private void setRecords(){
        List<WebElement> webElements = containerElement.getWebElement().findElements(recordsLctr);
        for (WebElement webElement :
                webElements) {
            ElmntViewRecord viewRecord = new ElmntViewRecord(new Element(webElement));
            records.add(viewRecord);
        }
    }

    public void selectViewByText(String viewName){
        Objects.requireNonNull(findViewRecord(viewName)).viewName.click();
    }

//    public void selectByIndex(int index){
//        records.get(index - 1).viewName.click();
//    }


    ElmntViewRecord findViewRecord(String name){
        for (ElmntViewRecord record : records) {
            if(record.viewName.getText().equals(name)){
                return  record;
            }
        }
        Assert.fail("FAILED: There is no view records with name : <" + name + ">");
        return null;
    }

    boolean isViewPresent(String name){
        for (ElmntViewRecord record : records) {
            if(record.viewName.getText().equals(name)){
                return  true;
            }
        }
        return false;
    }

    PopupNewViewWarning clickCreateNewView(){
        new Element(createNewViewLctr, "Create New View Btn").click();
        return new PopupNewViewWarning();
    }


    class ElmntViewRecord extends AbstractElementsContainer{

        private By viewNameLctr = By.xpath(".//span[contains(@class, 'editor-menu-text')]");
        private By isVisibleLctr = By.xpath(".//input[contains(@type, 'checkbox')]");
        private By deleteIconLctr = By.xpath(".//a[@data-method='delete']");

        private Element rowElement;
        private Element viewName;

        ElmntViewRecord(Element rowElement){
            this.rowElement = rowElement;
            viewName = new Element(viewNameLctr);
        }

        void clickIsVisible(){
            new Element(
                    rowElement.getWebElement().findElement(isVisibleLctr))
                    .click();
        }

        void delete(){
            new Element(rowElement, deleteIconLctr);
        }


    }



}
