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
package tudresden.ocl.sql.orstrategy;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.Hashtable;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;
import tudresden.ocl.codegen.decl.Guide;

  /**
   * This class handles all associations that are not handled elsewhere.
   * It maps 3 basic kinds of Association
   * <UL>
   * <LI> 1 : 1 and  0..1 : 1 <BR>
   * These associations are mapped to a buried foreign key in one
   * of the tables. Navigation directions are taken into account if
   * only one way is possible.<BR>
   * <LI> 1 : N and 0..1 : N<BR>
   * Mapped as buried foreign key in the table representing the
   * N-multiplicity. If 0..1-multiplicity appears on corresponding
   * association side, the foreign key may contain null-values<BR>
   * <LI> N : M <BR>
   * Mapped, using an association table, which will appear in
   * associatioTables
   * */
public class StandardAssociationMapping implements AssociationStrategy{

  private static StandardAssociationMapping myInstance;
  private Map classToTables;
  private Map associationTables;
  private Map navigationGuides;
  private Map classViews;


  private StandardAssociationMapping(){}

  public static StandardAssociationMapping getInstance(){
    if(myInstance == null){
      myInstance = new StandardAssociationMapping();
    }
    return myInstance;
  }

/**
 * @param association the association to map
 * @param classToTables contains a List of Table for every MClassifier mapped
 * @param classViews contains an ObjectView for every MClassifier
 * @param associationTables a Map containing Association-Tables for n:m or 1:n MAssociations
 * @param navigationGuides String classifier -> (String roleName -> Guide associationEnd)
 */
  public synchronized void map(MAssociation association, Map classToTables,
  Map associationTables, Map navigationGuides, Map classViews){
    this.classToTables = classToTables;
    this.associationTables = associationTables;
    this.navigationGuides = navigationGuides;
    this.classViews = classViews;
    MAssociationEnd one = association.getConnection(0);
    MAssociationEnd two = association.getConnection(1);
    int upperOne = one.getMultiplicity().getUpper();
    int upperTwo = two.getMultiplicity().getUpper();
    if(upperOne == 1 && upperTwo == 1){
      mapOneToOne(one, two);
    }else if(upperOne == 1 && (upperTwo > 1 || upperTwo < 0)){
        mapOneToMany(one, two);
      }else if((upperOne > 1 || upperOne < 0) && upperTwo == 1){
        mapOneToMany(two, one);
      }else
        mapManyToMany(association);
  }

  private void mapOneToOne(MAssociationEnd one, MAssociationEnd two){
    Table t1 = (Table) ((List) classToTables.get(one.getType())).get(0);
    Table t2 = (Table) ((List) classToTables.get(two.getType())).get(0);
    ObjectView ov1 = (ObjectView) classViews.get(one.getType());
    ObjectView ov2 = (ObjectView) classViews.get(two.getType());
    if(one.isNavigable()){
      String[] t1PK = t1.getPrimaryKeyColumns();
      String[] t2FK = new String[t1PK.length];
      for ( int i=0; i<t1PK.length; i++) {
        t2.addColumn(one.getName(), t1.getColumnType(t1PK[i]),
        one.getName().toUpperCase()+"_"+t1PK[i], false);
        ov2.addColumn(one.getName(),
        one.getName().toUpperCase()+"_"+t1PK[i], t2);
        t2FK[i] = one.getName().toUpperCase()+"_"+t1PK[i];
        if(one.getMultiplicity().getLower() < 1)
          t2.setOptional(one.getName().toUpperCase()+"_"+t1PK[i]);
      }
      t2.setForeignKey(t2FK, t1, t1PK);
      //Vector guides = new Vector();
      Guide g2 = new Guide(true);
      g2.add("*", ov1.getName(), ov1.getPrimaryKeyRepresentation());
      StringBuffer str = new StringBuffer();
      for(int i=0; i<t2FK.length; i++)
        str.append(", "+t2FK[i]);
      g2.add(str.substring(2), ov2.getName(), ov2.getPrimaryKeyRepresentation());
      Map twoGuides = (Map) navigationGuides.get(two.getType().getName());
      if(twoGuides == null){
        twoGuides = new Hashtable();
        navigationGuides.put(two.getType().getName(), twoGuides);
      }
      twoGuides.put(one.getName(), g2);
      if(two.isNavigable()){
        String[] t2PK = t2.getPrimaryKeyColumns();
        for(int i=0; i<t2PK.length; i++){
          ov1.addColumn(two.getName(),
            two.getName().toUpperCase()+"_"+t2PK[i], t2);
          ov1.addConnection(t1PK[i], t1, t2FK[i], t2);
        }
        Guide g1 = new Guide(true);
        g1.add("*", ov2.getName(), ov2.getPrimaryKeyRepresentation());
        str = new StringBuffer();
        for(int i=0; i<t2PK.length; i++)
          str.append(", "+two.getName().toUpperCase()+"_"+t2PK[i]);
        g1.add(str.substring(2), ov1.getName(), ov1.getPrimaryKeyRepresentation());
        Map oneGuides = (Map) navigationGuides.get(one.getType().getName());
        if(oneGuides == null){
          oneGuides = new Hashtable();
          navigationGuides.put(one.getType().getName(), oneGuides);
        }
        oneGuides.put(two.getName(), g1);
      }
    }else{
      String[] t2PK = t2.getPrimaryKeyColumns();
      String[] t1FK = new String[t2PK.length];
      for ( int i=0; i<t2PK.length; i++) {
        t1.addColumn(two.getName()+"_"+t2PK[i], t2.getColumnType(t2PK[i]),
        two.getName().toUpperCase()+"_"+t2PK[i], false);
        ov1.addColumn(two.getName(),
        two.getName().toUpperCase()+"_"+t2PK[i], t1);
        t1FK[i] = two.getName().toUpperCase()+"_"+t2PK[i];
        if(two.getMultiplicity().getLower() < 1)
          t1.setOptional(two.getName().toUpperCase()+"_"+t2PK[i]);
      }
      t1.setForeignKey(t1FK, t2, t2PK);
      Guide g1 = new Guide(true);
      g1.add("*", ov2.getName(), ov2.getPrimaryKeyRepresentation());
      StringBuffer str = new StringBuffer();
      for(int i=0; i<t1FK.length; i++)
        str.append(", "+t1FK[i]);
      g1.add(str.substring(2), ov1.getName(), ov1.getPrimaryKeyRepresentation());
      Map oneGuides = (Map) navigationGuides.get(one.getType().getName());
      if(oneGuides == null){
        oneGuides = new Hashtable();
        navigationGuides.put(one.getType().getName(), oneGuides);
      }
      oneGuides.put(two.getName(), g1);
    }
  }


