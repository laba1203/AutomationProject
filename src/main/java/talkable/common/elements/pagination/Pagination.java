package talkable.common.elements.pagination;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import util.WaitFactory;


public class Pagination extends AbstractElementsContainer{
    private static final By firstLctr = By.xpath("./li[@class = 'first']/a");
    private static final By previousLctr = By.xpath("./li[@class = 'prev']/a");
    private static final By nextLctr = By.xpath("./li[@class = 'next_page']/a");
    private static final By lastLctr = By.xpath("./li[@class = 'last next']/a");
    private static final By currentPageLctr = By.xpath("./li[contains(@class, 'active')]/a");
    private By ownElementXpath;

    private Element own;
    private Element currentPage;


    /* XPath of the related <ul> element should be provided in the constructor
    *  @ulElementXpath
    * */
    public Pagination(By ulElementXpath){
        ownElementXpath = ulElementXpath;
        own = getOwnElement();
        setElements();
    }

    private Element getOwnElement(){
        return new Element(ownElementXpath);
    }

    private void setElements(){
        currentPage = new Element(getOwnElement(), currentPageLctr);
    }

    public String getCurrentPage(){
        currentPage = new Element(getOwnElement(), currentPageLctr);
        return currentPage.getText();
    }

    public void next(){
        String currentPageText = getCurrentPage();
        new Element(getOwnElement(), nextLctr).click();
        WaitFactory.waitInvisibilityOfElementWithText(currentPageLctr, currentPageText);
        System.out.println("LOG: Pagination --> The Next page is opened.");
    }

    public void last(){
        String currentPageText = getCurrentPage();
        new Element(getOwnElement(), lastLctr).click();
        WaitFactory.waitInvisibilityOfElementWithText(currentPageLctr, currentPageText);
        System.out.println("LOG: Pagination --> The Last page is opened.");
    }

    public void first(){
        String currentPageText = getCurrentPage();
        new Element(getOwnElement(), firstLctr).click();

        WaitFactory.waitInvisibilityOfElementWithText(currentPageLctr, currentPageText);
        System.out.println("LOG: Pagination --> The First page is opened.");
    }

    public void previous(){
        String currentPageText = getCurrentPage();
        new Element(getOwnElement(), previousLctr).click();
        WaitFactory.waitInvisibilityOfElementWithText(currentPageLctr, currentPageText);
        System.out.println("LOG: Pagination --> The Previous page is opened.");
    }

}
