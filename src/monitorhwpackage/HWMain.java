/**
 * @author Mariana Herzog
 * @version April 2, 2015
 * @class CSCI 331
 * 
 * Program creates two threads: Producer and Consumer. Producer produces
 * 10 million items and inserts them into a shared buffer of size 100. Items are
 * integers between 0 and 10.
 * Consumer processes each item into a running sum and removes each processed 
 * item from the shared buffer.
 * In main, the number of times producer and the consumer blocked is printed.
 */
package monitorhwpackage;

public class HWMain {

    public static final int BUFFER_SIZE = 100;      // buffer size
    public static final int NUM_ITEMS = 10000000;   //number of items
    // The ProdConsMonitor class's object can be a static field in this main class
    public static ProdConsMonitor monitor = new ProdConsMonitor();

    public static void main(String[] args) throws InterruptedException {
        
        // create producer and consumer objects.
        Producer prod = new Producer();
        Consumer cons = new Consumer();
        // start their threads, join their threads, and then finally
        prod.start();
        cons.start();
        prod.join();
        cons.join();
        // print out how many times the monitor insert blocked
        monitor.prodBlocked();
        // and how many times the monitor remove blocked.
        monitor.consBlocked();
    }

}
