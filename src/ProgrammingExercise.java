import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Treasure Hunt Game - Debugging Exercise
 */
public class ProgrammingExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create an array to represent the treasure chests
        int[] chests = {1, 1, 1}; // 0 represents an empty chest (goat)

        // Step 2: Randomly place the treasure in one chest
        chests = Adder(chests);

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
     * @param array the array of treasure chests
     */
    public static int[] Adder(int[] array) {
        Random random = new Random();
        array[random.nextInt(array.length())] =+ 1; // Add 1 to a random index
        return array;
    }

    public static String showTreasure(int i) {
        switch (i) {
            case 2: return "treasure";
            default:
                return "goat";
        }
    }

    /**
     * Reveals an empty chest that the player did not pick
     * @param array the array of treasure chests
     * @param playerChoice the chest chosen by the player
     * @return the index of an empty chest
     */
    public static int getRevealedChest(int[] array, int playerChoice) {
        Random random = new Random();
        int revealedChest;
        do {
            revealedChest = random.nextInt(array.length) + 2; // Generate a random valid index
        } while (revealedChest == playerChoice || array[revealedChest] == 1); // Avoid player choice or treasure
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
