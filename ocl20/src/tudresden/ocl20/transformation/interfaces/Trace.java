/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the trace information, that is
 * created when a transformation is executed.
 * 
 * @author Christian Wende
 *
 */
public class Trace {

	
	private List<Item> traces;
	
	/**
	 * The Constructor for the Trace
	 *
	 */
	public Trace() {
		traces = new ArrayList<Item>();
	}
	
	/**
	 * Adds a new item to the trace.
	 * @param type The type of the trace item
	 * @param trace the content of the trace item
	 */
	public void addTrace(TraceType type, String trace) {
		traces.add(new Item(type, trace));
	}
	
	/**
	 * Returns all trace items.
	 * @return Returns all trace items.
	 */
	public List<Item> getTraces() {
		return traces;
	}
	
	/**
	 * Adds all trace items of the given Trace to this Trace.
	 * @param t A Trace, whose items should be added to the current.
	 */
	public void addTrace(Trace t) {
		traces.addAll(t.getTraces());
	}
	
	/**
	 * Generates a String that represents the content of the Trace.
	 */
	public String toString() {
		String out = "";
		for (Item i : traces) {
			if(!i.getTracetype().equals(TraceType.QUERY)) {
				out += "<" + i.getTracetype() + ">" + " " + i.getTrace() + "\n"; 
			}
		}
		return out;
		
	}

	private class Item {
		private TraceType tracetype;
		private String trace;
		
		/**
		 * @param trace
		 * @param tracetype
		 */
		public Item(TraceType tt, String trace) {
			this.trace = trace;
			this.tracetype = tt;
		}
		
		/**
		 * @return Returns the trace.
		 */
		public String getTrace() {
			return trace;
		}
		
		/**
		 * @return Returns the tt.
		 */
		public TraceType getTracetype() {
			return tracetype;
		}
		
		
	}

}
