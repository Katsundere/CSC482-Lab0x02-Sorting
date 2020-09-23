package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class Main {
    private static int N = 100;          //The length of list to be sorted
    private static int k = 4;           // The size of the key-values(strings) to sort
    private static int d = 0;           // The size (in bytes or characters) of the digits used by the radix sort
    private static int minV = 97;       // smallest value for char
    private static int maxV = 122;      // Largest value for char

    //Function to generate the test list
    public static String[] generateTestList(int N, int k, int minV, int maxV){
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
        return generateList;
    }
    public static void print(String data[]){                                            // print function with some formatting.
        System.out.print("{ ");
        for(int i = 0; i < N; i++) {
            System.out.print(data[i] + " , ");
        }
        System.out.println("\b\b\b }");                                                 // \b used to erase characters at the end I don't want printed out.
    }
    public static String[] insertionSort(String data[], int N){                         // start of insertion sort.
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
        return data;
    }

    public static void main(String[] args) {                                            // Start of main function
        String[] testList = new String[N];
        String[] sortedList = new String[N];
        testList = generateTestList(N, k, minV, maxV);
        print(testList);
        sortedList = insertionSort(testList, N);
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
