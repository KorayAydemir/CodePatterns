package src;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONTokener;

public class Utils {
    public static void writeJSONArrayToFile(String filePath, JSONArray jsonArray) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonArray.toString(4)); // '4' is the number of spaces to use for indentation
        }
    }

    public static JSONArray parseJSONArrayFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        return new JSONArray(jsonTokener);
    }

    public static String generateUID() {
        return UUID.randomUUID().toString();
    }

}
