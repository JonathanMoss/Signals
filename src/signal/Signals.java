package signal;

/**
 * This interface contains mandatory methods for the Signal class.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public interface Signals {
    
    /**
     * This method informs the next signal which signal is in rear, and that therefore requires an aspect update.
     * @param signalInRear <code>Signal</code> The Signal in Rear.
     */
    void setSignalInRear (Signal signalInRear);
    
    


}
