package ch.competec;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ThreadController {

  NumberData numberData = new NumberData();

  public int preCreateThreads() {
    int numberOfThreads = 0;
    boolean repeat = true;

    while (repeat) {
      Scanner scanner = new Scanner(System.in);
      System.out.println(
          "How many Threads do you want to start? (number must be between 2 and 1000)");
      try {
        numberOfThreads = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException ex) {
      }
      if (numberOfThreads < 2 || numberOfThreads > 1000) {
        System.out.println("This is a invalid input");
      } else {
        repeat = false;
      }
    }
    return numberOfThreads;
  }

  public void createThreads(int numberofThreads) throws InterruptedException {
    for (int i = 0; i < numberofThreads; i++) {
      NumberThread numberThread = new NumberThread(numberData);
      numberThread.start();
    }
    if (!checkIfThreadsStarted(numberofThreads)) {
      System.out.println("The application didn't start the right amount of threads!");
      System.exit(0);
    }
  }

  public boolean checkIfThreadsStarted(int numberOfCreatedThreads) throws InterruptedException {
    Thread.sleep(1000);

    int numberOfStartedThreads = 0;

    for (Thread t : Thread.getAllStackTraces().keySet()) {
      if (t.getName().contains("Thread-")) {
        numberOfStartedThreads++;
        if (!t.getState().equals(State.TIMED_WAITING)) {
          return false;
        }
      }
    }

    return numberOfCreatedThreads == numberOfStartedThreads;
  }

  public void getInformation() throws InterruptedException {
    boolean repeat = true;

    List<String> outputList = new ArrayList<>();

    while (repeat) {
      outputList.clear();
      System.out.println();
      Thread.getAllStackTraces().keySet().forEach(t -> {
        if (t.getName().contains("Thread-")) {
          outputList.add(
              t.getId() + " | " + t.getState() + " | " + ((NumberThread) t).getStartTime());
        }
      });

      if (outputList.isEmpty()) {
        repeat = false;
        continue;
      }

      System.out.print(sortThreadOutput(outputList));

      Thread.sleep(1000);
    }

    System.out.println("Your randomly generated sum is: " + numberData.getSum());
  }

  private String sortThreadOutput(List<String> outputList) {
    String output = "";
    List<Integer> unsortedIntList = new ArrayList<>();
    List<Integer> sortedIntList = new ArrayList<>();
    List<Integer> indexList = new ArrayList<>();

    for (String s : outputList) {
      String x = s.substring(0, s.indexOf(' '));
      unsortedIntList.add(Integer.parseInt(x));
      sortedIntList.add(Integer.parseInt(x));
    }

    sortedIntList.sort(Comparator.naturalOrder());

    for (int i : sortedIntList) {
      indexList.add(unsortedIntList.indexOf(i));
    }

    for (int i : indexList) {
      output = output + outputList.get(i) + "\n";
    }

    return output;
  }
}
