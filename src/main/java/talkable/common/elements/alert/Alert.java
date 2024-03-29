package talkable.common.elements.alert;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import util.WaitFactory;
import util.logging.Log;

public class Alert extends AbstractElementsContainer {

    private org.openqa.selenium.Alert alert;

    public Alert(){
        alert = waitFactory()
                .getCustomWait(3, 500)
                .until(ExpectedConditions.alertIsPresent()
                );
    }

    public void confirm(){
        String alertMsg = alert.getText();
        alert.accept();
        Log.logRecord("Click OK in Alert with message <" + alertMsg + ">.");
    }

    public Alert verifyAlertMsg(String expectedMsg){
        Assert.assertEquals(
                alert.getText(),
                expectedMsg,
                "Incorrect Alert message."
        );
        return this;
    }

}
