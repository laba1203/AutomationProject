package talkable.headerFrame;

import talkable.headerFrame.elements.*;

public class Header {

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
        siteSelect.selectByVisibleText(siteName);
//        siteSelectButton.click();
//        searchSiteInput = new SiteSelectDropDown();
//        searchSiteInput.sendKeys(siteName);

//        searchSiteInput.click();
//        siteSelectDropDown.enterTextAndClickENTER(siteName);

    }

    public void openMenu(){

    }
}
