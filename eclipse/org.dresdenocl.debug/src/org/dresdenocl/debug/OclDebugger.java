package org.dresdenocl.debug;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dresdenocl.debug.model.EOclDebugMessageType;
import org.dresdenocl.debug.model.OclDebugCommunicationHelper;
import org.dresdenocl.debug.model.OclDebugMessage;
import org.dresdenocl.debug.util.EStepMode;
import org.dresdenocl.debug.util.OclStringUtil;
import org.dresdenocl.essentialocl.expressions.BooleanLiteralExp;
import org.dresdenocl.essentialocl.expressions.CallExp;
import org.dresdenocl.essentialocl.expressions.CollectionItem;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionRange;
import org.dresdenocl.essentialocl.expressions.EnumLiteralExp;
import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.IfExp;
import org.dresdenocl.essentialocl.expressions.IntegerLiteralExp;
import org.dresdenocl.essentialocl.expressions.InvalidLiteralExp;
import org.dresdenocl.essentialocl.expressions.IterateExp;
import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.LetExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.PropertyCallExp;
import org.dresdenocl.essentialocl.expressions.RealLiteralExp;
import org.dresdenocl.essentialocl.expressions.StringLiteralExp;
import org.dresdenocl.essentialocl.expressions.TupleLiteralExp;
import org.dresdenocl.essentialocl.expressions.TupleLiteralPart;
import org.dresdenocl.essentialocl.expressions.TypeLiteralExp;
import org.dresdenocl.essentialocl.expressions.UndefinedLiteralExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.VariableExp;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclCollection;
import org.dresdenocl.essentialocl.standardlibrary.OclComparable;
import org.dresdenocl.essentialocl.standardlibrary.OclIterator;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.internal.OclInterpreter;
import org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Type;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public class OclDebugger extends OclInterpreter implements IOclDebuggable {

	/**
	 * Name of the variable containing a {@link Constraint}'s result once
	 * interpreted completely.
	 */
	public static final String OCL_RESULT_VATRIABLE_NAME = "oclResult";

	/**
	 * Name of the variable containing a {@link CollectionLiteralExp}'s result
	 * during debugging.
	 */
	public static final String OCL_COLLECTION_RESULT_VATRIABLE_NAME = "oclCollection";

	/**
	 * Name of the variable containing a {@link CollectionLiteralExp}'s result
	 * during debugging.
	 */
	public static final String OCL_CALL_SOURCE_VATRIABLE_NAME = "oclSource";

	/**
	 * Name of the variable containing a {@link CollectionLiteralExp}'s result
	 * during debugging.
	 */
	public static final String OCL_IF_CONDITION_RESULT_VATRIABLE_NAME = "oclCondition";

	/**
	 * Name of the variable containing a {@link OperationCallExp}'s result
	 * during debugging.
	 */
	public static final String OCL_ITERATE_EXPRESSION_RESULT = "oclIterateResult";

	/**
	 * Name of the variable containing a {@link OperationCallExp}'s result
	 * during debugging.
	 */
	public static final String OCL_ITERATOR_EXPRESSION_RESULT = "oclIteratorResult";

	/**
	 * Name of the variable containing a {@link OperationCallExp}'s result
	 * during debugging.
	 */
	public static final String OCL_OPERATION_CALL_RESULT = "oclOperationValue";

	/**
	 * Name of the variable containing an {@link OperationCallExp}'s parameter's
	 * result during debugging.
	 */
	public static final String OCL_PARAMETER_VALUE_VARIBALE = "oclParameter";

	/**
	 * Name of the variable containing a {@link PropertyCallExp}'s result during
	 * debugging.
	 */
	public static final String OCL_PROPERTY_CALL_RESULT = "oclPropertyValue";

	/**
	 * Name of the variable containing the elements of an {@link OclCollection}
	 * that is ordered during iteration.
	 */
	public static final String OCL_SORTED_BY_ELEMENTS = "oclSortedByElements";

	/**
	 * Name of the variable containing the values an {@link OclCollection} is
	 * ordered by during iteration.
	 */
	public static final String OCL_SORTED_BY_VALUES = "oclSortedByValues";

	/**
	 * Stores the stack size at the last position being suspended (used for step
	 * over and return to decide where to suspend.
	 */
	private int m_lastStackSize = 0;

	private boolean m_debugMode;
	private boolean m_suspended;
	private boolean m_terminated;
	private boolean alreadySentStartEvent = false;
	private ServerSocket m_server;
	private PrintStream m_outputStream;
	private OclDebugCommunicationHelper m_communicationHelper = new OclDebugCommunicationHelper();
	private Set<Integer> m_lineBreakpointPositions = new HashSet<Integer>();
	private LinkedList<String> m_stackframes = new LinkedList<String>();
	private int m_nextId = 0;
	private Map<EObject, EObject> m_currentMappings = new IdentityHashMap<EObject, EObject>();
	private Map<String, Map<String, Object>> m_stackVariables = new LinkedHashMap<String, Map<String, Object>>();
	private Integer m_lastPassedBreakpoint = Integer.valueOf(-1);
	private Set<Integer> m_invalidBreakpoints = new HashSet<Integer>();
	private EStepMode m_stepMode = EStepMode.NORMAL;
	private Integer m_currentLine = 0;

	public void shutdown() throws Exception {

		// TODO Lars
		throw new Exception("The debugger is shutting down!");
	}

	public OclDebugger(IModelInstance aModelInstance) {

		super(aModelInstance);
	}

	@Override
	public IInterpretationResult interpretConstraint(Constraint constraint,
			IModelInstanceElement modelInstanceElement) {

		startupAndWait();
		// m_stackVariables.clear();
		// m_stackframes.clear();
		m_invalidBreakpoints.clear();
		m_lastPassedBreakpoint = Integer.valueOf(-1);
		// m_curAsmElement = null;

		IInterpretationResult result = super.interpretConstraint(constraint,
				modelInstanceElement);

		sendEvent(EOclDebugMessageType.CONSTRAINT_INTERPRETED, true);

		return result;
	}

	@Override
	public List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints,
			IModelInstanceElement modelInstanceElement) {

		List<IInterpretationResult> result = super.interpretConstraints(
				constraints, modelInstanceElement);
		// Lars TODO
		// terminate();
		return result;
	}

	@Override
	public void setEventPort(int eventPort) {

		if (isDebugMode()) {
			// check if server is already running and shut down if necessary to
			// change the port
			if (m_server != null) {
				if (!m_server.isClosed()) {
					if (m_server.getLocalPort() != eventPort) {
						stopEventSocket();
						startEventSocket(eventPort);
					}
					// no else. already listening to the port
				} else {
					// already closed, so create new one
					startEventSocket(eventPort);
				}
			} else {
				// still null
				startEventSocket(eventPort);
			}
		}
	}

	/**
	 * Computes the line of the EObject in the containing resource.
	 * 
	 * @param element
	 *            the EObject element
	 * @return the line element was defined in the resource
	 */
	protected int getLine(EObject element) {

		if (element == null) {

			RuntimeException e = new RuntimeException(
					"element is null in getLine()");
			e.printStackTrace();
			throw e;
			// return -1;
		}
		EObject e = m_currentMappings.get(element);
		if (e == null)
			safePrintln("getLine NULL BEI " + element);
		OclResource resource = (OclResource) e.eResource();
		int line;
		do {
			line = resource.getLocationMap().getLine(e);
			e = e.eContainer();
		} while (line == -1 && e != null);
		m_currentLine = line;
		return line;
	}

	public int getCurrentLine() {

		// return getLine(m_curAsmElement);
		return m_currentLine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.debug.IOclDebuggable#isLineBreakPointElement(org.eclipse
	 * .emf.ecore.EObject)
	 */
	public boolean isLineBreakPointElement(EObject element) {

		if (!isDebugMode()) {
			return false;
		}

		switch (m_stepMode) {

		// If normal mode : check if we have a breakpoint
		case NORMAL: {
			Integer line = Integer.valueOf(getLine(element));
			// check if the element is found in the map
			boolean result = false;

			if (line != null && line.intValue() != -1) {
				result = m_lineBreakpointPositions.contains(line)
						&& !m_invalidBreakpoints.contains(line);
			}
			// no else
			return result;
		}

		case STEP_INTO: {
			// to set m_currentLine
			getLine(element);
			return true;
		}

		case STEP_OVER: {
			/* Stop if stack is smaller or same size than on last suspend. */
			if (m_lastStackSize >= getStack().length) {
				// to set m_currentLine
				getLine(element);
				return true;
			} else
				return false;
		}

		case STEP_RETURN: {
			/* Stop if stack is smaller than on last suspend. */
			if (m_lastStackSize > getStack().length) {
				// to set m_currentLine
				getLine(element);
				return true;
			} else
				return false;
		}

		default:
			return false;
		}
		// end switch.
	}

	private void stopOnBreakpoint(String methodName, EObject parameter) {

		pushStackFrame(methodName, parameter);
		if (isLineBreakPointElement(parameter)) {
			m_lastPassedBreakpoint = Integer.valueOf(getLine(parameter));
			setSuspend(true);
		}
		waitIfSuspended();
	}

	public void startupAndWait() {

		safePrintln("startupAndWait()");
		if (isDebugMode()) {
			if (!alreadySentStartEvent) {
				alreadySentStartEvent = true;
				sendEvent(EOclDebugMessageType.STARTED, true);
				setSuspend(true);
				waitIfSuspended();
			}
		}
		m_currentMappings = ModelBusPlugin.getModelRegistry().getActiveModel()
				.getAllMappings();
	}

	public boolean isSuspended() {

		return m_suspended;
	}

	public void setSuspend(boolean suspend) {

		safePrintln("setSuspend ( " + suspend + " )");
		m_suspended = suspend;
		if (m_suspended) {
			sendEvent(EOclDebugMessageType.SUSPENDED, true);
		}
	}

	public void sendEvent(EOclDebugMessageType command,
			boolean sendOnlyInDebugMode, String... arguments) {

		safePrintln("OclInterpreter sendEvent " + command);
		if (isDebugMode() || !sendOnlyInDebugMode) {
			OclDebugMessage message = new OclDebugMessage(command, arguments);
			m_communicationHelper.sendEvent(message, m_outputStream);
		}
	}

	public void setDebugMode(boolean debugMode) {

		this.m_debugMode = debugMode;
	}

	public boolean isDebugMode() {

		// System.out.println("isDebugMode = " + m_debugMode);
		return m_debugMode;
	}

	public void startEventSocket(final int eventPort) {

		// System.out.println("OclInterpreter startEventSocket");
		try {
			m_server = new ServerSocket(eventPort);
			Socket accept = m_server.accept();
			/*
			 * try { Thread.sleep(100); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
			// System.out.println("OclInterpreter accepted client");
			m_outputStream = new PrintStream(accept.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopEventSocket() {

		try {
			m_server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitIfSuspended() {

		if (isSuspended()) {
			try {
				while (isSuspended()) {
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// sendEvent(EOclDebugMessageType.RESUMED, true);
		}
	}

	private void setTerminate(boolean terminate) {

		m_terminated = terminate;
	}

	public boolean isTerminated() {

		return m_terminated;
	}

	@Override
	public void terminate() {

		safePrintln("OclInterpreter terminate()");
		setTerminate(true);
		sendEvent(EOclDebugMessageType.TERMINATED, false);
		stopEventSocket();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.debug.IOclDebuggable#resume()
	 */
	@Override
	public synchronized void resume() {

		safePrintln("OclInterpreter resume()");
		m_invalidBreakpoints.add(m_lastPassedBreakpoint);
		m_stepMode = EStepMode.NORMAL;
		setSuspend(false);
		sendEvent(EOclDebugMessageType.RESUMED, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.debug.IOclDebuggable#stepInto()
	 */
	@Override
	public synchronized void stepInto() {

		// make the last passed breakpoint (the one we've been suspending,
		// thought) invalid for this model instance element and stop at the next
		// line
		m_invalidBreakpoints.add(m_lastPassedBreakpoint);
		m_stepMode = EStepMode.STEP_INTO;
		safePrintln("OclInterpreter stepInto()");
		if (isSuspended()) {
			setSuspend(false);
		} else {
			sendEvent(EOclDebugMessageType.RESUMED, true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.debug.IOclDebuggable#stepOver()
	 */
	@Override
	public synchronized void stepOver() {

		// make the last passed breakpoint (the one we've been suspending,
		// thought) invalid for this model instance element and stop at the next
		// line
		m_invalidBreakpoints.add(m_lastPassedBreakpoint);
		m_lastStackSize = getStack().length;
		m_stepMode = EStepMode.STEP_OVER;
		safePrintln("OclInterpreter stepOver()");
		if (isSuspended()) {
			setSuspend(false);
		} else {
			sendEvent(EOclDebugMessageType.RESUMED, true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.debug.IOclDebuggable#stepReturn()
	 */
	@Override
	public synchronized void stepReturn() {
		// make the last passed breakpoint (the one we've been suspending,
		// thought) invalid for this model instance element and stop at the next
		// line
		m_invalidBreakpoints.add(m_lastPassedBreakpoint);
		m_lastStackSize = getStack().length;
		m_stepMode = EStepMode.STEP_RETURN;
		safePrintln("OclInterpreter stepReturn()");
		if (isSuspended()) {
			setSuspend(false);
		} else {
			sendEvent(EOclDebugMessageType.RESUMED, true);
		}
	}

	@Override
	public void addLineBreakPoint(String location, int line) {

		Integer i = Integer.valueOf(line);
		m_lineBreakpointPositions.add(i);
		m_invalidBreakpoints.remove(i);
	}

	@Override
	public void removeLineBreakPoint(String location, int line) {

		Integer i = Integer.valueOf(line);
		m_lineBreakpointPositions.remove(i);
		m_invalidBreakpoints.add(i);
		m_lastPassedBreakpoint = Integer.valueOf(-1);
	}

	@Override
	public String[] getStack() {

		synchronized (m_stackframes) {
			List<String> reverseStack = new ArrayList<String>(m_stackframes);
			Collections.reverse(reverseStack);
			return reverseStack.toArray(new String[0]);
		}
	}

	@Override
	public Map<String, Object> getFrameVariables(String stackFrame) {

		synchronized (m_stackVariables) {
			return m_stackVariables.get(stackFrame);
		}
	}

	private String getNextStackId() {

		return Integer.toString(++m_nextId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseBooleanLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.BooleanLiteralExp)
	 */
	@Override
	public OclAny caseBooleanLiteralExp(BooleanLiteralExp booleanLiteralExp) {

		stopOnBreakpoint(
				"BooleanLiteralExpression ("
						+ booleanLiteralExp.isBooleanSymbol() + ")",
				booleanLiteralExp);
		OclAny result = super.caseBooleanLiteralExp(booleanLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseCollectionItem
	 * (org.dresdenocl.essentialocl.expressions.CollectionItem)
	 */
	@Override
	public OclAny caseCollectionItem(CollectionItem collectionItem) {

		/* Do not stop in front of collection items. */
		OclAny result = super.caseCollectionItem(collectionItem);

		/*
		 * Do not stop after collection literal items (at least not at their
		 * stack frame).
		 */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseCollectionLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.CollectionLiteralExp)
	 */
	@Override
	public OclAny caseCollectionLiteralExp(
			CollectionLiteralExp collectionLiteralExp) {

		/* Push local environment for temporary collection literal value(s). */
		pushLocalEnvironment();

		myEnvironment.setVariableValue(
				OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				adaptResultListAsCollection(new LinkedList<OclAny>(),
						collectionLiteralExp.getType()));

		stopOnBreakpoint("CollectionLiteralExpression ("
				+ collectionLiteralExp.getKind().getName() + ")",
				collectionLiteralExp);

		OclAny result = super.caseCollectionLiteralExp(collectionLiteralExp);
		popStackFrame();

		/* Do not stop after collection literals. */
		popEnvironment();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateCollectionItem
	 * (org.dresdenocl.essentialocl.expressions.CollectionItem,
	 * org.dresdenocl.essentialocl.expressions.CollectionLiteralExp)
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	protected OclAny evaluateCollectionItem(CollectionItem collectionItem,
			CollectionLiteralExp collectionLiteralExp) {

		OclAny result = super.evaluateCollectionItem(collectionItem,
				collectionLiteralExp);

		/*
		 * Add the value of the variable to the collection literal's stack
		 * frame.
		 */
		OclAny oclVar = myEnvironment
				.getVariableValue(OCL_COLLECTION_RESULT_VATRIABLE_NAME);
		if (oclVar instanceof OclCollection<?>) {
			@SuppressWarnings("unchecked")
			OclCollection<OclAny> oclCollection = (OclCollection<OclAny>) oclVar;
			oclCollection = oclCollection.asBag().including(result);

			switch (collectionLiteralExp.getKind()) {

			case ORDERED_SET:
				oclCollection = oclCollection.asOrderedSet();
				break;

			case SEQUENCE:
				oclCollection = oclCollection.asSequence();
				break;

			case SET:
				oclCollection = oclCollection.asSet();
				break;

			/* no default (use a Bag otherwise). */
			}

			myEnvironment.setVariableValue(
					OCL_COLLECTION_RESULT_VATRIABLE_NAME, oclCollection);
		}
		// no else.

		popStackFrame();
		stopOnBreakpoint("CollectionLiteralExpression ("
				+ collectionLiteralExp.getKind().getName() + ")",
				collectionLiteralExp);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateCollectionRange
	 * (org.dresdenocl.essentialocl.expressions.CollectionRange, java.util.List,
	 * org.dresdenocl.essentialocl.expressions.CollectionLiteralExp)
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	protected List<OclAny> evaluateCollectionRange(
			CollectionRange collectionRange, List<OclAny> resultList,
			CollectionLiteralExp collectionLiteralExp) {

		pushStackFrame("CollectionRange", collectionRange);
		List<OclAny> result = super.evaluateCollectionRange(collectionRange,
				resultList, collectionLiteralExp);
		popStackFrame();

		/*
		 * Add the value of the variable to the collection literal's stack
		 * frame.
		 */
		OclAny oclVar = myEnvironment
				.getVariableValue(OCL_COLLECTION_RESULT_VATRIABLE_NAME);
		if (oclVar instanceof OclCollection<?>) {
			@SuppressWarnings("unchecked")
			OclCollection<OclAny> oclCollection = (OclCollection<OclAny>) oclVar;
			oclCollection = oclCollection.asBag().union(
					myStandardLibraryFactory.createOclBag(result,
							((CollectionType) collectionLiteralExp.getType())
									.getElementType()));

			switch (collectionLiteralExp.getKind()) {

			case ORDERED_SET:
				oclCollection = oclCollection.asOrderedSet();
				break;

			case SEQUENCE:
				oclCollection = oclCollection.asSequence();
				break;

			case SET:
				oclCollection = oclCollection.asSet();
				break;

			/* no default (use a Bag otherwise). */
			}

			myEnvironment.setVariableValue(
					OCL_COLLECTION_RESULT_VATRIABLE_NAME, oclCollection);
		}
		// no else.

		popStackFrame();
		stopOnBreakpoint("CollectionLiteralExpression ("
				+ collectionLiteralExp.getKind().getName() + ")",
				collectionLiteralExp);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseEnumLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.EnumLiteralExp)
	 */
	@Override
	public OclAny caseEnumLiteralExp(EnumLiteralExp enumLiteralExp) {

		stopOnBreakpoint("EnumerationLiteralExpression ("
				+ enumLiteralExp.getReferredEnumLiteral().getEnumeration()
						.getName() + "::"
				+ enumLiteralExp.getReferredEnumLiteral().getName() + ")",
				enumLiteralExp);
		OclAny result = super.caseEnumLiteralExp(enumLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseExpressionInOcl
	 * (org.dresdenocl.essentialocl.expressions.ExpressionInOcl)
	 */
	@Override
	public OclAny caseExpressionInOcl(ExpressionInOcl expressionInOcl) {

		stopOnBreakpoint("ExpressionInOcl", expressionInOcl);
		OclAny result = super.caseExpressionInOcl(expressionInOcl);
		popStackFrame();

		/* The result of a constraint's interpretation. */
		myEnvironment.setVariableValue(OCL_RESULT_VATRIABLE_NAME, result);
		stopOnBreakpoint("ExpressionInOcl", expressionInOcl);
		popStackFrame();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseIfExp(org.dresdenocl
	 * .essentialocl.expressions.IfExp)
	 */
	@Override
	public OclAny caseIfExp(IfExp ifExp) {

		/* Additional environment for condition result. */
		pushLocalEnvironment();
		stopOnBreakpoint("IfExpression", ifExp);
		OclAny result = super.caseIfExp(ifExp);
		/* Do not stop after if expressions. */
		popStackFrame();
		popEnvironment();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateIfCondition
	 * (org.dresdenocl.essentialocl.expressions.IfExp)
	 */
	@Override
	protected OclAny evaluateIfCondition(IfExp ifExp) {
		OclAny result = super.evaluateIfCondition(ifExp);
		/* Add the result of the condition to the visible variables. */
		myEnvironment.setVariableValue(OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseIntegerLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.IntegerLiteralExp)
	 */
	@Override
	public OclAny caseIntegerLiteralExp(IntegerLiteralExp integerLiteralExp) {
		stopOnBreakpoint(
				"IntegerLiteralExpression ("
						+ integerLiteralExp.getIntegerSymbol() + ")",
				integerLiteralExp);
		popStackFrame();
		OclAny result = super.caseIntegerLiteralExp(integerLiteralExp);
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseInvalidLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.InvalidLiteralExp)
	 */
	@Override
	public OclAny caseInvalidLiteralExp(InvalidLiteralExp invalidLiteralExp) {

		stopOnBreakpoint("InvalidLiteralExpression", invalidLiteralExp);
		OclAny result = super.caseInvalidLiteralExp(invalidLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseIterateExp(org
	 * .dresdenocl.essentialocl.expressions.IterateExp)
	 */
	@Override
	public OclAny caseIterateExp(IterateExp iterateExp) {

		/* Additional local environment for it source and result variables. */
		pushLocalEnvironment();

		stopOnBreakpoint("IterateExpression", iterateExp);

		OclAny result = super.caseIterateExp(iterateExp);
		popStackFrame();

		myEnvironment.setVariableValue(OCL_ITERATE_EXPRESSION_RESULT, result);

		stopOnBreakpoint("IterateExpression", iterateExp);
		popStackFrame();

		popEnvironment();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateIterate(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * java.util.List, org.dresdenocl.essentialocl.standardlibrary.OclIterator,
	 * org.dresdenocl.essentialocl.expressions.Variable)
	 */
	@Override
	protected OclAny evaluateIterate(OclExpression bodyExpression,
			OclCollection<OclAny> source, List<Variable> iteratorVariables,
			OclIterator<OclAny> iterator, Variable resultVariable) {

		/* Add evaluated source expression to iterator. */
		myEnvironment.setVariableValue(OCL_CALL_SOURCE_VATRIABLE_NAME, source);

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint("IterateExpression", bodyExpression.eContainer());
		}
		// no else.

		OclAny result = super.evaluateIterate(bodyExpression, source,
				iteratorVariables, iterator, resultVariable);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseIteratorExp(org
	 * .dresdenocl.essentialocl.expressions.IteratorExp)
	 */
	@Override
	public OclAny caseIteratorExp(IteratorExp iteratorExp) {

		/* Additional local environment for it source and result variables. */
		pushLocalEnvironment();
		stopOnBreakpoint("IteratorExpression (" + iteratorExp.getName() + ")",
				iteratorExp);
		OclAny result = super.caseIteratorExp(iteratorExp);
		popStackFrame();
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT, result);
		stopOnBreakpoint("IteratorExpression (" + iteratorExp.getName() + ")",
				iteratorExp);
		popStackFrame();
		popEnvironment();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateSource(org
	 * .dresdenocl.essentialocl.expressions.IteratorExp)
	 */
	@Override
	protected OclAny evaluateSource(IteratorExp iteratorExp) {
		OclAny result = super.evaluateSource(iteratorExp);
		myEnvironment.setVariableValue(OCL_CALL_SOURCE_VATRIABLE_NAME, result);
		/*
		 * Do not stop here. Stop in front of iterator evaluation in the
		 * respective evaluate method.
		 */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateAny(org.dresdenocl
	 * .essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateAny(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclUndefined(resultType,
						"No element matching to the body expression yet."));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateAny(body, source, iterator, resultType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateCollectNested
	 * (org.dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateCollectNested(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclUndefined(resultType,
						"Iterator interpretation not started yet."));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateCollectNested(body, source, iterator, resultType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.interpreter.internal.OclInterpreter#
	 * evaluateCollectNestedElement
	 * (org.dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.essentialocl.standardlibrary.OclIterator, java.util.List,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected List<OclAny> evaluateCollectNestedElement(OclExpression body,
			OclCollection<OclAny> source, Variable iterator,
			OclIterator<OclAny> it, List<OclAny> resultList, Type resultType) {

		/* Update result variable (necessary in front of the first element. */
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				this.adaptCollectNestedResult(resultType, resultList));

		resultList = super.evaluateCollectNestedElement(body, source, iterator,
				it, resultList, resultType);

		/* Update result variable. */
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				this.adaptCollectNestedResult(resultType, resultList));

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateExists(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * java.util.List, org.dresdenocl.essentialocl.standardlibrary.OclIterator)
	 */
	@Override
	protected OclAny evaluateExists(OclExpression body,
			OclCollection<OclAny> source, List<Variable> iterators,
			OclIterator<OclAny> it) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclBoolean(false));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			/* Push additional environment to make it variables visible. */
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateExists(body, source, iterators, it);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateForAll(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * java.util.List, org.dresdenocl.essentialocl.standardlibrary.OclIterator)
	 */
	@Override
	protected OclAny evaluateForAll(OclExpression body,
			OclCollection<OclAny> source, List<Variable> iterators,
			OclIterator<OclAny> it) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclBoolean(true));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateForAll(body, source, iterators, it);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateIsUnique(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable)
	 */
	@Override
	protected OclAny evaluateIsUnique(OclExpression body,
			OclCollection<OclAny> source, Variable iterator) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclBoolean(true));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateIsUnique(body, source, iterator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateReject(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateReject(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclUndefined(resultType,
						"Iterator interpretation not started yet."));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateReject(body, source, iterator, resultType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateRejectElement
	 * (org.dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.essentialocl.standardlibrary.OclIterator, java.util.List,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateRejectElement(OclExpression body,
			OclCollection<OclAny> source, Variable iterator,
			OclIterator<OclAny> it, List<OclAny> resultList, Type resultType) {

		/* Update result variable (necessary in front of the first element. */
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				this.adaptResultListAsCollection(resultList, resultType));

		OclAny result = super.evaluateRejectElement(body, source, iterator, it,
				resultList, resultType);

		/* Update result variable. */
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				this.adaptResultListAsCollection(resultList, resultType));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateSelect(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateSelect(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclUndefined(resultType,
						"Iterator interpretation not started yet."));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateSelect(body, source, iterator, resultType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateSelectElement
	 * (org.dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.essentialocl.standardlibrary.OclIterator, java.util.List,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateSelectElement(OclExpression body,
			OclCollection<OclAny> source, Variable iterator,
			OclIterator<OclAny> it, List<OclAny> resultList, Type resultType) {

		/* Update result variable (necessary in front of the first element. */
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				this.adaptResultListAsCollection(resultList, resultType));

		OclAny result = super.evaluateSelectElement(body, source, iterator, it,
				resultList, resultType);

		/* Update result variable. */
		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				this.adaptResultListAsCollection(resultList, resultType));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateSortedBy(org
	 * .dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected OclAny evaluateSortedBy(OclExpression body,
			OclCollection<OclAny> source, Variable iterator, Type resultType) {

		myEnvironment
				.setVariableValue(
						OCL_ITERATOR_EXPRESSION_RESULT,
						myStandardLibraryFactory
								.createOclUndefined(resultType,
										"Interpretation of sortedBy iterator not finished yet."));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		OclAny result = super.evaluateSortedBy(body, source, iterator,
				resultType);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateSortedByElement
	 * (org.dresdenocl.essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.standardlibrary.OclAny,
	 * org.dresdenocl.pivotmodel.Type, java.util.Map)
	 */
	@Override
	protected OclAny evaluateSortedByElement(OclExpression body,
			OclCollection<OclAny> source, OclAny currentElement,
			Type resultType, Map<OclComparable, OclAny> results) {

		/* Set variables for current status of sorting. */
		List<OclAny> keys = new ArrayList<OclAny>(results.size());
		List<OclAny> values = new ArrayList<OclAny>(results.size());
		for (OclComparable value : results.keySet()) {
			values.add(value);
			keys.add(results.get(value));
		}
		// end for.

		myEnvironment.setVariableValue(OCL_SORTED_BY_ELEMENTS,
				myStandardLibraryFactory.createOclBag(keys,
						((CollectionType) resultType).getElementType()));
		myEnvironment.setVariableValue(OCL_SORTED_BY_VALUES,
				myStandardLibraryFactory.createOclBag(values, body.getType()));

		OclAny result = super.evaluateSortedByElement(body, source,
				currentElement, resultType, results);

		/* Probably update sort variables again (if after last element). */
		if (source.getModelInstanceCollection().getCollection().size() == results
				.size()) {
			/* Set variables for current status of sorting. */
			keys = new ArrayList<OclAny>(results.size());
			values = new ArrayList<OclAny>(results.size());
			for (OclComparable value : results.keySet()) {
				values.add(value);
				keys.add(results.get(value));
			}
			// end for.

			myEnvironment.setVariableValue(OCL_SORTED_BY_ELEMENTS,
					myStandardLibraryFactory.createOclBag(keys,
							((CollectionType) resultType).getElementType()));
			myEnvironment.setVariableValue(
					OCL_SORTED_BY_VALUES,
					myStandardLibraryFactory.createOclBag(values,
							body.getType()));
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateOne(org.dresdenocl
	 * .essentialocl.expressions.OclExpression,
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection,
	 * org.dresdenocl.essentialocl.expressions.Variable)
	 */
	@Override
	protected OclAny evaluateOne(OclExpression body,
			OclCollection<OclAny> source, Variable iterator) {

		myEnvironment.setVariableValue(OCL_ITERATOR_EXPRESSION_RESULT,
				myStandardLibraryFactory.createOclBoolean(false));

		/* Do not stop here during step over. */
		if (!m_stepMode.equals(EStepMode.STEP_OVER)) {
			popStackFrame();
			stopOnBreakpoint(
					"IteratorExpression ("
							+ ((NamedElement) body.eContainer()).getName()
							+ ")", body.eContainer());
		}
		// no else.

		return super.evaluateOne(body, source, iterator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseLetExp(org.dresdenocl
	 * .essentialocl.expressions.LetExp)
	 */
	@Override
	public OclAny caseLetExp(LetExp letExp) {

		stopOnBreakpoint("LetExpression (" + letExp.getVariable().getName()
				+ ")", letExp);

		OclAny result = super.caseLetExp(letExp);
		popStackFrame();
		/* Do not stop after let expressions. */

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseOperationCallExp
	 * (org.dresdenocl.essentialocl.expressions.OperationCallExp)
	 */
	@Override
	public OclAny caseOperationCallExp(OperationCallExp operationCallExp) {

		/* Additional environment for source and parameter values. */
		pushLocalEnvironment();
		OclAny result = super.caseOperationCallExp(operationCallExp);

		myEnvironment.setVariableValue(OclDebugger.OCL_OPERATION_CALL_RESULT,
				result);
		stopOnBreakpoint("OperationCallExpression ("
				+ operationCallExp.getReferredOperation().getName() + ")",
				operationCallExp);
		popStackFrame();
		popEnvironment();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateSource(org
	 * .dresdenocl.essentialocl.expressions.CallExp)
	 */
	@Override
	protected OclAny evaluateSource(CallExp callExp) {
		OclAny result = super.evaluateSource(callExp);
		myEnvironment.setVariableValue(OCL_CALL_SOURCE_VATRIABLE_NAME, result);
		return result;
	}

	/*
	 * @Override public OclAny caseVariable(Variable variable) {
	 * stopOnBreakpoint("caseVariable", variable); OclAny result =
	 * super.caseVariable(variable); popStackFrame();
	 * stopOnBreakpoint("caseVariable", variable); popStackFrame(); return
	 * result; }
	 */
	/*
	 * @Override public OclAny caseVariableExp(VariableExp variableExp) {
	 * stopOnBreakpoint("caseVariableExp", variableExp); OclAny result =
	 * super.caseVariableExp(variableExp); popStackFrame();
	 * stopOnBreakpoint("caseVariableExp", variableExp); popStackFrame(); return
	 * result; }
	 */

	@Override
	protected OclAny evaluateNonStaticOperation(
			OperationCallExp operationCallExp) {

		stopOnBreakpoint("OperationCallExpression ("
				+ operationCallExp.getReferredOperation().getName() + ")",
				operationCallExp);
		OclAny result = super.evaluateNonStaticOperation(operationCallExp);
		popStackFrame();
		/* Do not stop after evaluation (stop in caseOperationCallExp). */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateStaticOperation
	 * (org.dresdenocl.essentialocl.expressions.OperationCallExp)
	 */
	@Override
	protected OclAny evaluateStaticOperation(OperationCallExp operationCallExp) {

		stopOnBreakpoint("OperationCallExpression ("
				+ operationCallExp.getReferredOperation().getName() + ")",
				operationCallExp);
		OclAny result = super.evaluateStaticOperation(operationCallExp);
		popStackFrame();
		/* Do not stop after evaluation (stop in caseOperationCallExp). */
		return result;
	}

	/*
	 * @Override public OclAny caseVariable(Variable variable) {
	 * stopOnBreakpoint("caseVariable", variable); OclAny result =
	 * super.caseVariable(variable); popStackFrame();
	 * stopOnBreakpoint("caseVariable", variable); popStackFrame(); return
	 * result; }
	 */
	/*
	 * @Override public OclAny caseVariableExp(VariableExp variableExp) {
	 * stopOnBreakpoint("caseVariableExp", variableExp); OclAny result =
	 * super.caseVariableExp(variableExp); popStackFrame();
	 * stopOnBreakpoint("caseVariableExp", variableExp); popStackFrame(); return
	 * result; }
	 */

	@Override
	protected LinkedHashMap<String, OclAny> computeParameters(
			OperationCallExp anOperationCallExp, Constraint oclDefinedOperation) {

		/* Do not stop before computing parameters. */
		// stopOnBreakpoint("computeParameters", anOperationCallExp);
		LinkedHashMap<String, OclAny> result = super.computeParameters(
				anOperationCallExp, oclDefinedOperation);
		// popStackFrame();
		int i = 0;
		for (String paramKey : result.keySet()) {
			myEnvironment.setVariableValue(OCL_PARAMETER_VALUE_VARIBALE + ++i,
					result.get(paramKey));
		}

		/*
		 * Stop after parameter computation but on the operation call stack
		 * level if stepping is step into.
		 */
		if (result.size() > 0 && m_stepMode.equals(EStepMode.STEP_INTO)) {
			popStackFrame();
			stopOnBreakpoint(
					"OperationCallExpression ("
							+ anOperationCallExp.getReferredOperation()
									.getName() + ")", anOperationCallExp);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#casePropertyCallExp
	 * (org.dresdenocl.essentialocl.expressions.PropertyCallExp)
	 */
	@Override
	public OclAny casePropertyCallExp(PropertyCallExp propertyCallExp) {

		/* Push additional environment for property call result. */
		pushLocalEnvironment();

		stopOnBreakpoint("PropertyCallExpression ("
				+ propertyCallExp.getReferredProperty().getName() + ")",
				propertyCallExp);

		OclAny result = super.casePropertyCallExp(propertyCallExp);
		popStackFrame();

		/* Set the property call result. */
		myEnvironment.setVariableValue(OCL_PROPERTY_CALL_RESULT, result);

		stopOnBreakpoint("PropertyCallExpression ("
				+ propertyCallExp.getReferredProperty().getName() + ")",
				propertyCallExp);
		popStackFrame();

		/* Pop additional environment for property call result. */
		popEnvironment();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseRealLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.RealLiteralExp)
	 */
	@Override
	public OclAny caseRealLiteralExp(RealLiteralExp realLiteralExp) {

		stopOnBreakpoint(
				"RealLiteralExpression (" + realLiteralExp.getRealSymbol()
						+ ")", realLiteralExp);
		OclAny result = super.caseRealLiteralExp(realLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseStringLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.StringLiteralExp)
	 */
	@Override
	public OclAny caseStringLiteralExp(StringLiteralExp stringLiteralExp) {

		stopOnBreakpoint(
				"StringLiteralExpression ('"
						+ stringLiteralExp.getStringSymbol() + "')",
				stringLiteralExp);
		OclAny result = super.caseStringLiteralExp(stringLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseTupleLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.TupleLiteralExp)
	 */
	@Override
	public OclAny caseTupleLiteralExp(TupleLiteralExp tupleLiteralExp) {

		/* Push new environment for results of TupleLiteralParts. */
		pushLocalEnvironment();

		stopOnBreakpoint("TupleLiteralExpression", tupleLiteralExp);
		OclAny result = super.caseTupleLiteralExp(tupleLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */

		popEnvironment();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#evaluateTupleLiteralPart
	 * (org.dresdenocl.essentialocl.expressions.TupleLiteralPart,
	 * org.dresdenocl.essentialocl.expressions.TupleLiteralExp)
	 */
	@Override
	protected OclAny evaluateTupleLiteralPart(
			TupleLiteralPart tupleLiteralPart, TupleLiteralExp tupleLiteralExp) {

		OclAny result = super.evaluateTupleLiteralPart(tupleLiteralPart,
				tupleLiteralExp);

		/* Add the value of the variable to the tuple literal's stack frame. */
		popStackFrame();
		myEnvironment.setVariableValue(
				tupleLiteralPart.getProperty().getName(), result);
		stopOnBreakpoint("TupleLiteralExpression", tupleLiteralExp);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseTupleLiteralPart
	 * (org.dresdenocl.essentialocl.expressions.TupleLiteralPart)
	 */
	@Override
	public OclAny caseTupleLiteralPart(TupleLiteralPart tupleLiteralPart) {

		stopOnBreakpoint("TupleLiteralPart ("
				+ tupleLiteralPart.getProperty().getName() + ")",
				tupleLiteralPart);
		OclAny result = super.caseTupleLiteralPart(tupleLiteralPart);
		popStackFrame();

		/*
		 * Do not stop after tuple literal parts (at least not at their stack
		 * frame).
		 */

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseTypeLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.TypeLiteralExp)
	 */
	@Override
	public OclAny caseTypeLiteralExp(TypeLiteralExp typeLiteralExp) {

		stopOnBreakpoint("TypeLiteralExpression ("
				+ typeLiteralExp.getReferredType().getQualifiedName()
						.substring("root::".length()) + ")", typeLiteralExp);
		OclAny result = super.caseTypeLiteralExp(typeLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseUndefinedLiteralExp
	 * (org.dresdenocl.essentialocl.expressions.UndefinedLiteralExp)
	 */
	@Override
	public OclAny caseUndefinedLiteralExp(
			UndefinedLiteralExp undefinedLiteralExp) {

		stopOnBreakpoint("UndefinedLiteralExpression", undefinedLiteralExp);
		OclAny result = super.caseUndefinedLiteralExp(undefinedLiteralExp);
		popStackFrame();
		/* Do not stop after literals. */
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.interpreter.internal.OclInterpreter#caseVariableExp(org
	 * .dresdenocl.essentialocl.expressions.VariableExp)
	 */
	@Override
	public OclAny caseVariableExp(VariableExp variableExp) {
		stopOnBreakpoint("VariableExpression ("
				+ variableExp.getReferredVariable().getName() + ")",
				variableExp);
		OclAny result = super.caseVariableExp(variableExp);
		popStackFrame();

		/*
		 * Do not stop after evaluation of self variable or variables having not
		 * init expression (e.g., iterator variables).
		 */
		if (!variableExp.getReferredVariable().getName()
				.equals(SELF_VARIABLE_NAME)
				&& null != variableExp.getReferredVariable()
						.getInitExpression()) {
			stopOnBreakpoint("VariableExpression ("
					+ variableExp.getReferredVariable().getName() + ")",
					variableExp);
			popStackFrame();
		}
		// no else.

		return result;
	}

	/*
	 * @Override public OclAny caseVariable(Variable variable) {
	 * stopOnBreakpoint("caseVariable", variable); OclAny result =
	 * super.caseVariable(variable); popStackFrame();
	 * stopOnBreakpoint("caseVariable", variable); popStackFrame(); return
	 * result; }
	 */
	/*
	 * @Override public OclAny caseVariableExp(VariableExp variableExp) {
	 * stopOnBreakpoint("caseVariableExp", variableExp); OclAny result =
	 * super.caseVariableExp(variableExp); popStackFrame();
	 * stopOnBreakpoint("caseVariableExp", variableExp); popStackFrame(); return
	 * result; }
	 */

	protected OclResource getOclResource(URI uri) {

		String platformString = uri.toPlatformString(true);
		org.eclipse.core.resources.IResource member = org.eclipse.core.resources.ResourcesPlugin
				.getWorkspace().getRoot().findMember(platformString);
		if (member instanceof OclResource) {
			return (OclResource) member;
		} else
			return null;
	}

	protected void pushStackFrame(String functionName, EObject parameter) {

		synchronized (m_stackframes) {

			int line = getLine(parameter);
			String[] data = new String[6];
			data[0] = functionName;
			// FIXME + " ( "
			// + parameter.getClass().getSimpleName() + " )";
			data[1] = getNextStackId();
			data[2] = "dummy Resource (FIXME)";
			data[3] = Integer.toString(line);
			data[4] = "1";
			data[5] = "2";

			EObject astParameter = m_currentMappings.get(parameter);
			OclResource resource = (OclResource) astParameter.eResource();
			if (resource != null) {
				data[2] = resource.getURI().toString();
				data[4] = Integer.toString(resource.getLocationMap()
						.getCharStart(astParameter));
				data[5] = Integer.toString(resource.getLocationMap()
						.getCharEnd(astParameter) + 1);
			}
			// no else

			String stackFrame = OclStringUtil.encode(',', data);
			m_stackframes.push(stackFrame);
			// store the mapping from current stackframe to variables
			Map<String, Object> map = new HashMap<String, Object>(
					myEnvironment.getVariableValues());
			// map.put(parameter.getClass().getSimpleName(),
			// parameter.toString());
			/*
			 * if (!myEnvironmentStack.isEmpty()) {
			 * map.putAll(myEnvironmentStack
			 * .peek().getStoredVariableMappings()); }
			 */
			m_stackVariables.put(data[1], map);
		}
	}

	protected String popStackFrame() {

		return m_stackframes.pop();
	}

	private void safePrintln(String s) {

		// TODO synchronized (System.out) {
		// System.out.println(s);
		// }
	}
}
