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

//    public FraudSettingsPage setHighProfile(){
//        new ElmntHighProfileButton().click();
//        return saveChanges();
//    }
//
//    public FraudSettingsPage setElevatedProfile(){
//        new ElmntElevatedButton().click();
//        return saveChanges();
//    }
//
//    public FraudSettingsPage setModerateProfile(){
//        new ElmntModerateProfileButton().click();
//        return saveChanges();
//    }
//
//    public FraudSettingsPage setLowProfile(){
//        new ElmntLowProfileButton().click();
//        return saveChanges();
//    }
//
//    public FraudSettingsPage setCustomProfile(){
//        new ElmntCustomProfileButton().click();
//        return saveChanges();
//    }

    public String getProfileDescription(){
        return new ElmntProfileDescription().getText();
    }

    public String getProfileName(){
        return new ElmntProfileNameHeader().getText();
    }

    public FraudSettingsPage setManualReferralApprovalMode(){
        new ElmntManualReferralApproval().click();
        return saveChanges();
    }

    public FraudSettingsPage setAutomaticReferralApprovalMode(){
        new ElmntAutomaticReferralApproval().click();
        return saveChanges();
    }

    public SectionRulesForAdvocate getRulesForAdvocateSection(){
        return new SectionRulesForAdvocate();
    }

    public SectionRulesForFriend getRulesForFriend() {
        return new SectionRulesForFriend();
    }

    private FraudSettingsPage saveChanges(){
        saveChangesButton = new ElmntSaveChangesButton();
        saveChangesButton.click();
        waitSaving();
        return new FraudSettingsPage();
    }
}
