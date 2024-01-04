package ch.competec;

import java.time.LocalDateTime;

public class NumberThread extends Thread {

  private final NumberData numberData;
  private LocalDateTime startTime;

  public NumberThread(NumberData numberData) {
    this.numberData = numberData;
  }

  @Override
  public void run() {
    startTime = LocalDateTime.now();
    numberData.randomWait();
    numberData.addRandomNumberToSum();
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }
}
