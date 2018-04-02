package util;

public class EnvFactory {

    private static final String VOID_LINK = "void.talkable.com";
    private static final String PROD_LINK = "talkable.com";

    private static final String adminVoidUrl = "https://admin.void.talkable.com";

    private static final String registrationPath = "/register?object_or_array";

    private static final String user = "maxim.laba+automation.smoke.test@talkable.com";
    private static final String password = "Password@1";

    private static String getUrl(){
        //property described in pom.xml  <test.environment>${env.NAME}</test.environment>
        String env = System.getProperty("test.environment");
        if(env.equals("PROD")){
            return PROD_LINK;
        }
        else{
            return VOID_LINK;
        }
    }



    public static String getEnvUrl(){
        return "https://" + getUrl();

    }

    public static String getAdminUrl(){
        return "https://admin." + getUrl();
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
