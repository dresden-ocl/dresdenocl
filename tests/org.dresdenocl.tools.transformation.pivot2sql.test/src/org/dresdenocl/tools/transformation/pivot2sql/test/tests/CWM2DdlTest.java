package org.dresdenocl.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLSimpleType;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.impl.RelationalPackageImpl;

import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.TransformationFactory;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.ModelAccessException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.ModelChecker;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.TestPerformer;

public class CWM2DdlTest {

	private static RelationalPackage rp;

	private Schema schema;

	@BeforeClass
	public static void setUp_class() {

		rp = RelationalPackageImpl.init();
	}

	@Before
	public void setUp() {

		schema = (Schema) rp.getEFactoryInstance().create(rp.getSchema());
	}

	private Table createTable(String tablename) {

		Table table = (Table) rp.getEFactoryInstance().create(rp.getTable());
		table.setName(tablename);
		schema.getOwnedElement().add(table);
		return table;
	}

	private PrimaryKey createPrimaryKey(String pkname, Table table) {

		PrimaryKey pk =
				(PrimaryKey) rp.getEFactoryInstance().create(rp.getPrimaryKey());
		pk.setName(pkname);
		table.getOwnedElement().add(pk);
		pk.getFeature().add(create_Column(pkname));
		return pk;
	}

	private SQLSimpleType create_SQLSimpleType(String name) {

		SQLSimpleType dataType =
				(SQLSimpleType) rp.getEFactoryInstance().create(rp.getSQLSimpleType());
		dataType.setName(name);
		return dataType;
	}

	private Column create_Column(String columnName) {

		Column column = (Column) rp.getEFactoryInstance().create(rp.getColumn());
		column.setName(columnName);
		return column;
	}

	private Column create_Column(String columnName, Table table, String type) {

		Column column = create_Column(columnName);
		table.getOwnedElement().add(column);
		column.setType(create_SQLSimpleType(type));
		return column;
	}

	/**
	 * 
	 * <p>
	 * Checks if a class mapped correctly.
	 * </p>
	 */
	@Test
	public void testClass() {

		String sql;
		Table table = createTable("T_Person");
		createPrimaryKey("PK_Person", table);
		sql = "CREATE TABLE T_Person (\nPK_Person VARCHAR(255) PRIMARY KEY\n);";
		ModelChecker.sameDdl(sql, exceptionCWMDdl(schema).substring(2));
	}

	private void testProperty(String cwm, String ddl) {

		String sql;
		Table table = createTable("T_Person");
		create_Column("prop", table, cwm);
		sql = "CREATE TABLE T_Person (\nprop " + ddl + "\n);";
		ModelChecker.sameDdl(sql, exceptionCWMDdl(schema).substring(2));
	}

	@Test
	public void testProperty_String() {

		testProperty("String", "VARCHAR(255)");
	}

	@Test
	public void testProperty_Integer() {

		testProperty("Integer", "INT");
	}

	@Test
	public void testProperty_Long() {

		testProperty("Long", "BIGINT");
	}

	@Test
	public void testProperty_Float() {

		testProperty("Float", "FLOAT");
	}

	@Test
	public void testProperty_Double() {

		testProperty("Double", "DOUBLE");
	}

	@Test
	public void testProperty_Boolean() {

		testProperty("Boolean", "NUMBER(1)");
	}

	@Test
	public void testProperty_Short() {

		testProperty("Short", "INT");
	}

	@Test
	public void testProperty_Char() {

		testProperty("Char", "CHAR");
	}

	@Test
	public void testProperty_Date() {

		testProperty("Date", "DATE");
	}

	@Test
	public void testProperty_Other() {

		testProperty("xxer", "VARCHAR(255)");
	}

	@Test
	public void testRelation1to1() {

		// TODO Auto-generated method stub

	}

	@Test
	public void testRelation1toN() {

		// TODO Auto-generated method stub

	}

	protected String exceptionCWMDdl(Schema schema) {

		try {
			return generateCWMDdl(schema);
		} catch (Exception e) {
			e.printStackTrace();
			fail("The ddl can't generate");
		}
		return "";
	}

	protected String generateCWMDdl(Schema schema) throws ModelAccessException,
			InvalidModelException, TransformationException {

		ITransformation<Schema, IOcl2DeclSettings, String> cwm2Ddl =
				TransformationFactory.getInstance().getTransformation("Cwm2DdlImpl",
						Schema.class, String.class, IOcl2DeclSettings.class, "CWM", "DDL");
		cwm2Ddl.setParameterIN(schema);
		IOcl2DeclSettings oclSettings = TestPerformer.getSettings();
		cwm2Ddl.setSettings(oclSettings);
		cwm2Ddl.invoke();
		return cwm2Ddl.getResult();
	}
}
