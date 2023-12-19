package ch.competec;

import java.util.concurrent.ThreadLocalRandom;

public class NumberData {

  public synchronized int getNumber() {
    try {
      wait(ThreadLocalRandom.current().nextInt(5000, 20000));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return ThreadLocalRandom.current().nextInt(1, 10);
  }
}
