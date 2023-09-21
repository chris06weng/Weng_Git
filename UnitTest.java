import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Path path = Paths.get("bin/Index/");
        Files.deleteIfExists(path);
        File directory = new File("bin/objects/");
        for (File subfile : directory.listFiles()) {
            subfile.delete();
        }
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        Path path = Paths.get("bin/Index/");
        Files.deleteIfExists(path);
        File directory = new File("bin/objects/");
        for (File subfile : directory.listFiles()) {
            subfile.delete();
        }
    }

    @Test
    @DisplayName("[8] Test if initialize and objects are created correctly")
    public void testInitialize() throws Exception {
        Index index = new Index();
        // check if the file exists
        File file = new File("bin/Index"); // verifies Index
        Path path = Paths.get("bin/objects"); // verifies objects folder
        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("[15] Test if adding a blob works. 5 for sha, 5 for file contents, 5 for correct location")
    void testCreateBlob() throws Exception {
        File file = new File("file1");
        if (!file.exists())
            file.createNewFile();
        Blob testBlob = new Blob("file1");
        File obj = new File("bin/objects/" + testBlob.getHash());
        assertTrue(obj.exists());
    }

    @Test
    @DisplayName("tests if blob contents matches original file contents")
    void testBlobContents() throws Exception {

    }

    @Test
    @DisplayName("[15] Test if tree works")
    void testTree() throws Exception {
        Tree tree = new Tree();
        // check if the file exists
        File file = new File("bin/Tree");
        Path path = Paths.get("bin/objects");
        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

}
// try {
// // Manually create the files and folders before the 'testAddFile'
// MyGitProject myGitClassInstance = new MyGitProject();
// myGitClassInstance.init();
// TestHelper.runTestSuiteMethods("testCreateBlob", file1.getName());

// } catch (Exception e) {
// System.out.println("An error occured: " + e.getMessage());
// }
// // Check blob exists in the objects folder
// File file_junit1 = new File("objects/" + file1.methodToGetSha1());
// assertTrue("Blob file to add not found", file_junit1.exists());
// // Read file contents
// String indexFileContents = MyUtilityClass.readAFileToAString("objects/" +
// file1.mexthodToGetSha1());
// assertEquals("File contents of Blob don't match file contents pre-blob
// creation", indexFileContents,
// file1.getContents());