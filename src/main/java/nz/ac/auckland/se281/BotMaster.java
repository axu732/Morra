package nz.ac.auckland.se281;

public class BotMaster implements MorraAI {
  // Intialise private fields
  private Strategies strategy;
  private int roundCount = 0;
  private Player playerMoves = new Player();

  @Override
  public int[] createFingersSum() {
    // Increment our round counter to account for the switch at round 4
    roundCount++;
    int playerArg = playerMoves.averageFinger();
    // When we hit round 4, set our strategy to top so we switch into average on round 4
    if (roundCount == 4) {
      Top topStrategy = new Top();
      setStrategy(topStrategy);
    }
    // We will check if the round counter is more than 4 and if our strategy is the top one
    // currently, if so we switch to the average strategy
    if (roundCount >= 4 && strategy instanceof Top) {
      Average averageStrategy = new Average();
      setStrategy(averageStrategy);
      return averageStrategy.createFingersSum(playerArg);
    } else if (roundCount >= 4 && strategy instanceof Average) {
      // Now if we have the average strategy, we will switch into the top strategy
      playerArg = playerMoves.findFrequentNumber();
      Top topStrategy = new Top();
      setStrategy(topStrategy);
      return topStrategy.createFingersSum(playerArg);
    }
    // If we aren't at round 4, we will just use the random strategy

    Random randomStrategy = new Random();
    return randomStrategy.createFingersSum(playerArg);
  }

  // Set the current strategy
  public void setStrategy(Strategies strategy) {
    this.strategy = strategy;
  }

  // Function that will add the latest move from the user to an arraylist
  @Override
  public void addLastFinger(Integer finger) {
    playerMoves.addPreviousMove(finger);
  }
}
