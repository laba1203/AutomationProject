package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;

public class FraudSettingsPage extends AbstractTalkableSitePage{

    private ElmntSaveChangesButton saveChangesButton = new ElmntSaveChangesButton();
    private ElmntHighProfileButton highProfileButton = new ElmntHighProfileButton();
    private ElmntElevatedButton elevatedProfileButton = new ElmntElevatedButton();
    private ElmntModerateProfileButton moderateProfileButton = new ElmntModerateProfileButton();
    private ElmntLowProfileButton lowProfileButton = new ElmntLowProfileButton();
    private ElmntCustomProfileButton customProfileButton = new ElmntCustomProfileButton();
    private ElmntManualReferralApproval manualReferralApproval = new ElmntManualReferralApproval();

    private SectionRulesForAdvocate rulesForAdvocate = new SectionRulesForAdvocate();
    private SectionRulesForFriend rulesForFriend = new SectionRulesForFriend();

    public enum ProfileType{HIGH, ELEVATED, MODERATE, LOW, CUSTOM}

    public enum ApprovalMode{AUTOMATIC, MANUAL}


    public FraudSettingsPage setProfile(ProfileType profileType){
        AbstractElement profile = null;
        switch (profileType){
            case HIGH:
                profile = new ElmntHighProfileButton();
                break;
            case ELEVATED:
                profile = new ElmntElevatedButton();
                break;
            case MODERATE:
                profile = new ElmntModerateProfileButton();
                break;
            case LOW:
                profile = new ElmntLowProfileButton();
                break;
            case CUSTOM:
                profile = new ElmntCustomProfileButton();
                break;
            default:
                Assert.fail("FAILED: Unknown Fraud Settings profile <" + profile.toString() + ">");
        }
        profile.click();

        return saveChanges();
    }


    public String getProfileDescription(){
        return new ElmntProfileDescription().getText();
    }

    public String getProfileName(){
        return new ElmntProfileNameHeader().getText();
    }

    public FraudSettingsPage setApprovalMode(ApprovalMode mode){
        AbstractElement option = null;
        switch (mode){
            case MANUAL:
                option = new ElmntManualReferralApproval();
                break;
            case AUTOMATIC:
                option = new ElmntAutomaticReferralApproval();
                break;
            default:
                Assert.fail("Unknown referral approval mode <" + mode.toString() + ">");

        }
        option.click();
        return saveChanges();
    }

//    public FraudSettingsPage setManualReferralApprovalMode(){
//        new ElmntManualReferralApproval().click();
//        return saveChanges();
//    }
//
//    public FraudSettingsPage setAutomaticReferralApprovalMode(){
//        new ElmntAutomaticReferralApproval().click();
//        return saveChanges();
//    }

    public SectionRulesForAdvocate getRulesForAdvocateSection(){
        return new SectionRulesForAdvocate();
    }

    public FraudSettingsPage setAdvocateRules(String matchingEmailOrCookiesOnPurchase,
                                              String similarEmailMatch,
                                              String matchingShippingAddress,
                                              String matchingByIpAndAgent,
                                              String matchingByIpOnly,
                                              String crossReferral)
    {
        getRulesForAdvocateSection().setMatchingEmailOrCookieValue(matchingEmailOrCookiesOnPurchase);
        getRulesForAdvocateSection().setSimilarEmailMatch(similarEmailMatch);
        getRulesForAdvocateSection().setMatchingShippingAddress(matchingShippingAddress);
        getRulesForAdvocateSection().setMatchingIpAddressAndUserAgent(matchingByIpAndAgent);
        getRulesForAdvocateSection().setIpAddressOnly(matchingByIpOnly);
        getRulesForAdvocateSection().setFriendAndAdvocateReferEachOther(crossReferral);

        return saveChanges();
    }

    public FraudSettingsPage setFriendRules(String matchingCookies,
                                            String matchingByIpAndAgent,
                                            String matchingByIpOnly,
                                            String similarEmailMatch,
                                            String crossReferral)
    {
        getRulesForFriend().setFriendAndAdvocateReferEachOther(crossReferral);
        getRulesForFriend().setSimilarEmailMatch(similarEmailMatch);
        getRulesForFriend().setIpAddressOnly(matchingByIpOnly);
        getRulesForFriend().setMatchingCookieValue(matchingCookies);
        getRulesForFriend().setMatchingIpAddressAndUserAgent(matchingByIpAndAgent);




        return saveChanges();
    }


    public SectionRulesForFriend getRulesForFriend() {
        return new SectionRulesForFriend();
    }

    public FraudSettingsPage setReferralApprovalDelay(int value){
        new ElmntReferralApprovalDelayInput().clear();
        new ElmntReferralApprovalDelayInput().sendKeys(String.valueOf(value));
        return saveChanges();
    }

    public String getReferralApprovalDelay(){
        return new ElmntReferralApprovalDelayInput().getAttribute("value");
    }

    private FraudSettingsPage saveChanges(){
        saveChangesButton = new ElmntSaveChangesButton();
        saveChangesButton.click();
        waitSaving();
        return new FraudSettingsPage();
    }

}
