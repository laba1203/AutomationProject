package util;

import org.testng.annotations.Parameters;

public class EnvFactory {

    private static final String voidUrl = "https://void.talkable.com";
    private static final String adminVoidUrl = "https://admin.void.talkable.com";
    private static final String prodUrl = "https://talkable.com";
    private static final String registrationPath = "/register?object_or_array";

    private static final String user = "maxim.laba+automation.smoke.test@talkable.com";
    private static final String password = "Password@1";

    @Parameters()
    public static String getEnvUrl(){
        return voidUrl;
    }

    public static String getAdminUrl(){
        return adminVoidUrl;
    }

    public static String getUser(){
        return user;
    }

    public static String getPassword(){
        return password;
    }

    public static String getRegistrationURL(){
        return getAdminUrl() + registrationPath;
    }
}
