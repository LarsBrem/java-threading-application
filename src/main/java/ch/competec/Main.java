package ch.competec;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    int numberOfThreads = 0;
    List<NumberThread> numberThreadList = new ArrayList<>();
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

    for (int i=0;i<numberOfThreads;i++) {
      NumberThread numberThread = new NumberThread(new NumberData());
      numberThreadList.add(numberThread);
    }

    System.out.println(numberThreadList.size());
  }
}
