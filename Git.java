import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Git {
    private String objectsFolderPath, gitFilePath;
    HashMap<String, String> gitMap = new HashMap<String, String>();

    public Git(String path) throws IOException {
        gitFilePath = path;
        objectsFolderPath = "objects";
        File folder = new File(objectsFolderPath);
        if (!folder.exists())
            folder.mkdirs();
        File file = new File(gitFilePath);
        if (!file.exists())
            file.createNewFile();
        writeGit();
    }

    public void add(String fileName) throws IOException {
        // creates blob from fileName
        Blob blob = new Blob(fileName);
        String hash = blob.getHash();
        if (gitMap.containsKey(fileName)) {
            replace(fileName, hash);
            blob.rewriteFile();
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(gitFilePath, true))) {
                if (gitMap.isEmpty())
                    pw.print(hash + " : " + fileName);
                else
                    pw.print("\n" + hash + " : " + fileName);
            }
            gitMap.put(fileName, hash);
        }
    }

    public void replace(String fileName, String hash) throws IOException {
        if (!hash.equals(gitMap.get(fileName))) {
            gitMap.remove(fileName);
            gitMap.put(fileName, hash);
            writeGit();
        }
    }

    public void remove(String fileName) throws IOException {
        gitMap.remove(fileName);
        writeGit();
    }

    public void writeGit() throws IOException {
        FileOutputStream fos = new FileOutputStream(gitFilePath);
        File file = new File(gitFilePath);
        PrintWriter pw = new PrintWriter(file);
        Set<String> fileSet = gitMap.keySet();
        Boolean isFirst = true;
        for (String k : fileSet) {
            if (isFirst)
                isFirst = false;
            else
                pw.print("\n");
            pw.print(gitMap.get(k) + " : " + k);
        }
        pw.close();
        fos.close();
    }

    public void addTree() throws IOException {
        add("Tree");

    }
}