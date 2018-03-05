package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PopupEditPlacement extends AbstractElementsContainer{

    ElmntAddInclusionUrl addInclusionUrlButton = new ElmntAddInclusionUrl();
    ElmntAddExclusionUrl addExclusionUrlButton = new ElmntAddExclusionUrl();
    Element shownOnSection = new Element(By.xpath("//*[contains(@class, 'is-edit')]//div[@class = 'Routes-form'][1]"));
    Element hiddenOnSection = new Element(By.xpath("//*[contains(@class, 'is-edit')]//div[@class = 'Routes-form'][2]"));


    private ArrayList<Element> getRows(Element parentSection){
        ArrayList<Element> rows = new ArrayList<>();
        List<WebElement> webElements = parentSection.getWebElement().findElements(By.xpath(".//*[@type = 'checkbox']/../../.."));
        for (WebElement webElement :
                webElements) {
            rows.add(new Element(webElement));
        }
        return rows;
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
