package execution.integration.shopify;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;

public class ShopifyIntegrationTest extends BaseTest{

    private String user = PropertyLoader.loadProperty("talkable.user.shopifyIntegration");
    private String passrd = EnvFactory.getPassword();
    private String shopifyUrl = PropertyLoader.loadEnvProperty("test.sites.shopifyIntegration");

    @Test
    public void integrateShopifySite(){
        String shopifyPlatform = "Shopify";

        CommonScenarios.login(user, passrd);
        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.setUrlAndPlatformOnSiteSettings(shopifyUrl, shopifyPlatform);
        CommonScenarios.openSiteIntegrationPage();

    }
}
