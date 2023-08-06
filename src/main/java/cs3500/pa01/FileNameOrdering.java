package cs3500.pa01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * FileNameOrdering implements the OrderingType interface
 * that sorts a list of MarkdownFile objects based on their filename
 */
public class FileNameOrdering implements OrderingType {
  /**
   * Sorts the given list of MarkdownFile by their filename
   *
   * @param files The list of MarkdownFile that will be sorted
   * @return The sorted list of MarkdownFile
   */
  public ArrayList<MarkdownFile> sort(ArrayList<MarkdownFile> files) {
    Collections.sort(files, Comparator.comparing((MarkdownFile file) -> file.getFilename()));
    return files;
  }
}
