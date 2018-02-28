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
//    private ArrayList<ArrayList<Element>> table = new ArrayList<>();
    private ArrayList<Row> table = new ArrayList<>();

    public Table(By by) {
        WebElement tableElement = this.driver.findElement(by);
        List<WebElement> allRows = tableElement.findElements(By.xpath("./tbody/tr"));


        for (WebElement webElement :
                allRows) {
            this.table.add(new Row(webElement));
        }

//        Iterator var4 = allRows.iterator();
//        while(var4.hasNext()) {
//            WebElement element = (WebElement)var4.next();
//            ArrayList<Element> row = this.setRowObjects(element);
//            this.table.add(row);
//        }

    }

//    private ArrayList<Element> setRowObjects(WebElement row) {
//        ArrayList<Element> rowObjects = new ArrayList();
//        rowObjects.add(new Element(row.findElement(By.xpath(".//div[contains(@class, 'campaign-type')]/span"))));
//        rowObjects.add(new Element(row.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-name')]"))));
//        rowObjects.add(new Element(row.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-id')]"))));
//        rowObjects.add(new Element(row.findElement(By.xpath(".//*[contains(@class, 'campaign-teaser-count-text')]"))));
//        return rowObjects;
//    }

    public String getCampaignType(int rowNumber) {
        return this.table.get(rowNumber).type.getText();
//        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(0);
    }

    public String getCampaignName(int rowNumber) {
        return this.table.get(rowNumber).name.getText();
//        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(1);
    }

    public String getCampaignId(int rowNumber) {
        return this.table.get(rowNumber).id.getText();
//        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(2);
    }

    public String getCampaignOffers(int rowNumber) {
        return this.table.get(rowNumber).offers.getText();
//        return (Element)((ArrayList)this.table.get(rowNumber - 1)).get(3);
    }


//    public ArrayList<Element> getRowByCampaignName(String campaignName) {
//        Iterator var2 = this.table.iterator();
//
//        ArrayList row;
//        do {
//            if (!var2.hasNext()) {
//                Log.rowIsNotFound(campaignName);
//                return null;
//            }
//
//            row = (ArrayList)var2.next();
//        } while(!campaignName.equals(((Element)row.get(1)).getText()));
//
//        return row;
//
//    }

    public Row getRowByCampaignName(String campaignName){
        Row out = null;
        for (Row row :
                this.table) {
            if (row.name.getText().equals(campaignName)) {
                out = row;
            }
        }
        out = isNull(out, campaignName);
//        if(out == null){
//            Assert.fail("Campaign with name <" + campaignName + "> is not found");
//            return out;
//        }
        return out;
    }

    private Row isNull(Row row, String campaignName){
        if(row == null){
            Assert.fail("Campaign with name <" + campaignName + "> is not found");
            return row;
        }
        return row;
    }




    class Row{
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

