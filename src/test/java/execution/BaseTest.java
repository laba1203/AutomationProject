package execution;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import util.DriverConfig;
import util.EnvFactory;

    public class BaseTest {
        public DriverConfig driverFactory;
        public WebDriver driver;


        @BeforeSuite
        public void setup() {
            this.driverFactory = new DriverConfig();
            this.driver = this.driverFactory.getDriver();
            this.driver.navigate().to(EnvFactory.getEnvUrl());
        }


        @AfterSuite
        public void quit() {
            driver.quit();
            driverFactory.cleanWebDriver();
            System.out.println("After class executed");
        }
    }


