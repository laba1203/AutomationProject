package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class ElmntViewRecord extends AbstractElementsContainer{

    Element viewName;
    Element isVisible;
    Element rowElement;

    //.ac-editor-widget-navigation.js-editor-widget-shown span.editor-menu-text

    ElmntViewRecord(Element rowElement){
        this.rowElement = rowElement;
        viewName = new Element(rowElement.getWebElement().findElement(By.xpath(".//span[contains(@class, 'editor-menu-text')]")));
        isVisible = new Element(rowElement.getWebElement().findElement(By.xpath(".//input[contains(@type, 'checkbox')]")));
    }


}
