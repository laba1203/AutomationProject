package talkable.talkableSite.customerServicePortal.personLookup;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import util.logging.Log;

public class PersonInfoSection extends AbstractElementsContainer{
    private static final By userEmailLctr = By.xpath("//table[@class='Person-details-info']//tr[1]//td[contains(@class, 'value')]//span");
    private static final By referralsTabBtnLctr = By.xpath("//li[@class='Person-details-navigation-item']/a[contains(@data-action, 'Referrals')]");
    private static final By purchaseTabBtnLctr = By.xpath("//li[@class='Person-details-navigation-item']/a[contains(@data-action, 'Purchases')]");
    private static final By purchasesCountLctr = By.xpath("//li[@class='Person-details-navigation-item']/a[contains(@data-action, 'Purchases')]/span");
    private static final By offersTabBtnLctr = By.xpath("//li[@class='Person-details-navigation-item']/a[contains(@data-action, 'Offers')]");


    private Element userEmail = new Element(userEmailLctr, "User Email");
    private Element referralsBtn = new Element(referralsTabBtnLctr, "Referrals tab");
    private Element purchaseBtn = new Element(purchaseTabBtnLctr, "Purchases tab");
    private Element offersBtn = new Element(offersTabBtnLctr, "Offers tab");

    public String getUserEmail(){
        return userEmail.getText();
    }

    public ReferralDetailsSection openReferralDetailsFor(String friendEmail){
        ReferralDetailsSection referralDetails = openReferralsTab()
                .findReferralByFriendEmail(friendEmail)
                .clickDetails();
        Log.logRecord("Referral details section is opened for the referral with friend email <" + friendEmail + ">");
        return referralDetails;
    }

    public String getReferralStatusFor(String friendEmail){
        String status = openReferralsTab()
                .findReferralByFriendEmail(friendEmail)
                .getReferralStatus();
        Log.logRecord("Referral status for row with friend email <"+friendEmail+">: "+  status + ".");
        return status;
    }

    public ReferralsGrid openReferralsTab(){
        referralsBtn.click();
        return new ReferralsGrid();
    }

    public PurchasesGrid openPurchasesTab(){
        purchaseBtn.click();
        return new PurchasesGrid();
    }

    public OffersGrid openOffersTab(){
        offersBtn.click();
        return new OffersGrid();
    }

    public String getPurchasesRowsCount(){
        return new Element(purchasesCountLctr).getText();
    }


//    public void waitTillPurchaseCountChanged(String initialPurchasesCount){
//        WaitFactory.getCustomWait(30, 500)
//                .until(ExpectedConditions.
//                        invisibilityOfElementWithText(purchasesCountLctr, initialPurchasesCount));
//        Log.logRecord("Purchases count is changed.");
//    }



}
