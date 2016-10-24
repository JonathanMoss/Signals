package signal;

import java.util.ArrayList;

/**
 * This Class provides the Superclass functionality for a Signal Object.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public abstract class Signal implements Signals {
    
    public static ArrayList <Signal> SIGNAL_ARRAY;
    private String prefix;
    private String identity;
    private Signal signalInRear;
    
    public static void setSignalArray (ArrayList signalArray) {
        SIGNAL_ARRAY = signalArray;
    }
    
    public static Signal getSignalObject (String prefix, String identity) {
    
        for (int i = 0; i < SIGNAL_ARRAY.size(); i++) {
            if (SIGNAL_ARRAY.get(i).getPrefix().equals(prefix) && SIGNAL_ARRAY.get(i).identity.equals(identity)) {
                return SIGNAL_ARRAY.get(i);
            }
        }
        
        return null;
        
    }
    
    public Signal (String signalPrefix, String signalIdentity, SignalType signalType) {
        
    }

    @Override
    public void setSignalInRear(Signal signalInRear) {
        this.signalInRear = signalInRear;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getIdentity() {
        return identity;
    }

    
    
}
