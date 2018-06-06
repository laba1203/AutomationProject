package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import abstractObjects.SelectElement;
import org.openqa.selenium.By;

public class SectionRulesForAdvocate extends AbstractElementsContainer{

    private static final By matchingEmailOrCookieLctr = By.xpath("//select[@name = 'self_referral_by_email_or_visitor']");
    private static final By similarEmailMatchLctr = By.xpath("//select[@name = 'self_referral_by_similar_emails']");
    private static final By shippingAddressLctr = By.xpath("//select[@name = 'self_referral_by_shipping_address']");
    private static final By matchingByIpAndUserAgentLctr = By.xpath("//select[@name = 'self_referral_by_ip_and_user_agent']");
    private static final By ipAddressOnly = By.xpath("//select[@name = 'self_referral_by_ip']");
    private static final By crossReferralLctr = By.xpath("//select[@name = 'cross_referral']");

    private static final By similarEmailRowLctr = By.xpath("//div[@data-trigger = 'self_referral_by_similar_emails']");
    private static final By emailOrCookieRowLctr = By.xpath("//div[@data-trigger = 'self_referral_by_email_or_visitor']");
    private static final By shippingAddrRowLctr = By.xpath("//div[@data-trigger = 'self_referral_by_shipping_address']");
    private static final By combinationIpAndAgetnRowLctr = By.xpath("//div[@data-trigger = 'self_referral_by_ip_and_user_agent']");
    private static final By ipOnlyRowLctr = By.xpath("//div[@data-trigger = 'self_referral_by_ip']");
    private static final By crossRefferalRowLctr = By.xpath("//div[@data-trigger = 'cross_referral']");



    SectionRulesForAdvocate(){
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
        new Element(emailOrCookieRowLctr, "'Matching Email or Cookie on Friend Purchase' row").click();
        new SelectElement(matchingEmailOrCookieLctr).selectByVisibleText(newValue);
    }

    public void setMatchingShippingAddress(String newValue){
        new Element(shippingAddrRowLctr, "'Matching Shipping Address' row").click();
        new SelectElement(shippingAddressLctr).selectByVisibleText(newValue);
    }

    public void setSimilarEmailMatch(String newValue){
        new Element(similarEmailRowLctr, "'Similar Email Match' row").click();
        new SelectElement(similarEmailMatchLctr).selectByVisibleText(newValue);
    }

    public void setMatchingIpAddressAndUserAgent(String newValue){
        new Element(combinationIpAndAgetnRowLctr, "'Matching by Combination of IP Address & User Agent' row").click();
        new SelectElement(matchingByIpAndUserAgentLctr).selectByVisibleText(newValue);
    }

    public void setIpAddressOnly(String newValue){
        new Element(ipOnlyRowLctr, "'Matching by IP Address only' row").click();
        new SelectElement(ipAddressOnly).selectByVisibleText(newValue);
    }

    public void setFriendAndAdvocateReferEachOther(String newValue){
        new Element(crossRefferalRowLctr, "'Friend and Advocate Refer Each Other' row").click();
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
