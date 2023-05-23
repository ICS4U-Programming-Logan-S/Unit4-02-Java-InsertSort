import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This program reads a list of integers from a file, sorts them using the
 * insertion sort algorithm, and writes the sorted list to an output file.
 * The input file should contain one line per list of integers, with
 * space-separated values.
 * Empty lines and invalid input lines will be skipped, and appropriate error
 * messages will be written to the output file.
 * If the input file is missing or inaccessible, an error message will be
 * displayed.
 *
 * @author Logan S
 * @version 1.0
 * @since 2023-05-22
 */
public final class InsertSort {

    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */

    private InsertSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Reads a list of integers from an input file, sorts them using
     * insertion sort, and writes the sorted list to an output file.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Name of the input file
        final String inputFileName = "input.txt";
        // Name of the output file
        final String outputFileName = "output.txt";

        // Read input lists from file
        final List<int[]> inputLists = readInputFile(inputFileName);
        // Sort the input lists
        final List<int[]> sortedLists = sortLists(inputLists);
        // Write sorted lists to the output file
        writeOutputFile(outputFileName, sortedLists);
    }

    /**
     * Reads a list of integers from an input file.
     * Skips empty lines and invalid input lines.
     *
     * @param fileName The name of the input file.
     * @return A list of integer arrays.
     */
    public static List<int[]> readInputFile(String fileName) {
        // List to store input lists
        final List<int[]> inputLists2 = new ArrayList<>();
        // Open the input file for reading
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                // Read the next line and remove leading/trailing whitespaces
                final String line = scanner.nextLine().trim();
                // Skip empty lines
                if (!line.isEmpty()) {
                    // Parse the line into an array of integers
                    final int[] numbers = parseLine(line);
                    // Skip invalid lines
                    if (numbers != null) {
                        // Add the valid list to the inputLists
                        inputLists2.add(numbers);
                    }
                }
            }
        } catch (IOException exception) {
            System.out.println("Error occurred while reading input file: "
                    + exception.getMessage());
        }
        // Return the list of input lists
        return inputLists2;
    }

    /**
     * Parses a line of text containing space-separated integers.
     * Returns the array of integers if the line is valid, or null otherwise.
     *
     * @param line The line of text to parse.
     * @return An array of integers, or null if the line is invalid.
     */
    public static int[] parseLine(String line) {
        // Split the line by spaces
        final String[] numberStrings = line.split(" ");
        // Array to store the parsed integers
        final int[] numbers2 = new int[numberStrings.length];
        try {
            for (int i = 0; i < numberStrings.length; i++) {
                // Parse each number string to an integer
                numbers2[i] = Integer.parseInt(numberStrings[i]);
            }
            // Return the array of parsed integers
            return numbers2;
        } catch (NumberFormatException except) {
            // Display an error message for invalid input
            System.out.println("Error: Invalid input - "
                    + except.getMessage());
            return null;
        }
    }

    /**
     * Sorts the given lists of integers using insertion sort.
     *
     * @param inputLists The lists of integers to be sorted.
     * @return A list of sorted integer arrays.
     */
    public static List<int[]> sortLists(List<int[]> inputLists) {
        // List to store sorted lists
        final List<int[]> sortedLists = new ArrayList<>();
        for (int[] numbers : inputLists) {
            // Sort each list using insertion sort
            final int[] sortedNumbers = insertionSort(numbers);
            // Add the sorted list to the sortedLists
            sortedLists.add(sortedNumbers);
        }
        // Return the list of sorted lists
        return sortedLists;
    }

    /**
     * Sorts an array of integers using insertion sort.
     *
     * @param array The array of integers to be sorted.
     * @return The sorted array of integers.
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            final int current = array[i];
            int location = i - 1;
            while (location >= 0 && array[location] > current) {
                array[location + 1] = array[location];
                location--;
            }
            array[location + 1] = current;
        }
        // Return the sorted array
        return array;
    }

    /**
     * Writes the sorted lists of integers to an output file.
     *
     * @param fileName    The name of the output file.
     * @param sortedLists The lists of sorted integers.
     */

    public static void writeOutputFile(String fileName, List<int[]> sortedLists) {
        // Open the output file for writing
        try (PrintWriter writer =
            new PrintWriter(new FileWriter(fileName))) {
            for (int[] numbers : sortedLists) {
                // Write each sorted list to a new line in the output file
                writer.println(Arrays.toString(numbers));
            }
        } catch (IOException ex) {
            System.out.println("Error occurred while writing to output file: "
                    + ex.getMessage());
        }
    }
}
