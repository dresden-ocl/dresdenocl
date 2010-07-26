package tudresden.ocl20.testautomation.tools;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Stores information about a single statement
 * 
 * @TODO: the statementdefinition should also contain its type like
 *        INV/PRE/POST/DEF etc. in order to avoid external type handling which
 *        is currently done when parsing the statements
 */
public class StatementDefinition {

	// @ package name as used in ocl (e.g.
	// tudresden::ocl20::benchmark::testdata::b1)
	protected String packName;

	// @ full ocl specification
	protected String specification;

	// @ name (which is inclued in the specification)
	protected String name;

	// @ buffer for qualified name --> call getQualifiedName() to retrieve it
	protected String qualifiedName;

	// @ buffer for the namespace which can be used to find a constraint
	protected String namespace;

	// @ buffer for the nested namespaces in a list
	protected List<String> namespaceList;

/**
 * Instantiates a new statement definition.
 * 
 * @param pack
 * @param name
 * @param spec
 */
public StatementDefinition(String pack, String name, String spec) {

	this.packName = pack;
	this.specification = spec.replaceAll("\n", "\n\t");

	this.name = name;

}

/**
 * Gets the full statement.
 * 
 * @return the full statement
 */
public String getStatement() {

	return "package " + this.packName + "\n" + this.specification
			+ "\nendpackage";
}

/**
 * Gets the qualified name.
 * 
 * @return the qualified name
 */
public String getQualifiedName() {

	if (this.qualifiedName == null) {
		this.qualifiedName = "root::" + this.packName + "::" + this.name;
	}
	return this.qualifiedName;
}

/**
 * Gets the namespace.
 * 
 * @return the namespace
 */
public String getNamespace() {

	if (this.namespace == null) {
		this.namespace = "root::" + this.packName;
	}
	return this.namespace;
}

/**
 * Gets the namespace list.
 * 
 * @return the namespace list
 */
public List<String> getNamespaceList() {

	if (this.namespaceList == null) {
		String ns = this.getNamespace();
		String[] nsArray = ns.split("::");
		this.namespaceList = new ArrayList<String>(nsArray.length);
		for (String item : nsArray) {
			this.namespaceList.add(item);
		}
	}

	return this.namespaceList;
}

/**
 * Returns the (non qualified) name
 * 
 */
public String getName() {

	return this.name;
}

/*
 * (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
public boolean equals(Object rhs) {

	if (rhs instanceof StatementDefinition) {
		StatementDefinition rhsStmt = (StatementDefinition) rhs;
		return this.getQualifiedName().equals(rhsStmt.getQualifiedName());
	}
	else if (rhs instanceof String) {
		return this.getQualifiedName().equals(rhs.toString());
	}

	return false;
}

/**
 * Checks if this statement is the source of the passed constraint. This
 * assumes that statement names are not used twice as only the names are
 * compared.
 * 
 * @param rhs
 * 
 * @return true, if is source of
 */
public boolean isSourceOf(Constraint rhs) {

	return this.getQualifiedName().equals(rhs.getQualifiedName());
}

}
