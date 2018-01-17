package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import util.logging.Log;

public class AbstractSelectElement extends AbstractElement{

    Select dropdown;
    Log log = new Log();

    @Override
    public void setWebElement(By locator) {
        super.setWebElement(locator);
        dropdown = new Select(super.getWebElement());
    }

    public void selectByVisibleText(String text){
        dropdown.selectByVisibleText(text);
        log.selectFromDropDownLogMsg(text, dropdown);
    }

    public void selectByValue(String value){
        dropdown.selectByValue(value);
        log.selectFromDropDownLogMsg(value, dropdown);
    }

    public void selectByIndex(int index){
        dropdown.selectByIndex(index);
        log.selectFromDropDownLogMsg(String.valueOf(index), dropdown);
    }




}
