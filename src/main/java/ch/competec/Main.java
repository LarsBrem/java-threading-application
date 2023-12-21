package ch.competec;

public class Main {

  public static void main(String[] args){
    ThreadController threadController = new ThreadController();
    int numberOfThreads = threadController.preCreateThreads();
    threadController.createThreads(numberOfThreads);
    try {
      threadController.startThreads();
      threadController.getInformation();
    } catch (InterruptedException ex) {
      System.out.println("Thread couldn't sleep!");
      System.exit(0);
    }
  }
}
