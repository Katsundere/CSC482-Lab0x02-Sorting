package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int N = 10;          //The length of list to be sorted
    private static int k = 4;           // The size of the key-values(strings) to sort
    private static int d = 0;           // The size (in bytes or characters) of the digits used by the radix sort
    private static int minV = 37;        // smallest value for char
    private static int maxV = 255;      // Largest value for char

    //Function to generate the test list
    public static String[] generateTestList(int N, int k, int minV, int maxV){
        String[] generateList = new String[N];                                         // Makes a new string Array of size N
        char[] generateChar = new char[k+1];                                           // Makes a char array of size k+1
        Random rnd = new Random();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < k; j++){
                generateChar[j] = (char)(rnd.nextInt(maxV-minV) + minV);          // (char) converts to char with the Random function of type int for the char array
                //System.out.println("Char: " + generateChar[j]);                         //testing output
            }
            generateChar[k] = '\0';                                                     // appends a null terminator to generateChar at position k in the array. (The end of the segment)
            generateList[i] = String.valueOf(generateChar);                             //puts the character segments into the string array at position i
            //System.out.println("String: " + generateList[i]);                         // testing output
        }
        return generateList;
    }
    public static void print(String data[]){                                                  // print function with some formatting.
        System.out.print("{ ");
        for(int i = 0; i < N; i++) {
            System.out.print(data[i] + " , ");
        }
        System.out.println("\b\b\b }");
    }
    public static void main(String[] args) {
        String[] data = new String[N];
        data = generateTestList(N, k, minV, maxV);
        print(data);

    }


    /** Get CPU time in nanoseconds since the program(thread) started. */
    /** from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime **/
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}
