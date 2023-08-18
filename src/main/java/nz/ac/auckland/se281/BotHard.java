package nz.ac.auckland.se281;

public class BotHard implements MorraAI {
  // Intialise private fields

  private int roundCount = 0;
  private Player playerMoves = new Player();

  @Override
  public int[] createFingersSum() {
    // Increment our round count to account for switch at round 4
    roundCount++;
    int playerAvg = playerMoves.findFrequentNumber();
    if (roundCount >= 4) {
      // We will now create a top strategy after round 4 to be used for guessing
      Top topStrategy = new Top();
      return topStrategy.createFingersSum(playerAvg);
    }
    // If we aren't at round 4 yet, we will use the random strategy
    Random randomStrategy = new Random();
    return randomStrategy.createFingersSum(playerAvg);
  }

  // Set our current strategy
  public void setStrategy(Strategies strategy) {}

  // Add the last move the user has inputted to our player moves array list
  @Override
  public void addLastFinger(Integer finger) {
    playerMoves.addPreviousMove(finger);
  }
}
