package talkable.talkableSite.reports.couponLists;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaignsPage.Table;

import java.util.ArrayList;

public class CouponListsReportPage extends AbstractTalkableSitePage{

    private static final By reportHeaderLctr = By.xpath("//h2[text()='Coupon Lists']");
    private static final By createNewBtnLctr = By.xpath("//*[text()='Create new']");
    private static final By couponListsRows = By.xpath("//tbody/tr");

    private Element createNewBtn = new Element(createNewBtnLctr, "'Create new' button");

    public CouponListsReportPage(){
        new Element(reportHeaderLctr);
    }

    public NewCouponListPage clickCreateNewList(){
        createNewBtn.click();
        return new NewCouponListPage();
    }

    public CouponListPage openCouponList(String listName){
        findRow(listName)
                .clickToName();
        return new CouponListPage();
    }

    private CouponListRow findRow(String name){
        for (CouponListRow row :
                getRows()) {
            if (row.getListName().equals(name)){
                return row;
            }
        }
        Assert.fail("Coupon list with name <" + name + "> was not found on the Coupon Lists report page.");
        return null;
    }

    private ArrayList<CouponListRow> getRows(){
        ArrayList<CouponListRow> rows = new ArrayList<>();
        for (Element element :
                getElementsList(couponListsRows)) {
            rows.add(new CouponListRow(element));
        }
        return rows;
    }

    class CouponListRow{

        private By listNameLctr = By.xpath("./td[1]/a");

        WebElement element;


        CouponListRow(Element element){
            this.element = element.getWebElement();
        }

        String getListName(){
            return new Element(element.findElement(listNameLctr)).getText();
        }

        void clickToName(){
            new Element(element.findElement(listNameLctr)).click();
        }



    }







}
