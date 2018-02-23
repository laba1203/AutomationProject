package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SearchSiteInput extends AbstractElement{
    private static  final By siteSelectLocator = By.cssSelector(".chosen-drop > .chosen-search > input[type = 'text']" );

    public SearchSiteInput(){
        setWebElement(siteSelectLocator);
    }

}
