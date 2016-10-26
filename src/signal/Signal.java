package signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private final Map <SignalAspect, Boolean> signalLamps = new LinkedHashMap<>();
    private Boolean displayHighestAspect = false;
    
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
        
        this.createSignalLamps();
        this.DisplayHighestAspect();
        
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
        this.DisplayHighestAspect(); 
        
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
    
        SignalAspect curAspect = this.currentAspect;
        
        if (this.currentAspect == SignalAspect.TOP_YELLOW) {
            curAspect = SignalAspect.DOUBLE_YELLOW;
        }
        
        for (int i = 0; i < this.signalsInRear.size(); i++) {
            this.signalsInRear.get(i).applicableSignalAspectUpdate(curAspect);
        }
        
    }

    @Override
    public SignalAspect getCurrentAspect() {
        return this.currentAspect;
    }

    @Override
    public SignalAspect calculateBestAspect() {
        
        if (this.applicableSignalAspect == null) {
            this.applicableSignalAspect = SignalAspect.RED;
        }
        
        if (this.getIdentity().contains("CA")) {
            return this.applicableSignalAspect;
        }
        
        switch (this.applicableSignalAspect) {
        
            case SPAD_INDICATOR_ILLUMINATED:
            case BLACK:
            case TOP_YELLOW:
                if (this instanceof RepeaterSignal) {
                    return SignalAspect.CAUTION;
                } else {
                    return SignalAspect.RED;
                }
                
            case SUB_OFF:
            case FLASHING_WHITE:
            case RED:
                if (this instanceof RepeaterSignal) {
                    return SignalAspect.CAUTION;
                } else {
                    return SignalAspect.YELLOW;
                }
                
            case YELLOW:
                if (this instanceof RepeaterSignal) {
                    return SignalAspect.CLEAR;
                } else {
                    if (this.getSignalType().toString().contains("_4")) {
                        return SignalAspect.DOUBLE_YELLOW;
                    } else {
                        return SignalAspect.GREEN;
                    }
                }
                
            case GREEN:
            case FLASHING_DOUBLE_YELLOW:
            case DOUBLE_YELLOW:
                if (this instanceof RepeaterSignal) {
                    return SignalAspect.CLEAR;
                } else {
                    return SignalAspect.GREEN;
                }
            
            case FLASHING_YELLOW:
                if (this instanceof RepeaterSignal) {
                    return SignalAspect.CLEAR;
                } else {
                    if (this.getSignalType().toString().contains("_4")) {
                        return SignalAspect.FLASHING_DOUBLE_YELLOW;
                    } else {
                        return SignalAspect.GREEN;
                    }
                }

        }
        
        return SignalAspect.RED;
        
    }

    @Override
    public final void createSignalLamps() {
        
        int numberOfApplicableAspects = 0;
        int startingPointWithinArray = 0;
        
        switch (this.getSignalType()) {
            
            case SPAD_INDICATOR:
            case FIXED_RED:
                numberOfApplicableAspects = 1;
                startingPointWithinArray = 0;
                break;
                
            case COLOUR_LIGHT_REPEATER:
            case BANNER:
            case POS_LIGHT:
                numberOfApplicableAspects = 2;
                startingPointWithinArray = 0;
                break;
                
            case CA_COLOUR_LIGHT_3:
            case COLOUR_LIGHT_3:
            case POS_LIGHT_POSA:
                numberOfApplicableAspects = 3;
                startingPointWithinArray = 0;
                break;
                
            case COLOUR_LIGHT_3_CA_POSA:
            case COLOUR_LIGHT_3_CA:
                numberOfApplicableAspects = 3;
                startingPointWithinArray = 1;
                break;
            
            case CA_COLOUR_LIGHT_4:
            case COLOUR_LIGHT_4:
                numberOfApplicableAspects = 4;
                startingPointWithinArray = 0;
                break;
                
            case COLOUR_LIGHT_4_CA:
            case COLOUR_LIGHT_4_CA_POSA:
                numberOfApplicableAspects = 4;
                startingPointWithinArray = 1;
                break;

        }
        
        for (int i = startingPointWithinArray; i < (numberOfApplicableAspects + startingPointWithinArray); i++) {
            
            this.signalLamps.put(this.getSignalType().returnApplicableSignalAspect()[i], true);
            
        }
        
    }
    
    @Override
    public final void DisplayHighestAspect() {
        
        if (this.displayHighestAspect) {
            this.DisplayAspect(this.calculateBestAspect());
        } else {
            Map.Entry<SignalAspect, Boolean> lampMap = this.signalLamps.entrySet().iterator().next();
            this.DisplayAspect(lampMap.getKey()); 
        }
        
    }

    @Override
    public Boolean getDisplayHighestAspect() {
        return displayHighestAspect;
    }

    @Override
    public void setDisplayHighestAspect(Boolean displayHighestAspect) {
        this.displayHighestAspect = displayHighestAspect;
        this.DisplayHighestAspect();
    }

    @Override
    public void failSignalLamp(SignalAspect aspect) {
        
        if (this.signalLamps.containsKey(aspect)) {
            this.signalLamps.replace(aspect, false);
        }
        
    }

    @Override
    public void restoreSignalLamp(SignalAspect aspect) {
        
        if (this.signalLamps.containsKey(aspect)) {
            this.signalLamps.replace(aspect, true);
        }
        
    }

    @Override
    public Map<SignalAspect, Boolean> getSignalLampMap() {
        
        return this.signalLamps;
        
    }

    @Override
    public void DisplayAspect(SignalAspect aspect) {
        
        if (this.isAspectValid(aspect)) { // Validate the aspect for this signal type.
            
            SignalAspect newAspect;
            
            switch (aspect) {
                
                case DOUBLE_YELLOW:
                    
                    if (!this.signalLamps.get(aspect)) {
                        newAspect = SignalAspect.YELLOW;
                    } else if (!this.signalLamps.get(SignalAspect.YELLOW)) {
                        newAspect = SignalAspect.TOP_YELLOW;
                    } else {
                        newAspect = SignalAspect.DOUBLE_YELLOW;
                    }
                    break;
                    
                default:

                    if (this.signalLamps.containsKey(aspect) && !this.signalLamps.get(aspect)) {
                        newAspect = SignalAspect.BLACK;
                    } else {
                        newAspect = aspect;
                    }
            } 
         
            if (this.currentAspect != newAspect) {
            
                this.currentAspect = newAspect;
                this.updateSignalsInRear();
                
            }
            
        }
        
    }

    @Override
    public void setDisplayHighestAspect(SignalAspect aspect) {
        
    }

    @Override
    public Boolean isAspectValid(SignalAspect aspect) {
        
        if (Arrays.toString(this.signalType.returnApplicableSignalAspect()).contains(aspect.toString())) {
            return true;
        } else {
            return false;
        }
        
    }

}
