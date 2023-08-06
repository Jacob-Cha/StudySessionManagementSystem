package cs3500.pa01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ModifiedOrdering implements the OrderingType interface
 * that sorts a list of MarkdownFile objects based on their modified date
 */
public class ModifiedOrdering implements OrderingType {
  /**
   * Sorts the given list of MarkdownFile by their modified date
   *
   * @param files The list of MarkdownFile that will be sorted
   * @return The sorted list of MarkdownFiles
   */
  public ArrayList<MarkdownFile> sort(ArrayList<MarkdownFile> files) {
    Collections.sort(files, Comparator.comparing((MarkdownFile file) -> file.getModified()));
    return files;
  }
}
