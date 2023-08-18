package nz.ac.auckland.se281;

public class Top implements Strategies {

  @Override
  public int[] createFingersSum(int playerArgument) {
    // Generate a random value from 1 - 5
    int finger = Utils.getRandomNumber(1, 5);
    // We will now use our finger value and the most frequent value from the user to generate our
    // sum
    int sum = finger + playerArgument;
    int[] fingersSum = {finger, sum};
    return fingersSum;
  }
}
