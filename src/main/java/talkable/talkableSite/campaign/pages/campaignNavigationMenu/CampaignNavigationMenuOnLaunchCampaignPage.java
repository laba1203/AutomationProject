package talkable.talkableSite.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.campaign.pages.ab.tests.newAbTest.campaign.menu.CampaignAbTestsPage;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;

public class CampaignNavigationMenuOnLaunchCampaignPage extends AbstractElementsContainer{

    private static final By abTestsTab = By.xpath("//a[text()='A/B Tests']");
    private ElmntDetailsButton elmntDetailsButton;
    private ElmntRulesButton rulesButton;
    private ElmntPreviewButton elmntPreviewButton;
    private ElmntEditorButton elmntEditorButton;

    public CampaignNavigationMenuOnLaunchCampaignPage(){
//        initialization of containers:
//        initiateVisibleElements(containers);
        elmntDetailsButton = new ElmntDetailsButton();
        rulesButton = new ElmntRulesButton();
        elmntPreviewButton = new ElmntPreviewButton();
        elmntEditorButton = new ElmntEditorButton();
    }


    public SimpleEditorPage openEditorPage(){
        elmntEditorButton.click();
        return new SimpleEditorPage();
    }

    public CampaignDetailsPage openDetailsPage(){
        elmntDetailsButton.click();
        return new CampaignDetailsPage();
    }

    public PageCampaignRules openRulesPage(){
        rulesButton.click();
        return new PageCampaignRules();
    }

    public CampaignAbTestsPage openAbTestsPage(){
        new Element(abTestsTab, "A/B Tests tab").click();
        return new CampaignAbTestsPage();

    }


}
