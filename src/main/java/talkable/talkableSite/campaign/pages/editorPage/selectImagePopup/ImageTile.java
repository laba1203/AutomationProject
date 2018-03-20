package talkable.talkableSite.campaign.pages.editorPage.selectImagePopup;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class ImageTile extends AbstractElementsContainer{

//    private Element parent;
    private Element select;
    private Element name;

    ImageTile(Element parent){
//        this.parent = parent;
        setElements(parent.getWebElement());
    }

    private void setElements(WebElement parent){
        select = new Element(parent.findElement(By.xpath(".//div[contains(@class, 'is-select ')]")));
        name = new Element(parent.findElement(By.xpath(".//div[contains(@class, 'item-image')]/following-sibling::div[contains(@class, 'list-item-info')][1]")));
    }

    String getName(){
        return name.getText();
    }

    void select(){
        select.click();
    }

}
