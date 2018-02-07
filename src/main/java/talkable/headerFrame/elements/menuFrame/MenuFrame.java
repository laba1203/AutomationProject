package talkable.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.addYourSitePage.AddSitePage;
import talkable.createNewCampaignPage.CreateNewCampaignPage;
import talkable.headerFrame.elements.menuFrame.elements.CreateNewCampaignButton;
import talkable.headerFrame.elements.menuFrame.elements.CreateNewSiteButton;

public class MenuFrame extends AbstractElementsContainer {


    private static final By menuIsOpenedElement = By.cssSelector(".dropdown.open");

    //    Elements:
    private CreateNewCampaignButton createNewCampaignButton;
    private CreateNewSiteButton createNewSiteButton;


    public MenuFrame(){
        verifyIfMenuIsOpened();

//        Initialize containers
        createNewCampaignButton = new CreateNewCampaignButton();
        createNewSiteButton = new CreateNewSiteButton();
    }


    private void verifyIfMenuIsOpened(){
        if(driver.findElements(menuIsOpenedElement).size() == 0){
            Assert.fail ("Menu popup is not opened");
        }
        System.out.println("Menu drop down is opened");
    }

    public AddSitePage clickCreateNewSiteButton(){
        createNewSiteButton.click();
        return new AddSitePage();
    }

    public CreateNewCampaignPage clickCreateNewCampaignButton() {
        createNewCampaignButton.click();
        return new CreateNewCampaignPage();
    }
}
