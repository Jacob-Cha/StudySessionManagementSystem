package cs3500.pa01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class writes questions to a file
 */
public class QuestionsAnswersWriter {
  private final String outputPath;

  /**
   * Constructs a QuestionsAnswersWriter given a filepath
   *
   * @param outputPath the path that the questions will be written to
   */
  public QuestionsAnswersWriter(String outputPath) {
    this.outputPath = outputPath;
  }

  /**
   * Writes the questions from a list of MarkdownFiles to the file specified in outputPath
   * constructor
   *
   * @param files the list of MarkdownFiles that will have their questions extracted from
   */
  public void writeQuestions(ArrayList<MarkdownFile> files) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
      for (MarkdownFile file : files) {
        for (Section section : file.getSections()) {
          for (Question question : section.getQuestions()) {
            writer.write("Question: " + question.getQuestionText());
            writer.newLine();
            writer.write("Answer: " + question.getAnswerText());
            writer.newLine();
            writer.write("Difficulty: " + question.getDifficulty());
            writer.newLine();
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
