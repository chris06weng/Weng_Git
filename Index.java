import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Index {
    private String indexFilePath = "Index.txt";
    private String objectsFolderPath = "objects";

    public Index() throws IOException {
        File folder = new File(objectsFolderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(indexFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void add(String fileName) throws IOException {
        Blob blob = new Blob(fileName);

        String content = Blob.fileToString(new File(fileName));
        String hash = Blob.hashString(content);

        try (PrintWriter pw = new PrintWriter(new FileWriter(indexFilePath, true))) {
            pw.println(fileName + ": " + hash);
        }
    }

    public void remove(String fileName) {
        String content = Blob.fileToString(new File(fileName));
        String hash = Blob.hashString(content);
        String entryToDelete = fileName + ": " + hash;

        try {
            List<String> indexContents = new ArrayList<>();
            BufferedReader lineReader = new BufferedReader(new FileReader(indexFilePath));
            String line;
            while ((line = lineReader.readLine()) != null) {
                if (!line.equals(entryToDelete)) {
                    indexContents.add(line);
                }
            }
            lineReader.close();

            try (BufferedWriter indexWriter = new BufferedWriter(new FileWriter(indexFilePath))) {
                for (String contents : indexContents) {
                    indexWriter.write(contents);
                    indexWriter.newLine();
                }
            }

            File fileToDelete = new File(objectsFolderPath, hash);
            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}