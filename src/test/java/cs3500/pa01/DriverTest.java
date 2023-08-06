package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests for the Driver class
 */
class DriverTest {
  /**
   * temporary directory created in which temporary files will be held for testing
   */
  @TempDir
  Path tempDirectory;

  /**
   * Tests the main method with the filename argument for ordering
   * prepares two markdown files in the temporary directory
   *
   * @throws Exception if there is error
   */

  @Test
  public void testMainFilename() throws Exception {
    Path inputFile1 = tempDirectory.resolve("arrays.md");
    Files.writeString(inputFile1, "test content 1");
    Path inputFile2 = tempDirectory.resolve("vectors.md");
    Files.writeString(inputFile2, "test content 2");
    Path outputPath = tempDirectory.resolve("output.md");

    String[] args = new String[] {tempDirectory.toString(), "filename", outputPath.toString()};
    Driver.main(args);

    assertFalse(Files.exists(outputPath));
  }

  /**
   * Tests the main method with the created argument for ordering
   * prepares two markdown files in the temporary directory
   *
   * @throws Exception if there is error
   */
  @Test
  public void testMainCreationDate() throws Exception {
    Path inputFile1 = tempDirectory.resolve("arrays.md");
    Files.writeString(inputFile1, "test content 1");
    Path inputFile2 = tempDirectory.resolve("vectors.md");
    Files.writeString(inputFile2, "test content 2");
    Path outputPath = tempDirectory.resolve("output.md");

    String[] args = new String[] {tempDirectory.toString(), "created", outputPath.toString()};
    Driver.main(args);

    assertFalse(Files.exists(outputPath));
  }

  /**
   * Tests the main method with the modified argument for ordering
   * prepares two markdown files in the temporary directory
   *
   * @throws Exception if there is error
   */
  @Test
  public void testMainModified() throws Exception {
    Path inputFile1 = tempDirectory.resolve("arrays.md");
    Files.writeString(inputFile1, "test content 1");
    Path inputFile2 = tempDirectory.resolve("vectors.md");
    Files.writeString(inputFile2, "test content 2");
    Path outputPath = tempDirectory.resolve("output.md");

    String[] args = new String[] {tempDirectory.toString(), "modified", outputPath.toString()};
    Driver.main(args);

    assertFalse(Files.exists(outputPath));
  }

  /**
   * tests the study session feature of main
   */
  @Test
  public void testStudySession() {
    StringReader in = new StringReader("test");
    StringBuilder out = new StringBuilder();
    StudySessionView view = new StudySessionView(out);
    StudySessionController controller = new StudySessionController(view, in);

    String[] args = new String[0];
    Driver.main(args);
  }
}
