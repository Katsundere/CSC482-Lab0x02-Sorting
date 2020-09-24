package com.company;

import javax.naming.PartialResultException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;
;


public class Main {
    private static int N = 20;          //The length of list to be sorted
    private static int k = 4;          // The size of the key-values(strings) to sort
    private static int d = 32;         // The size (in bytes or characters) of the digits used by the radix sort
    private static int minV = 97;      // smallest value for char
    private static int maxV = 122;     // Largest value for char

    //Function to generate the test list
    public static String[] generateTestList(int N2, int k2){
        String[] generateList = new String[N2];                                         // Makes a new string Array of size N
        char[] generateChar = new char[k2+1];                                           // Makes a char array of size k+1
        Random rnd = new Random();

        for(int i = 0; i < N2; i++){
            for(int j = 0; j < k2; j++){
                generateChar[j] = (char)(rnd.nextInt(maxV-minV) + minV);          // (char) converts to char with the Random function of type int for the char array
            }
            generateChar[k2] = '\0';                                                     // appends a null terminator to generateChar at position k in the array. (The end of the segment)
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
    public static String[] insertionSort(String data[], int N){                                // start of insertion sort.
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
            quickSort(data, low, h);                                                    // recursive call passing low index and h index
            quickSort(data, h+1, high);                                             // recursive call passing  h index + 1 and high index
        }
        return data;                                                                    // returns the sorted data array
    }

    public static String[] radixSort(String[] data, int d, int N) {                                   // Start of radix sort referenced https://algs4.cs.princeton.edu/51radix/LSD.java.html
        int R = 256;
        String[] aux = new String[N];

        for(int i = k-1; i >= 0; i--) {

            int[] count = new int[R + 1];
            for (int j = 0; j < N; j++) {                                                 // compute frequency counts
                count[data[j].charAt(i) + 1]++;
            }
            for (int j = 0; j < R; j++) {                                                 // compute cumulates
                count[j + 1] += count[j];
            }
            for (int j = 0; j < N; j++) {                                                 // moves data
                aux[count[data[j].charAt(i)]++] = data[j];
            }
            for (int j = 0; j < N; j++) {                                                 // copies back
                data[j] = aux[j];
            }
        }
         return data;
    }
    public static boolean isSorted(String[] data){                                          // start of isSorted function
        String[] listSorted = new String[N];
        System.out.println("Generating list of length " + N +", key width of " + k);
        System.out.println("Sorting with Insertion Sort");
        listSorted = insertionSort (data, N);
        System.out.print("Verified: " );
        for (int i = 0; i < N-1; i++){
            if(listSorted[i].compareTo(listSorted[i + 1]) > 0);                                     // compares the sorted String. if the value doesn't compare, it isn't sorted.
            System.out.println("Sorted!");
            return true;
        }
        System.out.println("Not Sorted!");

        return false;
    }
    public static void visualTesting(String[] testList){
        String[] sortedList = new String[N];
        System.out.println("Insertion sort");
        String[] tempList = testList.clone();
        print(testList);
        sortedList = insertionSort(tempList, N);
        print(sortedList);
        isSorted(testList);
        System.out.println("Merge Sort");
        print(testList);
        tempList = testList.clone();
        sortedList = mergeSort(tempList, 0, N);
        print(sortedList);
        System.out.println("Quick Sort");
        print(testList);
        tempList = testList.clone();
        sortedList = quickSort(tempList, 0, N);
        print(sortedList);
        System.out.println("RadixSort");
        print(testList);
        tempList = testList.clone();
        sortedList = radixSort(tempList, d, N);
        print(sortedList);
    }
    /** Get CPU time in nanoseconds since the program(thread) started. */
    /** from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime **/
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;

    }
    public static void timePerformanceTesting(){
        String[] testList;
        int changingN;
        int changingK;
        int changingD;
        int iterations = 10;
        long startTime = 0;
        long endTime = 3000000000L;
        long timingStart;
        long timingEnd;
        long totalTime;
        long averageTime;
        double prev ;

        System.out.printf("Insertion Sort\n");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","","k=6","","k=12","","k=24","","k=48","");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","N","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio");
        startTime = 0;
        for(changingN = 1; startTime < endTime; changingN = changingN *2){          // Timing for insertion Sort
            System.out.printf("| %5d|",changingN);
            prev=1;
            startTime = getCpuTime();
            for(changingK = 6; changingK < 49; changingK = changingK * 2) {
                totalTime = 0;
                for (int i = 0; i < iterations; i++) {
                    testList = generateTestList(changingN, changingK);
                    timingStart = getCpuTime();
                    insertionSort(testList, changingN);
                    timingEnd = getCpuTime();
                    totalTime = timingEnd - timingStart;
                }

                averageTime = totalTime / iterations;

                System.out.printf(" %10d|", averageTime);
                System.out.printf(" %15.2f|", averageTime/prev);
                prev = averageTime;

            }
            System.out.printf("\n");
        }

