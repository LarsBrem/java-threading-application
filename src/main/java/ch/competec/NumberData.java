package ch.competec;

import java.util.concurrent.ThreadLocalRandom;

public class NumberData {

  private int sum;

  public synchronized void randomWait() {
    try {
      wait(ThreadLocalRandom.current().nextInt(5000, 20000));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void addRandomNumberToSum() {
    setSum(getSum() + ThreadLocalRandom.current().nextInt(1, 10));
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }
}
