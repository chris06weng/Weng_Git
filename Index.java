import java.io.File;
import java.io.FileNotFoundException;

public class Index {
    public Index() {
        String folderName = "indexFolder";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void add(File fileName) {

    }

    public static void remove(File fileName) {

    }
}
