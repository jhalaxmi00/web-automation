package com.webautomation.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    static private Properties prop;

    static{
        prop = new Properties();
        try{
            InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(is);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }
}
