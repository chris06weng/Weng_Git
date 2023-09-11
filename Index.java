import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Index {
    public Index() {
        String folderName = "objects";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File("Index.txt");
    }

    public static void add(String fileName) throws FileNotFoundException {
        Blob blob = new Blob(fileName);
        File file = new File("Index.txt");

        String content = Blob.fileToString(new File(fileName));
        String hash = Blob.hashString(content);
        PrintWriter pw = new PrintWriter(file);
        pw.print(fileName + ": " + hash + "\n");
        pw.close();
    }

    public static void remove(String fileName) {
        String objectsFolderPath = "objects";

        String content = Blob.fileToString(new File(fileName));
        String hash = Blob.hashString(content);
        String entryToDelete = "fileName: " + hash;

        try {
            List<String> indexContents = new ArrayList<>();
            BufferedReader indexReader = new BufferedReader(new FileReader("Index.txt"));
            PrintWriter pw = new PrintWriter("Index.txt");
            String line;
            while ((line = indexReader.readLine()) != null) {
                if (!line.equals(entryToDelete)) {
                    indexContents.add(line);
                }
            }
            indexReader.close();

            BufferedWriter indexWriter = new BufferedWriter(new FileWriter("Index.txt"));
            for (String contents : indexContents) {
                indexWriter.write(content);
                indexWriter.newLine();
            }
            indexWriter.close();

            File fileToDelete = new File(hash);
            fileToDelete.delete();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to recursively delete a folder and its contents
    private static void deleteFolder(File folder) {
        File[] contents = folder.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }
}
