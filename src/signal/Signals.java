package signal;

/**
 * This interface contains mandatory methods for the Signal class.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public interface Signals {

    /**
     * This method adds a reference to a signal in rear that requires an update regarding the signal aspect.
     * @param signalInRear <code>Signal</code> The Signal in Rear that requires an aspect update.
     */
    void addSignalInRear (Signal signalInRear);
    
    /**
     * This method is used to remove a reference to a signal that no longer requires an aspect update.
     * @param signalInRear <code>Signal</code> The Signal in Rear that requires an aspect update.
     */
    void removeSignalInRear (Signal signalInRear);
    
    /**
     * This method provides a Signal Aspect update to all 'Signals In Rear'.
     * 
     * This method calls the 'applicableSignalAspectUpdate' method of all those Signals that require an aspect update.
     */
    void updateSignalsInRear();
    
    /**
     * This method returns the current Signal Aspect.
     * @return <code>SignalAspect</code> The current aspect of the signal.
     */
    SignalAspect getCurrentAspect();
    
    /**
     * This method returns the Signal Prefix.
     * @return <code>String</code> The Signal Prefix.
     */
    String getPrefix();

    /**
     * This method returns the Signal Identity.
     * @return <code>String</code> The Signal Prefix.
     */
    String getIdentity();

    /**
     * This method returns the Signal Type.
     * @return <code>SignalType</code> The Type of Signal.
     */
    SignalType getSignalType();
    
    /**
     * This method updates the aspect of the Applicable Signal.
     * Called by the Applicable Signal.
     * @param aspect <code>SignalAspect</code> The Signal Aspect that the Applicable Signal is currently displaying.
     */
    void applicableSignalAspectUpdate(SignalAspect aspect);
    
    /**
     * This Method informs the applicable signal that this Signal requires aspect updates.
     * 
     * @param applicableSignalPrefix <code>String</code> The Prefix of the Signal that is required to provide aspect updates.
     * @param applicableSignalIdentity <code>String</code> The Identity of the Signal that is required to provide aspect updates.
     */
    void informApplicableSignal (String applicableSignalPrefix, String applicableSignalIdentity);

    /**
     * This method calculates the highest aspect that can be displayed at the signal.
     * 
     * @return <code>SignalAspect</code> The highest Aspect that can be displayed at the signal.
     */
    SignalAspect calculateBestAspect();
    
    /**
     * This method is called by the constructor and creates a map that contains a Signal Lamp for each relevant Signal Aspect.
     */
    void createSignalLamps();
    
    /**
     * This method determines if the signal can display the highest (least restrictive) aspect available.
     */
    void DisplayHighestAspect();
    
    /**
     * This method returns the Display Highest Aspect Flag.
     * @return <code>Boolean</code> <i>'true'</i> indicates the highest aspect can be displayed, otherwise <i>'false'</i>.
     */
    Boolean getDisplayHighestAspect();
    
    /**
     * This method sets the Display Highest Aspect Flag.
     * @param displayHighestAspect <code>Boolean</code> <i>'true'</i> indicates the highest aspect can be displayed, otherwise <i>'false'</i>.
     */
    void setDisplayHighestAspect(Boolean displayHighestAspect);
}
