package signal;

/**
 * This Class provides the blueprint for an Automatic Signal.
 * 
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class AutomaticSignal extends Signal {
    
    /**
     * This is the Constructor Method for an Automatic Signal Object.
     * 
     * @param signalPrefix <code>String</code> The Prefix of the signal.
     * @param signalIdentity <code>String</code> The Identity of the signal.
     * @param signalType <code>SignalType</code> The Type of Signal.
     * @param applicableSignalPrefix <code>String</code> The Prefix of the applicableSignal.
     * @param applicableSignalIdentity <code>String</code> The Identity of the applicableSignal.
     */
    public AutomaticSignal(String signalPrefix, String signalIdentity, SignalType signalType, String applicableSignalPrefix, String applicableSignalIdentity) {
        
        super(signalPrefix, signalIdentity, signalType);
        super.informApplicableSignal (applicableSignalPrefix, applicableSignalIdentity);
        
    }

    
}