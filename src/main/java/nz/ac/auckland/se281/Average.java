package nz.ac.auckland.se281;

public class Average implements Strategies {

  @Override
  public int[] createFingersSum(int playerAvg) {
    // We will generate a random number for our fingers
    int finger = Utils.getRandomNumber(1, 5);
    // Using the average calculated in our bot object, we will calculate a sum value
    int sum = finger + playerAvg;
    int[] fingersSum = {finger, sum};
    return fingersSum;
  }
}
