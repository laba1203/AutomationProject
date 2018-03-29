package execution.editor.mce;

import execution.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1P-VCSCq04_lqZQXhbhEm09duImidJBJGbIaOnmIlLDg
 * */
public class MceAdditionalScenarios extends BaseTest{

    //ToDo: test cases for filtering and preview verification


    @BeforeClass
    public void testDataSetup(){
        // create test data
        // open MCE for PP campaign
    }


    @Test(groups = "filtering")
    public void test11_filterByStatus(){

    }

    @Test(groups = "filtering")
    public void test12_filterByCampaignName(){

    }

    @Test(groups = "filtering")
    public void test13_filterByCampaignId(){
    //??????? to be discussed do we need this test?
    }

    @BeforeGroups("preview")
    public void test21_selectMultipleCampaigns(){

    }

    @Test(groups = "preview")
    public void test22_preview(){

    }

    @AfterClass
    public void deactivateAndDelete(){

    }

}
