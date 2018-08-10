package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.ContentValueRecord;

import java.util.ArrayList;

import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.*;

public class AbTestPage extends AbstractTalkableSitePage{

    private static final By aVariantRowContainer = By.xpath("//div[contains(@class, 'test-variants-row ')][1]");
    private static final By bVariantRowContainer = By.xpath("//div[contains(@class, 'test-variants-row ')][2]");
    private static final By variantsLctr = By.xpath("//div[contains(@class, 'test-variants-row ')]");
    private static final By saveBtnLctr = By.xpath("//button[contains(@class, 'is-success')]");
    private static final By abTestNameInputLctr = By.xpath("//div[@id='ab-test-name']/input");

    private Element saveBtn = new Element(saveBtnLctr, "Save A/B Test");
    private Element abTestNameInput = new Element(abTestNameInputLctr, "A/B Test Name input");

    private SimpleEditorPage.LocalizationType mode;

    public AbTestPage(){
        mode = calculateLocaleType();
    }

    public AbTestPage editVariantB(String newValue){
        return getVariantB()
                .updateVariant(newValue);
    }

    public SimpleEditorPage save(){
        saveBtn.click();
        return new SimpleEditorPage();
    }

    public String getAbTestName(){
        return abTestNameInput.getAttribute("value");
    }

    private VariantRow getVariantA(){
        return new VariantRow(aVariantRowContainer);
    }

    private VariantRow getVariantB(){
        return new VariantRow(bVariantRowContainer);
    }

    private ArrayList<VariantRow> getVariants(){
        ArrayList<VariantRow> variants = new ArrayList<>();
        for (WebElement el :
                driver.findElements(variantsLctr)){
            variants.add(new VariantRow(el));
        }
        return variants;
    }

    private SimpleEditorPage.LocalizationType calculateLocaleType(){
        String value = new Element(aVariantRowContainer).getAttribute("class");
        if(value.contains("is-text")) return COPY;
        if(value.contains("is-enum")) return CONFIGURATION;
        if(value.contains("is-color")) return COLOR;
        if(value.contains("is-asset")) return IMAGES;
        else {
            Assert.fail("Can not calculate localization mode on AB Test page. Unknown class <"+value+">.");
            return null;
        }
    }


    class VariantRow {
        private WebElement parent;

        VariantRow(WebElement parent){
            this.parent = parent;
        }

        VariantRow(By parent){
            this.parent = new Element(parent).getWebElement();
        }

        AbTestPage updateVariant(String newValue){
            getContentRecord().update(newValue);
            return new AbTestPage();
        }

        public String getVariantValue(){
            return getContentRecord().getText();
        }

        private ContentValueRecord getContentRecord(){
            switch (mode){
                case COPY:
                    return new ContentCopyInput(parent);
                case IMAGES:
                    return new ContentImageInput(parent);
                case CONFIGURATION:
                    return new ContentConfigInput(parent);
                case COLOR:
                    return new ContentColorInput(parent);
                default:
                    return null;
            }
        }

    }
}
