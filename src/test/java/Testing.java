import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing extends BaseTest{


    @Test
    public void test(){
        driver.navigate().to("https://admin.void.talkable.com/sites/custom2501/");
        Assert.fail("FAILED");
    }

}
