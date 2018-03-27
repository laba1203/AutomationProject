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
    private String absoluteFilePath;
    
    public void getScreenshot(){
        File srcFile = ((TakesScreenshot)new DriverConfig().getDriver()).getScreenshotAs(OutputType.FILE);
        copyFile(srcFile);
        //
        Log.getScreenshotMsg(absoluteFilePath);
    }

    private void copyFile(File file){
        String filePath = PATH_TO_SAVE + "Screenshot_" + getTimeStamp() + ".png";
        try {
            File newFile = new File(filePath);
            FileUtils.copyFile(file, newFile);
            absoluteFilePath = newFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTimeStamp() {
        return String.valueOf(new Timestamp(System.currentTimeMillis()));
    }

}
