package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {
  // Initialise variables
  private int currentRound;
  private String playerName;
  private MorraAI jarvis;
  private int neededPoints;
  private boolean gameLive;
  private int humanPoints;
  private int aiPoints;

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    // Take the name of our player and respond, then set the current round to 1.
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    playerName = options[0];
    currentRound = 0;
    // Create the AI from the difficulty with our factory
    jarvis = FactoryBots.createBot(difficulty);
    gameLive = true;
    humanPoints = 0;
    aiPoints = 0;
    neededPoints = pointsToWin;
  }

  public void play() {
    if (gameLive == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Start the round then increment the current round value
    currentRound++;
    MessageCli.START_ROUND.printMessage(Integer.toString(currentRound));

    // Intialise our variables before enter the while loop that will check the user's input
    boolean invalidValues = true;
    Integer finger = null;
    Integer sum = null;

    while (invalidValues == true) {
      MessageCli.ASK_INPUT.printMessage();
      String input = Utils.scanner.nextLine();
      String[] sumFingers = input.split(" ");

      // Check if any of the values are not integers, return a -1 if so and will be detected
      // by our if statement
      finger = integerCheck(sumFingers[0]);
      sum = integerCheck(sumFingers[1]);

      if ((finger > 5 || finger < 1) || (sum > 10 || sum < 1)) {
        MessageCli.INVALID_INPUT.printMessage(playerName);
        continue;
      }

      invalidValues = false;
      jarvis.addLastFinger(finger);
    }

    MessageCli.PRINT_INFO_HAND.printMessage(
        playerName, Integer.toString(finger), Integer.toString(sum));

    // Generate AI guesses
    int[] botGuess = jarvis.createFingersSum();
    int fingerBot = botGuess[0];
    int sumBot = botGuess[1];

    // Print out AI moves
    MessageCli.PRINT_INFO_HAND.printMessage(
        "Jarvis", Integer.toString(fingerBot), Integer.toString(sumBot));

    // Check who won buy adding up our fingers
    int totalSum = fingerBot + finger;

    // If statements will check if one person won, else if both won or lost it is a draw.
    if (totalSum == sum && sumBot != totalSum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      humanPoints++;
    } else if (totalSum == sumBot && totalSum != sum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      aiPoints++;
    } else {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    }

    // We will check if the points we have increment has lead to a victory of game
    if (humanPoints == neededPoints) {
      MessageCli.END_GAME.printMessage(playerName, Integer.toString(currentRound));
      gameLive = false;
    } else if (aiPoints == neededPoints) {
      MessageCli.END_GAME.printMessage("Jarvis", Integer.toString(currentRound));
      gameLive = false;
    }
  }

  public void showStats() {
    // If the game is not live, we will return and print an error to the user
    if (gameLive == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    // Print out the stats of who how many rounds someone has won and how many are needed for them
    // to win
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        playerName, Integer.toString(humanPoints), Integer.toString(neededPoints - humanPoints));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        "Jarvis", Integer.toString(aiPoints), Integer.toString(neededPoints - aiPoints));
  }

  // Helper Function that will check if a inputted value from the user is an integer or not
  public Integer integerCheck(String sumFinger) {
    try {
      return Integer.parseInt(sumFinger);
    } catch (NumberFormatException e) {
      MessageCli.INVALID_INPUT.printMessage();
      return -1;
    }
  }
}
