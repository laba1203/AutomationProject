package talkable.talkableSite.campaign.pages.ab.tests.newAbTest.campaign.menu;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.campaign.pages.ab.tests.newAbTest.AbTestReportPage;

public class CampaignAbTestsPage extends AbstractCampaignPage {
    private static final By firstContentRowLctr = By.xpath("//div[@id='content-table']//table/tbody/tr[1]");
    private static final By firstIncentiveRowLctr = By.xpath("//div[@id='incentive-table']//table/tbody/tr[1]");

    public Row openFirstContentRow(){
        return new Row(firstContentRowLctr);
    }


    public class Row {
        private By testNameLctr = By.xpath(".//a[contains(@class, 'is-text')]");
        private By statusLctr = By.xpath(".//mark");
        private By viewReportBtnLctr = By.xpath(".//a[contains(@class, 'is-success')]");

        private WebElement parentElement;

        private Row(By parentLctr){
            parentElement = new Element(parentLctr).getWebElement();
        }

        public String getAbTestName(){
            return new Element(parentElement, testNameLctr, "AB Test name").getText();
        }

        public AbTestReportPage viewReport(){
            new Element(parentElement, viewReportBtnLctr, "View Report").click();
            return new AbTestReportPage();
        }

        public String getStatus(){
            return new Element(parentElement, statusLctr, "AB Test status").getText();
        }
    }


}
