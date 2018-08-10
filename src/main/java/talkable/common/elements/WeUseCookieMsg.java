package talkable.common.elements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.WaitFactory;
import util.logging.Log;

public class WeUseCookieMsg extends AbstractElementsContainer{
    private static final By acceptBtnLctr = By.xpath("//button[@class = 'Gdpr-policy-button Gdpr-policy-button-desktop']");
    private static final By gdprContent = By.xpath("//*[@class = 'Gdpr-policy-desktop-content']");

    public WeUseCookieMsg(){
        new Element(gdprContent);
    }

    public void accept(){
        new Element(acceptBtnLctr, "'Accept Cookie Usage' button")
                .click();
        waitFactory().getCustomWait(2, 500)
                .until(ExpectedConditions.invisibilityOfElementLocated(acceptBtnLctr));
        Log.logRecord("Using of cookies is accepted.");
    }

}
