package cs3500.pa01;

/**
 * Represents a question, with a question text, an answer text and a difficulty level
 */
public class Question {
  private final String questionText;
  private final String answerText;
  private Difficulty difficulty;

  /**
   * Constructs a question given the question text, answer text and difficulty
   *
   * @param question question text of a question
   * @param answer answer text of a question
   * @param diff difficulty of a question
   */
  public Question(String question, String answer, Difficulty diff) {
    this.questionText = question;
    this.answerText = answer;
    this.difficulty = diff;
  }

  /**
   * updates the difficulty of a question
   *
   * @param newDiff the new difficulty
   */
  public void updateDifficulty(Difficulty newDiff) {
    this.difficulty = newDiff;
  }

  /**
   * returns the question text
   *
   * @return the question text
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * returns the answer text
   *
   * @return the answer text
   */
  public String getAnswerText() {
    return answerText;
  }

  /**
   * returns the question text
   *
   * @return the difficulty of a question
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }
}
