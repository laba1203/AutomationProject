package talkable.site.contact.us;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.site.TlkblSiteFooter;

public class ContactUsPage extends AbstractElementsContainer implements TlkblSiteFooter {

    private static final By firstNameLctr = By.xpath("//*[@name='first_name']");
    private static final By lastNameLctr = By.xpath("//*[@name='last_name']");
    private static final By companyLctr = By.xpath("//*[@name='company']");
    private static final By emailLctr = By.xpath("//*[@name='email']");
    private static final By phoneLctr = By.xpath("//*[@name='phone']");
    private static final By questionLctr = By.xpath("//*[@id='description']");
    private static final By contactUsBtnLctr = By.xpath("//*[@name='submit']");
    private static final By thanlYouMsgLctr = By.xpath("//*[contains(text(), 'Thank you, we will contact you shortly!')]");
    private static final By recaptchaLctr = By.xpath("//*[@class = 'recaptcha-checkbox-checkmark']");


    public ContactUsPage populateForm(String firstName, String lastName, String company, String email, String phone, String question){
        new Element(firstNameLctr, "First name").sendKeys(firstName);
        new Element(lastNameLctr, "Last name").sendKeys(lastName);
        new Element(companyLctr, "Company").sendKeys(company);
        new Element(emailLctr, "Email").sendKeys(email);
        new Element(phoneLctr, "Phone Number field").sendKeys(phone);
        new Element(questionLctr, "Question/Comment").sendKeys(question);
        new Element(recaptchaLctr, "reCAPTCHA checkbox").click();

        return new ContactUsPage();
    }

    public ContactUsPage submit(){
        new Element(contactUsBtnLctr, "Contact Us button").click();
        return assertThankYouMsg();
    }

    private ContactUsPage assertThankYouMsg(){
        new Element(thanlYouMsgLctr, "Thank you message");
        return new ContactUsPage();
    }

}
