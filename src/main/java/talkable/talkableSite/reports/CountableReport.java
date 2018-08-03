package talkable.talkableSite.reports;

import abstractObjects.Element;
import org.openqa.selenium.By;

public interface CountableReport {
    By totalLctr = By.xpath("//*[@class='base-form-label']");

    default String getTotalCount(){
        String value = new Element(totalLctr).getText();
        if(value.equals("Not found")){
            return "0";
        }
        return value.substring(7);
    }
}
