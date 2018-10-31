package com.bulsoft.zoomautomation.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverConfig {

    //Properties class to deal with properties
    // YAML properties
    private static Properties properties;

    private DriverConfig(){
        Path path = Paths.get("src/test/resources/","driver.properties");
        File propertyFile = path.toFile();
        try {
            InputStream inputStream = new FileInputStream(propertyFile);
            properties = new Properties();
            properties.load(inputStream);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static String getPropertyValue(String propertyName){
        if(properties==null){
            new DriverConfig();
        }
        return properties.getProperty(propertyName);
    }

}
