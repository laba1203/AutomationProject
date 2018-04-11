package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class ActionMenu extends AbstractElementsContainer{

    Element parentElement;
    Element editBtn;
//    Element deleteBtn;

    ActionMenu(Element parentTileElement){
       this.parentElement = parentTileElement;
       editBtn = new Element(parentTileElement.getWebElement().findElement(By.xpath(".//*[text() = 'Edit']")));
//       deleteBtn = new Element(parentTileElement.getWebElement().findElement(By.xpath(".//*[text() = 'Delete']")));
    }

    void deleteTile(){
        new Element(parentElement.getWebElement().findElement(By.xpath(".//*[text() = 'Delete']")))
                .click();
    }
}
