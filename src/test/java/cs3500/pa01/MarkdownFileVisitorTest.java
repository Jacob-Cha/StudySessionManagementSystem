package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


/**
 * Tests the MarkdownFileVisitor class which visits files in a directory
 * and processes the markdown files
 */
public class MarkdownFileVisitorTest {

  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/sampleData";

  /**
   * tests the visitFile method by accessing the sampleData directory and
   * processing and sorting only the MarkdownFiles
   */
  @Test
  public void testVisitFile() {
    MarkdownFileVisitor visitor = new MarkdownFileVisitor(SAMPLE_INPUTS_DIRECTORY);

    try {
      Files.walkFileTree(Paths.get(SAMPLE_INPUTS_DIRECTORY), visitor);
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<MarkdownFile> processedFiles = visitor.getProcessedFiles();
    processedFiles.sort(Comparator.comparing((MarkdownFile file) -> file.getFilename()));

    assertEquals(6, processedFiles.size());
    assertEquals("arrays.md", processedFiles.get(0).getFilename());
    assertEquals("outputdata.md", processedFiles.get(1).getFilename());
  }

  /**
   * creates temporary directory and temporary sample files for testing purposes
   */
  @TempDir
  Path tempDirectory;

  /**
   * tests that both sample files were processed and are in the list of processed files
   * calls the processMain method to initialize the main method by walking the starting
   * file tree path
   */
  @Test
  public void testProcessMain() throws Exception {
    Path inputFile1 = tempDirectory.resolve("arrays.md");
    Files.writeString(inputFile1, "hello world 1");

    Path inputFile2 = tempDirectory.resolve("vectors.md");
    Files.writeString(inputFile2, "hello wolrd 2");

    MarkdownFileVisitor markdownVisitor = new MarkdownFileVisitor(tempDirectory.toString());
    markdownVisitor.processMain();

    assertEquals(2, markdownVisitor.getProcessedFiles().size());
  }
}