        System.out.printf("Merge Sort\n");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","","k=6","","k=12","","k=24","","k=48","");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","N","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio");
        startTime = 0;
        for(changingN = 1; startTime < endTime; changingN = changingN *2){          // Timing for insertion Sort
            System.out.printf("| %5d|",changingN);
            prev=1;
            startTime = getCpuTime();
            for(changingK = 6; changingK < 49; changingK = changingK * 2) {
                totalTime = 0;
                for (int i = 0; i < iterations; i++) {
                    testList = generateTestList(changingN, changingK);
                    timingStart = getCpuTime();
                    mergeSort(testList,0, changingN);
                    timingEnd = getCpuTime();
                    totalTime = timingEnd - timingStart;
                }

                averageTime = totalTime / iterations;

                System.out.printf(" %10d|", averageTime);
                System.out.printf(" %15.2f|", averageTime/prev);
                prev = averageTime;

            }
            System.out.printf("\n");
        }

        System.out.printf("Quick Sort\n");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","","k=6","","k=12","","k=24","","k=48","");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","N","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio");
        startTime = 0;
        for(changingN = 1; startTime < endTime; changingN = changingN *2){          // Timing for insertion Sort
            System.out.printf("| %5d|",changingN);
            prev=1;
            startTime = getCpuTime();
            for(changingK = 6; changingK < 49; changingK = changingK * 2) {
                totalTime = 0;
                for (int i = 0; i < iterations; i++) {
                    testList = generateTestList(changingN, changingK);
                    timingStart = getCpuTime();
                    quickSort(testList,0, changingN);
                    timingEnd = getCpuTime();
                    totalTime = timingEnd - timingStart;
                }

                averageTime = totalTime / iterations;

                System.out.printf(" %10d|", averageTime);
                System.out.printf(" %15.2f|", averageTime/prev);
                prev = averageTime;

            }
            System.out.printf("\n");
        }

        System.out.printf("Radix Sort\n");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","","k=6","","k=12","","k=24","","k=48","");
        System.out.printf("| %5s| %10s| %15s| %10s| %15s| %10s| %15s| %10s| %15s|\n","N","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio","Time","Doubling Ratio");
        startTime = 0;
        for(changingD =1; startTime< endTime; changingD++) {
            for (changingN = 1; startTime < endTime; changingN = changingN * 2) {          // Timing for insertion Sort
                System.out.printf("| %5d|", changingN);
                prev = 1;
                startTime = getCpuTime();
                for (changingK = 6; changingK < 49; changingK = changingK * 2) {
                    totalTime = 0;
                    for (int i = 0; i < iterations; i++) {
                        testList = generateTestList(changingN, changingK);
                        timingStart = getCpuTime();
                        radixSort(testList, changingD, changingN);
                        timingEnd = getCpuTime();
                        totalTime = timingEnd - timingStart;
                    }

                    averageTime = totalTime / iterations;

                    System.out.printf(" %10d|", averageTime);
                    System.out.printf(" %15.2f|", averageTime / prev);
                    prev = averageTime;

                }
                System.out.printf("\n");
            }
        }
    }

    public static void main(String[] args) {                                            // Start of main function
        String[] testList = new String[N];
        testList = generateTestList(N,k);
        //visualTesting(testList);
        timePerformanceTesting();
        //isSorted(testList);
    }



}