  private void mapOneToMany(MAssociationEnd one, MAssociationEnd two){
    Table t1 = (Table) ((List) classToTables.get(one.getType())).get(0);
    Table t2 = (Table) ((List) classToTables.get(two.getType())).get(0);
    ObjectView ov1 = (ObjectView) classViews.get(one.getType());
    ObjectView ov2 = (ObjectView) classViews.get(two.getType());
    String[] t1PK = t1.getPrimaryKeyColumns();
    String[] t2FK = new String[t1PK.length];
    boolean optional = false;
    if(one.getMultiplicity().getLower() < 1)
      optional = true;
    for(int i=0; i<t1PK.length; i++){
      t2.addColumn(one.getName(), t1.getColumnType(t1PK[i]),
      one.getName().toUpperCase()+"_"+t1PK[i], false);
      if(one.isNavigable())
        ov2.addColumn(one.getName(),
        one.getName().toUpperCase()+"_"+t1PK[i], t2);
      t2FK[i] = one.getName().toUpperCase()+"_"+t1PK[i];
      if (optional)
        t2.setOptional(one.getName().toUpperCase()+"_"+t1PK[i]);
    }
    t2.setForeignKey(t2FK, t1, t1PK);
    if(one.isNavigable()){
      Guide g2 = new Guide(true);
      g2.add("*", ov1.getName() , ov1.getPrimaryKeyRepresentation());
      StringBuffer str = new StringBuffer();
      for(int i=0; i<t2FK.length; i++)
        str.append(", "+t2FK[i]);
      g2.add(str.substring(2), ov2.getName(), ov2.getPrimaryKeyRepresentation());
      Map twoGuides = (Map) navigationGuides.get(two.getType().getName());
      if(twoGuides == null){
        twoGuides = new Hashtable();
        navigationGuides.put(two.getType().getName(), twoGuides);
      }
      twoGuides.put(one.getName(), g2);
      if(two.isNavigable()){
        Guide g1 = new Guide(true);
        str = new StringBuffer();
        for(int i=0; i<t2FK.length; i++)
          str.append(", "+t2FK[i]);
        g1.add("*", ov2.getName(), str.substring(2));
        g1.add(ov1.getPrimaryKeyRepresentation(), ov1.getName(),
          ov1.getPrimaryKeyRepresentation());
        Map oneGuides = (Map) navigationGuides.get(one.getType().getName());
        if(oneGuides == null){
          oneGuides = new Hashtable();
          navigationGuides.put(one.getType().getName(), oneGuides);
        }
        oneGuides.put(two.getName(), g1);
      }
    }else{
      Guide g1 = new Guide(true);
      g1.add("*", ov2.getName(), ov2.getPrimaryKeyRepresentation());
      StringBuffer str = new StringBuffer();
      for(int i=0; i<t2FK.length; i++)
        str.append(", "+t2FK[i]);
      g1.add(t2.getPrimaryKeyRepresentation(), t2.getTableName(),
        str.substring(2));
      g1.add(ov1.getPrimaryKeyRepresentation(), ov1.getName(),
        ov1.getPrimaryKeyRepresentation());
      Map oneGuides = (Map) navigationGuides.get(one.getType().getName());
      if(oneGuides == null){
        oneGuides = new Hashtable();
        navigationGuides.put(one.getType().getName(), oneGuides);
      }
      oneGuides.put(two.getName(), g1);
    }
  }


