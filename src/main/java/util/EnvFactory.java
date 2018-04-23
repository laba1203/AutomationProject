package util;

public class EnvFactory {

    private static final String VOID_LINK = "void.talkable.com";
    private static final String PROD_LINK = "talkable.com";

    private static final String COMMON_USER = PropertyLoader.loadProperty("talkable.user");
    private static final String REPORTS_USER = PropertyLoader.loadProperty("talkable.user.reports");

    private static final String password = PropertyLoader.loadProperty("talkable.password");
    private static final String registrationPath = "/register?object_or_array";

    private static String getUrl(){
        //property described in pom.xml  <test.environment>${env.NAME}</test.environment>
        String env = PropertyLoader.getMavenEnvName();
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

    public static String getCommonUser(){
        return COMMON_USER;
    }

    public static String getReportsUser() {
        return REPORTS_USER;
    }

    public static String getPassword(){
        return password;
    }

    public static String getRegistrationURL(){
        return getAdminUrl() + registrationPath;
    }
}
