package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Player {
  // Initialise an array list that will be used to track the player's previous moves
  private ArrayList<Integer> previousMoves = new ArrayList<Integer>();

  // Function that will add the latest finger move from the player
  public void addPreviousMove(int finger) {
    previousMoves.add(finger);
  }

  // Function that will find the average finger input from the user
  public int averageFinger() {
    // If our array list is 0, we will ignore as we cannot find average of 0.
    if (previousMoves.size() == 0) {
      return 0;
    }
    // Initialise the sum of the finger to 0 and then add them all together
    int totalFinger = 0;
    for (int i = 0; i < previousMoves.size() - 1; i++) {
      totalFinger = previousMoves.get(i) + totalFinger;
    }
    // Return the the sum divided by the total amount of items in our array list
    return Math.round((float) totalFinger / (float) (previousMoves.size() - 1));
  }

  // This function will help us find the most frequent input the user has done
  public int findFrequentNumber() {
    // If our array list is 0, we cannot find a frequent input yet.
    if (previousMoves.size() == 0) {
      return 0;
    }
    // Initialise variables that will keep track of our current most frequent number and its literal
    // value
    int largestCount = 0;
    int mostFrequentNumber = 0;
    // Loop through our array list to count up the user's input
    for (int i = 0; i < previousMoves.size() - 1; i++) {
      int count = 0;
      for (int j = 0; j < previousMoves.size() - 1; j++) {
        if (previousMoves.get(i) == previousMoves.get(j)) {
          count++;
        }
      }
      // We are checking now if the the number we are checking has a larger frequency than our other
      // numbers, if so we will set that number as our largest count.
      // Equally we have to consider if there is a number that has the same amount of frequency as
      // the largest count but is larger, we will instead make that our largest count.
      if ((count > largestCount)
          || (count == largestCount && previousMoves.get(i) > mostFrequentNumber)) {
        largestCount = count;
        mostFrequentNumber = previousMoves.get(i);
      }
    }

    return mostFrequentNumber;
  }
}
