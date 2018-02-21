package talkable.campaignsPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.logging.Log;

public class Table extends AbstractElementsContainer {
    private ArrayList<ArrayList<Element>> table = new ArrayList();

    public Table(By by) {
        WebElement table = this.driver.findElement(by);
        List<WebElement> allRows = table.findElements(By.xpath("./tbody/tr"));
        Iterator var4 = allRows.iterator();

        while(var4.hasNext()) {
            WebElement element = (WebElement)var4.next();
            ArrayList<Element> row = this.setRowObjects(element);
            this.table.add(row);
        }

    }

    private ArrayList<Element> setRowObjects(WebElement row) {
        ArrayList<Element> rowObjects = new ArrayList();
        rowObjects.add(new Element(row.findElement(By.xpath(".//div[contains(@class, 'campaign-type')]/span"))));
        rowObjects.add(new Element(row.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-name')]"))));
        rowObjects.add(new Element(row.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-id')]"))));
        rowObjects.add(new Element(row.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-count-text')]"))));
        return rowObjects;
    }

    public Element getCampaignType(int rowNumber) {
        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(0);
    }

    public Element getCampaignName(int rowNumber) {
        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(1);
    }

    public Element getCampaignId(int rowNumber) {
        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(2);
    }

    public Element getCampaignOffers(int rowNumber) {
        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(3);
    }

    public ArrayList<Element> getRowByCampaignName(String campaignName) {
        Iterator var2 = this.table.iterator();

        ArrayList row;
        do {
            if (!var2.hasNext()) {
                Log.rowIsNotFound(campaignName);
                return null;
            }

            row = (ArrayList)var2.next();
        } while(!campaignName.equals(((Element)row.get(1)).getText()));

        return row;
    }
}

