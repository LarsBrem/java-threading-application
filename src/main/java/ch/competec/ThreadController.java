package ch.competec;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadController {

  private List<NumberThread> threadList = new ArrayList<>();

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

  public void createThreads(int numberofThreads) {
    for (int i = 0; i < numberofThreads; i++) {
      NumberThread numberThread = new NumberThread(new NumberData());
      threadList.add(numberThread);
    }
  }

  public void startThreads() throws InterruptedException {
    for (NumberThread n : threadList) {
      n.start();
    }

    Thread.sleep(1000);

    if (!threadList.stream().allMatch(o -> State.TIMED_WAITING.equals(o.getState()))) {
      System.out.println("The application didn't start the right amount of threads!");
      System.exit(0);
    }
  }

  public void getInformation() throws InterruptedException {
    int sum = 0;

    while (threadList.size() > 0) {
      System.out.println();
      List<NumberThread> temporaryList = new ArrayList<>(threadList);
      for (NumberThread n : temporaryList) {
        if (n.getNumber() != 0) {
          sum = sum + n.getNumber();
        }
        if (n.getState() == State.TERMINATED) {
          threadList.remove(n);
        } else {
          System.out.println(n.getId()+" | "+n.getState()+" | "+n.getStartTime());
        }
      }
      Thread.sleep(1000);
    }

    System.out.println("Your randomly generated sum is: "+sum);
  }

  public List<NumberThread> getThreadList() {
    return threadList;
  }
}
