package talkable.talkableSite.reports.referralsReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRowItem extends AbstractElement{

    ElmntRowItem(By locator){
        setWebElement(locator);
    }
}
