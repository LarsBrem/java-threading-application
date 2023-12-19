package ch.competec;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

public class ThreadController {

  private List<NumberThread> threadList = new ArrayList<>();

  public void createThreads(int numberOfThreads) {
    for (int i=0;i<numberOfThreads;i++) {
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
    for (NumberThread n : threadList) {
        System.out.println(n.getId()+" | "+n.getState()+" | "+n.getStartTime());
    }

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
