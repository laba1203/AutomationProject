package util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.testng.*;
import util.logging.Log;

public class Listeners implements IInvokedMethodListener, ITestListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod() && ITestResult.FAILURE == result.getStatus()) {
            Throwable throwable = result.getThrowable();
            String originalMessage = throwable.getMessage();
            String newMessage = "FAILED: " + originalMessage +
                    "\r\nTest failed on URL: " + DriverConfig.getDriver().getCurrentUrl() +"\r\n" +
                    "Screenshot: " + new Screenshot().makeScreenshot();
            try {
                FieldUtils.writeField(throwable, "detailMessage", newMessage, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(method.isTestMethod() && ITestResult.SUCCESS == result.getStatus()){
            Log.testPassed("Test method: " + method.getTestMethod().getMethodName());
        }
    }


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
