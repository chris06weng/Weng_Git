import java.io.*;

public class Index extends Git {
    public Index() throws IOException {
        super("bin/Index");
    }

    public void add(String fileName) throws IOException {
        addBlob(fileName, "");
    }

}
