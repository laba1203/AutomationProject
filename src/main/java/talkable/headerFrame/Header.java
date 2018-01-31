package talkable.headerFrame;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.headerFrame.elements.*;
import talkable.headerFrame.elements.siteSelectContainer.SiteSelectContainer;

public class Header extends AbstractElementsContainer{

    //Elements:
    public SelectedSiteElement siteSelectButton;
    public SearchSiteInput searchSiteInput;
    public CustomerServicesButton customerServicesButton;
    public DashboardButton dashboardButton;
    public CampaignsButton campaignsButton;
    public ReportsButton reportsButton;
    public MenuButton menuButton;
    public SiteSelect siteSelect;





    public Header(){
        siteSelectButton = new SelectedSiteElement();
        customerServicesButton = new CustomerServicesButton();
        dashboardButton = new DashboardButton();
        campaignsButton = new CampaignsButton();
        reportsButton = new ReportsButton();
        menuButton = new MenuButton();
        siteSelect = new SiteSelect();

        siteSelect = new SiteSelect();

    }

    public void switchSiteTo(String siteName){
        siteSelectButton.click();
        SiteSelectContainer siteSelect = new SiteSelectContainer();
        siteSelect.selectSiteByText(siteName);
        wait.until(ExpectedConditions.textToBePresentInElement(siteSelectButton.getWebElement(), siteName));
    }

    public void openMenu(){

    }
}
