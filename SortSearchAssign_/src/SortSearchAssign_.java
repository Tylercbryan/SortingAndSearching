//Tyler Bryan
//Mon/Wed 1:30-3:10 Java244
import java.io.*;
import java.util.*;

public class SortSearchAssign_ {

    public static void createFile()
    {
        // File to be created in default directory
        File file = new File("somanynumbers.txt");
        Random rng = new Random();
        int r;

        try {
            // Create the file
            PrintWriter output = new PrintWriter(file);

            // Write random numbers into a file
            for (int i = 0; i < 1000000; i++)
            {
                // Genereates a random number in range of (0 - 100)
                r = rng.nextInt(101);
                output.write(r + ", ");
            }
            System.out.println("File created.");
            // Close file
            output.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Cannot do that.");
        }
    }


    // Method to read the numbers from the file and store them in an int array
    public static int[] getArray() {
        int[] array = new int[1000000];
        int count = 0;
        //creates a BuffferReader to read txt file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("somanynumbers.txt"));
            String line = reader.readLine();

            //while look to go through every number in txt file and remove commas and spaces
            //then stores numbers into an array
            while (line != null) {
                String[] numbers = line.split(", ");
                for (String number : numbers) {
                    if (!number.equals("")) {
                        array[count++] = Integer.parseInt(number);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }
        System.out.println("Array created.");
        return array;
    }


    public static void main(String[] args) throws IOException {

        // Call createFile method to generate the numbers file
        createFile();

        // creating and array out of the file
        int[] array = getArray();
        System.out.println("Finished reading file and creating an array.");

        // Create an object of AllSorts and AllSearch classes
        AllSorts allSorts = new AllSorts();
        AllSearch allSearch = new AllSearch();
        int target = 42;
        long startTime, endTime, totalTime;

        //starting timer
        startTime = System.nanoTime();

        // Selecting sorting and searching methods./
        //  Efficient way - 0 seconds to completion
        allSorts.mergeSort(array);
        System.out.println("Array sorted.");
        int index = allSearch.binSearch(array, target);

        /*
        //Inefficient solution
        //These sort/search methods were chosen because they have a very high time complexity
        //bubble sort has an average time complexity of θ(n^2)
        //linear search has an average time complexity of θ(n^2)
        //Doing a test run of this combination took 1865 seconds to complete
        allSorts.bubbleSort(array);
        System.out.println("Array sorted.");
        int index = allSearch.linearSearch(array, target);
        */


        //ending timer and changing units into seconds
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000000;

        //displays if element was found or not and time it took to sort/search
        if (index == -1) {
            System.out.println("Element not found in " + totalTime + " seconds");
        } else {
            System.out.println("Element found at index " + index + " in " + totalTime + " seconds");
            System.out.println(endTime + " " + startTime);
        }
    }
}
