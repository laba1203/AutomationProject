package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class SiteSelect extends AbstractSelectElement{

    private static final String xpathToSelect = "//select[contains(@class,'js-sites-select')]";
//    private WebElement selectWebElement = getDriver().findElement(By.xpath("//select[contains(@class,'js-sites-select')]"));
    private WebElement selectWebElement = getDriver().findElement(By.xpath(xpathToSelect));

    public SiteSelect(){
        try {
            initializeWebElement();
            setWebElement(selectWebElement);
        }catch (StaleElementReferenceException e){
             initializeWebElement();
             setWebElement(selectWebElement);
        }
    }

    private void initializeWebElement(){
        selectWebElement = getDriver().findElement(By.xpath(xpathToSelect));
    }


}
