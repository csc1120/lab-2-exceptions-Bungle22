/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: William Terracina
 * Last Updated: 09/16/2024
 */

package terracinaw;

import java.util.Scanner;

/**
 * Driver class
 */
public class Driver {
    /**
     * Minimum number of dice we can create
     */
    public static final int MIN_DICE = 2;
    /**
     * Maximum number of dice we can create
     */
    public static final int MAX_DICE = 10;
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
                report(diceSidesRolls[0], rolls, findMax(rolls));
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic Error");
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
            throw new IllegalStateException("Invalid input: Expected 3 " +
                    "values but only received " + params.length + ".");
        } else if (params.length > 3){
            throw new IllegalStateException("Invalid input: Expected 3 " +
                    "values but received " + params.length + ".");
        } else {
            input = new int[params.length];
            for (int i = 0; i < params.length; i++) {
                input[i] = Integer.parseInt(params[i]);
            }
            if (input[0] < MIN_DICE || input[0] > MAX_DICE) {
                throw new IllegalStateException("Invalid input: Can only use between 2 and 6 dice");
            }
        }
        return input;
    }

    private static Die[] createDice(int numDice, int numSides) throws DieNotRolledException {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int[] results = new int[numRolls];
        for (int i = 0; i < numRolls; i++) {
            int value = 0;
            for (Die die : dice) {
                die.roll();
                value += die.getCurrentValue();
            }
            results[i] = value;
        }
        return results;
    }

    private static int findMax(int[] rolls) {
        int max = 0;
        for (int rollValue : rolls) {
            int count = 0;
            for (int roll : rolls) {
                if (roll == rollValue) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    private static void report(int numDice, int[] rolls, int max) {
        int highValue = rolls[0];
        int lowValue = rolls[0];
        for (int roll : rolls) {
            if (roll > highValue) {
                highValue = roll;
            }
            if (roll <= lowValue) {
                lowValue = roll;
            }
        }
        int[] storage = new int[highValue - lowValue + 1];
        for (int i = 0; i <= rolls.length - 1; i++) {
            storage[rolls[i] - lowValue]++;
        }
        final int highValueLength = String.valueOf(highValue).length();
        final int highFreqLength = String.valueOf(storage[highValue - lowValue]).length();
        final int whiteSpace2Int = 5;
        final int percentage10 = 10;
        double scale = (double) max / percentage10;
        if (scale == 0) {
            scale = 1;
        }
        StringBuilder build4 = new StringBuilder();
        for (int i = 0; i < storage.length; i++) {
            StringBuilder build1 = new StringBuilder();
            StringBuilder build2 = new StringBuilder();
            StringBuilder build3 = new StringBuilder();
            int value = i + numDice;
            int length = String.valueOf(value).length();
            int numSpaces = 0;
            int freqLength = String.valueOf(storage[i]).length();
            while (numSpaces < (highValueLength - length)) {
                build1.append(" ");
                numSpaces++;
            }
            String whiteSpace1 = build1.toString();
            int whiteSpace2Length = 0;
            String whiteSpace2;
            while (whiteSpace2Length < (highFreqLength - freqLength + whiteSpace2Int)) {
                build2.append(" ");
                whiteSpace2Length++;
            }
            whiteSpace2 = build2.toString();
            int numStars = (int) (storage[i] / scale);
            build3.append("*".repeat(Math.max(0, numStars)));
            String numAst = build3.toString();
            build4.append(String.format("%d%s:%d%s%s%n", value, whiteSpace1,
                    storage[i], whiteSpace2, numAst));
        }
        System.out.println(build4);
    }
}