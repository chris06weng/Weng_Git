import java.io.File;
import java.io.IOException;

public class Tree {

    StringBuilder fileContents;
    String treeFilePath = "Tree";

    public Tree() throws IOException {
        File file = new File(treeFilePath);
        if (!file.exists())
            file.createNewFile();
    }

    public void add(String fileFor) {
        // checks if String is fileName or typeOfFile : shaOfFile : optionalFileName
        // if true
        // else

    }

    public void remove(String fileOrHash) {
        // checks if String is fileName or hash

    }

    public void makeFile() throws IOException {
        // creates NEW file based on tree File
        Blob treeBlob = new Blob(treeFilePath);
    }

}
