package testDataCreation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import util.DriverConfig;

public class FriendOffersGenerator {
    private WebDriver driver;

    @Test
    public void test1_makeImpression() {
        driver = new DriverConfig().getDriver();
        //put below advocate share link
        driver.navigate().to("https://void.talkable.com/x/sWPJRc");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        driver.quit();
        DriverConfig.quitAndRemoveWebDriver();
    }

    @Test
    public void repeat(){

        for (int i = 0; i<5000; i++) {
            test1_makeImpression();
            System.out.println("\n\rCounter: " + i);
        }
    }
}
