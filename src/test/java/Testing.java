import org.testng.annotations.Test;
import util.Util;

public class Testing {//extends BaseTest{


//    @Test
//    public void test(){
//        driver.navigate().to("https://admin.void.talkable.com/sites/custom2501/");
//        Assert.fail("FAILED");
//    }


    @Test
    public void test4(){
//        Util.getDifference("http://learn.talkable.com/QA-Max/Prod/home.htmlSpecific site pages", "Specific site pages");

        System.out.println(Util.cutFirstPartOfString("http://learn.talkable.com/QA-Max/Prod/home.htmlSpecific site pages", "http://learn.talkable.com/"));
    }

}
