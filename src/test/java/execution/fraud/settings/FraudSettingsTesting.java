package execution.fraud.settings;

import api.objects.Site;
import api.scenarios.ViaAPI;
import common.cases.CommonScenarios;
import common.cases.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import talkable.talkableSite.fraud.settings.pageData.FraudRulesExpectedTextData;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;

import static talkable.talkableSite.fraud.settings.FraudSettingsPage.ApprovalMode.AUTOMATIC;
import static talkable.talkableSite.fraud.settings.FraudSettingsPage.ApprovalMode.MANUAL;
import static talkable.talkableSite.fraud.settings.FraudSettingsPage.ProfileType.*;

public class FraudSettingsTesting extends BaseTest{

    private Site site;

    @BeforeGroups("api-usage")
    public void getApiValues(){
        site = CommonScenarios.getSiteIntegrationValues();
    }

    @Test
    public void login(){
        CommonScenarios.login(
                PropertyLoader.loadProperty("talkable.user.fraudRules"),
                EnvFactory.getPassword());
    }

    @Test(dependsOnMethods = "login")
    public void setHighFraudSettingsProfile(){
        CommonScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                HIGH,
                "Fraud Profile: High",
                FraudRulesExpectedTextData.highProfileDescription
        );
        CommonScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Block Advocate",
                "Block Advocate",
                "Block Advocate",
                "Skip",
                "Block Advocate"
        );

        CommonScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Block Friend",
                "Skip",
                "Block Friend",
                "Block Friend"
        );

    }

    @Test(dependsOnMethods = "login")
    public void setElevatedFraudSettingsProfile(){
        CommonScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                ELEVATED,
                "Fraud Profile: Elevated",
                FraudRulesExpectedTextData.elevatedProfileDescription
        );
        CommonScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Skip",
                "Block Advocate"
        );
        CommonScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Block Friend",
                "Skip",
                "Block Friend",
                "Block Friend"
        );

    }


    @Test(dependsOnMethods = "login")
    public void setModerateFraudSettingsProfile(){
        CommonScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                MODERATE,
                "Fraud Profile: Moderate",
                FraudRulesExpectedTextData.moderateProfileDescription
        );

        CommonScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Skip",
                "Flag Advocate"
        );

        CommonScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Skip",
                "Skip",
                "Skip",
                "Skip"
        );
    }

    @Test(dependsOnMethods = "login")
    public void setLowFraudSettingsProfile(){
        CommonScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                LOW,
                "Fraud Profile: Low",
                FraudRulesExpectedTextData.lowProfileDescription
        );
        CommonScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip"
        );

        CommonScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Skip",
                "Skip",
                "Skip",
                "Skip"
        );
    }

    @Test(dependsOnMethods = "login")
    public void setCustomFraudSettingsProfile(){
        CommonScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                CUSTOM,
                "Fraud Profile: Custom",
                FraudRulesExpectedTextData.customProfileDescription
        );
    }


    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void automaticReferralApprovalMode(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";

        CommonScenarios.openFraudSettings();
        CommonScenarios.setReferralApprovalModeOnFraudSetting(AUTOMATIC);
        ViaAPI.createReferral(
                site,
                advocateEmail,
                "friend" + TestDataGenerator.getRandomId() + "@gmail.com");

        ReportsScenarios.openReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getAdvocateEmailFromReferralReportFirstRow(),
                advocateEmail,
                "FAILED: Referral is not created for advocate: <" + advocateEmail + ">"
        );

        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Approved",
                "FAILED: Incorrect referral status (advocate email = <" + advocateEmail + ">)."
        );
    }

    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void manualReferralApprovalMode_ApproveReferral(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";

        CommonScenarios.openFraudSettings();
        CommonScenarios.setReferralApprovalModeOnFraudSetting(MANUAL);
        ViaAPI.createReferral(
                site,
                advocateEmail,
                "friend" + TestDataGenerator.getRandomId() + "@gmail.com"
        );
        ReportsScenarios.openReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getAdvocateEmailFromReferralReportFirstRow(),
                advocateEmail,
                "FAILED: Referral is not created for advocate: <" + advocateEmail + ">"
        );
        ReportsScenarios.approveFirstRowInReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Approved",
                "FAILED: Incorrect referral status(advocate email = <" + advocateEmail + ">)."
        );
    }

    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void manualReferralApprovalMode_VoidReferral(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";

        CommonScenarios.openFraudSettings();
        CommonScenarios.setReferralApprovalModeOnFraudSetting(MANUAL);
        ViaAPI.createReferral(
                site,
                advocateEmail,
                "friend" + TestDataGenerator.getRandomId() + "@gmail.com"
        );
        ReportsScenarios.openReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getAdvocateEmailFromReferralReportFirstRow(),
                advocateEmail,
                "FAILED: Referral is not created for advocate: <" + advocateEmail + ">"
        );
        ReportsScenarios.voidFirstRowInReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Voided",
                "FAILED: Incorrect referral status"
        );
    }







}
