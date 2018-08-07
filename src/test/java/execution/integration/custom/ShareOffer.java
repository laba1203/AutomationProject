package execution.integration.custom;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import common.cases.ExternalScenarios;
import common.cases.functionalities.FraudRulesScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.Util;
import util.logging.Log;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.common.CampaignType.Invite;


public class ShareOffer extends BaseTest{
    private String tkblUser = PropertyLoader.loadProperty("talkable.user.customIntegration");
    private String tkblPswrd = EnvFactory.getPassword();
    private String gitHubUserName = PropertyLoader.loadProperty("github.user");
    private String gitHubPswrd = PropertyLoader.loadProperty("github.password");
    private String siteLocation = "scenario/";
    private String sitePage = "index.html";
    private String gitHubEditorPage = EnvFactory.getGitHubEditLink() + siteLocation + sitePage;
    private String customerSiteUrl = EnvFactory.getGitHubSiteLink() + siteLocation;


    @Test
    public void shareOfferByLink(){
        String tkblSiteUrl;
        String tkblIntegrationScript;
        String shareUrl;
        String advocateEmail = "advocate.auto"+ TestDataGenerator.getRandomId()+"@gmail.com";
        String fiendEmail = "firnd.auto"+ TestDataGenerator.getRandomId()+"@gmail.com";

        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(tkblUser, tkblPswrd, customerSiteUrl);
        tkblSiteUrl = CommonScenarios.getCurrentUrl();
        tkblIntegrationScript = CommonScenarios
                .getSiteIntegrationValues()
                .getTalkableIntegrationScript();
        String jsLibResource = CommonScenarios.getJSLibResourceFromIntegrationPage();
        tkblIntegrationScript += jsLibResource;
        ExternalScenarios.editGiHubFile(
                gitHubEditorPage,
                gitHubUserName,
                gitHubPswrd,
                Util.generateItegratedPageCode(tkblIntegrationScript));
        CommonScenarios.navigateToUrl(tkblSiteUrl);
        CommonScenarios.openCampaignsPage();
        CommonScenarios.createAndLaunchCampaign(Invite, FloatingWidget);
        try {
            Assert.assertEquals(
                    ClientSiteScenarios.isCampaignOnCustomerSite(Invite, FloatingWidget, customerSiteUrl + sitePage),
                    true,
                    "Campaign is not displayed on the customer site: " + customerSiteUrl + sitePage
            );
        }catch (AssertionError e){
            Log.debagRecord("Not displayed from the first time.");
            driver.navigate().refresh();
            Assert.assertEquals(
                    ClientSiteScenarios.isCampaignOnCustomerSite(Invite, FloatingWidget, customerSiteUrl + sitePage), true,
                    "Campaign is not displayed on the customer site: " + customerSiteUrl + sitePage
            );
        }

        shareUrl = ClientSiteScenarios.completeAdvocateOfferForFloatingWidget("advocate.from.autotest", advocateEmail);
        CommonScenarios.navigateToUrl(tkblSiteUrl);
        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setMatchingCookiesOnFriendClaimPageValue("Skip");
        driver.manage().deleteAllCookies();
        CommonScenarios.navigateToUrl(shareUrl);
        String couponCode = ClientSiteScenarios.getDiscountFromFrindSignUpPage(fiendEmail);
        Assert.assertEquals(
                couponCode,
                "FR_NEW_5_OFF",
                "Incorrect coupon code on the Friend Claim page. Expected default coupon code."
        );

    }



}
