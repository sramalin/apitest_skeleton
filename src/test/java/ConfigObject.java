import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigObject{


    public  String strURL;

    public ConfigObject(String propFileName){
         Properties prop = new Properties();
        try {

            File propFile = new File(propFileName);
            prop.load(new FileInputStream(propFile));
            this.strURL = prop.getProperty("baseURL");
        } catch (IOException e) {
            System.err.println("Error reading "+propFileName);
            e.printStackTrace();

        }
    }


}