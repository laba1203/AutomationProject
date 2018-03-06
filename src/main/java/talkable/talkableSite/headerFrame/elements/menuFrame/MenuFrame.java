package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.addYourSitePage.AddSitePage;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage;

public class MenuFrame extends AbstractElementsContainer {


    private static final By menuIsOpenedElement = By.cssSelector(".dropdown.open");

    //    Elements:
    private ElmntCreateNewCampaignButton elmntCreateNewCampaignButton;
    private ElmntCreateNewSiteButton elmntCreateNewSiteButton;
    private ElmntCampaignPlacementsButton campaignPlacementsButton;


    public MenuFrame(){
        verifyIfMenuIsOpened();

//        Initialize containers
        elmntCreateNewCampaignButton = new ElmntCreateNewCampaignButton();
        elmntCreateNewSiteButton = new ElmntCreateNewSiteButton();
        campaignPlacementsButton = new ElmntCampaignPlacementsButton();
    }


    private void verifyIfMenuIsOpened(){
        if(driver.findElements(menuIsOpenedElement).size() == 0){
            Assert.fail ("Menu popup is not opened");
        }
        System.out.println("Menu drop down is opened");
    }

    public AddSitePage clickCreateNewSiteButton(){
        elmntCreateNewSiteButton.click();
        return new AddSitePage();
    }

    public CreateNewCampaignPage clickCreateNewCampaignButton() {
        elmntCreateNewCampaignButton.click();
        return new CreateNewCampaignPage();
    }

    public PageCampaignPlacements clickCampaignPlacementsButton(){
        campaignPlacementsButton.click();
        return new PageCampaignPlacements();
    }
}
