package signal;

import java.util.Map;

/**
 * This interface contains mandatory methods for the Signal class.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public interface Signals {
    
    /**
     * This method returns the current signal aspect.
     * @return <code>SignalAspect</code> the current signal aspect.
     */
    SignalAspect getCurrentAspect ();
    
    /**
     * This method returns the Signal Prefix.
     * @return <code>String</code> The Signal Prefix.
     */
    String getSignalPrefix ();
    
    /**
     * This method returns the Identity of the Signal.
     * @return <code>String</code>
     */
    String getSignalIdentity ();
    
    /**
     * This method returns the Signal Prefix and Identity as a single string.
     * @return <code>String</code> The Signal Prefix and Identity.
     */
    String getFullIdentity ();
    
    /**
     * This method returns the Signal Type.
     * @return <code>SignalType</code> The type of Signal.
     */
    SignalType getSignalType ();
    
    /**
     * This method returns the signal to its lowest aspect.
     * 
     * This is usually RED, but for a banner etc.. it will be caution.
     */
    void signalOn ();
    
    /**
     * This method attempts to clear the signal.
     * 
     * This method attempts a main signal aspect, with the highest aspect available.
     * Note: This method should only be called by the Remote Interlocking.
     * @param toSignalPrefix <code>String</code> The prefix of the next signal.
     * @param toSignalIdentity <code>String</code> The Identity of the next signal.
     */
    void signalOff (String toSignalPrefix, String toSignalIdentity);
    
    /**
     * This method attempts to clear the signal to the aspect required.
     * 
     * This method is used to clear the signal to anything other than a main aspect - for example a sub, or flashing aspect route.
     * 
     * @param toSignalPrefix <code>String</code> The prefix of the next signal.
     * @param toSignalIdentity <code>String</code> The Identity of the next signal.
     * @param aspect <code>SignalAspect</code> The required signal aspect.
     */
    void signalOff (String toSignalPrefix, String toSignalIdentity, SignalAspect aspect);
    
    /**
     * This method receives the aspect of the next signal ahead.
     * @param aspect <code>SignalAspect</code> The current aspect of the next signal.
     */
    void updatedAspectFromSignalAhead (SignalAspect aspect);
    
    /**
     * This method informs the signal in rear which aspect is being displayed.
     */
    void updateSignalInRearAspect ();
    
    /**
     * This method receives the prefix and identity of the signal in rear.
     * 
     * This method is required in order that this signal knows the correct signal in rear to inform which aspect is being displayed, as required.
     * 
     * @param prefix <code>String</code> The prefix of the signal in rear.
     * @param identity <code>String</code> The identity of the signal in rear.
     */
    void receiveSignalInRearUpdate (String prefix, String identity);
    
    /**
     * This method informs the next signal, which signal is in rear of it.
     * 
     * This enables the next signal to pass back the current aspect, when required.
     * This method should only be called whenever the 'signalOff()' method is called.
     * 
     * @param prefix <code>String</code> The prefix of the next signal.
     * @param identity <code>String</code> The identity of the next signal.
     */
    void updateNextSignal (String prefix, String identity);
    
    /**
     * This method updates the current signal aspect.
     */
    void updateAspect();
    
    /**
     * This method returns the Signal Lamps map object.
     * @return <code>Map</code> The map containing the Signal Lamps object
     */
    Map getSignalLamps();
}
