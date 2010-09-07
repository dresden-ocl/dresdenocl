package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.View;

import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.Guide;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedClass;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;


public class ModelChecker {
			
		public static void checkCWM(Schema schema, List<String> tables,
				List<String> views,
				Map<String, List<Tuple<String, String>>> table2properties,
				Map<String, String> table2PrimaryKey,
				Map<String, List<String>> table2ForeignKey,
				Map<String, String> view2queryexpression) {
			
			checkSchema1(schema,tables,views,table2properties,table2PrimaryKey,table2ForeignKey,view2queryexpression);
			checkSchema2(schema,tables,views,table2properties,table2PrimaryKey,table2ForeignKey,view2queryexpression);
		}
		
		/**
		 * Check is no extra element generated.
		 */
		private static void checkSchema1(Schema schema, List<String> tables,
				List<String> views,
				Map<String, List<Tuple<String, String>>> table2properties,
				Map<String, String> table2PrimaryKey,
				Map<String, List<String>> table2ForeignKey,
				Map<String, String> view2queryexpression) {
			for (ModelElement me : schema.getOwnedElement()) {
				if (me instanceof Table) {
					if (tables.contains(me.getName())) {
						Table table = (Table)me;
						for (ModelElement m : table.getOwnedElement()) {
							if (m instanceof PrimaryKey) {
								assertNotNull("extra primary key "+m.getName() + " in table "+table.getName(),table2PrimaryKey.get(table.getName()));
								assertTrue("extra primary key "+m.getName() + " in table "+table.getName(),table2PrimaryKey.get(table.getName()).equals(m.getName()));
							} else if (m instanceof ForeignKey) {
								List<String> foreignKeys = table2ForeignKey.get(table.getName());
								assertNotNull("extra foreign key "+m.getName() + " in table "+table.getName(),foreignKeys);
								boolean check = false;
								for (String fk : foreignKeys) {
									if (fk.equals(m.getName())) {
										check = true;
									}
								}
								assertTrue("extra foreign key "+m.getName() + " in table "+table.getName(),check);
							} else if (m instanceof Column) {
								List<Tuple<String, String>> tuples = table2properties.get(table.getName());
								boolean check = false;
								for (Tuple<String, String> t : tuples) {
									if (t.getElem1().equals(m.getName())) {
										assertTrue("Not the same datatype of "+m.getName()+ " in table "+table.getName(),t.getElem2().equals(((Column)m).getType().getName())) ;
										check = true;
										break;
									} 
								}
								assertTrue("the column "+m.getName()+" isn't a part of table" +table.getName(),check);
							} else {
								fail("the element "+m.getName()+" isn't a part of table"+table.getName());
							}
						}
					} else {
						fail("the element "+me.getName()+" isn't a part of schema");
					}
				} else if (me instanceof View) {
					View view = (View)me;
					if (views.contains(me.getName())) {
						if (view.getQueryExpression() != null) {
							assertNotNull("extra query expression in table "+view.getName(),view2queryexpression.get(view.getName()));
							String actual = view.getQueryExpression().getBody();
							String expected = view2queryexpression.get(view.getName());
							String message = "No correct queryexpression in "+view.getName()+"\n";
							message += "expected: "+expected+"\n";
							message += "actual: "+actual;
							assertTrue(message,expected.equals(actual));
						}	
					} else {
						fail("the element "+me.getName()+" isn't a part of schema");
					}
				} else {
					fail("the model element isn't table or view");
				}
			}
	
			
		}

		private static List<ModelElement> findModelElement(Namespace namespace,String name) {
				List<ModelElement> mes = new ArrayList<ModelElement>();
				for (ModelElement me : namespace.getOwnedElement()) {
					if (me.getName().equals(name)) {
						mes.add(me);
					}
				}
				return mes;
		}
		
