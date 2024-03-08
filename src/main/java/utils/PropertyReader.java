package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader instance = null;
    private PropertyReader() {}
    public static PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader();
        }
        return instance;
    }

    public Properties read(String filePath) {
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(ResourceReader.getInstance().asInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
