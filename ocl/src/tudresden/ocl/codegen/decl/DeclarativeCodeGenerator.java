/*
Copyright (C) 2000  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


To submit a bug report, send a comment, or get the latest news on
this project, please see the contactReadme.txt file in this package.
*/
package tudresden.ocl.codegen.decl;

import tudresden.ocl.codegen.CodeGenerator;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.decl.DeclarativeCodeFragment;
import tudresden.ocl.OclTree;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.ReversedDepthFirstAdapter;


import java.lang.String;
import java.lang.StringBuffer;
import java.lang.Exception;
import java.lang.Class;
import java.lang.reflect.Method;
import java.util.Vector;
import java.util.LinkedList;

/**
 * This class provides functionality to generate declarative code such as SQL by
 * traversing the abstract syntax tree in reversed depth first order.
 * The basic concept is simple substring replacing in a StringBuffer.
 * Therefore placeholders are going to be created and inserted at the proper
 * positions within the buffer. The task of replacing these placeholders with target
 * code is up to the nodes of the abstract syntax tree. To associate nodes of the
 * syntax tree with such tasks, a Hashtable is used provided by AnalysisAdapter.
 * It contains the nodes as keys and the tasks (placeholders) as elements.
 * It can be accessed by using the setIn and getIn methodes of Analysis Adapter.
 * To implement a code generator using the explained strategy, it is necessary to derive
 * a subclass from DeclarativeCodeGenerator and implement all inA... methodes of
 * the nodes that are destined to produce code. If a node is not able to produce complete
 * target code, new unique placeholders should be inserted at the critical positions. The task
 * to replace these placeholders should be forwarded to subnodes using the proper methodes
 * of this class.
 * DeclarativeCodeGenerator does not produce any code or codefragments. It only creates
 * the first task at the start node and delegates it to the leaf nodes. Code generation
 * and CodeFragment handling is up to the derived subclasses.
 *
 * @author Sten Loecher
 */
public class DeclarativeCodeGenerator extends ReversedDepthFirstAdapter implements CodeGenerator {
	/** the abstract syntax tree */
	protected OclTree theTree;
	/** the container for the produced code fragments */
	protected Vector fragments;
	/** the temporary code buffer, later inserted into code fragments */
	protected StringBuffer code;
	/** the code agent that provides code templates */
	protected CodeAgent ca;
	/** the counter for the creation of unique tasks */
	private   int taskCounter;

	// constructor
	public DeclarativeCodeGenerator(String rules) {
		fragments = new Vector();
		taskCounter = 0;
		ca = new CodeAgent(rules);
	}

	// service methodes
        /**
         * @param the URL of a new mapping rules file
         */
        void setRules(String rules) {
                ca = new CodeAgent(rules);
        }

	/**
	 * @return a new unique task (placeholder)
	 */
	String getUniqueTask() {
		taskCounter++;
		return new String("_t" + taskCounter + "_");
	}

	/**
	 * Replaces all occurences of the task with the given template in the code buffer.
	 * If task is contained in the template it is not going to be replaced.
	 * @param task the placeholder
	 * @param template the code template
	 */
	void replaceTask(String task, String template) {
		String temp = code.toString();
		int i = temp.indexOf(task, 0);

		while (i != -1) {
			temp = null;
			code.replace(i, i+task.length(), template);
			temp = code.toString();
			i += template.length();
			i = temp.indexOf(task, i);
		}
	}

	/**
	 * A replacement methode for Strings.
	 * @param source the string containing substrings that equal to oldStr
	 * @param oldStr the substring to be replaced
	 * @param newStr the replacement
	 * @return A String with replaced occurences of oldStr with newStr
	 */
	String replaceInString(String source, String oldStr, String newStr) {
		StringBuffer sourceBuffer = new StringBuffer(source);
		int i = source.indexOf(oldStr, 0);

		while (i != -1) {
			sourceBuffer.replace(i, i+oldStr.length(), newStr);
			i += newStr.length();
			i = sourceBuffer.toString().indexOf(oldStr, i);
		}

		return sourceBuffer.toString();
	}

	/**
	 * Delegates a task to an list of subnodes.
	 * @param from the owner of the task
	 * @param to the list of recievers
	 */
	void transferTask(Node from, LinkedList to) {
		if ((from == null) || (to == null)) return;

		String task = (String)getIn(from);
		Object temp[] = to.toArray();

		if (task != null)
            		for(int i=0; i < temp.length; i++) {
                		setIn((Node)temp[i], new String(task));
            		}
	}

	/**
	 * Delegates a task to an subnode.
	 * @param from the owner of the task
	 * @param to the reciever
	 */
	void transferTask(Node from, Node to) {
		if ((from ==null) || (to == null)) return;

		String task = (String)getIn(from);
		if (task != null)
			setIn(to, new String(task));
	}

	// handling of grammar rules
	/**
	 * Creates the first task, initializes the code buffer with it and delegates it to all subnodes.
	 */
	public void inAConstraint(AConstraint node) {
        	String startTask = getUniqueTask();
        	setIn(node, startTask);
        	transferTask(node, node.getConstraintBody());
        	transferTask(node, node.getContextDeclaration());
        }

	/**
	 * Automated task delegation for all nodes.
	 */
	public void defaultIn(Node node)
{
		Method[] method = node.getClass().getMethods();
		Object target;

		try {
			for (int i=0; i<method.length; i++) {
				if (method[i].getName().substring(0,3).equals("get")) {
					target = method[i].invoke(node, null);
					if (target instanceof Node) {
						transferTask(node, (Node)target);
					} else if (target instanceof LinkedList) {
						transferTask(node, (LinkedList)target);
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// CodeGenerator interface
	public CodeFragment[] getCode(OclTree tree) {
		theTree = tree;
		tree.apply(this);
		return (CodeFragment[])fragments.toArray(new CodeFragment[fragments.size()]);
	}
}