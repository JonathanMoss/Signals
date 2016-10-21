package signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Signal implements Signals{
    
    private final String signalPrefix;
    private final String signalIdentity;
    private final SignalType signalType;
    private SignalAspect currentSignalAspect; 
    private SignalAspect signalAheadAspect = SignalAspect.BLACK;
    private String signalInRearPrefix;
    private String signalInRearIdentity;
    private static ArrayList <Signals> SIGNALS_ARRAY;
    private Map <SignalAspect, Boolean> signalLamp = new LinkedHashMap<>();
    private Boolean signalOff;
    
    public Signal (String prefix, String identity, SignalType type, ArrayList signalsArray) {
        
        this.signalPrefix = prefix;
        this.signalIdentity = identity;
        this.signalType = type;
        SIGNALS_ARRAY = signalsArray;
        
        // Ensure the signal is displaying its lowest available aspect.
        this.signalOn();
        
        // Create a signalLampProved object for each available signal lamp.
        int applicableAspects = 0;
        int startAspect = 0;
        switch (this.signalType) {
            case SPAD_INDICATOR: 
            case FIXED_RED:
                applicableAspects = 1;
                startAspect = 0;
                break;
            case BANNER:
            case COLOUR_LIGHT_REPEATER:
            case POS_LIGHT:
                applicableAspects = 2;
                startAspect = 0;
                break;
            case COLOUR_LIGHT_3:
            case POS_LIGHT_POSA:
                applicableAspects = 3;
                startAspect = 0;
                break;
            case COLOUR_LIGHT_3_CA_POSA:
            case COLOUR_LIGHT_3_CA:
                applicableAspects = 3;
                startAspect = 1;
                break;    
            case COLOUR_LIGHT_4:
                applicableAspects = 4;
                startAspect = 0;
                break;
            case COLOUR_LIGHT_4_CA_POSA:
            case COLOUR_LIGHT_4_CA:
                applicableAspects = 4;
                startAspect = 1;
                break;

        }
        
        for (int i = startAspect; i < (startAspect + applicableAspects); i++) {
            signalLamp.put(this.signalType.returnApplicableSignalAspect()[i], true);
        }
        
        System.out.println(Arrays.asList(signalLamp)); // method 1


       
       this.signalOff = false;
        
    }

    @Override
    public SignalAspect getCurrentAspect() {
        return this.currentSignalAspect;
    }

    @Override
    public String getSignalPrefix() {
        return this.signalPrefix;
    }

    @Override
    public String getSignalIdentity() {
        return this.signalIdentity;
    }

    @Override
    public String getFullIdentity() {
        return String.format ("%s%s", this.signalPrefix, this.signalIdentity);
    }

    @Override
    public SignalType getSignalType() {
        return this.signalType;
    }

    @Override
    public final void signalOn() {
        
        if (this.signalType.toString().contains("_CA")) {
            // Signals with a Co-acting position light.
            this.currentSignalAspect = this.signalType.returnApplicableSignalAspect()[1];
        } else {
            // All other signals.
            this.currentSignalAspect = this.signalType.returnApplicableSignalAspect()[0];
        }
        
        this.signalOff = false;
        this.updateSignalInRearAspect();
        
    }

    @Override
    public void signalOff(String toSignalPrefix, String toSignalIdentity) {
        
        if (toSignalPrefix == null) {
            this.signalAheadAspect = SignalAspect.GREEN;
        } else {
            this.updateNextSignal(toSignalPrefix, toSignalIdentity);
        }

        this.signalOff = true;
        this.updateAspect();
        
    }
    

    @Override
    public void signalOff(String toSignalPrefix, String toSignalIdentity, SignalAspect aspect) {
        this.updateNextSignal(toSignalPrefix, toSignalIdentity);
    }

    @Override
    public void updatedAspectFromSignalAhead(SignalAspect aspect) {
        this.signalAheadAspect = aspect;
        this.updateAspect();
    }
    
    @Override
    public void updateSignalInRearAspect() {
        
        if (this.signalInRearPrefix != null) { // Make sure there is a signal in rear we need to provide our aspect to.
            for (int i = 0; i < SIGNALS_ARRAY.size(); i++) {
                if (SIGNALS_ARRAY.get(i).getSignalPrefix().contains(this.signalInRearPrefix) && SIGNALS_ARRAY.get(i).getSignalIdentity().contains(this.signalInRearIdentity)) {
                    SIGNALS_ARRAY.get(i).updatedAspectFromSignalAhead(this.currentSignalAspect);
                    break;
                }
            }
        }

    }

    @Override
    public void updateNextSignal(String prefix, String identity) {
    
        for (int i = 0; i < SIGNALS_ARRAY.size(); i++) {
            if (SIGNALS_ARRAY.get(i).getSignalPrefix().contains(prefix) && SIGNALS_ARRAY.get(i).getSignalIdentity().contains(identity)) {
                SIGNALS_ARRAY.get(i).receiveSignalInRearUpdate(this.signalPrefix, this.signalIdentity);
                break;
            }
        }
        
    }

    @Override
    public void receiveSignalInRearUpdate(String prefix, String identity) {
        this.signalInRearPrefix = prefix;
        this.signalInRearIdentity = identity;
        this.updateSignalInRearAspect(); // Tell the signal in rear which aspect is currently being displayed.
    }

    @Override
    public void updateAspect() {
    
        SignalAspect newAspect;
        
        if (this.signalOff) {
            // RED, YELLOW, SUB_OFF, BLACK, TOP_YELLOW, DOUBLE_YELLOW, GREEN, CAUTION, CLEAR, FLASHING_YELLOW, FLASHING_DOUBLE_YELLOW, FLASHING_WHITE, SPAD_INDICATOR_ILLUMINATED;
            switch (this.signalAheadAspect) {
                    
                case RED:
                    newAspect = SignalAspect.YELLOW;
                    break;
                case FLASHING_YELLOW:
                    if (this.signalType.toString().contains("_4")) {
                        newAspect = SignalAspect.FLASHING_DOUBLE_YELLOW;
                    } else {
                        newAspect = SignalAspect.GREEN;
                    }
                    break;
                case FLASHING_DOUBLE_YELLOW:
                    newAspect = SignalAspect.GREEN;
                    break;
                case YELLOW:
                    if (this.signalType.toString().contains("_4")) {
                        newAspect = SignalAspect.DOUBLE_YELLOW;
                    } else {
                        newAspect = SignalAspect.GREEN;
                    }
                    break;
                case DOUBLE_YELLOW:
                    newAspect = SignalAspect.GREEN;
                    break;
                case GREEN:
                    newAspect = SignalAspect.GREEN;
                    break;
                default:
                    newAspect = SignalAspect.RED;
                    
            }
            
        } else {
            
            newAspect = SignalAspect.RED;
            
        }
        
        // This block of code checks that the aspects are available (not blown) and displays an appropriate aspect (or none).
        if (newAspect != this.currentSignalAspect) {
            if (newAspect.equals(SignalAspect.DOUBLE_YELLOW)) {
                if (this.signalLamp.get(SignalAspect.YELLOW) == false) {
                    this.currentSignalAspect = SignalAspect.TOP_YELLOW;
                } else if (this.signalLamp.get(SignalAspect.DOUBLE_YELLOW) == false) {
                    this.currentSignalAspect = SignalAspect.YELLOW;
                } else {
                    this.currentSignalAspect = newAspect;
                }
            } else {
                if (this.signalLamp.get(newAspect)) {
                    this.currentSignalAspect = newAspect;
                } else {
                    this.currentSignalAspect = SignalAspect.BLACK;
                }
            }
            
            this.updateSignalInRearAspect();
        }
        
    }

    @Override
    public Map getSignalLamps() {
        return this.signalLamp;
    }

}
