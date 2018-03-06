package util;

public class TestDataGenerator {

    public static String getRandomId(){
        return String.valueOf(System.currentTimeMillis()).substring(4);
    }
}
