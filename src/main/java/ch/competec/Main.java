package ch.competec;

public class Main {

  public static void main(String[] args){
    ThreadController threadController = new ThreadController();
    try {
      threadController.createThreads(threadController.preCreateThreads());
      threadController.getInformation();
    } catch (InterruptedException ex) {
      System.out.println("Thread couldn't sleep!");
      System.exit(0);
    }
  }
}
