import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Index {
    public Index() {
        String folderName = "objects";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File("Index");
    }

    public static void add(String fileName) throws FileNotFoundException {
        Blob blob = new Blob(fileName);
        File file = new File("Index");

        String content = Blob.fileToString(new File(fileName));
        String hash = Blob.hashString(content);
        PrintWriter pw = new PrintWriter(file);
        pw.print(fileName + " " + hash + "\n");
        pw.close();
    }

    public static void remove(String fileName) {

    }
}
