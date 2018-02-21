package talkable.headerFrame;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.campaignsPage.PageCampaigns;
import talkable.headerFrame.elements.*;
import talkable.headerFrame.elements.menuFrame.MenuFrame;
import talkable.headerFrame.elements.siteSelectContainer.SiteSelectContainer;
import talkable.reports.reportsPage.ReportsPage;

public class Header extends AbstractElementsContainer{

    //Elements:
//    public SelectedSiteElement siteSelectButton;
//    public SearchSiteInput searchSiteInput;
    private CustomerServicesButton customerServicesButton;
    private DashboardButton dashboardButton;
    private CampaignsButton campaignsButton;
    private ReportsButton reportsButton;
    private MenuButton menuButton;
    private SiteSelect siteSelect;


    public Header(){
//        siteSelectButton = new SelectedSiteElement();
        customerServicesButton = new CustomerServicesButton();
        dashboardButton = new DashboardButton();
        campaignsButton = new CampaignsButton();
        reportsButton = new ReportsButton();
        menuButton = new MenuButton();
        siteSelect = new SiteSelect();

        siteSelect = new SiteSelect();

    }

    public void switchSiteTo(String siteName){
        siteSelect.searchAndSelect(siteName);
//        siteSelectButton.click();
//        SiteSelectContainer siteSelect = new SiteSelectContainer();
//        siteSelect.selectSiteByText(siteName);
//        wait.until(ExpectedConditions.textToBePresentInElement(siteSelectButton.getWebElement(), siteName));
    }

    public String getSiteName(){
        return siteSelect.getSelectedItemText();
    }

    public MenuFrame openMenu(){
        menuButton.click();
        return new MenuFrame();
    }

    public ReportsPage clickReportsButton(){
        reportsButton.click();
        return new ReportsPage();
    }

    public PageCampaigns clickCampaignsPage(){
        campaignsButton.click();
        return new PageCampaigns();
    }


}
