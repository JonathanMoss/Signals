package signal;

import java.util.ArrayList;

public class Main {

    private static final ArrayList <Signal> SIGNALS = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Signal.setSignalArray(SIGNALS);
        
        SIGNALS.add(new ControlledSignal ("CE", "190", SignalType.COLOUR_LIGHT_3));
        SIGNALS.add(new RepeaterSignal ("CE", "190CA", SignalType.COLOUR_LIGHT_3, "CE", "190"));
        SIGNALS.add(new RepeaterSignal ("CE", "190R", SignalType.COLOUR_LIGHT_REPEATER, "CE", "190"));
        SIGNALS.add(new AutomaticSignal ("CE", "192", SignalType.COLOUR_LIGHT_REPEATER, "CE", "190"));
        SIGNALS.add(new ControlledSignal ("CE", "198", SignalType.COLOUR_LIGHT_3));
        SIGNALS.add(new ControlledSignal ("CE", "198", SignalType.COLOUR_LIGHT_3));
    }
    
}
