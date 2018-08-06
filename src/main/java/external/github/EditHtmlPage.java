package external.github;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import util.logging.Log;

public class EditHtmlPage extends AbstractElementsContainer{
    private static final By codeMirrorElement = By.xpath("//div[contains(@class, 'CodeMirror ')]");
    private static final By commitBtnLCtr = By.xpath("//button[@id='submit-file']");


    public EditHtmlPage setCodeValue(String query){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].CodeMirror.setValue(`" + query + "`);";
        Log.logRecord("Script for execution: \r\n" + script);
        js.executeScript(script, new Element(codeMirrorElement).getWebElement());
        return new EditHtmlPage();
    }

    public EditHtmlPage commitChanges(){
        new Element(commitBtnLCtr, "Commit Changes").click();
        return new EditHtmlPage();
    }


}
