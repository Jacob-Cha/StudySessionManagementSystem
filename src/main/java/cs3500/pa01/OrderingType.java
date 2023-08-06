package cs3500.pa01;

import java.util.ArrayList;

/**
 * The OrderingType interface defines the sort method for Markdown files
 */
public interface OrderingType {

  /**
   * Sorts a list of Markdown files given a specific ordering flag
   *
   * @param files the list of files to be sorted
   * @return the sorted list of files
   */
  ArrayList<MarkdownFile> sort(ArrayList<MarkdownFile> files);
}
