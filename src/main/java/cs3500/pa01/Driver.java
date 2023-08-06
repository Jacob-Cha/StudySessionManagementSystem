package cs3500.pa01;

import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * main method for driver class, accepts three args, notesRoot,
   * orderingFlag and outputPath
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      StudySessionView view = new StudySessionView(System.out);
      StudySessionController controller =
          new StudySessionController(view, new InputStreamReader(System.in));
      controller.startStudySession();
    } else {
      String notesRoot = args[0];
      String orderingFlag = args[1];
      MarkdownFileVisitor processor = new MarkdownFileVisitor(notesRoot);
      processor.processMain();
      ArrayList<MarkdownFile> files = processor.getProcessedFiles();
      OrderingType orderingType;
      switch (orderingFlag) {
        case "filename" -> orderingType = new FileNameOrdering();
        case "created" -> orderingType = new CreationOrdering();
        case "modified" -> orderingType = new ModifiedOrdering();
        default -> {
          System.err.println("Invalid ordering flag");
          System.exit(1);
          return;
        }
      }
      String outputPath = args[2];
      files = orderingType.sort(files);
      MarkdownFileWriter fileWriter = new MarkdownFileWriter(outputPath + ".md");
      QuestionsAnswersWriter srWriter = new QuestionsAnswersWriter(outputPath + ".sr");
      fileWriter.writeMarkdownFiles(files);
      srWriter.writeQuestions(files);
    }
  }
}
