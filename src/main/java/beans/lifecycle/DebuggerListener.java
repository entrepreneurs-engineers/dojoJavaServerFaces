package beans.lifecycle;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebuggerListener implements PhaseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4270568079747491048L;
	
	private Logger log = LogManager.getRootLogger();

	@Override
	public void beforePhase(PhaseEvent pe) {
		if(log.isInfoEnabled()){
			log.info("Antes de la fase: " + pe.getPhaseId().toString());
		}
	}
	
	@Override
	public void afterPhase(PhaseEvent pe) {
		if(log.isInfoEnabled()){
			log.info("Despues de la fase: " + pe.getPhaseId().toString());
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
