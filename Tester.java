import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        // Blob blob = new Blob("sample.txt");

        Index index = new Index();
        Tree tree = new Tree();
        tree.add("TEST/sample1.txt");

        // index.add("sample1.txt");
        // index.add("TEST/sample1.txt");

        tree.add("TEST/sample1.txt");
        index.add(
                "/Users/ardendoyle/Documents/personal/coding/Honors Topics/Weng_Git_arden/Research Website Title.png");
        index.add("/Users/ardendoyle/Documents/personal/coding/Honors Topics/Weng_Git_arden/bin/Tree");
        
        // tree.remove("TEST/sample1.txt");
        index.remove("TEST/sample1.txt");
        // Path path = Paths.get("bin/Index/");
        // Files.deleteIfExists(path);
        // File directory = new File("bin/objects/");
        // for (File subfile : directory.listFiles()) {
        // subfile.delete();
    }

}
