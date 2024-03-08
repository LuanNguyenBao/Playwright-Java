package utils;

import core.ERROR_MESSAGE;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceReader {

    private static ResourceReader instance = null;

    public static ResourceReader getInstance() {
        if (instance == null) {
            instance = new ResourceReader();
        }
        return instance;
    }

    public InputStream asInputStream(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            return is;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE.FILE_NOT_FOUND);
        }
    }

    public File asFile(String fileName) throws URISyntaxException {
        URL link = getClass().getClassLoader().getResource(fileName);
        if (link != null) {
            return new File(link.toURI());
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE.FILE_NOT_FOUND);
        }
    }
}
