package cs3500.pa01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MarkdownFileWriter is a class that writes a list of MarkdownFiles to a file
 */
public class MarkdownFileWriter {
  private String outputPath;

  /**
   * Constructor for MarkdownFileWriter
   *
   * @param outputPath The path where the output file will be written
   */
  public MarkdownFileWriter(String outputPath) {
    this.outputPath = outputPath;
  }

  /**
   * Writes the given list of MarkdownFile objects to the output file
   * First the sections of each file is written, followed by a list of
   * important phrases in that section right after the section is written
   *
   * @param files The list of MarkdownFiles that will be written to the output file
   */
  public void writeMarkdownFiles(ArrayList<MarkdownFile> files) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
      for (MarkdownFile file : files) {
        for (Section section : file.getSections()) {
          writer.write(section.getHeading());
          writer.newLine();
          for (String phrase : section.getImportantPhrases()) {
            writer.write("- " + phrase);
            writer.newLine();
          }
          writer.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}