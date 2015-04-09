/**
 * @author Mariana Herzog
 * @version April 2, 2015
 * @class CSCI 331
 */

package monitorhwpackage;

public class Consumer extends Thread {

    // you may use a constructor to initialize any data fields.
    // The first line of your constructor must be
    // invoking the superclass constructor like this:
    // super();
    int sum;
    int item;
    
    public Consumer(){
        super();
        sum = 0;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < HWMain.NUM_ITEMS; i++) {
            // remove an item from the buffer using the monitor's remove method
            item = HWMain.monitor.remove();
            // then process the item to incorporate it into your summation
            sum += item;
        }
        // print out the value of the summation of all the consumed items
        System.out.printf("Sum: %d\n", sum);
    }
}
