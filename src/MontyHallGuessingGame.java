import java.util.Random;
import java.util.Scanner;

/**
 * Treasure Hunt Guessing Game - Monty Hall.
 * The user chooses which door to open.
 * Monty opens an empty door of the remaining doors.
 * The user decides whether to switch door or not.
 * Since Monty knows what is hidden behind the doors, there is a greater chance to win if the user switches door.
 */
public class MontyHallGuessingGame {
    /**
     * Runs the guessing game
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create an array to represent the treasure chests
        int[] chests = {0, 0, 0}; // 0 represents an empty chest (goat)

        // Step 2: Randomly place the treasure in one chest
        chests = randomlyPlaceTreasure(chests);

        // Step 3: Player picks a chest
        System.out.println("Pick a chest (0, 1, or 2): ");
        int playerChoice = scanner.nextInt();

        // Step 4: Computer reveals an empty chest
        int revealedChest = getRevealedChest(chests, playerChoice);
        System.out.println("You chose chest " + (playerChoice));
        System.out.println("Monty shows a " + showTreasure(chests[playerChoice]) + " in chest " + (revealedChest));

        // Step 5: Ask player if they want to switch
        System.out.print("Do you want to switch your choice? (yes/no): ");
        String switchChoice = scanner.next();

        if (switchChoice.equalsIgnoreCase("yes")) {
            playerChoice = switchChest(playerChoice, revealedChest);
        }

        // Step 6: Reveal if the player won
        if (chests[playerChoice] == 1) {
            System.out.println("Congratulations! You found the treasure!");
        } else {
            System.out.println("Sorry, no treasure in this chest.");
        }

        scanner.close();
    }

    /**
     * Randomly places a treasure in one of the chests
     * @param chests the empty chests
     * @return the chests with a randomly placed treasure
     */
    public static int[] randomlyPlaceTreasure(int[] chests) {
        Random random = new Random();
        chests[random.nextInt(chests.length)] += 1; // Add 1 to a random index
        return chests;
    }

    /**
     * Show the treasure represented by a number (1 for treasure, otherwise goat)
     * @param number the number hidden in the chest
     * @return the textual representation of the number
     */
    public static String showTreasure(int number) {
        if (number == 1) {
            return "treasure";
        } else {
            return "goat";
        }
    }

    /**
     * Reveals an empty chest that the player did not pick
     * @param chests the chests
     * @param playerChoice the number of the chest chosen by the player
     * @return the index of an empty chest that the player has not chosen
     */
    public static int getRevealedChest(int[] chests, int playerChoice) {
        Random random = new Random();
        int revealedChest;
        do {
            revealedChest = random.nextInt(chests.length);
        } while (revealedChest == playerChoice || chests[revealedChest] == 1); // Avoid player choice or treasure
        return revealedChest;
    }

    /**
     * Switches the player's choice to the remaining chest
     * @param playerChoice the player's current choice
     * @param revealedChest the chest revealed by the host
     * @return the new choice of the player
     */
    public static int switchChest(int playerChoice, int revealedChest) {
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && i != revealedChest) {
                playerChoice = i;
                return playerChoice;
            }   }
        return playerChoice;
    }
}
