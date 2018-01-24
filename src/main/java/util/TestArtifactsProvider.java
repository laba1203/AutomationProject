package util;

import java.io.File;

public class TestArtifactsProvider {

    private static final String dirPath = "src/main/resources/testData/";

    static public String getPreviousCustomerFilePath(String fileName){
        return new File(dirPath +"previousCustomersLists/" + fileName).getAbsolutePath();
    }

}
