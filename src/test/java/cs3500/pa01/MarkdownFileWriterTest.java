package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests the MarkdownFileWriter class which writes processed markdown files
 * to a specified output path.
 */
public class MarkdownFileWriterTest {

/*
  @Test
  public void testWriteMarkdownFiles() throws IOException {

    Path arraysPath = Paths.get("src/test/resources/sampleData/arrays.md");
    Path vectorsPath = Paths.get("src/test/resources/sampleData/vectors.md");

    BasicFileAttributes arraysAttr = Files.readAttributes(arraysPath, BasicFileAttributes.class);
    BasicFileAttributes vectorsAttr = Files.readAttributes(vectorsPath, BasicFileAttributes.class);

    String arraysContent = Files.readString(arraysPath);
    String vectorsContent = Files.readString(vectorsPath);

    MarkdownFile arraysFile =
        new MarkdownFile(arraysPath, arraysAttr.creationTime(), arraysAttr.lastModifiedTime(),
            arraysContent);
    MarkdownFile vectorsFile =
        new MarkdownFile(vectorsPath, vectorsAttr.creationTime(), vectorsAttr.lastModifiedTime(),
            vectorsContent);

    ArrayList<MarkdownFile> files = new ArrayList<>();
    files.add(arraysFile);
    files.add(vectorsFile);

    Path outputPath = Paths.get("src/test/resources/outputData/output.md");
    MarkdownFileWriter writer = new MarkdownFileWriter(outputPath.toString());
    writer.writeMarkdownFiles(files);
    Path expectedOutputPath = Paths.get("src/test/resources/outputData/expectedOutput.md");

    String expectedContent = Files.readString(expectedOutputPath);
    String actualContent = Files.readString(outputPath);

    assertEquals(expectedContent, actualContent);
  }
  */
}
