package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import util.WaitFactory;
import util.logging.Log;

import java.util.ArrayList;
import java.util.List;

class Section extends AbstractElementsContainer
{

    private Element sectionElement;
    ArrayList<PlacementRow> rows;
    Element addButton;


    Section(Element sectionElement){
        this.sectionElement = sectionElement;
        rows = getRows(sectionElement);
        addButton = new Element(sectionElement.getWebElement().findElement(By.xpath(".//*[@class = 'Routes-form-new']/span")));

    }

    private ArrayList<PlacementRow> getRows(Element parentSection){
        ArrayList<PlacementRow> rows = new ArrayList<>();
        List<WebElement> webElements = WaitFactory
                .waitChildElementsForElement(parentSection, By.xpath(".//*[@type = 'checkbox']/../../.."));
        //        List<WebElement> webElements = parentSection.getWebElement().findElements(By.xpath(".//*[@type = 'checkbox']/../../.."));
        for (WebElement webElement :
                webElements) {
            rows.add(new PlacementRow(webElement));
        }
        return rows;
    }

    PopupEditPlacement add(boolean regexMode, String value){
        addButton.click();

        rows = getRows(sectionElement);

        int lastRow = rows.size() - 1;
        PlacementRow newRow = rows.get(lastRow);
        if(regexMode){
            newRow.regexCheckbox.click();
        }
        newRow.textInput.clear();
        newRow.textInput.sendKeys(value);

        return new PopupEditPlacement();
    }

    PopupEditPlacement updateFirstRow(String newValue){
        PlacementRow row = rows.get(0);
        row.textInput.clear();
        row.textInput.sendKeys(newValue);
        return new PopupEditPlacement();
    }

    PopupEditPlacement remove(String name){
        findPlacementRow(name).delete();
        return new PopupEditPlacement();
    }

    //doesn't work correctly
    PopupEditPlacement removeAll(boolean isInclusion){
        ArrayList<PlacementRow> rows = getRows();

        if (isInclusion){
//            for (int i = 0; i < rows.size() - 1; i++){
//                rows.get(i).delete();
//            }
            int size = getRows().size();
            while (size > 1){
                rows.get(0).delete();
                rows = getRows();
                size = rows.size();
            }
        }else{
            int size = getRows().size();
            while (size > 0){
                rows.get(0).delete();
                rows = getRows();
                size = rows.size();
            }
        }
        return new PopupEditPlacement();

    }




    private ArrayList<PlacementRow> getRows(){
        return getRows(sectionElement);
    }

    PlacementRow findPlacementRow(String name){
        for (PlacementRow row :
                rows) {
            if (row.textInput.getAttribute("value").equals(name)) {
                return row;
            }
        }
        Assert.fail("FAILED: FirstRow with placement text <" + name + "> is not found");
        return null;
    }


    class PlacementRow
    {
        WebElement rowElement;
        Element regexCheckbox;
        Element textInput;


        PlacementRow(WebElement rowElement){
            this.rowElement = rowElement;
            regexCheckbox = new Element(rowElement.findElement(By.xpath(".//input[@type = 'checkbox']/..")));
            textInput = new Element(rowElement.findElement(By.xpath(".//input[@type = 'text']")));
        }

        void delete(){
            new Element(rowElement
                    .findElement(By.xpath(".//*[contains(@data-balloon, 'Delete this rule')]")))
                    .click();
            Log.debagRecord("FirstRow deleted");
        }


    }

}
