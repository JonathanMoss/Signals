package signal;

import java.util.ArrayList;

public class Main {

    private static final ArrayList <Signal> SIGNALS = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SIGNALS.add(new ControlledSignal("CE", "175", SignalType.COLOUR_LIGHT_4, SIGNALS));
        SIGNALS.add(new ControlledSignal("CE", "183", SignalType.COLOUR_LIGHT_4, SIGNALS));
        SIGNALS.add(new ControlledSignal("CE", "185", SignalType.COLOUR_LIGHT_4, SIGNALS));
        SIGNALS.add(new ControlledSignal("CE", "189", SignalType.COLOUR_LIGHT_4, SIGNALS));
        
        SIGNALS.get(3).getSignalLamps().put(SignalAspect.GREEN, false);
        SIGNALS.get(0).getSignalLamps().put(SignalAspect.YELLOW, false);
        SIGNALS.get(0).signalOff("CE", "183");
        SIGNALS.get(1).signalOff("CE", "185"); 
        SIGNALS.get(2).signalOff("CE", "189");
        SIGNALS.get(3).signalOff(null, null);

        
        for (int i = 0; i < SIGNALS.size(); i++) {
            System.out.println(String.format ("%s%s: %s", 
                SIGNALS.get(i).getSignalPrefix(),
                SIGNALS.get(i).getSignalIdentity(),
                SIGNALS.get(i).getCurrentAspect()));
        }
        
        

    }
    
}
