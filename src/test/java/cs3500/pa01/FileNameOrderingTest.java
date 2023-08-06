package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * Tests the sort method in the FileNameOrdering class, this class sorts a list of
 * MarkdownFiles by filename
 */
class FileNameOrderingTest {

  /**
   * Tests filename sort on sample files and ensures they are in the expected order
   */
  @Test
  public void testSort() {

    String content = "# Heading\nhellow world [[phrase]] text";


    MarkdownFile file1 = new MarkdownFile(Paths.get("arrays.md"),
        FileTime.from(3, TimeUnit.HOURS),
        FileTime.from(3, TimeUnit.HOURS),
        content);

    MarkdownFile file2 = new MarkdownFile(Paths.get("vectors.md"),
        FileTime.from(6, TimeUnit.HOURS),
        FileTime.from(6, TimeUnit.HOURS),
        content);

    ArrayList<MarkdownFile> files = new ArrayList<>();
    files.add(file1);
    files.add(file2);

    FileNameOrdering ordering = new FileNameOrdering();
    ArrayList<MarkdownFile> sortedFiles = ordering.sort(files);

    assertEquals(file1, sortedFiles.get(0));
    assertEquals(file2, sortedFiles.get(1));
  }
}