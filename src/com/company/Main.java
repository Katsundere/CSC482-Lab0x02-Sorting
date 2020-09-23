package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;


public class Main {
    private static int N = 5;          //The length of list to be sorted
    private static int k = 4;           // The size of the key-values(strings) to sort
    private static int d = 0;           // The size (in bytes or characters) of the digits used by the radix sort
    private static int minV = 97;       // smallest value for char
    private static int maxV = 122;      // Largest value for char

    //Function to generate the test list
    public static String[] generateTestList(){
        String[] generateList = new String[N];                                         // Makes a new string Array of size N
        char[] generateChar = new char[k+1];                                           // Makes a char array of size k+1
        Random rnd = new Random();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < k; j++){
                generateChar[j] = (char)(rnd.nextInt(maxV-minV) + minV);          // (char) converts to char with the Random function of type int for the char array
            }
            generateChar[k] = '\0';                                                     // appends a null terminator to generateChar at position k in the array. (The end of the segment)
            generateList[i] = String.valueOf(generateChar);                             //puts the character segments into the string array at position i
        }
        return generateList;                                                            // returns the generated list to main function
    }
    public static void print(String data[]){                                            // print function with some formatting.
        System.out.print("{ ");
        for(int i = 0; i < N; i++) {
            System.out.print(data[i] + " , ");
        }
        System.out.println("\b\b\b }");                                                 // \b used to erase characters at the end I don't want printed out.
    }
    public static String[] insertionSort(String data[]){                                // start of insertion sort.
        String temp;

        for(int i = 0; i < N; i++){                                                     //loops run until size N is reached
            for(int j = i+1; j < N; j++){
                if(data[i].compareToIgnoreCase(data[j])>0){                             // compares string i to string j and swaps them if j is less than i
                    temp = data[i];                                                     // stores the string that is being swapped from i into temp
                    data[i] = data[j];                                                  // Swaps j to i
                    data[j] = temp;                                                     // puts temp into j
                }
            }
        }
        return data;                                                                    // returns the sorted array to the main function
    }
    public static String[] mergeSort(String data[], int low, int high){                 // Start of merge sort
        int middle = (int) Math.ceil((high - low)/2) + low;                             // determines the midpoint between a high and low point given two values
        String[] sort = new String[high-low];
        if(high - low >= 2) {                                                           // Anything that is 2 or more will be sorted, otherwise ignored.
            String[] left = mergeSort(data, low, middle);                               // recursive call for the left side of the sort
            String[] right = mergeSort(data, middle, high);                             // recursive call for the right side of the sort
            int l = 0;                                                                  // l and h are increment trackers for low and high
            int h = 0;
            for (int i = 0; i < high - low; i++) {                                      //for loop with if statements to determine where to add to array sort
                if (l >= middle - low && h < high - middle) {
                    sort[i] = right[h];
                    h++;
                } else if (h >= high - middle && l < middle - low) {
                    sort[i] = left[l];
                    l++;
                } else if (left[l].compareToIgnoreCase(right[h]) < 0) {
                    sort[i] = left[l];
                    l++;
                } else {
                    sort[i] = right[h];
                    h++;
                }
            }
        }
        else {
            sort[0] = data[low];                                                        //If the array is less than 2 it will return the value that was passed back to main
        }
        return sort;                                                                    // returns sort to main function
    }
    public static String[] quickSort(String[] data, int low, int high){                 // Start of quick sort function
        String pivot;
        String temp;
        boolean pivotPoint = false;
        int l = low +1;
        int h = high -1;
        if(low >= high){                                                                // prevents sort from going out of bounds.
            return data;
        }
        else{                                                                           // sets pivot to data indexed at low.
            pivot = data[low];
        }
        if(high-low >=2){                                                               // if there are 2 or more strings to sort it will run
            while(!pivotPoint){
                while(l < high - 1 && data[l].compareTo(pivot) < 0){                    // iterates comparison of data[l] the pivot
                    l++;
                }
                while(h > low && data[h].compareTo(pivot) > 0){                         // iterates comparison of data[h] and pivot
                    h--;
                }
                if(l >= h){                                                             // sets pivotPoint to true if the values are equivalent
                    pivotPoint = true;
                }
                else{                                                                   // swaps data[l] with data[h]
                    temp = data[l];
                    data[l] = data[h];
                    data[h] = temp;
                    l++;
                    h--;
                }
            }
            data[low] = data[h];                                                        // swaps data[l] for data[h] setting pivot to data[h]
            data[h] = pivot;
            quickSort(data, low, h);                                                    //recursive call passing low index and h index
            quickSort(data, h+1, high);                                             //recursive call passing  h index + 1 and high index
        }
        return data;                                                                    // returns the sorted data array
    }

    public static void main(String[] args) {                                            // Start of main function
        String[] testList = new String[N];
        String[] sortedList = new String[N];
        System.out.println("Insertion sort");
        testList = generateTestList();
        print(testList);
        sortedList = insertionSort(testList);
        print(sortedList);
        System.out.println("Merge Sort");
        testList = generateTestList();
        print(testList);
        sortedList = mergeSort(testList, 0, N);
        print(sortedList);
        System.out.println("Quick Sort");
        testList = generateTestList();
        print(testList);
        sortedList = quickSort(testList, 0, N);
        print(sortedList);

    }


    /** Get CPU time in nanoseconds since the program(thread) started. */
    /** from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime **/
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}
