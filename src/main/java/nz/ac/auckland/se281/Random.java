package nz.ac.auckland.se281;

public class Random implements Strategies {

  public int[] createFingersSum(int playerAvg) {
    // Generate a random number for the fingers and then create a sum value based off that
    int finger = Utils.getRandomNumber(1, 5);
    int sum = Utils.getRandomNumber(finger + 1, finger + 5);
    // Store these random values into an array to be accessed later.
    int[] fingersSum = {finger, sum};
    return fingersSum;
  }
}
