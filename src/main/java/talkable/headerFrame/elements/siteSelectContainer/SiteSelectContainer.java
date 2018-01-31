package talkable.headerFrame.elements.siteSelectContainer;

import abstractObjects.AbstractElementsContainer;
import talkable.headerFrame.elements.SearchSiteInput;
import talkable.headerFrame.elements.SelectedSiteElement;

public class SiteSelectContainer extends AbstractElementsContainer{

//    SelectedSiteElement selectedSite;
    private SearchSiteInput searchSiteInput;

    public SiteSelectContainer(){
        searchSiteInput = new SearchSiteInput();
    }

    public void selectSiteByText(String text){
        searchSiteInput.sendKeys(text);
        FirstResult firstSite = new FirstResult();
        firstSite.moveMouseOver();
        firstSite.click();
    }



}
