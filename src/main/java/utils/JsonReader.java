package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.*;

public class JsonReader {

    public static Object[][] load(String path) {
        try {
            List<LinkedHashMap<?, ?>> listLHM = new ObjectMapper().readValue(
                    new FileReader(ResourceReader.getInstance().asFile(path)),
                    new TypeReference<List<LinkedHashMap<?, ?>>>() {});
            Object[][] jsonDataObject = new Object[listLHM.size()][];
            for (int i = 0; i < listLHM.size(); i++) {
                jsonDataObject[i] = listLHM.get(i).values().toArray();
            }
            return jsonDataObject;
        }
        catch (URISyntaxException | IOException | RuntimeJsonMappingException ex) {
            return null;
        }
    }
}
