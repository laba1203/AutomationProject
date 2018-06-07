package talkable.talkableSite.reports.newAffiliateMember;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.reports.advocateOffers.advocateOfferDetailsPage.AdvocateOfferDetailsPage;
import util.DriverConfig;

public class PageNewAffiliateMember extends AbstractTalkableSitePage{

    private String parentHandle;
    private String childHandle;

    private ElmntEmailInput emailInput;
    private ElmntCreateOriginButton createOriginButton;


    public PageNewAffiliateMember() {
        parentHandle = driver.getWindowHandle();
        childHandle = DriverConfig.switchToUnknownWindow(parentHandle);

        emailInput = new ElmntEmailInput();
        createOriginButton = new ElmntCreateOriginButton();
    }

    public AdvocateOfferDetailsPage create(String email){
        emailInput.sendKeys(email);
        createOriginButton.click();

        return new AdvocateOfferDetailsPage();
    }

    public CampaignDetailsPage createMemberAndSwitchToCampaign(String email){
        create(email);
        driver.close();
        DriverConfig.switchToWindow(parentHandle);
        CampaignDetailsPage campaignDetailsPage = new CampaignDetailsPage();

        campaignDetailsPage.refresh();
        return new CampaignDetailsPage();
    }

}
