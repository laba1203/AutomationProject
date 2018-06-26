package talkable.talkableSite.customerServicePortal.personLookup;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

public class ReferralDetailsSection extends AbstractElementsContainer{
    private static final By advocateRewardStatusLctr = By.xpath("//div[contains(@class, 'is-advocate')]//div[contains(@class, 'rewards')]//span[contains(@class, 'value')]");
    private static final By friendRewardStatusLctr = By.xpath("//div[contains(@class, 'is-friend')]//div[contains(@class, 'rewards')]//span[contains(@class, 'value')]");
    private static final By friendEmailLctr = By.xpath("//div[contains(@class, 'is-friend')]//li[contains(@class, 'record-email')]/span");

    private Element friendEmail = new Element(friendEmailLctr);
    private Element advocateRewardStatus = new Element(advocateRewardStatusLctr);

    public String getAdvocateRewardStatus(){
        advocateRewardStatus.moveMouseOver();
        return advocateRewardStatus.getText();
    }

    public String getFriendRewardStatus(){
        return new Element(friendRewardStatusLctr).getText();
    }

    public String getFriendEmail(){
        return friendEmail.getText();
    }

}
