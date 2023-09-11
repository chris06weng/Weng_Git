import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class Blob {
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

}