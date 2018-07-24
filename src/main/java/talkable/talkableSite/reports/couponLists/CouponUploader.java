package talkable.talkableSite.reports.couponLists;

import abstractObjects.Element;
import org.openqa.selenium.By;
import util.TestArtifactsProvider;

interface CouponUploader{
    By uploadFileInputLctr = By.xpath("//input[@name='coupon_list[upload_file]']");
    By createNewCounListBtnLctr = By.xpath("//input[@name='commit']");
    By manualCouponListFieldLctr = By.xpath("//*[@name='coupon_list[coupons_codes]']");


    default AddCouponsPage uploadCcvFile(String fileName){
        new Element(uploadFileInputLctr, "Upload file input")
                .sendKeys(
                        TestArtifactsProvider.getCouponListsFilePath(fileName)
        );
        return new AddCouponsPage();
    }

    default AddCouponsPage populateCouponCodesManually(String couponList){
        new Element(manualCouponListFieldLctr, "Manual Coupon Codes field")
                .sendKeys(couponList);
        return new AddCouponsPage();
    }

    default CouponListPage saveChanges(){
        new Element(createNewCounListBtnLctr, "'Save' button")
                .click();
        return new CouponListPage();
    }

}
