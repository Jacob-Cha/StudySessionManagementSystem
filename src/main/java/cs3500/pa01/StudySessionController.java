package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handles user inputs and updates model
 */
public class StudySessionController {
  private final StudySessionView viewer;
  private final Scanner scanner;

  /**
   * Constructor
   *
   * @param viewer the viewer of the study session
   * @param input readable that takes user input
   */
  public StudySessionController(StudySessionView viewer, Readable input) {
    this.viewer = viewer;
    this.scanner = new Scanner(input);
  }

  /**
   * Handles input and validity of inputted filepath
   *
   * @return the inputted file path
   */
  private String getFilePath() {
    boolean validFilePath = false;
    String filePath = null;
    while (!validFilePath) {
      try {
        viewer.askForFilePath();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (!scanner.hasNextLine()) {
        break;
      }
      filePath = scanner.nextLine();
      File file = new File(filePath);
      if (file.exists()) {
        validFilePath = true;
      } else {
        try {
          viewer.showError("Invalid file path");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return filePath;
  }


  /**
   * Handles input and validity of inputted number of questions
   *
   * @return the validated number of questions.
   */
  private int getNumberOfQuestions() {
    try {
      viewer.askQuestionCount();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!scanner.hasNextInt()) {
      try {
        viewer.showError("Invalid input, must input a number");
      } catch (IOException e) {
        e.printStackTrace();
      }
      scanner.nextLine();
    }
    return scanner.nextInt();
  }

  /**
   * Processes the user input to update difficulty of given question
   *
   * @param question the current question
   * @param totalQuestionsAnswered the total number of questions answered in a session
   * @param sessionModel the model of the study session
   * @return the new difficulty of the current question
   */
  private Difficulty userInput(Question question, int totalQuestionsAnswered,
                               StudySessionModel sessionModel) {
    Difficulty updatedDifficulty = null;
    while (updatedDifficulty == null) {
      String input = scanner.nextLine().trim();
      try {
        switch (input) {
          case "1", "EASY" -> updatedDifficulty = Difficulty.EASY;
          case "2", "HARD" -> updatedDifficulty = Difficulty.HARD;
          case "3", "SHOW ANSWER" -> viewer.showAnswer(question.getAnswerText());
          case "4", "EXIT" -> {
            sessionModel.writeNewDiffQuestions();
            viewer.printSessionStats(totalQuestionsAnswered, sessionModel.getEasyToHard(),
                sessionModel.getHardToEasy(), sessionModel.getNumberOfHardQuestions(),
                sessionModel.getNumberOfEasyQuestions());
            return null;
          }
          default -> viewer.showError(
              "Invalid input, enter either 1. 'EASY', 2. 'HARD', 3. 'SHOW ANSWER', 4. 'EXIT'");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return updatedDifficulty;
  }

  /**
   * Handles the process of answering questions
   *
   * @param numOfQuestions the number of questions that will be answered
   * @param sessionModel the model of the study session
   * @param totalQuestionsAnswered the total number of questions answered in a session
   */
  private void questionAnswering(int numOfQuestions, StudySessionModel sessionModel,
                                 int totalQuestionsAnswered) {
    ArrayList<Question> selectedQuestions = sessionModel.getQuestionsForSession(numOfQuestions);
    scanner.nextLine();
    for (int i = 0; i < selectedQuestions.size(); i++) {
      Question question = selectedQuestions.get(i);
      try {
        viewer.showQuestion(question.getQuestionText());
      } catch (IOException e) {
        e.printStackTrace();
      }
      Difficulty updatedDifficulty = userInput(question, totalQuestionsAnswered, sessionModel);
      if (updatedDifficulty == null) {
        return;
      }
      totalQuestionsAnswered++;
      sessionModel.changeDifficulty(i, updatedDifficulty);
    }
    sessionModel.writeNewDiffQuestions();
    try {
      viewer.printSessionStats(totalQuestionsAnswered, sessionModel.getEasyToHard(),
          sessionModel.getHardToEasy(), sessionModel.getNumberOfHardQuestions(),
          sessionModel.getNumberOfEasyQuestions());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * starts the study session
   */
  public void startStudySession() {
    try {
      viewer.printWelcomeMessage();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String filePath = getFilePath();
    if (filePath == null) {
      try {
        viewer.showError("Must input file path to .sr file");
      } catch (IOException e) {
        e.printStackTrace();
      }
      return;
    }
    StudySessionModel sessionModel = new StudySessionModel(filePath);
    int totalQuestionsAnswered = 0;
    int numOfQuestions = getNumberOfQuestions();
    questionAnswering(numOfQuestions, sessionModel, totalQuestionsAnswered);
  }
}



