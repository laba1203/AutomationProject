package util;

import java.io.File;

public class TestArtifactsProvider {

    private static final String dirPath = "src/main/resources/testData/";
    private static final String imageDirPath = "src/main/resources/testData/images/";

    public static String getPreviousCustomerFilePath(String fileName){
        return new File(dirPath +"previousCustomersLists/" + fileName).getAbsolutePath();
    }

    public static String getImagesFilePath(String fileName){
        return new File(imageDirPath + fileName).getAbsolutePath();
    }

}
