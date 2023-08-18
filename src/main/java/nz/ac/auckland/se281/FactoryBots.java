package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class FactoryBots {

  public static MorraAI createBot(Difficulty difficulty) {
    // Using our difficulty given by the player, we will create the bot that is needed for our game
    switch (difficulty) {
      case EASY:
        return new BotEasy();

      case MEDIUM:
        return new BotMedium();

      case HARD:
        return new BotHard();

      case MASTER:
        return new BotMaster();

      default:
        System.err.println("No Difficulty of that type");
        System.exit(0);
    }
    return null;
  }
}
