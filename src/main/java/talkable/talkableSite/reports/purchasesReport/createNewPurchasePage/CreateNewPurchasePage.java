package talkable.talkableSite.reports.purchasesReport.createNewPurchasePage;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.reports.advocateOffersReport.advocateOfferDetailsPage.AdvocateOfferDetailsPage;
import util.DriverConfig;

public class CreateNewPurchasePage extends AbstractTalkableSitePage{
    private String parentHandle;
    private String childHandle;

    private ElmntEmailInput emailField;
    private ElmntOrderNumberInput orderNumberField;
    private ElmntSubtotalInput subtotalField;
    private ElmntCouponCodeInput couponCodeField;
    private ElmntVisitorUUIDInput visitorUUIDField;
    private ElmntIpAddressInput ipAddressField;
    private ElmntCreateOriginButton createOriginButton;
    private ElmntViewOfferButton viewOfferButton;

    public CreateNewPurchasePage(){
        parentHandle = driver.getWindowHandle();
        childHandle = DriverConfig.switchToUnknownWindow(parentHandle);

        emailField = new ElmntEmailInput();
        orderNumberField = new ElmntOrderNumberInput();
        subtotalField = new ElmntSubtotalInput();
        couponCodeField = new ElmntCouponCodeInput();
        visitorUUIDField = new ElmntVisitorUUIDInput();
        ipAddressField = new ElmntIpAddressInput();
        createOriginButton = new ElmntCreateOriginButton();
        viewOfferButton = new ElmntViewOfferButton();

    }

    public AdvocateOfferDetailsPage createOfferWithDefaultValues(){
        createOriginButton.click();
        return new AdvocateOfferDetailsPage();
    }

    public AdvocateOfferDetailsPage createOfferWithAllValues(String email, String orderNumber, String subtotal, String couponCode, /*String visitorUuid,*/ String ipAddress){
        emailField.clear();
        emailField.sendKeys(email);
        orderNumberField.clear();
        orderNumberField.sendKeys(orderNumber);
        subtotalField.clear();
        subtotalField.sendKeys(subtotal);
        couponCodeField.sendKeys(couponCode);
//        visitorUUIDField.clear();  // not possible to use the parameter due to defect PR-8436
//        visitorUUIDField.sendKeys(visitorUuid);
        ipAddressField.clear();
        ipAddressField.sendKeys(ipAddress);
        createOriginButton.click();

        return new AdvocateOfferDetailsPage();
    }

    public CampaignDetailsPage createOfferAndSwitchToCampaign(){
        createOfferWithDefaultValues();
        driver.close();
        DriverConfig.switchToWindow(parentHandle);
        CampaignDetailsPage campaignDetailsPage = new CampaignDetailsPage();

        return (CampaignDetailsPage) campaignDetailsPage.refresh();
    }


}
