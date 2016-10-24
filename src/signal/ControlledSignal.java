package signal;

/**
 * This Class provides the blueprint for a Controlled Signal.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class ControlledSignal extends Signal {

    public ControlledSignal(String signalPrefix, String signalIdentity, SignalType signalType) {
        super(signalPrefix, signalIdentity, signalType);
    }
    
    public void setSignal (Signal toSignal) {
        toSignal.setSignalInRear(this);
    }


    
   
   
 
   

   

   
}
