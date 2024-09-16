/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: William Terracina
 * Last Updated: 09/16/2024
 */

package terracinaw;

import java.util.Scanner;

public class Driver {
    public static int MIN_DICE = 2;
    public static int MAX_DICE = 10;

    public static void main(String[] args) {
        boolean valid = false;
        while(!valid) {
            System.out.println("Please enter the number of dice to roll, " +
                    "how many sides the dice have,\nand how many " +
                    "rolls to complete, separating the values by a space.");
            System.out.println("Example: \"2 6 1000\"\n");
            System.out.print("Enter Configuration: ");
            try {
                int[] diceSidesRolls = getInput();
                Die[] dice = createDice(diceSidesRolls[0], diceSidesRolls[1]);
                int[] rolls = rollDice(dice, diceSidesRolls[1], diceSidesRolls[2]);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (IllegalStateException | IllegalArgumentException | DieNotRolledException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static int[] getInput() throws IllegalStateException, NumberFormatException{
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\\n");
        String text = in.next();
        String[] params = text.split(" ");
        int[] input;
        if (params.length < 3) {
            throw new IllegalStateException("Invalid input: Expected 3 values but only received " + params.length + ".");
        } else if (params.length > 3){
            throw new IllegalStateException("Invalid input: Expected 3 values but received " + params.length + ".");
        } else {
            input = new int[params.length];
            for (int i = 0; i < params.length; i++) {
                input[i] = Integer.parseInt(params[i]);
            }
        }
        return input;
    }

    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int[] results = new int[numSides];
        for (int i = 0; i < numRolls; i++) {
            int value = 0;
            for (int j = 0; j < dice.length; j++) {
                dice[i].roll();
                value += dice[i].getCurrentValue();
            }
            results[i] = value;
        }
        return results;
    }

    private static int findMax(int[] rolls) {
        int max = 0;
        for (int i = 0; i < rolls.length; i++) {
            int rollValue = rolls[i];
            int count = 0;
            for (int j = 0; j < rolls.length; j++) {
                if (rolls[j] == rollValue) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    private static void report(int numDice, int[] rolls, int max) {}
}