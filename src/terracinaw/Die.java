/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: William Terracina
 * Last Updated: 09/11/2024
 */
package terracinaw;

import java.util.Random;

/**
 * Die Object
 */
public class Die {
    /**
     * Minimum number of sides a valid die can have.
     */
    public static final int MIN_SIDES = 2;
    /**
     * Maximum number of sides a die can have.
     */
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();

    /**
     * Die constructor
     * @param numSides number of sides parameter
     * @throws IllegalArgumentException for bad die creation
     */
    public Die(int numSides) {
        if (numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException("Bad die creation: Illegal " +
                    "number of sides: " + numSides);
        } else {
            this.numSides = numSides;
        }
    }

    /**
     * Getter for die value when rolled
     * @return die's value when rolled
     * @throws DieNotRolledException custom exception for invalid number roll
     */
    public int getCurrentValue() {
        int value = this.currentValue;
        if (this.currentValue > this.numSides) {
            throw new DieNotRolledException();
        }
        this.currentValue = 0;
        return value;
    }

    /**
     * rolls the die and returns random number between 1 and number of sides
     */
    public void roll() {
        this.currentValue = this.random.nextInt(1, this.numSides+1);
    }
}