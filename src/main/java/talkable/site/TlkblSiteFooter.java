package talkable.site;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.site.contact.us.ContactUsPage;

public interface TlkblSiteFooter {
    By contactUsLcrtr = By.xpath("//*[@class='footer-nav']//a[text()='Contact Us']");

    default ContactUsPage openContactUsPage(){
        new Element(contactUsLcrtr, "Contact Us button").click();
        return new ContactUsPage();
    }


}
