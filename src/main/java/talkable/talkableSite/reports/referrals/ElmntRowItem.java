package talkable.talkableSite.reports.referrals;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRowItem extends AbstractElement{

    ElmntRowItem(By locator){
        setWebElement(locator);
    }
}
