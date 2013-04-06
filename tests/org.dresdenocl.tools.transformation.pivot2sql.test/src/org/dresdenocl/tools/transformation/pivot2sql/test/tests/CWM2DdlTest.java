package org.dresdenocl.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.TransformationFactory;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.ModelAccessException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.dresdenocl.tools.transformation.pivot2sql.impl.SchemaStringMap;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.ModelChecker;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.TestPerformer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLSimpleType;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.impl.RelationalPackageImpl;

public class CWM2DdlTest {

	private static RelationalPackage rp;

	private Schema schema;
	
	private Catalog catalog;

	@BeforeClass
	public static void setUp_class() {

		rp = RelationalPackageImpl.init();
	}

	@Before
	public void setUp() {
		catalog = (Catalog) rp.getEFactoryInstance().create(rp.getCatalog());
		createSchema("test");
		
	}
	
	private void createSchema(String schemaname) {
		schema = (Schema) rp.getEFactoryInstance().create(rp.getSchema());
		schema.setName(schemaname);
		schema.setNamespace(catalog);
	}

	private Table createTable(String tablename) {

		Table table = (Table) rp.getEFactoryInstance().create(rp.getTable());
		table.setName(tablename);
		table.setNamespace(schema);
		return table;
	}

	private PrimaryKey createPrimaryKey(String pkname, Table table) {

		PrimaryKey pk =
				(PrimaryKey) rp.getEFactoryInstance().create(rp.getPrimaryKey());
		pk.setName(pkname);
		pk.setNamespace(table);
		Column c = create_Column(pkname,table,"VARCHAR(255)");
		pk.getFeature().add(c);
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
		column.setOwner(table);
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
		sql = "CREATE TABLE T_Person (\nPK_Person VARCHAR(255)\n);\n\nALTER TABLE T_Person ADD CONSTRAINT PK_Person\nPRIMARY KEY (PK_Person);";
		ModelChecker.sameDdl(sql, exceptionCWMDdl(catalog).substring(2));
	}

	private void testProperty(String cwm, String ddl) {

		String sql;
		Table table = createTable("T_Person");
		create_Column("prop", table, cwm);
		sql = "CREATE TABLE T_Person (\nprop " + ddl + "\n);";
		ModelChecker.sameDdl(sql, exceptionCWMDdl(catalog).substring(2));
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
	public void testMultipleSchema() {

		String sql;
		Table table = createTable("T_Person");
		createPrimaryKey("PK_Person", table);
		createSchema("test2");
		table = createTable("T_Person1");
		createPrimaryKey("PK_Person1", table);
		sql = "CREATE TABLE test.T_Person (\nPK_Person VARCHAR(255)\n);\n\nALTER TABLE test.T_Person ADD CONSTRAINT PK_Person\nPRIMARY KEY (PK_Person);";
		sql += "\n\nCREATE TABLE test2.T_Person1 (\nPK_Person1 VARCHAR(255)\n);\n\nALTER TABLE test2.T_Person1 ADD CONSTRAINT PK_Person1\nPRIMARY KEY (PK_Person1);";
		ModelChecker.sameDdl(sql, exceptionCWMDdl(catalog).substring(2));

	}
	
	protected String exceptionCWMDdl(Catalog catalog) {

		try {
			return generateCWMDdl(catalog);
		} catch (Exception e) {
			e.printStackTrace();
			fail("The ddl can't generate");
			return "";
		}
	}

	protected String generateCWMDdl(Catalog catalog) throws ModelAccessException,
			InvalidModelException, TransformationException {

		ITransformation<Catalog, IOcl2DeclSettings, SchemaStringMap> cwm2Ddl =
				TransformationFactory.getInstance().getTransformation("Cwm2DdlImpl",
						Catalog.class, SchemaStringMap.class, IOcl2DeclSettings.class, "CWM", "DDL");
		cwm2Ddl.setParameterIN(catalog);
		IOcl2DeclSettings oclSettings = TestPerformer.getSettings();
		cwm2Ddl.setSettings(oclSettings);
		oclSettings.setSchemaUsing(true);
		cwm2Ddl.invoke();
		return cwm2Ddl.getResult().toFullString();
	}
}
