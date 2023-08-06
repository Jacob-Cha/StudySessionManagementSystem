package cs3500.pa01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Model for the study session
 */
public class StudySessionModel {
  private final ArrayList<Question> questionBank;
  private int easyToHard;
  private int hardToEasy;
  private final String filePath;

  /**
   * default constructor
   *
   * @param filePath path to file that contains questions
   */
  public StudySessionModel(String filePath) {
    this.questionBank = new ArrayList<>();
    this.filePath = filePath;
    addQuestionsToFile(filePath);
    this.easyToHard = 0;
    this.hardToEasy = 0;
  }

  /**
   * Adds questions to the question bank from a file
   *
   * @param filePath path to file that contains questions
   */
  private void addQuestionsToFile(String filePath) {
    File file = new File(filePath);
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String questionLine = scanner.nextLine();
        if (questionLine.startsWith("Question: ")) {
          String questionText = questionLine.substring(10);

          String answerLine = scanner.nextLine();
          if (answerLine.startsWith("Answer: ")) {
            String answerText = answerLine.substring(8);

            String difficultyLine = scanner.nextLine();
            if (difficultyLine.startsWith("Difficulty: ")) {
              String difficultyText = difficultyLine.substring(12);
              Difficulty difficulty = Difficulty.valueOf(difficultyText.toUpperCase());

              Question question = new Question(questionText, answerText, difficulty);
              this.questionBank.add(question);
            }
          }
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets a list of questions for a study session
   *
   * @param numQuestions number of questions that will be in the session
   * @return list of questions that will be used in the field
   */
  public ArrayList<Question> getQuestionsForSession(int numQuestions) {
    ArrayList<Question> hardQuestions = new ArrayList<>();
    ArrayList<Question> easyQuestions = new ArrayList<>();

    for (Question question : this.questionBank) {
      if (question.getDifficulty() == Difficulty.HARD) {
        hardQuestions.add(question);
      } else {
        easyQuestions.add(question);
      }
    }
    Collections.shuffle(hardQuestions);
    Collections.shuffle(easyQuestions);

    ArrayList<Question> studySessionPossibleQuestions = new ArrayList<>();
    studySessionPossibleQuestions.addAll(hardQuestions);
    studySessionPossibleQuestions.addAll(easyQuestions);

    ArrayList<Question> finalStudySessionQuestions = new ArrayList<>();
    for (int i = 0; i < Math.min(numQuestions, studySessionPossibleQuestions.size()); i++) {
      finalStudySessionQuestions.add(studySessionPossibleQuestions.get(i));
    }
    return finalStudySessionQuestions;
  }

  /**
   * changes the difficulty of a question in the question bank
   *
   * @param index the index of the question in the question bank
   * @param newDifficulty the new difficulty for the question
   */
  public void changeDifficulty(int index, Difficulty newDifficulty) {
    if (index >= 0 && index < questionBank.size()) {
      Question question = questionBank.get(index);
      Difficulty oldDifficulty = question.getDifficulty();
      question.updateDifficulty(newDifficulty);
      if (oldDifficulty == Difficulty.EASY && newDifficulty == Difficulty.HARD) {
        this.easyToHard++;
      } else if (oldDifficulty == Difficulty.HARD && newDifficulty == Difficulty.EASY) {
        this.hardToEasy++;
      }
    } else {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }
  }

  /**
   * writes the questions to the sr file with their updated difficulty
   */
  public void writeNewDiffQuestions() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      for (Question question : questionBank) {
        writer.write("Question: " + question.getQuestionText());
        writer.newLine();
        writer.write("Answer: " + question.getAnswerText());
        writer.newLine();
        writer.write("Difficulty: " + question.getDifficulty());
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * counts number of hard questions in question bank
   *
   * @return number of hard questions in question bank
   */
  public int getNumberOfHardQuestions() {
    int count = 0;
    for (Question question : questionBank) {
      if (question.getDifficulty() == Difficulty.HARD) {
        count++;
      }
    }
    return count;
  }

  /**
   * counts number of easy questions in question bank
   *
   * @return number of easy questions in question bank
   */
  public int getNumberOfEasyQuestions() {
    int count = 0;
    for (Question question : questionBank) {
      if (question.getDifficulty() == Difficulty.EASY) {
        count++;
      }
    }
    return count;
  }

  /**
   * returns number of questions that changed from easy to hard
   *
   * @return number of questions that changed from easy to hard
   */
  public int getEasyToHard() {
    return this.easyToHard;
  }

  /**
   * returns number of questions that changed from hard to easy
   *
   * @return number of questions that changed from hard to easy
   */
  public int getHardToEasy() {
    return this.hardToEasy;
  }


}
