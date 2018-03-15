package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SiteSelect extends AbstractSelectElement{

//    private static final By locator = By.className("js-sites-select");
    private static final String xpathToSelect = "//select[contains(@class,'js-sites-select')]";
    private WebElement selectWebElement = getDriver().findElement(By.xpath("//select[contains(@class,'js-sites-select')]"));

    public SiteSelect(){
//        setWebElement(xpathToSelect);
        setWebElement(selectWebElement);
    }


}
