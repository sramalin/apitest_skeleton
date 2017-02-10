package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileObject{


    public String strURL;
    public String basicAuth;
    public String existingUser;
    public String existingUserPassword;

    public ConfigFileObject(String propFileName){
         Properties prop = new Properties();
        try {
            File propFile = new File(propFileName);
            prop.load(new FileInputStream(propFile));
            this.strURL = prop.getProperty("baseURL");
            this.basicAuth = prop.getProperty("basicAuth");
            this.existingUser = prop.getProperty("existingUser");
            this.existingUserPassword = prop.getProperty("existingUserPassword");

        } catch (IOException e) {
            System.err.println("Error reading "+propFileName);
            e.printStackTrace();

        }
    }


}