package talkable.access.managment.access.request;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.headerFrame.Header;
import util.logging.Log;

public class AccessRequestPage extends AbstractElementsContainer{

//    private static final By titleLctr = By.xpath("//h2[contains(text(), 'You don’t have  access to')]");
    private static final By oneDayAccessBtnLctr = By.xpath("//input[@name='commit']");
    private static final By onGoingAccessBtnLctr = By.xpath("//input[@name='ongoing']");
    private static final By accessPiiCheckboxLctr = By.xpath("//label[@for='access_pii']");
    private static final By seeYourCurrentAccessLctr = By.xpath("//*[text()='See your current access']");
    private static final By requestAccessSubmittedMsg = By.xpath("//*[text()='Access request submitted']");
    private static final By piiAccessRequiredMcsg = By.xpath("//*[text() = 'PII access is required']");

    public enum TermOfAccess{ON_GOING, ONE_DAY}
    public enum PiiAccess{NON_PII, PII}

    private Element oneDayAccessBtn = new Element(oneDayAccessBtnLctr, "Request 1-day access");
    private Element onGoingAccessBtn = new Element(onGoingAccessBtnLctr, "Request ongoing access");
    private Element piiCheckbox = new Element(accessPiiCheckboxLctr, "Access to PII");
    private Element seeYourCurrentAccessBtn = new Element(seeYourCurrentAccessLctr, "See your current access");

//    public AccessRequestPage(){
//        new Element(titleLctr);
//    }

    public Header requestAccessWithAutoApproval(TermOfAccess term, PiiAccess pii){
        switchPiiCheckBox(pii)
                .selectTerm(term);
        return new Header();
    }

    private AccessRequestPage switchPiiCheckBox(PiiAccess pii){
        switch (pii){
            case PII:
                piiCheckbox.click();
                break;
            case NON_PII:
                Log.logRecord("PII checkbox is not selected.");
                break;
        }
        return new AccessRequestPage();
    }

    private void selectTerm(TermOfAccess term){
        switch (term){
            case ONE_DAY:
                oneDayAccessBtn.click();
                break;
            case ON_GOING:
                onGoingAccessBtn.click();
                break;
        }
        waitRequestSubmission();
    }

    private void waitRequestSubmission(){
        new Element(requestAccessSubmittedMsg);
    }

    public AccessRequestPage verifyPiiAccessRequiredMsg(){
        new Element(piiAccessRequiredMcsg);
        Log.logRecord("Access request page with message \"PII Access is required.\" is displayed.");
        return new AccessRequestPage();
    }

}
