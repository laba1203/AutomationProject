
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.createNewCampaignPage.CreateNewCampaignPage;
import talkable.headerFrame.Header;
import talkable.loginPage.LoginPage;
import talkable.headerFrame.elements.menuFrame.MenuFrame;
import talkable.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.reports.reportsPage.ReportsPage;
import util.DriverConfig;


public class FirstTest {

    WebDriver driver;

    CommonScenarios commonScenarios = new CommonScenarios();

//    LoginPage loginPage;
//    Header header;
//    MenuFrame menuFrame;
//    CreateNewCampaignPage createNewCampaignPage;

    private String siteName = "automationSite";

    @BeforeClass
    public void setup(){

        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://void.talkable.com");
    }

    @Test
    public void test1_login(){
        commonScenarios.login("maxim.laba@talkable.com", "Password@1");
//        HomePage homePage = new HomePage();
//        homePage.loginButton.click();
//        loginPage = new LoginPage();
//        loginPage.submitLoginForm("maxim.laba@talkable.com", "Password@1");
    }

    @Test
    public void test2(){
        Header header = new Header();
        driver.navigate().to("https://admin.void.talkable.com/sites/test1101/");
    }

//    @Test
//    public void test3(){
////        commonScenarios.initiateCampaignCreation(AdvocateDashboard, FloatingWidget);
//
////        commonScenarios.launchCampaign();
//
//    }

//    @Test
//    public void test3() throws InterruptedException {
//        Thread.sleep(1000);
//        new CampaignNavigationMenu().editorButton.click();
//        EditorPage editorPage = new EditorPage();
//        editorPage.switchViewByIndex(2);
//        Thread.sleep(1000);
//        editorPage.localizationSidebar.copyButton.click();
//
//
//    }

    @Test
    public void test3(){
        Header header = new Header();
        header.reportsButton.click();
        ReportsPage reportsPage = new ReportsPage();
        reportsPage.previousCustomersButton.click();
        PreviousCustomersReportPage previousCustomersReport = new PreviousCustomersReportPage();
        previousCustomersReport.uploadFile("existingCustomers.csv");
    }

    @Test
    public void test4(){
        PreviousCustomersReportPage previousCustomersReport = new PreviousCustomersReportPage();
        System.out.println("Count: <" + previousCustomersReport.getUploadedListsCount() + ">");
        System.out.println("-----------------");
        System.out.println(previousCustomersReport.getFirstRowValuesFromUploadedCSVLists().toString());
        System.out.println("-----------------");
        System.out.println(previousCustomersReport.getUploadedCSVListsTable().getFileName(1));
        System.out.println("-----------------");
        System.out.println(previousCustomersReport.getUploadedCSVListsTable().getProgress(1));
        System.out.println("-----------------");
        System.out.println(previousCustomersReport.getUploadedCSVListsTable().getEmailsUploaded(1));
        System.out.println("-----------------");
        System.out.println(previousCustomersReport.getUploadedCSVListsTable().getStatus(1));
    }


//    @Test
//    public void test2_switchToSite(){
//        header = new Header();
//        header.switchSiteTo("automationSite");
//        System.out.println(header.siteSelectButton.getText());
//    }




}
