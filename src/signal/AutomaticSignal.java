package signal;

/**
 * This Class provides the blueprint for an Automatic Signal.
 * 
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class AutomaticSignal extends Signal {
    
    public AutomaticSignal(String signalPrefix, String signalIdentity, SignalType signalType, String applicableSignalPrefix, String applicableSignalIdentity) {
        
        super(signalPrefix, signalIdentity, signalType);
        super.informApplicableSignal (applicableSignalPrefix, applicableSignalIdentity);
        
    }

    
}