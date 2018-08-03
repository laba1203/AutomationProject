package talkable.talkableSite.reports.settingsChanges;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.reports.AbstractReportPage;
import talkable.talkableSite.reports.FilterableReport;

public class SettingsChangesReportPage extends AbstractReportPage implements FilterableReport{
    private static final String header = "Settings Changes";
    private static final String firstRowXpath = "//*[contains(@class, 'settings_changes_table')]/tbody/tr[1]";
    private static final By updatedFieldFromFirstRowLctr = By.xpath(firstRowXpath + "//*[@class='key']");
    private static final By oldValueFromFirstRowLctr = By.xpath(firstRowXpath + "//*[@class='old']");
    private static final By newValueFromFirstRowLctr = By.xpath(firstRowXpath + "//*[@class='new']");

    public SettingsChangesReportPage(){
        verifyHeader(header);
    }

    public String getUpdatedFieldFromFirstRow(){
        return new Element(updatedFieldFromFirstRowLctr).getText();
    }

    public String getOldValueFromFirstRow(){
        return new Element(oldValueFromFirstRowLctr).getText();
    }

    public String getNewValueFromFirstRow(){
        return new Element(newValueFromFirstRowLctr).getText();
    }


}
