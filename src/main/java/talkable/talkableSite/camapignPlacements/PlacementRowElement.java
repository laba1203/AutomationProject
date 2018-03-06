package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElement;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Util;

public class PlacementRowElement extends Element{

    Element relatedLabel;


    public PlacementRowElement(WebElement webElement) {
        super(webElement);
        relatedLabel = new Element(webElement.findElement(By.xpath("./text()/following-sibling::span[1]")));
    }

    @Override
    public String getText() {
        String initialText = super.getText();
        String labelText = relatedLabel.getText();

        return Util.getDifference(initialText, labelText);
    }
}
