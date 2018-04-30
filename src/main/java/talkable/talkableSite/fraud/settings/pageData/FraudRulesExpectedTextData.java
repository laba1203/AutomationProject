package talkable.talkableSite.fraud.settings.pageData;

public class FraudRulesExpectedTextData {

    /*
    Static values of text on the Fraud Rules page
    * */

    public static final String highProfileDescription = "Talkable automatically blocks all referrals when the email address is identical or very similar, when it detects the same cookie on the friend purchase, when it identifies users referring each other to earn a discount before either has made a first purchase, and when the IP address and User-Agent match for a particular purchase.";

    public static final String elevatedProfileDescription = "Talkable automatically blocks all referrals when the email address is identical, when it detects the same cookie on the friend purchase, when it identifies users referring each other to earn a discount before either has made a first purchase, and when the IP address and User-Agent match for a particular purchase.";

    public static final String moderateProfileDescription = "Talkable automatically blocks all referrals when the email address is identical, when it detects the same cookie on the friend purchase, and when it identifies users referring each other to earn a discount before either has made a first purchase. Talkable flags referrals with similar emails and when the IP address and User-Agent match for a particular purchase.";

    public static final String lowProfileDescription = "Talkable automatically blocks all referrals when the email address is identical and when it detects the same cookie on the friend purchase. Talkable flags referrals with similar emails, when it identifies users referring each other to earn a discount before either has made a first purchase, and when the IP address and User-Agent match for a particular purchase.";

    public static final String customProfileDescription = "This is your custom configuration of Talkable Fraud settings. You can switch to one of the recommended Fraud Profiles at any time, they are all listed on the left.";
}
