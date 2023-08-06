package cs3500.pa01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * CreationOrdering implements the OrderingType interface
 * that sorts a list of MarkdownFile objects based on their creation date
 */
public class CreationOrdering implements OrderingType {
  /**
   * Sorts the given list of MarkdownFile by their creation date
   *
   * @param files The list of MarkdownFile that will be sorted
   * @return The sorted list of MarkdownFile
   */
  public ArrayList<MarkdownFile> sort(ArrayList<MarkdownFile> files) {
    Collections.sort(files, Comparator.comparing((MarkdownFile file) -> file.getCreated()));
    return files;
  }
}
