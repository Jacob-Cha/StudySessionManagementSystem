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
 * tests for questionsAnswerWriter class
 */
public class QuestionsAnswersWriterTest {

  /**
   * Tests the writing to sr file functionality
   *
   * @throws IOException if IO error occurs
   */
  @Test
  public void testWriteQuestions() throws IOException {
    Path testFilePath = Paths.get("src/test/resources/sampleData/testFile.md");

    BasicFileAttributes testFileAttr =
        Files.readAttributes(testFilePath, BasicFileAttributes.class);

    String testFileContent = Files.readString(testFilePath);

    MarkdownFile testFile =
        new MarkdownFile(testFilePath, testFileAttr.creationTime(), testFileAttr.lastModifiedTime(),
            testFileContent);

    ArrayList<MarkdownFile> files = new ArrayList<>();
    files.add(testFile);

    Path outputPath = Paths.get("src/test/resources/outputData/outputQuestions.sr");

    QuestionsAnswersWriter writer = new QuestionsAnswersWriter(outputPath.toString());
    writer.writeQuestions(files);

    String actualContent = Files.readString(outputPath);

    String expectedContent = """
        Question: What is the question?
        Answer: This is the answer.
        Difficulty: HARD
        Question: Another question?
        Answer: Another answer.
        Difficulty: HARD
        """;

    assertEquals(expectedContent, actualContent);
  }
}
