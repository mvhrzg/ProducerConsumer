/**
 * @author Mariana Herzog
 * @version April 2, 2015
 * @class CSCI 331
 */

package monitorhwpackage;

public class Producer extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < HWMain.NUM_ITEMS; i++) {
            int range = (int) Math.floor((Math.random() * 11));
            HWMain.monitor.insert(range);
        }
    }
}