package execution;

import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.DriverConfig;
import util.EnvFactory;

    public class BaseTest {
        public DriverConfig driverFactory;
        public WebDriver driver;


        @BeforeClass
        public void setup() {
            this.driverFactory = new DriverConfig();
            this.driver = this.driverFactory.getDriver();
            this.driver.navigate().to(EnvFactory.getEnvUrl());
        }


        @AfterClass
        public void quit() {
            driver.quit();
            driverFactory.cleanWebDriver();
            System.out.println("After class executed");
        }
    }


