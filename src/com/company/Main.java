package com.company;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
public class Main {

    public static void main(String[] args) {

        int N; //The length of list to be sorted
        int k; // The size of the key-values(strings) to sort
        int d; // The size (in bytes or characters) of the digits used by the radix sort.
    }
    /** Get CPU time in nanoseconds since the program(thread) started. */
    /** from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime **/
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}
