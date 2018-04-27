package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.SelectElement;
import org.openqa.selenium.By;

public class SectionRulesForFriend extends AbstractElementsContainer{


    private static final By matchingCookieLctr = By.xpath("//select[@name = 'block_fr_by_visitor']");
    private static final By matchingByIpAndUserAgentLctr = By.xpath("//select[@name = 'block_fr_by_ip_and_user_agent']");
    private static final By ipAddressOnly = By.xpath("//select[@name = 'block_fr_by_ip']");
    private static final By similarEmailMatchLctr = By.xpath("//select[@name = 'block_fr_by_similar_emails']");
    private static final By crossReferralLctr = By.xpath("//select[@name = 'block_fr_cross_referral']");

    public SectionRulesForFriend(){
        setAllElements();
    }

    public String getMatchingCookieValue(){
        return new SelectElement(matchingCookieLctr).getSelectedItemText();
    }

    public String getSimilarEmailMatch(){
        return new SelectElement(similarEmailMatchLctr).getSelectedItemText();
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

    public void setMatchingCookieValue(String newValue){
        new SelectElement(matchingCookieLctr).selectByVisibleText(newValue);
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
        new SelectElement(matchingCookieLctr);
        new SelectElement(similarEmailMatchLctr);
        new SelectElement(matchingByIpAndUserAgentLctr);
        new SelectElement(ipAddressOnly);
        new SelectElement(crossReferralLctr);
    }


}
