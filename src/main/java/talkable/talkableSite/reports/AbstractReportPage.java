package talkable.talkableSite.reports;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;

public abstract class AbstractReportPage extends AbstractTalkableSitePage{

    private static final By headerLctr = By.xpath("//h2[1]");


    protected void verifyHeader(String header){
        verifyHeader(headerLctr, header);
    }

    protected void verifyHeader(By headerLctr, String expectedHeader){
        Assert.assertEquals(
                new Element(headerLctr).getText(),
                expectedHeader,
                "Header doesn't match for report <" + this.getClass().getName() + ">."
        );
    }
}
