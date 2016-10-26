package signal;

/**
 * This Class provides the blueprint for a Controlled Signal.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class ControlledSignal extends Signal {

    /**
     * This is the Constructor Method for a ControlledSignal Object
     * 
     * @param signalPrefix <code>String</code> The Prefix of the signal.
     * @param signalIdentity <code>String</code> The Identity of the signal.
     * @param signalType <code>SignalType</code> The Type of Signal.
     */
    public ControlledSignal(String signalPrefix, String signalIdentity, SignalType signalType) {
        
        super(signalPrefix, signalIdentity, signalType);
        
    }
    
    /**
     * This method attempts to clear the signal to its highest aspect.
     * @param toSignalPrefix <code>String</code> The Prefix of the signal that the route is being set to.
     * @param toSignalIdentity <code>String</code> The Identity of the signal that the route is being set to.
     */
    public void setSignal (String toSignalPrefix, String toSignalIdentity) {
        super.informApplicableSignal(toSignalPrefix, toSignalIdentity);
    }
    

   
}