  private void mapManyToMany(MAssociation association){
    MAssociationEnd one = association.getConnection(0);
    MAssociationEnd two = association.getConnection(1);
    Table t1 = (Table) ((List) classToTables.get(one.getType())).get(0);
    Table t2 = (Table) ((List) classToTables.get(two.getType())).get(0);
    ObjectView ov1 = (ObjectView) classViews.get(one.getType());
    ObjectView ov2 = (ObjectView) classViews.get(two.getType());
    String assTabName = "";
    if(association.getName() == null){
      assTabName = ""+one.getName().toUpperCase()+"_"+two.getName().toUpperCase();
    }else{
      assTabName=association.getName().toUpperCase();
    }
    Table assTab = new Table(assTabName);
    String[] t1PK = t1.getPrimaryKeyColumns();
    String[] t2PK = t2.getPrimaryKeyColumns();
    String[] ass1FK = new String[t1PK.length];
    String[] ass2FK = new String[t2PK.length];
    for(int i=0; i<t1PK.length; i++){
      assTab.addColumn(one.getName(), t1.getColumnType(t1PK[i]),
      one.getName().toUpperCase()+"_"+t1PK[i], true);
      ass1FK[i] = one.getName().toUpperCase()+"_"+t1PK[i];
    }
    assTab.setForeignKey(ass1FK, t1, t1PK);
    for(int i=0; i<t2PK.length; i++){
      assTab.addColumn(two.getName(), t2.getColumnType(t2PK[i]),
      two.getName().toUpperCase()+"_"+t2PK[i], true);
      ass2FK[i] = two.getName().toUpperCase()+"_"+t2PK[i];
    }
    assTab.setForeignKey(ass2FK, t2, t2PK);
    associationTables.put(association, assTab);
    if(one.isNavigable()){
      Guide g2 = new Guide(true);
      g2.add("*", ov1.getName(), ov1.getPrimaryKeyRepresentation());
      StringBuffer str1 = new StringBuffer();
      for(int i=0; i<ass1FK.length; i++)
        str1.append(", "+ass1FK[i]);
      StringBuffer str2 = new StringBuffer();
      for(int i=0; i<ass2FK.length; i++)
        str2.append(", "+ass2FK[i]);
      g2.add(str1.substring(2), assTab.getTableName(), str2.substring(2));
      g2.add(ov2.getPrimaryKeyRepresentation(), ov2.getName(),
        ov2.getPrimaryKeyRepresentation());
      Map twoGuides = (Map) navigationGuides.get(two.getType().getName());
      if(twoGuides == null){
        twoGuides = new Hashtable();
        navigationGuides.put(two.getType().getName(), twoGuides);
      }
      twoGuides.put(one.getName(), g2);
    }
    if(two.isNavigable()){
      Guide g1 = new Guide(true);
      g1.add("*", ov2.getName(), ov2.getPrimaryKeyRepresentation());
      StringBuffer str1 = new StringBuffer();
      for(int i=0; i<ass1FK.length; i++)
        str1.append(", "+ass1FK[i]);
      StringBuffer str2 = new StringBuffer();
      for(int i=0; i<ass2FK.length; i++)
        str2.append(", "+ass2FK[i]);
      g1.add(str2.substring(2), assTab.getTableName(), str1.substring(2));
      g1.add(ov1.getPrimaryKeyRepresentation(), ov1.getName(),
        ov1.getPrimaryKeyRepresentation());
      Map oneGuides = (Map) navigationGuides.get(one.getType().getName());
      if(oneGuides == null){
        oneGuides = new Hashtable();
        navigationGuides.put(one.getType().getName(), oneGuides);
      }
      oneGuides.put(two.getName(), g1);
    }

  }

  public Object clone(){
    return getInstance();
  }

  public String toString(){
    return "simple";
  }
}
