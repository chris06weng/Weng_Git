import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Blob {
    public Blob(String inputFile) {
        File file = new File(inputFile);
        String content = fileToString(file);
        String hash = hashString(content);

        String folderPath = "objects";
        String fileName = hash;

        String filePath = folderPath + File.separator + fileName;

        File newFile = new File(filePath);
    }

    public static String hashString(String input) {
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

    public static String fileToString(File file) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return sb.toString();
    }

}