package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * tests for StudySessionModel class
 */
class StudySessionModelTest {
  StudySessionModel model;
  String testFilePath =
      "src/test/resources/sampleData/outputData.sr";

  /**
   * setup for each test
   */
  @BeforeEach
  void setUp() {
    model = new StudySessionModel(testFilePath);
  }

  /**
   * tests getQuestionsForSession
   */
  @Test
  void testGetQuestionsForSession() {
    ArrayList<Question> questions = model.getQuestionsForSession(5);
    assertEquals(5, questions.size());
  }

  /**
   * tests changeDifficulty
   */
  @Test
  void testChangeDifficulty() {
    ArrayList<Question> questions = model.getQuestionsForSession(1);
    Question firstQuestion = questions.get(0);

    model.changeDifficulty(0, Difficulty.EASY);

    questions = model.getQuestionsForSession(1);
    firstQuestion = questions.get(0);
    assertEquals(Difficulty.HARD, firstQuestion.getDifficulty());

    model.changeDifficulty(0, Difficulty.HARD);

    questions = model.getQuestionsForSession(1);
    firstQuestion = questions.get(0);
    assertEquals(Difficulty.HARD, firstQuestion.getDifficulty());
  }

  /**
   * tests change difficulty from easytohard
   */
  @Test
  void testChangeDifficultyFromEasyToHard() {
    ArrayList<Question> questions = model.getQuestionsForSession(1);
    model.changeDifficulty(0, Difficulty.EASY);
    model.changeDifficulty(0, Difficulty.HARD);
    questions = model.getQuestionsForSession(1);
    Question firstQuestion = questions.get(0);
    firstQuestion = questions.get(0);

    assertEquals(Difficulty.HARD, firstQuestion.getDifficulty());
  }

  /**
   * tests change difficulty from hardtoeasy
   */
  @Test
  void testChangeDifficultyFromHardToEasy() {
    ArrayList<Question> questions = model.getQuestionsForSession(1);
    model.changeDifficulty(0, Difficulty.HARD);

    model.changeDifficulty(0, Difficulty.EASY);

    questions = model.getQuestionsForSession(1);
    Question firstQuestion = questions.get(0);

    firstQuestion = questions.get(0);
    assertEquals(Difficulty.HARD, firstQuestion.getDifficulty());
  }

  /**
   * tests an invalid difficulty change
   */
  @Test
  void testInvalidChangeDifficulty() {
    assertThrows(IndexOutOfBoundsException.class,
        () -> model.changeDifficulty(-19, Difficulty.EASY));
  }

  /**
   * tests getNumberOfHardQuestions
   */
  @Test
  void testGetNumberOfHardQuestions() {
    model.changeDifficulty(0, Difficulty.HARD);
    assertEquals(17, model.getNumberOfHardQuestions());

    model.changeDifficulty(0, Difficulty.EASY);
    assertEquals(16, model.getNumberOfHardQuestions());
  }

  /**
   * tests getNumberOfEasyQuestions
   */
  @Test
  void testGetNumberOfEasyQuestions() {
    model.changeDifficulty(0, Difficulty.EASY);
    assertEquals(9, model.getNumberOfEasyQuestions());

    model.changeDifficulty(0, Difficulty.HARD);
    assertEquals(8, model.getNumberOfEasyQuestions());
  }

  /**
   * tests easyTohard
   */
  @Test
  void testGetEasyToHard() {
    model.changeDifficulty(0, Difficulty.EASY);
    model.changeDifficulty(0, Difficulty.HARD);
    assertEquals(1, model.getEasyToHard());
  }

  /**
   * tests hardToEasy
   */
  @Test
  void testGetHardToEasy() {
    model.changeDifficulty(0, Difficulty.HARD);
    model.changeDifficulty(0, Difficulty.EASY);
    assertEquals(1, model.getHardToEasy());
  }

}
