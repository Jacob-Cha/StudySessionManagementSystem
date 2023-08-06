package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MarkdownFileVisitor is a FileVisitor implementation to process
 * markdown files in a specified directory and subdirectories
 */
public class MarkdownFileVisitor implements FileVisitor<Path> {
  private Path notesRootPath;
  private ArrayList<MarkdownFile> files;


  /**
   * MarkdownFileVisitor constructs with a given notes root directory
   *
   * @param notesRoot The root directory to begin processing markdown files
   */
  public MarkdownFileVisitor(String notesRoot) {
    this.notesRootPath = Paths.get(notesRoot);
    this.files = new ArrayList<>();
  }

  /**
   * called before a directory is visited.
   *
   * @param dir   The directory about to be traversed
   * @param attrs The directory's basic attributes
   * @return The FileVisitResult after visiting the directory
   */
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    System.out.format("Starting Directory: %s%n", dir);
    return FileVisitResult.CONTINUE;
  }

  /**
   * Called when a file in a directory is visited
   *
   * @param file  The file being visited
   * @param attrs The files basic attributes
   * @return The FileVisitResult after visiting the file
   */
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (file.toString().endsWith(".md")) {
      try {
        StringBuilder contentBuilder = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          contentBuilder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        String content = contentBuilder.toString();
        FileTime creationDate = (FileTime) Files.getAttribute(file, "creationTime");
        FileTime modifiedDate = (FileTime) Files.getAttribute(file, "lastModifiedTime");
        files.add(new MarkdownFile(file, creationDate, modifiedDate, content));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * called when a file could not be visited
   *
   * @param file The file that could not be visited
   * @param exc  The IO exception that was thrown when trying to visit the file
   * @return The FileVisitResult after failing to visit the file
   */
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println(exc);
    return FileVisitResult.CONTINUE;
  }

  /**
   * Called after a directory has been completely traversed
   *
   * @param dir The directory being checked
   * @param exc IO exception that is thrown if there is a exception
   * @return The FileVisitResult after visiting the directory
   */
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    System.out.format("Finishing Directory: %s%n", dir);
    return FileVisitResult.CONTINUE;
  }


  /**
   * Returns a list of the processed markdown files found during the file traversal
   *
   * @return An ArrayList of MarkdownFiles representing the processed markdown files
   */
  public ArrayList<MarkdownFile> getProcessedFiles() {
    return files;
  }

  /**
   * Initiates the main processing by walking the file tree starting at the given notes root path
   */
  public void processMain() {
    try {
      Files.walkFileTree(notesRootPath, this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}





