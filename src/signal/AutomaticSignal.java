
package signal;

import java.util.ArrayList;

/**
 * This Class provides the blueprint for an Automatic Signal.
 * 
 * For the purposes of the Simulation, an Automatic Signal is any signal that the Signaller does not control (Other than replacement facilities)
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class AutomaticSignal extends Signal {
    
    private Boolean canTheSignalClear; // This is used as a flag to take interupt Automatic Working.
    
    public AutomaticSignal(String prefix, String identity, SignalType type, ArrayList signalsArray) {
        super(prefix, identity, type, signalsArray);
        this.canTheSignalClear = true;
    }
    
    /**
     * This method is called when there is a need to replace an automatic signal to danger.
     * 
     * Note: This action is only applicable to Colour Light automatic signals.
     */
    public void replaceSignalToDanger() {
        if (this.getSignalType().equals(SignalType.COLOUR_LIGHT_3) || this.getSignalType().equals(SignalType.COLOUR_LIGHT_4)) {
            this.signalOn();
        }
        
    }
    
    
    

    

    

    

}
