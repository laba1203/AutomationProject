package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.logging.Log;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Screenshot {


    private static final String PATH_TO_SAVE = "src/test/output/screenshots/";
    private Log log = new Log();
    private String filePath;
    
    public void getScreenshot(){
        File srcFile = ((TakesScreenshot)new DriverConfig().getDriver()).getScreenshotAs(OutputType.FILE);
        copyFile(srcFile);
        System.out.println(log.getScreenshotMsg() + filePath);
    }

    private void copyFile(File file){
        filePath = PATH_TO_SAVE + "Screenshot_" + getTimeStamp() + ".png";
        try {
            FileUtils.copyFile(file, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTimeStamp() {
        return String.valueOf(new Timestamp(System.currentTimeMillis()));
    }

}
