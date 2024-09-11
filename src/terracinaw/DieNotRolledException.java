/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: William Terracina
 * Last Updated: 09/11/2024
 */

package terracinaw;

public class DieNotRolledException extends RuntimeException {
    public String getMessage() {
        String error = "";
        if (getCause() instanceof IllegalArgumentException) {
            error = "Illegal number of sides: ";
        } else if (getCause() instanceof NumberFormatException) {
            error = "All values must be whole numbers.";
        } else if (getCause() instanceof IllegalStateException) {
            error = "Expected 3 values but only received ";
        }
        return "Invalid Input: " + error;
    }
}
