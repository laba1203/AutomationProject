package util;

import java.io.IOException;
import java.util.Properties;


public class PropertyLoader {

    private static final String PROP_FILE = "/application.properties";
    //
    private static final String VOID_PROP_FILE = "/void.properties";
    private static final String PROD_PROP_FILE = "/void.properties";

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
        String env = System.getProperty("test.environment");
        if(env.equals("PROD")){
            props = getPropertiesFile(PROD_PROP_FILE);
        }
        else {
            props = getPropertiesFile(VOID_PROP_FILE);
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




}