import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.Util;

public class Testing {//extends BaseTest{


//    @Test
//    public void test(){
//        driver.navigate().to("https://admin.void.talkable.com/sites/custom2501/");
//        Assert.fail("FAILED");
//    }

    @Test
    public void test2(){
        String s = Util.removeParametersFromUrl("https://di6re4dxelnn2.cloudfront.net/static_assets/files/95993/original/tkbl_default_icon-link-color-2x.png?localization_key=advocate_signup_page_background");
        s = Util.getLastUrlResource(s);
        System.out.println(s);
    }

}
