package cs3500.pa01;

import java.util.ArrayList;

/**
 * The Section class represents a section in a Markdown file
 * Each section has a heading and a list of important phrases
 */
public class Section {
  private final String heading;
  private final ArrayList<String> importantPhrases;
  private final ArrayList<Question> questions;


  /**
   * Constructs a new Section with a given heading
   *
   * @param heading the heading for this section
   */
  public Section(String heading) {
    this.heading = heading;
    this.importantPhrases = new ArrayList<>();
    this.questions = new ArrayList<>();
  }

  /**
   * Adds a phrase to the list of important phrases for this section
   *
   * @param phrase the phrase to be added
   */
  public void addPhrase(String phrase) {
    this.importantPhrases.add(phrase);
  }

  /**
   * Adds a question to the list of questions for this section
   *
   * @param questionText the question text
   * @param answerText   the answer text
   */
  public void addQuestion(String questionText, String answerText) {
    this.questions.add(new Question(questionText, answerText, Difficulty.HARD));
  }

  /**
   * Returns the heading for this section
   *
   * @return the heading for this section
   */
  public String getHeading() {
    return heading;
  }

  /**
   * Returns the list of important phrases for this section
   *
   * @return the list of important phrases for this section
   */
  public ArrayList<String> getImportantPhrases() {
    return importantPhrases;
  }

  /**
   * Returns the list of questions for this section
   *
   * @return the list of questions for this section
   */
  public ArrayList<Question> getQuestions() {
    return questions;
  }
}

