package talkable.site.handbook;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class GetHandbookPage extends AbstractElementsContainer{
    private static final By firstNameLctr = By.xpath("//*[@name='firstname']");
    private static final By lastNameLctr = By.xpath("//*[@name='lastname']");
    private static final By emailLctr = By.xpath("//*[@name='email']");
    private static final By webSiteLctr = By.xpath("//*[@name='website']");
    private static final By monthlyWebsiteViewsLctr = By.xpath("//*[@name='monthly_website_views']");
    private static final By submitLctr = By.xpath("//*[@type='submit']");

    public DownloadHandbookPage submitForm(String firstName, String lastName, String webSiteUrl, String email, String averageMonthlyWebsiteViews){
        new Element(firstNameLctr, "First Name").sendKeys(firstName);
        new Element(lastNameLctr, "Last Name").sendKeys(lastName);
        new Element(emailLctr, "Email").sendKeys(email);
        new Element(webSiteLctr, "Web Site").sendKeys(webSiteUrl);
        new Select(new Element(monthlyWebsiteViewsLctr).getWebElement())
                .selectByVisibleText(averageMonthlyWebsiteViews);
        new Element(submitLctr, "Submit").click();

        return new DownloadHandbookPage();
    }

}
