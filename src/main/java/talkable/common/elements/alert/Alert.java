package talkable.common.elements.alert;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.WaitFactory;
import util.logging.Log;

public class Alert extends AbstractElementsContainer {

    private org.openqa.selenium.Alert alert;

    public Alert(){
        alert = WaitFactory
                .getCustomWait(3, 500)
                .until(ExpectedConditions.alertIsPresent()
                );
    }

    public void confirm(){
        alert.accept();
        Log.alertAccepted();
    }

}
