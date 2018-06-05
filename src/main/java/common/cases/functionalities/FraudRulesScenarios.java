package common.cases.functionalities;

import common.cases.CommonScenarios;
import org.testng.Assert;
import talkable.talkableSite.fraud.settings.FraudSettingsPage;
import talkable.talkableSite.fraud.settings.SectionRulesForAdvocate;
import talkable.talkableSite.fraud.settings.SectionRulesForFriend;
import talkable.talkableSite.headerFrame.Header;
import util.logging.Log;

public class FraudRulesScenarios extends CommonScenarios{

    /* Scenarios for Fraud rules */
    public static FraudSettingsPage openFraudSettings() {
        return new Header().openMenu().clickFraudSettingsButton();
    }

    public static FraudSettingsPage setFraudSettingsProfile(FraudSettingsPage.ProfileType profileType) {
        return new FraudSettingsPage().setProfile(profileType);
    }

    private static String getFraudSettingsProfileDescription() {
        return new FraudSettingsPage().getProfileDescription();
    }

    private static String getFraudSettingsProfileName() {
        return new FraudSettingsPage().getProfileName();
    }

    public static void setFraudProfileAndAssertProfileNameAndDescription(FraudSettingsPage.ProfileType profileType,
                                                                         String profileName,
                                                                         String profileDescription) {
        openFraudSettings();
        setFraudSettingsProfile(profileType);
        Assert.assertEquals(
                getFraudSettingsProfileName(),
                profileName,
                "FAILED: Incorrect fraud settings profile name"
        );
        Assert.assertEquals(
                getFraudSettingsProfileDescription(),
                profileDescription,
                "FAILED: Incorrect fraud settings profile description"
        );
    }

    public static SectionRulesForAdvocate getAdvocateRuleSectionFromFraudSetting() {
        return new FraudSettingsPage().getRulesForAdvocateSection();
    }

    public static SectionRulesForFriend getFriendRulesSectionFromFraudSetting() {
        return new FraudSettingsPage().getRulesForFriend();
    }

    public static void assertValuesInRulesForAdvocateSection(String expectedMatchingEmailOrCookieValue,
                                                             String expectedSimilarEmailMatch,
                                                             String expectedMatchingShippingAddress,
                                                             String expectedIpAddressAndUserAgentValue,
                                                             String expectedIpAddressOnlyValue,
                                                             String expectedCrossReferralValue
    ) {
        String selectedOption = getAdvocateRuleSectionFromFraudSetting().getMatchingEmailOrCookieValue();
        Assert.assertEquals(selectedOption, expectedMatchingEmailOrCookieValue, "FAILED: Incorrect selected value in 'Matching Email or Cookie on Friend Purchase' in Advocate Rules.");

        selectedOption = getAdvocateRuleSectionFromFraudSetting().getSimilarEmailMatch();
        Assert.assertEquals(selectedOption, expectedSimilarEmailMatch, "FAILED: Incorrect selected value in 'Similar Email Match' in Advocate Rules.");

        selectedOption = getAdvocateRuleSectionFromFraudSetting().getMatchingShippingAddress();
        Assert.assertEquals(selectedOption, expectedMatchingShippingAddress, "FAILED: Incorrect selected value in 'Similar Email Match' in Advocate Rules.");

        selectedOption = getAdvocateRuleSectionFromFraudSetting().getMatchingIpAddressAndUserAgent();
        Assert.assertEquals(selectedOption, expectedIpAddressAndUserAgentValue, "FAILED: Incorrect selected value in 'Matching by Combination of IP Address & User Agent' in Advocate Rules.");

        selectedOption = getAdvocateRuleSectionFromFraudSetting().getIpAddressOnly();
        Assert.assertEquals(selectedOption, expectedIpAddressOnlyValue, "FAILED: Incorrect selected value in 'Matching by IP Address only' in Advocate Rules.");

        selectedOption = getAdvocateRuleSectionFromFraudSetting().getFriendAndAdvocateReferEachOther();
        Assert.assertEquals(selectedOption, expectedCrossReferralValue, "FAILED: Incorrect selected value in 'Friend and Advocate Refer Each Other' in Advocate Rules.");

    }

    public static void assertValuesInRulesForFriendSection(String expectedMatchingCookieValue,
                                                           String expectedIpAddressAndUserAgentValue,
                                                           String expectedIpAddressOnlyValue,
                                                           String expectedSimilarEmailMatch,
                                                           String expectedCrossReferralValue
    ) {
        String selectedOption = getFriendRulesSectionFromFraudSetting().getMatchingCookieValue();
        Assert.assertEquals(selectedOption, expectedMatchingCookieValue, "FAILED: Incorrect selected value in 'Matching Cookie on Friend Claim Page' in Rules for Friend section");

        selectedOption = getFriendRulesSectionFromFraudSetting().getMatchingIpAddressAndUserAgent();
        Assert.assertEquals(selectedOption, expectedIpAddressAndUserAgentValue, "FAILED: Incorrect selected value in 'Matching by Combination of IP Address & User Agent' in Rules for Friend section");

        selectedOption = getFriendRulesSectionFromFraudSetting().getIpAddressOnly();
        Assert.assertEquals(selectedOption, expectedIpAddressOnlyValue, "FAILED: Incorrect selected value in 'Matching by IP Address only' in Rules for Friend section");

        selectedOption = getFriendRulesSectionFromFraudSetting().getSimilarEmailMatch();
        Assert.assertEquals(selectedOption, expectedSimilarEmailMatch, "FAILED: Incorrect selected value in 'Similar Email Match' in Rules for Friend section");

        selectedOption = getFriendRulesSectionFromFraudSetting().getFriendAndAdvocateReferEachOther();
        Assert.assertEquals(selectedOption, expectedCrossReferralValue, "FAILED: Incorrect selected value in 'Friend and Advocate Refer Each Other' in Rules for Friend section");

    }


    public static FraudSettingsPage setReferralApprovalDelay(int period){
        FraudSettingsPage page = new FraudSettingsPage().setReferralApprovalDelay(period);
        //todo: investiagte and resolve the issue with '0' and '00' in the input field
//        Assert.assertEquals(page.getReferralApprovalDelay(), String.valueOf(period));

        return page;
    }

    public static FraudSettingsPage setReferralApprovalModeOnFraudSetting(FraudSettingsPage.ApprovalMode mode){
        return new FraudSettingsPage().setApprovalMode(mode);
    }

    public static void setAdvocateRules(String matchingEmailOrCookiesOnPurchase,
                                              String similarEmailMatch,
                                              String matchingShippingAddress,
                                              String matchingByIpAndAgent,
                                              String matchingByIpOnly,
                                              String crossReferral){
        new FraudSettingsPage().setAdvocateRules(matchingEmailOrCookiesOnPurchase,
                similarEmailMatch,
                matchingShippingAddress,
                matchingByIpAndAgent,
                matchingByIpOnly,
                crossReferral);

        Log.logRecord("Fraud Setting have been updated for Advocate");
    }

    public static void setFriendRules(String matchingCookies,
                                            String matchingByIpAndAgent,
                                            String matchingByIpOnly,
                                            String similarEmailMatch,
                                            String crossReferral)
    {
        new FraudSettingsPage().setFriendRules(matchingCookies,
                matchingByIpAndAgent,
                matchingByIpOnly,
                similarEmailMatch,
                crossReferral);

        Log.logRecord("Fraud Setting have been updated for Friend");
    }



}
