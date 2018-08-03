package talkable.talkableSite.reports;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.common.elements.RangeDatePicker;

public interface FilterableReport {
    By startDate = By.xpath("//input[contains(@class, 'start-date')]");
    By endDate = By.xpath("//input[contains(@class, 'end-date')]");
    By generateBtnLctr = By.xpath("//input[contains(@name, 'commit')]");


    default Element getStartDate(){
        return new Element(startDate, "Start Date in data picker");
    }

    default Element getEndDate(){
        return new Element(endDate, "End Date in data picker");
    }

    default FilterableReport enterFilterDates(String start, String end){
        getStartDate().clearAndSendKeys(start);
        getEndDate().clearAndSendKeys(start);
        new RangeDatePicker()
                .clickOk();
        return this;
    }

    default FilterableReport generate(){
        new Element(generateBtnLctr, "Generate button")
                .click();
        return this;
    }

}
