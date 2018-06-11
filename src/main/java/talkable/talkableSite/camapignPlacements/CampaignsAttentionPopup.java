package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class CampaignsAttentionPopup extends AbstractElementsContainer{
    private static final By deactivateLctr = By.xpath("//*[text() = 'Deactivate']");

    CampaignsAttentionPopup(){
        new Element(deactivateLctr);
    }

    void deactivate(){
        new Element(deactivateLctr, "Deactivate button").click();
    }
}
