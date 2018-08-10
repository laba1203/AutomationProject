package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;

import java.util.ArrayList;

public class AbTestReportPage extends AbstractTalkableSitePage{
    private static final By abTestNameLctr = By.xpath("//h1/span");
    private static final By varintRowsLctr = By.xpath("//tr[@class = 'abtest-table-variant']");
    private static final By statusLctr = By.xpath("//mark");
    private static final By impressionLctr = By.xpath("//div[text()='Impressions']/following::div");

    private Element abTestName = new Element(abTestNameLctr, "A/B Test name");
    private Element status = new Element(statusLctr, "Status");
    private Element impressionsField = new Element(impressionLctr, "Impressions field");

    public String getAbTestName(){
        return abTestName.getText();
    }

    public String getVariantValue(String variant){
        return findVariant(variant).getValue();
    }

    public String getImpressions(){
        return impressionsField.getText();
    }

    public String getStatus(){
        return status.getText();
    }

    private VariantRow findVariant(String variantChar){
        for (VariantRow row :
                getVariants()) {
            if (row.getVariantChar().equals(variantChar)) return row;
        }
        Assert.fail("A/B Test variant with char was not fount on the AB Test Report page." );
        return null;
    }

    private ArrayList<VariantRow> getVariants(){
        ArrayList<VariantRow> variants = new ArrayList<>();
        for (WebElement el:
                driver.findElements(varintRowsLctr)) {
            variants.add(new VariantRow(el));
        }
        return variants;
    }



    public class VariantRow{
        private By valueLctr = By.xpath(".//td[contains(@class, 'value')]/span");
        private By variantChar = By.xpath(".//div[contains(@class, 'abtest-char')][1]");

        private WebElement row;

        private VariantRow(WebElement rowEl){
            row = rowEl;
        }

        public String getValue(){
            return new Element(row, valueLctr, "A/B Test value").getText();
        }

        private String getVariantChar(){
            return new Element(row, variantChar, "Variant Literal").getText();
        }

    }

}
