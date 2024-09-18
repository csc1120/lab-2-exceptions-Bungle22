/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: William Terracina
 * Last Updated: 09/11/2024
 */

package terracinaw;

/**
 * Custom DieNotRolledException error.
 */
public class DieNotRolledException extends RuntimeException {
    public String getMessage() {
        return "Error: Die not rolled.";
    }
}
