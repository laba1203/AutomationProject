package abstractObjects;

import org.testng.Assert;
import talkable.talkableSite.reports.couponLists.CouponListPage;
import talkable.talkableSite.reports.couponLists.CouponListsReportPage;

import java.util.ArrayList;

@Deprecated
public class AbstractTable extends AbstractElementsContainer{

//    private CouponListsReportPage.CouponListRow findRow(String name){
//        for (CouponListsReportPage.CouponListRow row :
//                getRows()) {
//            if (row.getListName().equals(name)){
//                return row;
//            }
//        }
//        Assert.fail("Coupon list with name <" + name + "> was not found on the Coupon Lists report page.");
//        return null;
//    }
//
//    private ArrayList<CouponListPage.Row> getRows(){
//        ArrayList<CouponListPage.Row> rows = new ArrayList<>();
//        for (Element element :
//                getElementsList(couponListsRows)) {
//            rows.add(new CouponListPage.Row(element));
//        }
//        return rows;
//    }



}
