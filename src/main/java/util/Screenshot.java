package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import util.logging.Log;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

public class Screenshot {


    private static final String PATH_TO_SAVE = "src/test/output/screenshots/";
    private static boolean previousFiles = true;
    private String absoluteFilePath;
    private String fileName;
    
    public String makeScreenshot(){
        deleteExistingScreenshots();
        try {
            File srcFile = ((TakesScreenshot) DriverConfig.getDriver()).getScreenshotAs(OutputType.FILE);
            copyFile(srcFile);
        }catch (UnhandledAlertException e){
            Log.debagRecord("Failed to make a screenshot when Alert Message is returned in browser:");
            System.err.println(e.getAlert());
        }

//        String pathToScreenshot = "file://" + absoluteFilePath;
//        Log.getScreenshotMsg("<a href=\"" + pathToScreenshot + "\">"+fileName+"</a>");

        return "file://" + absoluteFilePath;
    }
    private void deleteExistingScreenshots(){
        if(previousFiles) {
            File directory = new File(PATH_TO_SAVE);
            if (directory.exists()) {
                for (File file : Objects.requireNonNull(directory.listFiles())) {
                    file.delete();
                }
            }
            previousFiles = false;
            Log.logRecord("Screenshots from previous execution have been deleted.");
        }
    }

    private void copyFile(File file){
        fileName =  "Screenshot_" + getTimeStamp() + ".png";
        String filePath = PATH_TO_SAVE + fileName;
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
