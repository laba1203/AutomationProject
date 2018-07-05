package execution.integration.shopify;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.Invite;

@Listeners(util.Listeners.class)
public class ShopifyIntegrationTest extends BaseTest{

    private String user = PropertyLoader.loadProperty("talkable.user.shopifyIntegration");
    private String passwrd = EnvFactory.getPassword();
    private String shopifyUrl = PropertyLoader.loadEnvProperty("test.sites.shopifyIntegration");



    @Test
    public void integrateShopifySite(){
        String shopifyPlatform = "Shopify";
        CampaignType cType = Invite;
        CampaignPlacement placement = Standalone;

        //integration should be removed firstly:

        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(user, passwrd);
        CommonScenarios.openCampaignsPage();
        CommonScenarios.deactivateAllCampaigns();

        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.setUrlAndPlatformOnSiteSettings(shopifyUrl, shopifyPlatform);
        CommonScenarios.openSiteIntegrationPage();
        CommonScenarios.installShopifyApp(user, passwrd);
        CommonScenarios.openCampaignsPageAndCreateCampaign(Invite, Standalone);
        CommonScenarios.launchCampaign();
        Assert.assertEquals(
                ClientSiteScenarios.isCampaignOnCustomerSite(Invite, Standalone, shopifyUrl),
                true,
                "FAILED: <"+cType+"."+placement+">Campaign is not displayed in Shopify Shop <" + shopifyUrl + ">."
        );
        CommonScenarios.navigateToAdminUrl();
        CommonScenarios.openCampaignsPage();
        CommonScenarios.deactivateAllCampaigns();
        Assert.assertEquals(
                ClientSiteScenarios.isCampaignOnCustomerSite(Invite, Standalone, shopifyUrl),
                false,
                "FAILED: <"+cType+"."+placement+">Campaign is displayed in Shopify Shop <" + shopifyUrl + "> when it was deactivated."
        );

        //todo: should be completed


    }
}
