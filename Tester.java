import java.io.IOException;
import java.io.PrintWriter;

public class Tester {
    public static void main(String[] args) throws IOException {
        // Blob blob = new Blob("sample.txt");

        Index index = new Index();
        index.add("sample2.txt");
        index.add("sample.txt");
        index.add("sample2.txt");
        PrintWriter pw = new PrintWriter("sample2.txt");
        pw.append("test");
        pw.close();
        index.add("sample2.txt");

        // index.remove("sample.txt");
    }
}
