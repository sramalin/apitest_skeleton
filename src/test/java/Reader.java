import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Reader {

    private static Properties prop;

    public static String getProperty(String key, String propFileName) {
        if (null == prop) {
            prop = new Properties();

            try {
                File propFile = new File(propFileName);
                prop.load(new FileInputStream(propFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Enumeration<Object> keys = prop.keys();
         System.out.println("Configured properties:");
            while (keys.hasMoreElements()) {
                String elt = (String) keys.nextElement();
              System.out.println(String.format("\t\tProperty: %s, value: '%s'", elt, prop.getProperty(elt)));
            }
        }

        return prop.getProperty(key, null);
    }



}