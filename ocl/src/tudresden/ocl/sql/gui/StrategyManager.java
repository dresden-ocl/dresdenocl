/*
Copyright (C) 2002 Andrea Kling

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

*/
package tudresden.ocl.sql.gui;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import java.util.List;
import tudresden.ocl.sql.*;
import tudresden.ocl.sql.orstrategy.*;

/**
 * a class managing all object-relational Mapping strategies
 * every mapping strategy must have a StrategyCreator providing
 * a description and a View for input of necessary data.
 * StrategyCreators are arranged by StrategyTypes as defined
 * in tudresden.ocl.sql.ORMappingImpl
 *
 * @see StrategyCreator
 * @see tudresden.ocl.sql.ORMappingImpl
 * @author Andrea Kling
 * */
public class StrategyManager{

  private static StrategyManager myInstance;
  private Hashtable strategies;         // ORMappingImpl.DESCRIPTION -> Set of StrategyCreator
                                        //(public Strategy create(JComponent view))
                                        //   public JComponent  getCreateView()
                                        // public String strategyDescription()

  private StrategyManager(){
    strategies = new Hashtable();
    Vector strategyList = new Vector();
    DefaultStrategyCreator creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.CLASS);
    creator.setStrategyDescription("one class to one table");
    creator.setStrategy(SimpleClassToTableMapping.getInstance());
    strategyList.add(creator);
    strategyList.add(ClassSplitterCreator.getInstance());
    // insert new class mappers here
    strategies.put(ORMappingImpl.CLASS, strategyList);

    strategyList = new Vector();
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.PK);
    creator.setStrategyDescription("additional object identity column");
    creator.setStrategy(OIDKeyMapping.getInstance());
    strategyList.add(creator);
    strategyList.add(KeyChooserCreator.getInstance());
    // insert new key mappers here
    strategies.put(ORMappingImpl.PK, strategyList);

    strategyList = new Vector();
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.INHERIT);
    creator.setStrategyDescription("split subclass into class and superclass tables");
    creator.setStrategy(InheritanceMapping.getInstance());
    strategyList.add(creator);
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.INHERIT);
    creator.setStrategyDescription("all subclasses in one superclass table");
    creator.setStrategy(OneTableInheritanceMapping.getInstance());
    strategyList.add(creator);
    // insert new inheritance mappers here
    strategies.put(ORMappingImpl.INHERIT, strategyList);

    strategyList = new Vector();
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.ONE_ONE);
    creator.setStrategyDescription("bury foreign key in one participating table (simple)");
    creator.setStrategy(StandardAssociationMapping.getInstance());
    strategyList.add(creator);
    // insert new one to one association mappers here
    strategies.put(ORMappingImpl.ONE_ONE, strategyList);

    strategyList = new Vector();
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.ONE_MANY);
    creator.setStrategyDescription("bury foreign key in table on 'many' side (simple)");
    creator.setStrategy(StandardAssociationMapping.getInstance());
    strategyList.add(creator);
    // insert new one to many association mappers here
    strategies.put(ORMappingImpl.ONE_MANY, strategyList);

    strategyList = new Vector();
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.MANY_MANY);
    creator.setStrategyDescription("use association table (simple)");
    creator.setStrategy(StandardAssociationMapping.getInstance());
    strategyList.add(creator);
    // insert new many to many association mappers here
    strategies.put(ORMappingImpl.MANY_MANY, strategyList);

    strategyList = new Vector();
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.ORDERED);
    creator.setStrategyDescription("add sequencing column (integer)");
    creator.setStrategy(OrderColumnMapping.getInstance());
    strategyList.add(creator);
    creator = new DefaultStrategyCreator();
    creator.setStrategyType(ORMappingImpl.ORDERED);
    creator.setStrategyDescription("doing nothing");
    creator.setStrategy(NaturalOrderMapping.getInstance());
    strategyList.add(creator);
    // insert new ordering mappers here
    strategies.put(ORMappingImpl.ORDERED, strategyList);
    strategyList = new Vector();
    strategyList.add(CollectionCreator.getInstance());
    List l = TypeManager.getInstance().getTypes();
    for(int i=0; i<l.size(); i++)
      strategyList.add(new SimpleTypeMappingCreator((String) l.get(i)));
    // insert new datatype mappers here
    strategies.put(ORMappingImpl.TYPE, strategyList);
    strategyList = new Vector();
    strategyList.add(new OracleSQLBuilder());
    strategyList.add(new SybaseSQLBuilder());
    // insert additional SQLBuilder here
    strategies.put("DBMS", strategyList);

  }

  public static StrategyManager getInstance(){
    if ( myInstance == null ){
      myInstance = new StrategyManager();
    }
    return myInstance;
  }

 /**
  * @param a StrategyType, see ORMappingImpl for types
  * @return a List of StrategyCreator according to Type
  *
  * @see tudresden.ocl.sql.ORMappingImpl
  * */
  public List getStrategies(String type){  // see ORMappingImpl.StrategyTypes
    return (List) strategies.get(type);
  }

  /**
   * @return a List of available SQLBuilders
   * @see tudresden.ocl.sql.SQLBuilder
   * */
  public List getSQLBuilders(){
    return (List) strategies.get("DBMS");
  }
}
