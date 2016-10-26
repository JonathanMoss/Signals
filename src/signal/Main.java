package signal;

import java.util.ArrayList;

public class Main {

    private static final ArrayList <Signal> SIGNALS = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Signal.setSignalArray(SIGNALS);
        
        SIGNALS.add(new ControlledSignal ("CE", "184", SignalType.COLOUR_LIGHT_4));
        SIGNALS.add(new RepeaterSignal ("CE", "R184", SignalType.COLOUR_LIGHT_REPEATER, "CE", "184"));
        SIGNALS.add(new ControlledSignal ("CE", "186", SignalType.COLOUR_LIGHT_4));
        SIGNALS.add(new ControlledSignal ("CE", "190", SignalType.COLOUR_LIGHT_4));
        SIGNALS.add(new AutomaticSignal ("CE", "190CA", SignalType.COLOUR_LIGHT_4, "CE", "190"));
        SIGNALS.add(new RepeaterSignal ("CE", "190R", SignalType.COLOUR_LIGHT_REPEATER, "CE", "190"));
        SIGNALS.add(new AutomaticSignal ("CE", "192", SignalType.COLOUR_LIGHT_4, "CE", "190"));
        SIGNALS.add(new ControlledSignal ("CE", "198", SignalType.COLOUR_LIGHT_4));
        SIGNALS.add(new ControlledSignal ("CE", "196", SignalType.COLOUR_LIGHT_4));

        ((ControlledSignal) getSignalObject("CE", "198")).setSignal("CE", "192");
        getSignalObject("CE", "190").failSignalLamp(SignalAspect.YELLOW);
        ((ControlledSignal) getSignalObject("CE", "190")).setSignal("CE", "186");
        ((ControlledSignal) getSignalObject("CE", "186")).setSignal("CE", "184");
        
        for (int i = 0; i < SIGNALS.size(); i++) {
            System.out.println(String.format ("%s%s: %s", SIGNALS.get(i).getPrefix(), SIGNALS.get(i).getIdentity(), SIGNALS.get(i).getCurrentAspect()));
        }
    }
    
    
    // This method returns a Signal Object based on the Prefix and Identity passed as parameters.
    private static Signal getSignalObject (String prefix, String identity) {
        
        for (int i = 0; i < SIGNALS.size(); i ++) {
            if (SIGNALS.get(i).getPrefix().equals(prefix) && SIGNALS.get(i).getIdentity().equals(identity)) {
                return SIGNALS.get(i);
            }
        }
        
        return null;
        
    }
    
}
