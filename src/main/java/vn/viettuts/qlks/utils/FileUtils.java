package vn.viettuts.qlks.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Chuyển đổi đối tượng object về định dạng JSON
     * Sau đó lưu vào fileName
     *
     * @param fileName
     * @param object
     */
    public static void writeJSONtoFile(String fileName, Object object) {
        try (FileWriter writer = new FileWriter(new File(fileName))) {
            // Convert object to JSON and write to file
            gson.toJson(object, writer);
            System.out.println("Successfully written JSON to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đọc nội dung fileName, sau đó chuyển đổi nội dung của file 
     * thành đối tượng có kiểu là clazz.
     *
     * @param fileName
     * @param clazz
     * @return
     */
    public static <T> T readJSONFile(String fileName, Class<T> clazz) {
        try (FileReader reader = new FileReader(new File(fileName))) {
            // Read JSON from file and convert it into an object of type clazz
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
