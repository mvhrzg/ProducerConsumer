/**
 * @author Mariana Herzog
 * @version April 2, 2015
 * @class CSCI 331
 */

package monitorhwpackage;

public class ProdConsMonitor {

    public static int top;      //variable for buffer index
    public static int[] array;  //shared buffer
    
    int prodBlock;            //how many times insert blocks
    int consBlock;            //how many times remove blocls
    
// The constructor initializes all data fields
    public ProdConsMonitor(){
        top = 0;
        array = new int[HWMain.BUFFER_SIZE];
        prodBlock = 0;
        consBlock = 0;
    }
    
    // insert an int value into the buffer
    public synchronized void insert(int value) {
        if (top == HWMain.BUFFER_SIZE) { // buffer is full; can't insert.
            // note that we are blocking during insert
            goToSleep();
            prodBlock++;              //increment producer block variable
        }
        //critical section code here.
        array[top] = value;             //insert into the stack
        top++;                          // and adjust the index

        if (top == 1) { // we just inserted the only item into a previously empty buffer
            notify(); // wake a sleeping thread that waited from inside this monitor
        }
    }

    // remove an item from the buffer, and return its value as an int
    public synchronized int remove() {
        int removed;
        if (top == 0) { // buffer is empty -- nothing to remove
            // note that we are blocking during remove
            goToSleep();
            consBlock++;                //increment consumer block variable
        }
        // critical section code here. 
        top--;                          //decrement index
        removed = array[top];           //remove from the stack
        
        if (top == HWMain.BUFFER_SIZE - 1) { // we just removed an item from a previously full buffer
            notify(); // wake a sleeping thread that waited from inside this monitor
        }
        // finally, return the item that was taken from the buffer
        return removed;
    }//remove

    // Jacketing for thread wait. DO NOT MODIFY
    private synchronized void goToSleep() {
        try {
            wait();
        } catch (InterruptedException e) {
            // do nothing
        }
    }//goToSleep

    // Make public synchronized getter methods for the number of times insert has blocked,
    // and the number of times remove has blocked.
    public synchronized void prodBlocked(){
        System.out.printf("Producer blocked %d times.\n", prodBlock);
    }//prodBlocked
    
    public synchronized void consBlocked(){
        System.out.printf("Consumer blocked %d times.\n", consBlock);
    }//consBlocked
}