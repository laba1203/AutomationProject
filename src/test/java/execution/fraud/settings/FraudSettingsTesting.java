package execution.fraud.settings;

import common.cases.CommonScenarios;
import common.cases.ReportsScenarios;
import execution.BaseTest;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;

public class FraudSettingsTesting extends BaseTest{

    @Test
    public void login(){
        CommonScenarios.login(
                PropertyLoader.loadProperty("talkable.user.fraudRules"),
                EnvFactory.getPassword());
    }

    @Test
    public void setHighFraudSettingsProfile(){
        CommonScenarios.openFraudSettings();


    }



}
