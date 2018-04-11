package talkable.talkableSite.camapignPlacements;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import util.Util;

class PlacementRowElement extends Element{

//    private static final By labelXpath = By.xpath("./text()/following-sibling::span[1]");
    private static final By labelXpath = By.xpath("./text()/following-sibling::span[1]");
    private static final By staticUrlXpath = By.xpath("./text()/preceding-sibling::span[1]");
    private boolean allPagesIndicator;
    private boolean isRegex = true;


    private Element relatedLabel;


    PlacementRowElement(WebElement webElement) {
        super(webElement);
    }

    private void setLabelElement(){
        try{
            relatedLabel = new Element(getWebElement().findElement(labelXpath));
        }catch (NoSuchElementException e){
            System.out.println("DEBAG: No inclusion/exclusion defined");
        }
    }

    private void setRegex(){
        isRegex = relatedLabel.getText().equals("Regex");
        System.out.println("DEBAG: RegEx value = " + isRegex + "; value in label: <" + relatedLabel.getText() + ">");
    }

    private void setAllPagesIndicator(){
        allPagesIndicator = super.getText().equals("All pages");
        System.out.println("DEBAG: <ALL PAGES> label is indicated ");
    }



    @Override
    public String getText() {
        setAllPagesIndicator();
        String text = super.getText();
        System.out.println("DEBAG: Full text: <" + text + ">");
        text = removeLabelText(text);
        System.out.println("DEBAG: Text without label: <" + text + ">");
        text = removeStaticUrl(text);
        System.out.println("DEBAG: Text without static url: <" + text + ">");
        return text;
    }


    private String removeLabelText(String initialString){
        setLabelElement();
        if(relatedLabel == null){
            System.out.println("DEBAG: Placement row doesn't have Label text");
            return initialString;
        }
        else {
            String labelText = relatedLabel.getText();
            setRegex();
            return Util.cutLastPartOfString(initialString, labelText);
        }
    }


    private String removeStaticUrl(String initialString){
        if(isRegex || allPagesIndicator){
            System.out.println("DEBAG: URL is not added to Regex and ALl Pages placement. Regex = " + isRegex + ", All Pages Indicator = " + allPagesIndicator);
            return initialString;
        }
        String url = new Element(getWebElement().findElement(staticUrlXpath)).getText();
        return Util.cutFirstPartOfString(initialString, url);

//        if(!isRegex){
//           String url = new Element(getWebElement().findElement(staticUrlXpath)).getText();
//           return Util.cutFirstPartOfString(initialString, url);
//        }
//        System.out.println("DEBAG: URL is not added to Regex placement. Regex = " + isRegex);
//        return initialString;
    }
}
