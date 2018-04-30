package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.SelectElement;
import org.openqa.selenium.By;

public class SectionRulesForAdvocate extends AbstractElementsContainer{

    private static final By matchingEmailOrCookieLctr = By.xpath("//select[@name = 'self_referral_by_email_or_visitor']");
    private static final By similarEmailMatchLctr = By.xpath("//select[@name = 'self_referral_by_similar_emails']");
    private static final By shippingAddressLctr = By.xpath("//select[@name = 'self_referral_by_shipping_address']");
    private static final By matchingByIpAndUserAgentLctr = By.xpath("//select[@name = 'self_referral_by_ip_and_user_agent']");
    private static final By ipAddressOnly = By.xpath("//select[@name = 'self_referral_by_ip']");
    private static final By crossReferralLctr = By.xpath("//select[@name = 'cross_referral']");

    public SectionRulesForAdvocate(){
        setAllElements();
    }

    public String getMatchingEmailOrCookieValue(){
        return new SelectElement(matchingEmailOrCookieLctr).getSelectedItemText();
    }

    public String getSimilarEmailMatch(){
        return new SelectElement(similarEmailMatchLctr).getSelectedItemText();
    }

    public String getMatchingShippingAddress(){
        return new SelectElement(shippingAddressLctr).getSelectedItemText();
    }

    public String getMatchingIpAddressAndUserAgent(){
        return new SelectElement(matchingByIpAndUserAgentLctr).getSelectedItemText();
    }

    public String getIpAddressOnly(){
        return new SelectElement(ipAddressOnly).getSelectedItemText();
    }

    public String getFriendAndAdvocateReferEachOther(){
        return new SelectElement(crossReferralLctr).getSelectedItemText();
    }

    public void setMatchingEmailOrCookieValue(String newValue){
        new SelectElement(matchingEmailOrCookieLctr).selectByVisibleText(newValue);
    }

    public void setMatchingShippingAddress(String newValue){
        new SelectElement(shippingAddressLctr).selectByVisibleText(newValue);
    }

    public void setSimilarEmailMatch(String newValue){
        new SelectElement(similarEmailMatchLctr).selectByVisibleText(newValue);
    }

    public void setMatchingIpAddressAndUserAgent(String newValue){
        new SelectElement(matchingByIpAndUserAgentLctr).selectByVisibleText(newValue);
    }

    public void setIpAddressOnly(String newValue){
        new SelectElement(ipAddressOnly).selectByVisibleText(newValue);
    }

    public void setFriendAndAdvocateReferEachOther(String newValue){
        new SelectElement(crossReferralLctr).selectByVisibleText(newValue);
    }

    private void setAllElements(){
        new SelectElement(crossReferralLctr);
        new SelectElement(similarEmailMatchLctr);
        new SelectElement(shippingAddressLctr);
        new SelectElement(matchingByIpAndUserAgentLctr);
        new SelectElement(ipAddressOnly);
        new SelectElement(crossReferralLctr);
    }


}
