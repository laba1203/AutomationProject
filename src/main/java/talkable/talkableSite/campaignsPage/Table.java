package talkable.talkableSite.campaignsPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.logging.Log;

public class Table extends AbstractElementsContainer {
    private ArrayList<Row> table = new ArrayList<>();

    public Table(By by) {
        WebElement tableElement = this.driver.findElement(by);
        List<WebElement> allRows = tableElement.findElements(By.xpath("./tbody/tr"));

        for (WebElement webElement :
                allRows) {
            this.table.add(new Row(webElement));
        }
    }


    public String getCampaignType(int rowNumber) {
        return this.table.get(rowNumber).type.getText();
    }

    public String getCampaignName(int rowNumber) {
        return this.table.get(rowNumber).name.getText();
    }

    public String getCampaignId(int rowNumber) {
        return this.table.get(rowNumber).id.getText();
    }

    public String getCampaignOffers(int rowNumber) {
        return this.table.get(rowNumber).offers.getText();
    }

    public Row getRowByCampaignName(String campaignName){
        Row out = null;
        for (Row row :
                this.table) {
            if (row.name.getText().equals(campaignName)) {
                out = row;
            }
        }
        out = isNull(out, campaignName);
        return out;
    }

    private Row isNull(Row row, String campaignName){
        if(row == null){
            Assert.fail("Campaign with name <" + campaignName + "> is not found");
            return row;
        }
        return row;
    }



    class Row {
        Element type;
        Element name;
        Element id;
        Element offers;

        Row(WebElement rowElement){
            type = new Element(rowElement.findElement(By.xpath(".//div[contains(@class, 'campaign-type')]/span")));
            name = new Element(rowElement.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-name')]")));
            id = new Element(rowElement.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-id')]")));
            offers = new Element(rowElement.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-count-text')]")));
        }


    }
}

