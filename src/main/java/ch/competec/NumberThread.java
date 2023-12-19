package ch.competec;

public class NumberThread implements Runnable {
  NumberData numberData;

  public NumberThread(NumberData numberData) {
    this.numberData = numberData;
  }

  @Override
  public void run() {}
}
