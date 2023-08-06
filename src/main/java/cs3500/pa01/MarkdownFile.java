package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

/**
 * MarkdownFile is a class that represents a markdown file. It contains
 * data about the files creation date, modification date, path, and sections/content of the file
 * The sections(sections contain headings and important phrases) are parsed from the
 * content of the file
 */
public class MarkdownFile {
  private final FileTime creationDate;
  private final FileTime modifiedDate;
  private final Path path;
  private final ArrayList<Section> sections;

  /**
   * MarkdownFile constructor
   *
   * @param path         The path of the file
   * @param creationDate The creation date of the file
   * @param modifiedDate The last modification date of the file
   * @param content      The content of the file
   */
  public MarkdownFile(Path path, FileTime creationDate, FileTime modifiedDate, String content) {
    this.path = path;
    this.creationDate = creationDate;
    this.modifiedDate = modifiedDate;
    this.sections = parseContent(content);
  }

  /**
   * Returns the creation date of the file
   *
   * @return The creation date of the file
   */
  public FileTime getCreated() {
    return creationDate;
  }

  /**
   * Returns the last modification date of the file
   *
   * @return The last modification date of the file
   */
  public FileTime getModified() {
    return modifiedDate;
  }

  /**
   * Returns the sections of the file
   *
   * @return The sections of the file
   */
  public ArrayList<Section> getSections() {
    return sections;
  }

  /**
   * Returns the filename of the file
   *
   * @return The filename of the file
   */
  public String getFilename() {
    return this.path.getFileName().toString().toLowerCase();
  }

  /**
   * Parses the content of the file into sections
   *
   * @param content The content of the file
   * @return The sections of the file
   */
  private ArrayList<Section> parseContent(String content) {
    ArrayList<Section> sections = new ArrayList<>();
    String[] lines = content.split("\n");
    Section currentSection = null;
    StringBuilder phraseBuilder = new StringBuilder();
    boolean isInMultiLinePhrase = false;

    for (String line : lines) {
      if (line.startsWith("#")) {
        if (currentSection != null) {
          sections.add(currentSection);
        }
        String heading = line;
        currentSection = new Section(heading);
      } else {
        if (isInMultiLinePhrase) {
          if (line.contains("]]")) {
            phraseBuilder.append(line, 0, line.indexOf("]]"));
            String phrase = phraseBuilder.toString();
            phraseBuilder.setLength(0);
            isInMultiLinePhrase = false;
            currentSection.addPhrase(phrase);
            line = line.substring(line.indexOf("]]") + 2);
          } else {
            phraseBuilder.append(line);
          }
        }
        while (line.contains("[[")) {
          int start = line.indexOf("[[") + 2;
          if (line.contains("]]")) {
            int end = line.indexOf("]]");
            String phrase = line.substring(start, end);
            if (phrase.contains(":::")) {
              String[] questionAnswer = phrase.split(":::");
              currentSection.addQuestion(questionAnswer[0], questionAnswer[1]);
            }
            currentSection.addPhrase(phrase);
            line = line.substring(end + 2);
          } else {
            phraseBuilder.append(line.substring(start));
            isInMultiLinePhrase = true;
            break;
          }
        }
      }
    }
    if (currentSection != null) {
      sections.add(currentSection);
    }
    return sections;
  }
}


