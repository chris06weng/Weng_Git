import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class Tree extends Git {
    static int fileNum = 0;

    public Tree() throws IOException {
        super("bin/Tree");
    }

    // original add method
    private void addOld(String fileName) throws IOException {
        File file = new File(objectsFolderPath + "/" + fileName);
        if (file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(gitFilePath, true))) {
                if (gitMap.isEmpty())
                    pw.print("tree : " + fileName);
                else
                    pw.print("\ntree : " + fileName);
            }
            gitMap.put(fileName, "");
        } else
            addBlob(fileName, "blob : ");
    }

    // if not specified, assume hash is a blob
    public boolean add(String fileName) throws IOException {
        String[] split = fileName.split(" : ");
        String hash;
        if (split.length == 2) {
            hash = split[1];
            addTreeFromHash(hash);
            return true;
        } else {
            hash = fileName;
        }
        File file = new File(objectsFolderPath + "/" + hash);
        if (file.exists()) {
            addBlobFromHash(hash);
            return true;
        } else {
            addBlob(fileName, "blob : ");
            return true;
        }
    }

    private void addTreeFromHash(String hash) throws IOException {
        File file = new File(objectsFolderPath + "/" + hash);
        if (file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(gitFilePath, true))) {
                if (gitMap.isEmpty())
                    pw.print("tree" + " : " + hash);
                else
                    pw.print("\n" + "tree" + " : " + hash);
            }
            gitMap.put(hash, "");
        }
    }

    private void addBlobFromHash(String hash) throws IOException {
        File file = new File(objectsFolderPath + "/" + hash);
        String fileName = "unnamedFile" + fileNum + ".txt";
        fileNum++;
        if (file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(gitFilePath, true))) {
                if (gitMap.isEmpty())
                    pw.print("blob" + " : " + hash + " : " + fileName);
                else
                    pw.print("\n" + "blob" + " : " + hash + " : " + fileName);
            }
            gitMap.put(fileName, hash);
        }
    }

    @Override
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
            String temp = gitMap.get(k);
            if (temp.length() == 0)
                pw.print("tree : " + k);
            else
                pw.print("blob : " + temp + " : " + k);
        }
        pw.close();
        fos.close();
    }

    public void generateTree() throws IOException {
        String fileName = "Tree";
        Blob blob = new Blob(fileName);
        String hash = blob.getHash();
        if (gitMap.containsKey(fileName)) {
            replace(fileName, hash);
            blob.rewriteFile();
        }

    }
}