package talkable.talkableSite.fraud.settings;

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


    public FraudSettingsPage setHighProfile(){
        new ElmntHighProfileButton().click();
        return saveChanges();
    }

    public FraudSettingsPage setElevatedProfile(){
        new ElmntElevatedButton().click();
        return saveChanges();
    }

    public FraudSettingsPage setModerateProfile(){
        new ElmntModerateProfileButton().click();
        return saveChanges();
    }

    public FraudSettingsPage setLowProfile(){
        new ElmntLowProfileButton().click();
        return saveChanges();
    }

    public FraudSettingsPage setCustomProfile(){
        new ElmntCustomProfileButton().click();
        return saveChanges();
    }

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
