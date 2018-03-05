package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class Section extends AbstractElementsContainer
{
    private Element sectionElement;
    ArrayList<PlacementRow> rows;
    Element addButton;


    Section(Element sectionElement){
        this.sectionElement = sectionElement;
        rows = addRows(sectionElement);
        addButton = new Element(sectionElement.getWebElement().findElement(By.xpath(".//*[@class = 'Routes-form-new']/span")));

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

    PopupEditPlacement add(boolean regexMode, String value){
        addButton.click();

        rows = addRows(sectionElement);

        int lastRow = rows.size() - 1;
        PlacementRow newRow = rows.get(lastRow);
        if(regexMode){
            newRow.regexCheckbox.click();
        }
        newRow.textInput.clear();
        newRow.textInput.sendKeys(value);

        return new PopupEditPlacement();
    }


    class PlacementRow
    {
        Element regexCheckbox;
        Element textInput;

        PlacementRow(WebElement rowElement){
            regexCheckbox = new Element(rowElement.findElement(By.xpath(".//input[@type = 'checkbox']")));
            textInput = new Element(rowElement.findElement(By.xpath(".//input[@type = 'text']")));

        }


    }

}
