package nz.ac.auckland.se281;

public class BotEasy implements MorraAI {
  private Player playerMoves = new Player();

  // Set our current strategy
  public void setStrategy(Strategies strategy) {}

  public int[] createFingersSum() {
    // Create a random strategy object and then generate a values to be used
    Random randomStrategy = new Random();
    int playerArg = playerMoves.averageFinger();
    int[] fingersSum = randomStrategy.createFingersSum(playerArg);
    return fingersSum;
  }

  // Function that will let us add the player's last move to our array list
  @Override
  public void addLastFinger(Integer finger) {
    playerMoves.addPreviousMove(finger);
  }
}
