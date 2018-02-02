package talkable.headerFrame.elements;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import util.logging.Log;

public class SiteSelect extends AbstractSelectElement{

    private static final By locator = By.className("js-sites-select");

    public SiteSelect(){
        setWebElement(locator);
    }

//    @Override
//    public void selectByValue(String value){
//        dropdown.selectByValue(value);
//        Log.selectFromDropDownLogMsg(value, dropdown);
//    }

}
