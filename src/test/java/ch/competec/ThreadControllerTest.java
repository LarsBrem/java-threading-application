package ch.competec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

public class ThreadControllerTest {

  ThreadController threadController = new ThreadController();

  @Test
  public void preCreateThreads_isSuccessful_givenValidNumber() {
    String numberOfThreads = "20";

    ByteArrayInputStream inContent = new ByteArrayInputStream(numberOfThreads.getBytes());
    InputStream originalIn = System.in;
    System.setIn(inContent);

    assertEquals(20, threadController.preCreateThreads());

    System.setIn(originalIn);
  }

  @Test
  public void preCreateThreads_returnsError_givenNonNumericValue() {
    String numberOfThreads = "No Number";

    ByteArrayInputStream inContent = new ByteArrayInputStream(numberOfThreads.getBytes());
    InputStream originalIn = System.in;
    System.setIn(inContent);

    assertThrows(NoSuchElementException.class, () -> threadController.preCreateThreads());

    System.setIn(originalIn);
  }

  @Test
  public void preCreateThreads_returnsError_givenNumberTooBig() {
    String numberOfThreads = "5000";

    ByteArrayInputStream inContent = new ByteArrayInputStream(numberOfThreads.getBytes());
    InputStream originalIn = System.in;
    System.setIn(inContent);

    assertThrows(NoSuchElementException.class, () -> threadController.preCreateThreads());

    System.setIn(originalIn);
  }

  @Test
  public void preCreateThreads_returnsError_givenNumberTooSmall() {
    String numberOfThreads = "1";

    ByteArrayInputStream inContent = new ByteArrayInputStream(numberOfThreads.getBytes());
    InputStream originalIn = System.in;
    System.setIn(inContent);

    assertThrows(NoSuchElementException.class, () -> threadController.preCreateThreads());

    System.setIn(originalIn);
  }

  @Test
  public void createThreads_isNumberOfThreadsCorrect() {
    int numberOfThreads = 5;

    try {
      threadController.createThreads(numberOfThreads);
      assertTrue(threadController.checkIfThreadsStarted(numberOfThreads));
    } catch (InterruptedException ex) {
      fail();
    }
  }

  @Test
  public void getInformation_returnsSum() {
    int numberOfThreads = 5;

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      threadController.createThreads(numberOfThreads);
      threadController.getInformation();
    } catch (InterruptedException ex) {
      fail();
    }

    String substring = outContent.toString()
        .substring(outContent.toString().lastIndexOf("Your randomly generated sum is:"));
    String number = substring.replaceAll("[^0-9]", "");
    int result = Integer.parseInt(number);
    System.setOut(originalOut);

    assertTrue(result >= numberOfThreads && result <= numberOfThreads * 10);
  }
}
