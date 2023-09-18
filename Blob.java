import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Blob {
    File file;
    String content, hash, fileName;
    String folderPath = "bin/objects";

    public Blob(String inputFile) throws IOException {
        fileName = inputFile;
        file = new File(inputFile);
        content = fileToString(file);
        hash = hashString(content);
        createFile();

    }

    public String getFileContents() {
        return content;
    }

    public String getHash() {
        return hash;
    }

    public void rewriteFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        // File file = new File(fileName);
        PrintWriter pw = new PrintWriter(fileName);
        pw.print(content);
        pw.close();
        fos.close();
    }

    private void createFile() throws IOException {
        String fileName = hash;

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = folderPath + File.separator + fileName;
        File newFile = new File(filePath);
        PrintWriter pw = new PrintWriter(newFile);
        pw.print(content);
        pw.close();
    }

    private String hashString(String input) {
        try {
            // Create a MessageDigest instance for SHA-1
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

            // Convert the input string to bytes using UTF-8 encoding
            byte[] bytes = input.getBytes("UTF-8");

            // Update the MessageDigest with the input bytes
            sha1.update(bytes);

            // Generate the SHA-1 hash
            byte[] hashBytes = sha1.digest();

            // Convert the hash bytes to a hexadecimal representation
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexHash.append("0");
                }
                hexHash.append(hex);
            }

            // Return the hexadecimal SHA-1 hash
            return hexHash.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // Handle exceptions (e.g., NoSuchAlgorithmException,
            // UnsupportedEncodingException)
            e.printStackTrace();
            return null;
        }
    }

    private String fileToString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        Boolean isFirst = true;
        while (br.ready()) {
            line = br.readLine();
            if (isFirst) {
                sb.append(line);
                isFirst = false;
            } else
                sb.append("\n" + line);
        }
        br.close();
        return sb.toString();

    }
}