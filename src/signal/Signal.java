package signal;

import java.util.ArrayList;

/**
 * This Class provides the Superclass functionality for a Signal Object.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class Signal implements Signals {
    
    public static ArrayList <Signal> SIGNAL_ARRAY; // The Arraylist that contains a reference to all the Signal Objects.
    
    private final String prefix; // The Signal prefix.
    private final String identity; // The Signal Identity.
    private final SignalType signalType; // The type of Signal.
    private final ArrayList <Signal> signalsInRear; // An ArrayList that contains all signals that require an Aspect Update.
    private SignalAspect applicableSignalAspect; // The aspect of the signal ahead.
    private SignalAspect currentAspect; // The current aspect of the signal.
    
    /**
     * This method allows this class to reference the ArrayList that contains all Signal Objects.
     * @param signalArray <code>ArrayList</code> The ArrayList that contains all Signal Objects.
     */
    public static void setSignalArray (ArrayList signalArray) {
        SIGNAL_ARRAY = signalArray;
    }
    
    /**
     * This method returns a Signal Object.
     * 
     * The Signal Prefix and Identity of the Signal Object are passed into this method.
     * The method returns the relevant Signal Object.
     * 
     * @param prefix <code>String</code> The Prefix of the signal.
     * @param identity <code>String</code> The Identity of the signal.
     * @return <code>Signak</code> The Signal Object.
     */
    public static Signal getSignalObject (String prefix, String identity) {
    
        for (int i = 0; i < SIGNAL_ARRAY.size(); i++) {
            if (SIGNAL_ARRAY.get(i).getPrefix().equals(prefix) && SIGNAL_ARRAY.get(i).identity.equals(identity)) {
                return SIGNAL_ARRAY.get(i);
            }
        }
        return null;
    }
    
    /**
     * This is the Constructor Method for a Signal Object.
     * 
     * @param signalPrefix <code>String</code> The Prefix of the signal.
     * @param signalIdentity <code>String</code> The Identity of the signal.
     * @param signalType <code>SignalType</code> The Type of Signal.
     */
    public Signal (String signalPrefix, String signalIdentity, SignalType signalType) {
        
        this.signalsInRear = new ArrayList<>();
        this.prefix = signalPrefix;
        this.identity = signalIdentity;
        this.signalType = signalType;
        
    }

    @Override
    public void addSignalInRear (Signal signalInRear) {
        
        // Check to make sure that there is not an identical entry within the ArrayList prior to inserting.
        if (!this.signalsInRear.contains(signalInRear)) {
            this.signalsInRear.add(signalInRear);
        }
        
        this.updateSignalsInRear(); // Call the method to update all signals in rear with the current Signal Aspect.
        
    }

    @Override
    public void removeSignalInRear (Signal signalInRear) {
        if (!this.signalsInRear.contains(signalInRear)) {
            this.signalsInRear.remove(signalInRear);
        }
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getIdentity() {
        return this.identity;
    }

    @Override
    public SignalType getSignalType() {
        return this.signalType;
    }

    @Override
    public void applicableSignalAspectUpdate(SignalAspect aspect) {
        this.applicableSignalAspect = aspect;
    }
    
    @Override
    public void informApplicableSignal (String applicableSignalPrefix, String applicableSignalIdentity) {
        
        Signal signalObject = Signal.getSignalObject(applicableSignalPrefix, applicableSignalIdentity);
        
        if (signalObject != null) {
            
            signalObject.addSignalInRear(this);
            
        }
        
    }

    @Override
    public void updateSignalsInRear() {
    
        for (int i = 0; i < this.signalsInRear.size(); i++) {
            this.signalsInRear.get(i).applicableSignalAspectUpdate(this.currentAspect);
        }
        
    }

    @Override
    public SignalAspect getCurrentAspect() {
        return this.currentAspect;
    }

}
