package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import util.logging.Log;

public abstract class AbstractSelectElement extends AbstractElement{

    protected Select dropdown;


    @Override
    public void setWebElement(By locator) {
        super.setWebElement(locator);
        dropdown = new Select(super.getWebElement());
    }

    public void selectByVisibleText(String text){
        dropdown.selectByVisibleText(text);
        Log.selectFromDropDownLogMsg(text, dropdown);
    }

    public void selectByValue(String value){
        dropdown.selectByValue(value);
        Log.selectFromDropDownLogMsg(value, dropdown);
    }

    public void selectByIndex(int index){
        dropdown.selectByIndex(index);
        Log.selectFromDropDownLogMsg(String.valueOf(index), dropdown);
    }




}
