package util;

import java.io.IOException;
import java.util.Properties;


public class PropertyLoader {

    private static final String PROP_FILE = "/application.properties";
    //
    private static final String VOID_PROP_FILE = "/void.properties";
    private static final String PROD_PROP_FILE = "/prod.properties";

    private PropertyLoader() {}

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

    public static String loadEnvProperty(String name){
        Properties props;
        //property described in pom.xml  <test.environment>${env.NAME}</test.environment>
        String env = getMavenEnvName();
        if(env.equals("PROD")){
            props = getPropertiesFile(PROD_PROP_FILE);
            System.out.println("DEBAG: Property loaded from prod.properties file. Property name: <" + name + ">");
        }
        else {
            props = getPropertiesFile(VOID_PROP_FILE);
            System.out.println("DEBAG: Property loaded from void.properties file. Property name: <" + name + ">");
        }
        String value = "";

        if (name != null) {
            assert props != null;
            value = props.getProperty(name);
        }
        return value;
    }

    private static Properties getPropertiesFile(String fileName){
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(fileName));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMavenEnvName(){
        //property described in pom.xml  <test.environment>${env.NAME}</test.environment>
        //correct working:
//        String env = System.getProperty("test.environment");
        //for local running of the project:
//        String env = "VOID";
        String env = "PROD";
//
        System.out.println("LOG - PropertyLoader: Test is running on <" + env + "> environment");
        return env;

    }




}