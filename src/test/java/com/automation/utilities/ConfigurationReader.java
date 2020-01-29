package com.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    // this class will be responsible for loading properties file and will provide
    // access to values based on key names
    //we use this class toload custom.properties files
    private static Properties configFile;

    static {
        try {
            //provides access to file
            //try-catch block stands for handling exceptions
            //if exception occures, code inside a catch block willbe executed
            //any class that is related to InputOutput produce checked exceptions
            //without handling checked exception, you can not run a code
            FileInputStream fileInputStream = new FileInputStream("configuration.properties");
//initialize properties object
                  configFile = new Properties();
            //load configuration.properties file
                 configFile.load(fileInputStream);
                 fileInputStream.close();
        } catch (IOException e) {
            System.out.println("File was not loaded:(");
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return configFile.getProperty(key);
    }
}
