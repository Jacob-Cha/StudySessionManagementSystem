package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * tests for studysessioncontroller tests
 */
public class StudySessionControllerTest {

  /**
   * tests the getFilePath method
   */
  @Test
  public void testGetFilePath() {
    String input =
        """
            nonexistentFilePath
            src/test/resources/sampleData/outputData.sr
            4
            2
            3
            4
            """;
    Readable in = new StringReader(input);
    Appendable out = new StringBuilder();
    StudySessionView view = new StudySessionView(out);
    StudySessionController controller = new StudySessionController(view, in);

    controller.startStudySession();

    assertTrue(out.toString().contains("Invalid file path"));
    assertTrue(out.toString().contains("Welcome to your study session"));
  }

  /**
   * tests getNumberOfQuestions
   *
   */
  @Test
  public void testGetNumberOfQuestions() {
    String input =
        """
            src/test/resources/sampleData/outputData.sr
            invalid
            4
            3
            1
            h
            2
            4
            """;
    Readable in = new StringReader(input);
    Appendable out = new StringBuilder();
    StudySessionView view = new StudySessionView(out);
    StudySessionController controller = new StudySessionController(view, in);

    controller.startStudySession();

    assertTrue(out.toString().contains("Invalid input, must input a number"));
    assertTrue(out.toString().contains("Total number of questions answered: 2"));
  }


  @Test
  public void testUserInput() {
    String input = "src/test/resources/sampleData/outputData.sr"
        + "\n1\ninvalid\nEASY\nEXIT\n";
    StringReader in = new StringReader(input);
    StringBuilder out = new StringBuilder();
    StudySessionView view = new StudySessionView(out);
    StudySessionController controller = new StudySessionController(view, in);

    controller.startStudySession();

    assertTrue(out.toString()
        .contains("Invalid input, enter either 1. 'EASY', 2. 'HARD', 3. 'SHOW ANSWER', 4. 'EXIT'"));
  }

  /**
   * tests the questionAnswering method
   *
   */
  @Test
  public void testQuestionAnswering() {
    String input = "src/test/resources/sampleData/outputData.sr\n30\nEASY\nEXIT\n";
    StringReader in = new StringReader(input);
    StringBuilder out = new StringBuilder();
    StudySessionView view = new StudySessionView(out);
    StudySessionController controller = new StudySessionController(view, in);

    controller.startStudySession();

    assertFalse(out.toString().contains("Question: What is the capital of Canada?"));
  }


}