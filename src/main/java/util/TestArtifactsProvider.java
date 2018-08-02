package util;

import java.io.File;

public class TestArtifactsProvider {

    private static final String dirPath = "src/main/resources/testData/";
    private static final String previousCustomersPath = dirPath + "previousCustomersLists/";
    private static final String imageDirPath = dirPath + "images/";
    private static final String couponCodesListsPath = dirPath + "couponCodeLists/";
    private static final String fontPath = dirPath + "fonts/";

    private static String getAbsolutePath(String filePath, String fileName){
        return new File(filePath + fileName).getAbsolutePath();
    }

    public static String getPreviousCustomerFilePath(String fileName){
//        return new File(previousCustomersPath + fileName).getAbsolutePath();
        return getAbsolutePath(previousCustomersPath, fileName);
    }

    public static String getImagesFilePath(String fileName){
        return getAbsolutePath(imageDirPath, fileName);
    }

    public static String getCouponListsFilePath(String fileName){
        return getAbsolutePath(couponCodesListsPath, fileName);
    }

    public static String getFontsFilePath(String fileName){
        return getAbsolutePath(couponCodesListsPath, fileName);
    }

}
