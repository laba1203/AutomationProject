package talkable.talkableSite.headerFrame;

import abstractObjects.AbstractElementsContainer;
import talkable.homePage.HomePage;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.headerFrame.elements.*;
import talkable.talkableSite.headerFrame.elements.menuFrame.MenuFrame;
import talkable.talkableSite.reports.reportsPage.ReportsPage;

public class Header extends AbstractElementsContainer{

    //Elements:
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

//    public Header switchSiteTo(String siteName){
//        siteSelect.searchAndSelect(siteName);
//        return new Header();
//    }

    public Header selectByVisibleText(String siteName){
        siteSelect.selectByVisibleText(siteName);
        return new Header();
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

    public PageCampaigns openCampaignsPage(){
        campaignsButton.click();
        return new PageCampaigns();
    }

    public HomePage logout(){
        return openMenu().clickLogout();



    }


}
