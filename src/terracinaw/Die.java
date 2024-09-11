/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: William Terracina
 * Last Updated: 09/11/2024
 */
package terracinaw;

import java.util.Random;

public class Die {
    public static final int MIN_SIDES = 2;
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();
    public Die(int numSides) {
        if (numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException();
        } else {
            this.numSides = numSides;
        }
    }
    public int getCurrentValue() {
        int value = this.currentValue;
        this.currentValue = 0;
        return value;
    }
    public void roll() {
        this.currentValue = this.random.nextInt(1,this.numSides+1);
    }
}