package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AbTestTileInEditor extends AbstractElementsContainer{

    private static final By statusLctr = By.xpath(".//div[contains(@class, 'ab-status')]//mark");
    private By ownLocator;
    private SimpleEditorPage.LocalizationType mode;

    public AbTestTileInEditor(By locator, SimpleEditorPage.LocalizationType mode){
        ownLocator = locator;
        this.mode = mode;
    }

    private WebElement getParentElement(){
        return new Element(ownLocator).getWebElement();
    }

    public String getStatus(){
        return new Element(getParentElement(), statusLctr, "Status")
                .getText();
    }


}