		/**
		 * Check is all elements generated.
		 */
		private static void checkSchema2(Schema schema, List<String> tables,
				List<String> views,
				Map<String, List<Tuple<String, String>>> table2properties,
				Map<String, String> table2PrimaryKey,
				Map<String, List<String>> table2ForeignKey,
				Map<String, String> view2queryexpression) {
			for(String table : tables) {
				List<ModelElement> mes = findModelElement(schema,table);
				assertTrue("No distinct table "+table+" found",mes.size() == 1);
				assertTrue("No table "+table+" found",(mes.get(0) instanceof Table));
				Table t = (Table)mes.get(0);
				if (table2PrimaryKey.containsKey(table)) {
					mes = findModelElement(t,table2PrimaryKey.get(table));
					if (mes.size() == 1) {
						assertTrue("No primary key "+table2PrimaryKey.get(table)+" in table "+table+" found",(mes.get(0) instanceof PrimaryKey));		
					} else if (mes.size() == 2)  {
						ModelElement me1 = mes.get(0);
						ModelElement me2 = mes.get(1);
						assertTrue("foreign key and primary key not same name",me1.getName().equals(me2.getName()));
						if (me2 instanceof ForeignKey) {
							assertTrue("No primary key "+table2PrimaryKey.get(table)+" in table "+table+" found",(me1 instanceof PrimaryKey));		
						} else {
							assertTrue("No primary key "+table2PrimaryKey.get(table)+" in table "+table+" found",(me2 instanceof PrimaryKey));								
						}
					} else {
						fail("No distinct primary key "+table2PrimaryKey.get(table)+" in table "+table+" found");
					}
					
				}
				if (table2ForeignKey.containsKey(table)) {
					for (String foreignKey : table2ForeignKey.get(table)) {
						mes = findModelElement(t,foreignKey);
						if (mes.size() == 1) {
							assertTrue("No foreign key "+foreignKey+" in table "+table+" found",(mes.get(0) instanceof ForeignKey));
						} else if (mes.size() == 2)  {
							ModelElement me1 = mes.get(0);
							ModelElement me2 = mes.get(1);
							assertTrue("foreign key and primary key not same name",me1.getName().equals(me2.getName()));
							if (me2 instanceof PrimaryKey) {
								assertTrue("No foreign key "+foreignKey+" in table "+table+" found",(me1 instanceof ForeignKey));		
							} else {
								assertTrue("No foreign key "+foreignKey+" in table "+table+" found",(me2 instanceof ForeignKey));								
							}
						} else {
							fail("No distinct foreign key "+table2PrimaryKey.get(table)+" in table "+table+" found");
						}					
					}
				}
				if (table2properties.containsKey(table)) {
					for (Tuple<String, String> property : table2properties.get(table)) {
						mes = findModelElement(t,property.getElem1());
						assertTrue("No distinct column "+property.getElem1()+" in table "+table+" found",mes.size() == 1);
						assertTrue("No column "+property.getElem1()+" in table "+table+" found",(mes.get(0) instanceof Column));
						String datatype = ((Column)mes.get(0)).getType().getName();
						assertTrue("No corrrect datatype of "+property.getElem1()+" in table "+table+" found expected:"+property.getElem2()+",actual:"+datatype,datatype.equals(property.getElem2()));
					}
				}
			}
			for(String view : views) {
				List<ModelElement> mes = findModelElement(schema,view);
				assertTrue("No distinct view "+view+" found",mes.size() == 1);
				assertTrue("No view "+view+" found",(mes.get(0) instanceof View));
				View v = (View)mes.get(0);
				if (view2queryexpression.containsKey(view)) {
					String actual = v.getQueryExpression().getBody();
					String expected = view2queryexpression.get(view);
					String message = "No correct queryexpression in "+view+"\n";
					message += "expected: "+expected+"\n";
					message += "actual: "+actual;
					assertTrue(message,expected.equals(actual));
				}
			}
		}

		public static void sameDdl(String expected, String actual) {
			List<String> expectedList = Arrays.asList(expected.split("\n\n"));
			List<String> actualList = Arrays.asList(actual.split("\n\n"));
			for (String s : expectedList) {
				assertTrue("expected string "+s+" not in \n"+actual,actualList.contains(s));
			}
			for (String s : actualList) {
				assertTrue("not expected string "+s+"not in actual",expectedList.contains(s));
			}
		}
		
		public static boolean sameMappedModel(IMappedModel mm1, IMappedModel mm2 ) {
			return false;
		}
		
		public static void checkMappedModel(IMappedModel mm, List<String> classes, Map<String,List<String>> class2attributes, Map<String,List<String>> class2assEnds) {
			// The mapped Model should not contain the mapped Class 'falsePositive'
			assertFalse("mapped model has class falsePositive",mm.isClass("falsePositive"));
			// It also should not return this Class
			try {
				mm.getClass("FalsePositive");
				// This should not happen.
				fail("IllegalArgumentException expected.");
			} 
			catch (IllegalArgumentException e){
				// Expected Exception
			}		
			
			// Check the Classes names
			for (String classname : classes) {
				// Each name should represent a Class
				assertTrue("the class with the name "+classname+" isn't represent", mm.isClass(classname));
				// Each name should return a Class
				IMappedClass mc = mm.getClass(classname);
				assertNotNull("mapped class of "+classname+" is null.",mc);
				
				// The returned Class should have the given name
				assertEquals("the name of mapped class is not equal ("+classname+","+mc.getName()+")",mc.getName(), classname);
				
				// The class should not have the atrribute 'falsePositive' 
				List<String> attributes = class2attributes.get(classname);
				assertFalse("mapped class "+classname+" has attribute falsePositive", mc.isAttribute("falsePositve"));

				// Each Class should have a Guide Object
				Guide cGuide = mc.getClassGuide();
				assertNotNull("the class "+classname +" has no classguide",cGuide);
								
				// The Class should not have the Attribute Guide 'falsePositive'
				try {
					mc.getAttributeGuide("falsePositive");	
					// This should not happen
					fail("Illegal ArgumentException expected");
				} 
				catch (IllegalArgumentException e) {
					// Expected Exception
				}
				
				// Check if the mapped Class has all attributes specified in the HashMap
				if (attributes != null) {
					for (String attribute : attributes) {
						assertTrue("the class "+classname+" has no attribute "+attribute,mc.isAttribute(attribute));
						// Check if each Attribute has its Attribute Guide
						Guide attG = mc.getAttributeGuide(attribute);
						assertNotNull("the attribute guide of "+attribute+" of class "+classname+" is missed",attG);
					}
				}
				
				// The mapped Class should not have the AssociationEnd 'falsePositive'
				assertFalse("the mapped class "+classname+" has an association end falsePositive",mc.isAssociationEnd("falsePositive"));
				try {
					// This Attribute should also not have an AssociationEndGuide
					mc.getAssociationEndGuide("falsePositive");	
					// This should not happen.
					fail("Illegal ArgumentException expected");
				} 
				catch (IllegalArgumentException e) {
					// Expected Exception
				}
				
				// Check if the mapped Class has all AssociationEnds specified in the HashMap
				List<String> assEnds = class2assEnds.get(classname);
				if (assEnds != null) {
					for (String assEnd : assEnds) {
						assertTrue("the mapped class "+classname+" has no association end "+assEnd,mc.isAssociationEnd(assEnd));
						// Check if each AssociationEnd has its AssociationEndGuide 
						Guide assEG = mc.getAssociationEndGuide(assEnd);
						assertNotNull("Miss the guide of the association end "+assEnd+" of class "+classname,assEG);
					}
				}
			}
		}
		
}
