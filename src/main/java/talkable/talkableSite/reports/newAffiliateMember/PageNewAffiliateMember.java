package talkable.talkableSite.reports.newAffiliateMember;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.reports.advocateOffersReport.advocateOfferDetailsPage.AdvocateOfferDetailsPage;
import util.DriverConfig;

public class PageNewAffiliateMember extends AbstractTalkableSitePage{

    private String parentHandle;
    private String childHandle;

    private ElmntEmailInput emailInput = new ElmntEmailInput();
    private ElmntCreateOriginButton createOriginButton = new ElmntCreateOriginButton();


    public PageNewAffiliateMember() {
        parentHandle = driver.getWindowHandle();
        childHandle = DriverConfig.switchToUnknownWindow(parentHandle);
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
        return new CampaignDetailsPage();
    }

}
