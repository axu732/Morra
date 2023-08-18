package nz.ac.auckland.se281;

public class BotMedium implements MorraAI {
  private Player playerMoves = new Player();
  private int roundCount = 0;

  @Override
  public int[] createFingersSum() {
    // Increment our round counter to account for the switch at 4
    roundCount++;
    // Using our player moves object, we will caluclate the average finger input of our player.
    int playerAvg = playerMoves.averageFinger();
    // When we reach round 4 and beyond, we will switch to the average strategy
    if (roundCount >= 4) {
      Average averageStrategy = new Average();
      setStrategy(averageStrategy);
      return averageStrategy.createFingersSum(playerAvg);
    }
    // If we aren't at round 4 yet, we will just use the random strategy
    Random randomStrategy = new Random();
    setStrategy(randomStrategy);
    return randomStrategy.createFingersSum(playerAvg);
  }

  // Set our current strategy
  public void setStrategy(Strategies strategy) {}

  // Function that will allow us to add our player's move into the array list.
  @Override
  public void addLastFinger(Integer finger) {
    playerMoves.addPreviousMove(finger);
  }
}
