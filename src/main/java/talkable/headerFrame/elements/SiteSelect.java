package talkable.headerFrame.elements;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import util.logging.Log;

public class SiteSelect extends AbstractSelectElement{

//    private static final By locator = By.className("js-sites-select");
    private static final String xpathToSelect = "//select[contains(@class,'js-sites-select')]";

    public SiteSelect(){
        setWebElement(xpathToSelect);
    }

//    @Override
//    public void selectByValue(String value){
//        dropdown.selectByValue(value);
//        Log.selectFromDropDownLogMsg(value, dropdown);
//    }

}
