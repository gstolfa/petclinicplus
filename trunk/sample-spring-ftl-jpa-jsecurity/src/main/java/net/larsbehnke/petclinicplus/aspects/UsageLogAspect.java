package net.larsbehnke.petclinicplus.aspects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Sample AspectJ annotation-style aspect that saves every owner name requested
 * to the clinic.
 * 
 * Not the most efficient implementation, but we're aiming to illustrate the
 * power of AspectJ AOP, not write perfect code here :-)
 * 
 * @author Rod Johnson
 * @author Lars Behnke
 * @since 2.0
 */
@Aspect
public class UsageLogAspect {

	private static Log log = LogFactory.getLog(UsageLogAspect.class);

	private int historySize = 5;

	private List<String> namesRequested = new ArrayList<String>(
			this.historySize);

	public void setHistorySize(int historySize) {
		this.historySize = historySize;
		this.namesRequested = new ArrayList<String>(historySize);
	}

	@Before("execution(* *.findOwners(String)) && args(name)")
	public synchronized void logNameRequest(String name) {

		if ("".equals(name)) {
			return;
		}
		int i;
		while ((i = namesRequested.indexOf(name)) > -1) {
			namesRequested.remove(i);
		}
		if (this.namesRequested.size() > this.historySize) {
			namesRequested.remove(0);
		}
		this.namesRequested.add(name);
		log.debug("Added owner '" + name + "' to history list ("
				+ this.namesRequested.size() + " entries).");
	}

	public List<String> getNamesRequested() {
		return this.namesRequested;
	}

}
