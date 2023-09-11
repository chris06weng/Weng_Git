import java.io.FileNotFoundException;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        Blob blob = new Blob("sample.txt");

        Index index = new Index();
        index.add("sample2.txt");
        index.add("sample.txt");

        index.remove("sample.txt");
    }
}
