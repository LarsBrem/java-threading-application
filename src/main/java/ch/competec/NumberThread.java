package ch.competec;

import java.time.LocalDateTime;

public class NumberThread extends Thread {

  private final NumberData numberData;
  private int number = 0;
  private LocalDateTime startTime;

  public NumberThread(NumberData numberData) {
    this.numberData = numberData;
  }

  @Override
  public void run() {
    startTime = LocalDateTime.now();
    number = numberData.getNumber();
  }

  public int getNumber() {
    return number;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }
}
