package signal;
/**
 * This Class provides the blueprint for a Repeater Signal.
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class RepeaterSignal extends Signal {

    private final Signal applicableSignal;
    
    public RepeaterSignal(String signalPrefix, String signalIdentity, SignalType signalType, String applicableSignalPrefix, String applicableSignalIdentity) {
        
        super(signalPrefix, signalIdentity, signalType);
        this.applicableSignal = Signal.getSignalObject(applicableSignalPrefix, applicableSignalIdentity);
        this.applicableSignal.setSignalInRear(this);
        
    }





    

}
