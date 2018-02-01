package talkable.userRegistration.chosePlatformPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.DrivenElement;
import util.logging.Log;

public class ChosePlatformPage extends AbstractElementsContainer{

    private ElmntShopifyButton shopifyButton;
    private ElmntDemandwareButton demandwareButton;
    private ElmntMagentoButton magentoButton;
    private ElmntOtherButton otherButton;

    public enum PlatformType{
        SHOPIFY, MAGENTO, DEMANDWARE, OTHER
    }

    public ChosePlatformPage(){
        shopifyButton = new ElmntShopifyButton();
        demandwareButton = new ElmntDemandwareButton();
        magentoButton = new ElmntMagentoButton();
        otherButton = new ElmntOtherButton();
    }

    public void selectPlatform(PlatformType platformType){
        getButton(platformType).click();
        Log.pageNotOpenedMsg(platformType);
    }


    private DrivenElement getButton(PlatformType platform) {
        DrivenElement button = null;

        switch (platform) {
            case SHOPIFY:
                button = shopifyButton;
                break;

            case MAGENTO:
                button = magentoButton;
                break;

            case DEMANDWARE:
                button = demandwareButton;
                break;

            case OTHER:
                button = otherButton;
                break;
        }
        return button;
    }

}
