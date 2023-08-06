package cs3500.pa01;

import java.io.IOException;

/**
 * view for a study session
 */
public class StudySessionView {
  private final Appendable output;

  /**
   * Constructs a new study session view
   *
   * @param output the appendable to output to
   */
  public StudySessionView(Appendable output) {
    this.output = output;
  }

  /**
   * Shows a question to the user
   *
   * @param questionText the question text of the question
   * @throws IOException if an IO error occurs
   */
  public void showQuestion(String questionText) throws IOException {
    output.append(questionText).append("\n");
    output.append("Input:\n1. Easy\n2. Hard\n3. Show Answer\n4. Exit\n");
  }

  /**
   * Shows a answer to the user
   *
   * @param answerText the answer text of the question
   * @throws IOException if an IO error occurs
   */
  public void showAnswer(String answerText) throws IOException {
    output.append("Answer: ").append(answerText).append("\n");
    output.append("Input:\n1. Easy\n2. Hard\n4. Exit\n");
  }

  /**
   * shows a error message to user
   *
   * @param errorMessage the error message outputted
   * @throws IOException if an IO error occurs
   */
  public void showError(String errorMessage) throws IOException {
    output.append(errorMessage).append("\n");
  }

  /**
   * Prints statistics about the study session after the session is done
   *
   * @param questionsSize the total number of questions answered
   * @param easyToHard the total number of questions that changed from easy to hard
   * @param hardToEasy the total number of questions that changed from hard to easy
   * @param hardQuestions the total number of hard questions in the question bank
   * @param easyQuestions the total number of easy questions in the question bank
   * @throws IOException if an IO error occurs
   */
  public void printSessionStats(int questionsSize, int easyToHard, int hardToEasy,
                                int hardQuestions, int easyQuestions) throws IOException {
    output.append("Session statistics:\n")
        .append("Total number of questions answered: ").append(String.valueOf(questionsSize))
        .append("\n")
        .append("Total number of questions that changed from easy to hard: ")
        .append(String.valueOf(easyToHard)).append("\n")
        .append("Total number of questions that changed from hard to easy: ")
        .append(String.valueOf(hardToEasy)).append("\n")
        .append("Updated total number of hard questions in the question bank: ")
        .append(String.valueOf(hardQuestions)).append("\n")
        .append("Updated total number of easy questions in the question bank: ")
        .append(String.valueOf(easyQuestions)).append("\n");
  }

  /**
   * Asks user for number of questions they want to answer
   *
   * @throws IOException if an IO error occurs
   */
  public void askQuestionCount() throws IOException {
    output.append("How many would you like to answer\n");
  }

  /**
   * welcomes the user with message
   *
   * @throws IOException if an IO error occurs
   */
  public void printWelcomeMessage() throws IOException {
    output.append("Welcome to your study session\n");
  }

  /**
   * Asks user for the file path of the study session
   *
   * @throws IOException if an IO error occurs
   */
  public void askForFilePath() throws IOException {
    output.append("Please enter the full path to a .sr file:\n");
  }
}
