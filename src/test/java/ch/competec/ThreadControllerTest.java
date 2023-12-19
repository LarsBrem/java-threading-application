package ch.competec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class ThreadControllerTest {

  ThreadController threadController= new ThreadController();

  @Test
  void createThreads_isSuccessful() {
    int numberOfThreads = 5;
    threadController.createThreads(numberOfThreads);

    assertEquals(numberOfThreads, threadController.getThreadList().size());
  }

  @Test
  void startThreads_isSuccessful() {
    int numberOfThreads = 5;
    threadController.createThreads(numberOfThreads);
    try {
      threadController.startThreads();
    } catch (InterruptedException ex) {
      assertEquals(1, 2);
    }
    assertTrue(threadController.getThreadList().get(0).isAlive());
  }

  @Test
  void getInformation_returnsSum() { //TODO: Work in progress
    int numberOfThreads = 5;
    threadController.createThreads(numberOfThreads);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    try {
      threadController.startThreads();
      threadController.getInformation();
    } catch (InterruptedException ex) {
      assertEquals(1, 2);
    }
    String s = outContent.toString().substring(outContent.toString().lastIndexOf("Your randomly generated sum is:"));
    String number = s.replaceAll("[^0-9]", "");
    int n = Integer.parseInt(number);
    System.setOut(originalOut);
    assertTrue(n>=numberOfThreads && n<=numberOfThreads*10);
  }

}
