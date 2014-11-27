/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.Collections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * A representation for a range in a document where a terminal (i.e., a
 * placeholder or a keyword) is expected. The range is expressed using two
 * integers denoting the start of the range including hidden tokens (e.g.,
 * whitespace) and excluding those token (i.e., the part of the document
 * containing the relevant characters).
 */
public class OclExpectedTerminal {
	
	/**
	 * Run the attachment code to create a model the reflects the state that would be
	 * reached if the completion was executed. This is required, because different
	 * completions can yield different models.
	 */
	private Runnable attachmentCode;
	
	private int followSetID;
	private EObject container;
	private org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement terminal;
	private int startIncludingHiddenTokens;
	private int startExcludingHiddenTokens;
	private String prefix;
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace containmentTrace;
	
	public OclExpectedTerminal(EObject container, org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement terminal, int followSetID, org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace containmentTrace) {
		super();
		this.container = container;
		this.terminal = terminal;
		this.followSetID = followSetID;
		this.containmentTrace = containmentTrace;
	}
	
	public Runnable getAttachmentCode() {
		return attachmentCode;
	}
	
	public void setAttachmentCode(Runnable attachmentCode) {
		this.attachmentCode = attachmentCode;
	}
	
	public int getFollowSetID() {
		return followSetID;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclExpectedElement getTerminal() {
		return terminal;
	}
	
	public String toString() {
		return terminal == null ? "null" : terminal.toString() + " at " + startIncludingHiddenTokens + "/" + startExcludingHiddenTokens;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		OclExpectedTerminal otherExpectedTerminal = (OclExpectedTerminal) o;
		if (this.container == null && otherExpectedTerminal.container != null) {
			return false;
		}
		boolean containersBothNull = this.container == null && otherExpectedTerminal.container == null;
		return this.terminal.equals((otherExpectedTerminal).terminal) && (containersBothNull || this.container.equals(otherExpectedTerminal.container));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((container == null) ? 0 : container.hashCode());
		result = prime * result + ((terminal == null) ? 0 : terminal.hashCode());
		return result;
	}
	
	public void setPosition(int startIncludingHiddenTokens, int startExcludingHiddenTokens) {
		assert this.startExcludingHiddenTokens <= startExcludingHiddenTokens;
		assert this.startIncludingHiddenTokens <= startExcludingHiddenTokens;
		this.startIncludingHiddenTokens = startIncludingHiddenTokens;
		this.startExcludingHiddenTokens = startExcludingHiddenTokens;
	}
	
	public int getStartIncludingHiddenTokens() {
		return startIncludingHiddenTokens;
	}
	
	public int getStartExcludingHiddenTokens() {
		return startExcludingHiddenTokens;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainmentTrace getContainmentTrace() {
		return containmentTrace;
	}
	
	public EObject getContainer() {
		return container;
	}
	
	/**
	 * This method creates a model that reflects the state that would be obtained if
	 * this proposal was accepted. This model can differ from the current model,
	 * because different proposals can result in different models. The code that is
	 * passed as argument is executed once the (changed) model was created. After
	 * executing the given code, all changes are reverted.
	 */
	public void materialize(Runnable code) {
		EObject root = EcoreUtil.getRootContainer(getContainer());
		if (root == null) {
			code.run();
			return;
		}
		ChangeRecorder recorder = new ChangeRecorder();
		recorder.beginRecording(Collections.singleton(root));
		
		// attach proposal model fragment to main model
		Runnable attachmentCode = getAttachmentCode();
		if (attachmentCode != null) {
			// Applying attachment code
			attachmentCode.run();
		}
		
		ChangeDescription changes = recorder.endRecording();
		code.run();
		// revert changes
		changes.apply();
	}
	
}
