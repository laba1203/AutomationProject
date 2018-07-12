package talkable.talkableSite.reports.couponLists;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import util.TestArtifactsProvider;

public class AddCouponsPage extends AbstractTalkableSitePage{

    private static final By uploadFileInputLctr = By.xpath("//input[@name='coupon_list[upload_file]']");
    private static final By createNewCounListBtnLctr = By.xpath("//input[@name='commit']");
    private static final By manualCouponListFieldLctr = By.xpath("//*[@name='coupon_list[coupons_codes]']");


    private Element uploadFileInput = new Element(uploadFileInputLctr, "Upload file input");
    private Element saveBtn = new Element(createNewCounListBtnLctr, "'Save' button");
    private Element manualCouponListField = new Element(manualCouponListFieldLctr, "Manual Coupon Codes field");


    public AddCouponsPage uploadCcvFile(String fileName){
        uploadFileInput.sendKeys(
                TestArtifactsProvider.getCouponListsFilePath(fileName)
        );
        return new AddCouponsPage();
    }

    public AddCouponsPage populateCouponCodesManually(String couponList){
        manualCouponListField.sendKeys(couponList);
        return new AddCouponsPage();
    }

    public CouponListPage saveChanges(){
        saveBtn.click();
        return new CouponListPage();
    }
}
