package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import abstractObjects.SelectElement;
import org.openqa.selenium.By;

public class SectionRulesForFriend extends AbstractElementsContainer{


    private static final By matchingCookieLctr = By.xpath("//select[@name = 'block_fr_by_visitor']");
    private static final By matchingByIpAndUserAgentLctr = By.xpath("//select[@name = 'block_fr_by_ip_and_user_agent']");
    private static final By ipAddressOnly = By.xpath("//select[@name = 'block_fr_by_ip']");
    private static final By similarEmailMatchLctr = By.xpath("//select[@name = 'block_fr_by_similar_emails']");
    private static final By crossReferralLctr = By.xpath("//select[@name = 'block_fr_cross_referral']");

    private static final By matchingCookieRowLctr = By.xpath("//div[@data-trigger='block_fr_by_visitor']");
    private static final By combinationIpAndAgentRowLctr = By.xpath("//div[@data-trigger='block_fr_by_ip_and_user_agent']");
    private static final By ipAddressOnlyRowLctr = By.xpath("//div[@data-trigger='block_fr_by_ip']");
    private static final By similarEmailMatchRowLctr = By.xpath("//div[@data-trigger = 'block_fr_by_similar_emails']");
    private static final By crossReferralRowLctr = By.xpath("//div[@data-trigger='block_fr_cross_referral']");

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
        new Element(matchingCookieRowLctr, "'Matching Cookie on Friend Claim Page' row").moveToElementAndClick();
        new SelectElement(matchingCookieLctr).selectByVisibleText(newValue);
    }

    public void setSimilarEmailMatch(String newValue){
        new Element(similarEmailMatchRowLctr, "'Similar Email Match' row").moveToElementAndClick();
        new SelectElement(similarEmailMatchLctr).selectByVisibleText(newValue);
    }

    public void setMatchingIpAddressAndUserAgent(String newValue){
        new Element(combinationIpAndAgentRowLctr, "'Matching by Combination of IP Address & User Agent' row").moveToElementAndClick();
        new SelectElement(matchingByIpAndUserAgentLctr).selectByVisibleText(newValue);
    }

    public void setIpAddressOnly(String newValue){
        new Element(ipAddressOnlyRowLctr, "'Matching by IP Address only' row").moveToElementAndClick();
        new SelectElement(ipAddressOnly).selectByVisibleText(newValue);
    }

    public void setFriendAndAdvocateReferEachOther(String newValue){
        new Element(crossReferralRowLctr, "'Friend and Advocate Refer Each Other' row").moveToElementAndClick();
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
