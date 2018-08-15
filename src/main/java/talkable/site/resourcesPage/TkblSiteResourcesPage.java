package talkable.site.resourcesPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.site.TkblSiteHeader;
import talkable.site.TlkblSiteFooter;
import talkable.site.handbook.GetHandbookPage;

public class TkblSiteResourcesPage extends AbstractElementsContainer implements TlkblSiteFooter, TkblSiteHeader{

    private static final By requestHandBookBtnLctr = By.xpath("//*[contains(@class, 'button-holder')]/a");

    public GetHandbookPage clickGetHandBookNow(){
        new Element(requestHandBookBtnLctr, "Get Handbook Now button").click();
        return new GetHandbookPage();
    }

}
