/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.workbench;

import tudresden.ocl20.*;
import tudresden.ocl20.oclscript.*;
import tudresden.ocl20.codegen.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;
import tudresden.ocl20.jmi.ocl.expressions.*;

import javax.jmi.model.ModelPackage;
import javax.jmi.reflect.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;

import tudresden.ocl20.lib.*;

import javax.swing.*;
import javax.swing.table.*;

/**
 * The model for the OCL Workbench.
 * @author  Stefan Ocke
 */
public class WorkbenchModel {
    
    private ModelPackage metamodel;
    private OclModel metamodelAsMofOcl;
    private String metamodelname = "<none>";
    
    
    private OclScriptRunner scriptRunner;
    
    private ModelManager mm = ModelManager.getInstance();
    
    private DefaultListModel metamodels = new DefaultListModel();
    private Set metamodelnames = new HashSet();
    
    private DefaultListModel models = new DefaultListModel();
    
    private DefaultListModel classifiers = new DefaultListModel();
    private DefaultListModel expressions = new DefaultListModel();
    JmiClassCodeGenerator ccg = null;
    Class evalClass = null;
    
    private static class ResultsTableModel extends AbstractTableModel {
        private final String [] columnNames = new String []{"Expression in Ocl", "Result"};
        
        private List names = new ArrayList();
        private List values = new ArrayList();
        
        public String getColumnName(int col) {
            return columnNames[col].toString();
        }
        public int getRowCount() { return names.size(); }
        public int getColumnCount() { return 2; }
        
        public Object getValueAt(int row, int col) {
            if(col==0){
                return names.get(row);
            } else {
                return values.get(row);
            }
            
        }
        
        public boolean isCellEditable(int row, int col){
            return false;
        }
        public void addRow(Object name, Object value) {
            names.add(name);
            values.add(value);
            int row = names.size()-1;
            fireTableRowsInserted(row, row);
        }
        
        public void clear(){
            names.clear();
            values.clear();
            fireTableDataChanged();
        }
    };
    
    
    private ResultsTableModel results = new ResultsTableModel();
    
    private ClassifierInfo selectedClassifier;
    //MetaModelInfo selectedMetaModel;
    private ModelInfo selectedModel;
    
    private static final String PRFX = "OCLWB__";
    private static final String METAMODELPRFX = "OCLWBMetamodel__";
    private static final String MODELPRFX = "OCLWBModel__";
    
    
    private static class ModelInfo{
        private RefPackage model;
        private String name;
        
        public ModelInfo(String name, RefPackage model){
            this.name=name;
            this.model=model;
        }
        
        public String toString(){
            return name;
        }
    }
    
    private static class MetaModelInfo{
        private ModelPackage metamodel;
        private OclModel metamodelAsMofOcl;
        private String name;
        
        public MetaModelInfo(String name, ModelPackage metamodel, OclModel metamodelAsMofOcl){
            this.name=name;
            this.metamodel=metamodel;
            this.metamodelAsMofOcl=metamodelAsMofOcl;
        }
        public String toString(){
            return name;
        }
    }
    
    private static class ClassifierInfo{
        private Classifier c;
        public ClassifierInfo(Classifier c){
            this.c=c;
        }
        public String toString(){
            return c.getPathNameA();
        }
    }
    
    private static class ExpressionInfo{
        private ExpressionInOcl eio;
        public ExpressionInfo(ExpressionInOcl eio){
            this.eio=eio;
        }
        public String toString(){
            Constraint con = eio.getConstraintA();
            if(con == null){
                return "<unknown kind>"; //later tbd: definition expressions etc
            } else {
                return con.getStereotypeNameA()+": "+con.getNameA();
            }
        }
        public void delete(){
            Constraint con = eio.getConstraintA();
            if(con != null){
                con.refDelete();
            } else {
                eio.refDelete();
            }
            
        }
    }
    
    /** Creates a new instance of WorkbenchModel */
    public WorkbenchModel() {
        updateMetamodels();
    }
    
    /**seek the Metamodels installed by the workbench by now*/
    private void updateMetamodels(){
        metamodels.clear();
        metamodelnames.clear();
        ModelPackage metamodel=null;
        OclModel metamodelAsMofOcl=null;
        try{
            Iterator it = OclModel.findOclModels(MetaModelConst.MOF14).iterator();
            while(it.hasNext()){
                metamodelAsMofOcl = (OclModel) it.next();
                String name = metamodelAsMofOcl.getName();
                if(name.startsWith(PRFX)){
                    name = name.substring(PRFX.length());
                    metamodel = mm.getMetaModel(METAMODELPRFX+name);
                }
                if (metamodel != null){
                    MetaModelInfo mmi = new MetaModelInfo(name, metamodel, metamodelAsMofOcl);
                    metamodels.addElement(mmi);
                    metamodelnames.add(name);
                } else {
                    System.out.println("Metamodel in repository is corrupt and will be removed: "+name);
                    metamodelAsMofOcl.delete();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void updateModels(){
        try{
            models.clear();
            if(this.metamodel!=null){
                Iterator it = mm.getModels(metamodel).iterator();
                while(it.hasNext()){
                    RefPackage model = (RefPackage) it.next();
                    String name = mm.getName(model);
                    if(name.startsWith(this.MODELPRFX)){
                        name = name.substring(MODELPRFX.length());
                        ModelInfo mi = new ModelInfo(name, model);
                        models.addElement(mi);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteExpression(int index){
        if(index>=0 && index<this.expressions.size()){
            ExpressionInfo ei = (ExpressionInfo) this.expressions.get(index);
            ei.delete();
            expressions.remove(index);
            
        }
    }
    
    public String getExpressionBody(int index){
        if(index<0 || index > expressions.size()){
            return "";
        }
        ExpressionInfo ei = (ExpressionInfo) expressions.get(index);
        String body = ei.eio.getBodyA();
        if(body == null){
            body="No body stated.";
        }
        return body;
    }
    
    public void loadModel(File file) throws Exception{
        if(this.metamodel != null){
//            for(int i = 0; i< 5; i++){
//                System.out.println(java.util.Calendar.getInstance().getTimeInMillis());
            String name = file.getName();
            int dotIndex = name.indexOf('.');
            name = this.MODELPRFX+name.substring(0,dotIndex);
            name = makeUniqueName(name, mm.getAllModelNames());
            
            mm.beginTrans(true);
            RefPackage model = mm.loadModel(metamodel, file.toURI().toString(), name);
            mm.endTrans(false);
            ModelInfo mi = new ModelInfo(name.substring(this.MODELPRFX.length()), model);
            models.addElement(mi);
//                System.out.println(java.util.Calendar.getInstance().getTimeInMillis());
//            }
        }
    }
    
    public void loadMetamodel(File file) throws Exception{
        ModelPackage metamodel=null;
        OclModel metamodelAsMofOcl=null;
        try{
            String name = file.getName();
            int dotIndex = name.indexOf('.');
            name = name.substring(0,dotIndex);
            name = makeUniqueName(name,this.metamodelnames);
            
            metamodel = mm.loadMetaModel(file.toURI().toString(), METAMODELPRFX+name);
            //load the metamodel as Instance of Mof-Ocl now
            metamodelAsMofOcl = new OclModel(MetaModelConst.MOF14, file.toURI().toString(), PRFX+name);
            
            MetaModelInfo mmi = new MetaModelInfo(name,metamodel,metamodelAsMofOcl);
            
            metamodels.addElement(mmi);
            metamodelnames.add(name);
            
            System.out.println("Metamodel succesfully loaded.");
        } catch (Exception e){
            //partial success? delete everything already openend.
            if(metamodel != null){
                mm.deleteMetaModel(metamodel);
            }
            if(metamodelAsMofOcl != null){
                metamodelAsMofOcl.delete();
            }
            throw e;
        }
    }
    
    private String makeUniqueName(String name, Collection names){
        return tudresden.ocl20.util.Naming.makeUniqueName(name, names);
    }
    
    
    public void runOclScript(File file) throws Exception{
        try{
            if(scriptRunner != null){
                scriptRunner.runScript(new FileInputStream(file));
                
            }
        } finally {
            updateClassifiers();
        }
        
    }
    
    public String getCode(){
        
        JmiClassCodeGenerator.SourceClass sc = ccg.getSourceClass(this.selectedClassifier.c);
        saveAndCompile(sc);
        try{
            //reload the class
            evalClass = this.loadEvalClass(selectedClassifier.c);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sc.srcCode;
    }
    
    private URL getEvalClassDirURL(){
        return ClassLoader.getSystemClassLoader().getResource("generatedOclEval/");
    }
    
    
    private void saveAndCompile(JmiClassCodeGenerator.SourceClass sc){
        try{
            java.net.URL dir =  getEvalClassDirURL();
            java.io.File file = new File(java.net.URLDecoder.decode(dir.getPath()));
            
            StringTokenizer st = new StringTokenizer(sc.pathname,".");
            String filename = null;
            while(st.hasMoreTokens()){
                String d = st.nextToken();
                if(st.hasMoreTokens()){
                    file = new File(file, d);
                } else {
                    filename = d;
                }
            }
            
            System.out.println(file);
            file.mkdirs();
            file = new File(file, filename+".java");
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sc.srcCode);
            bw.close();
            
            com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();
            javac.compile(new String [] {file.getAbsolutePath()});
            
            
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private Class loadEvalClass(Classifier classifier) throws ClassNotFoundException{
        URL[] urls = null;
        urls = new URL[]{this.getEvalClassDirURL()};
        
        // Create a new class loader with the directory
        ClassLoader cl = new URLClassLoader(urls);
        
        // Load in the class
        Class cls = cl.loadClass(ccg.getJavaPathname(classifier));
        
        return cls;
    }
    
    private void updateClassifiers(){
        
        classifiers.clear();
        
        if(metamodelAsMofOcl!=null){
            //look for classifiers that are context for an ExpressionInOcl
            //note that we use the ocl basis library...
            JmiOclFactory jof = JmiOclFactory.getInstance(metamodelAsMofOcl.getModel());
            JmiModelType jmt =  (JmiModelType) jof.getOclModelTypeFor("OCL::Expressions::ExpressionInOcl");
            
            Set visited = new HashSet();
            
            selectedClassifier=null;
            Iterator it = jmt.getRefClass().refAllOfType().iterator();
            while(it.hasNext()){
                ExpressionInOcl eio = (ExpressionInOcl) it.next();
                Classifier c = eio.getContextualClassifier();
                if(!visited.contains(c)){
                    visited.add(c);
                    classifiers.addElement(new ClassifierInfo(c));
                }
            }
        }
    }
    
    
    public String getSelectedClassifierName(){
        
        if(this.selectedClassifier == null){
            return "<none>";
        } else {
            return selectedClassifier.toString();
        }
    }
    
    public String getJavaClassName(){
        if(this.selectedClassifier == null){
            return "<none>";
        } else if(evalClass==null){
            String javaPathName = ccg.getJavaPathname(selectedClassifier.c);
            
            try{
                System.out.println("Class.forName( "+javaPathName+" )");
                evalClass = Class.forName(javaPathName);
                return evalClass.toString();
            } catch (ClassNotFoundException cnfe){
                return "Generate Code!";
            }
            
            //return ccg.getJavaPathname(selectedClassifier.c);
        } else{
            return evalClass.toString();
        }
    }
    
    public boolean isJavaClassAvailable(){
        return evalClass != null;
    }
    
    public void setSelectedModel(int index){
        if(index >= 0){
            this.selectedModel = (ModelInfo) this.models.get(index);
        } else {
            this.selectedModel = null;
        }
        results.clear();
    }
    
    public void setSelectedMetamodel(int index){
        if(index >= 0){
            MetaModelInfo mmi = (MetaModelInfo) this.metamodels.get(index);
            this.metamodel=mmi.metamodel;
            this.metamodelAsMofOcl=mmi.metamodelAsMofOcl;
            metamodelname=mmi.name;
            this.scriptRunner=new OclScriptRunner(metamodelAsMofOcl);
            ccg = new JmiClassCodeGenerator(metamodelAsMofOcl);
        } else {
            this.metamodel=null;
            this.metamodelAsMofOcl=null;
            this.scriptRunner=null;
            metamodelname="<none>";
            ccg=null;
        }
        this.updateClassifiers();
        updateModels();
    }
    
    public String getSelectedMetamodelName(){
        return this.metamodelname;
    }
    
    public void deleteSelectedMetamodel(int index){
        if(index >= 0){
            MetaModelInfo mmi = (MetaModelInfo) this.metamodels.get(index);
            metamodels.remove(index);
            
            metamodel=null;
            metamodelAsMofOcl=null;
            mm.deleteMetaModel(mmi.metamodel);
            mmi.metamodelAsMofOcl.delete();
            metamodelnames.remove(metamodelname);
            metamodelname="<none>";
        }
    }
    
    public void deleteSelectedModel(){
        if(this.selectedModel != null){
            mm.deleteModel(selectedModel.model);
            models.removeElement(selectedModel);
            selectedModel=null;
        }
    }
    
    
    public void setSelectedClassifier(int index){
        if(index >= 0){
            selectedClassifier = (ClassifierInfo) this.classifiers.get(index);
            
            try{
                evalClass = this.loadEvalClass(selectedClassifier.c);
            } catch (ClassNotFoundException cnfe){
                evalClass = null;
            }
            
        } else {
            selectedClassifier = null;
            evalClass = null;
        }
        
        results.clear();
        updateExpressions();
    }
    
    private void updateExpressions(){
        this.expressions.clear();
        if(this.selectedClassifier != null){
            
            Iterator it = selectedClassifier.c.getExpressionInOclA().iterator();
            
            while(it.hasNext()){
                expressions.addElement(new ExpressionInfo((ExpressionInOcl) it.next()));
            }
            
            //this.selectedClassifier.c.g
        }
        
    }
    
    //    public void closeMetamodel(){
    //        classifiers.clear();
    //        expressions.clear();
    //        scriptRunner=null;
    //        selectedClassifier=null;
    //
    //        if(metamodelAsMofOcl != null){
    //            metamodelAsMofOcl.close();
    //            metamodelAsMofOcl = null;
    //        }
    //        if(metamodel != null){
    //            metamodel.refDelete();
    //            metamodel = null;
    //        }
    //        scriptRunner = null;
    //    }
    
    public ListModel getClassifiers(){
        return this.classifiers;
    }
    
    public ListModel getExpressions(){
        return this.expressions;
    }
    
    public ListModel getMetaModels(){
        return this.metamodels;
    }
    
    public ListModel getModels(){
        return this.models;
    }
    
    public TableModel getResults(){
        return this.results;
    }
    
    public void evaluate(){
        try{
            if(evalClass != null && this.selectedModel!=null){
                
                Constructor cons = evalClass.getConstructor(new Class[]{RefPackage.class});
                Object evaluator = cons.newInstance(new Object[] {selectedModel.model});
                results.clear();
                
                Method getConstraintNames = evaluator.getClass().getDeclaredMethod("getConstraintNames", null);
                Method evaluate = evaluator.getClass().getDeclaredMethod("evaluate", new Class[]{String.class});
                Object  oConstraintNames = getConstraintNames.invoke(evaluator, null);
                String [] constraintNames = (String []) oConstraintNames;
                for(int i=0; i<constraintNames.length; i++){
                    Object result = evaluate.invoke(evaluator, new Object[]{constraintNames[i]});
                    results.addRow(constraintNames[i],  result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void exit(){
        try{
            
            System.out.println("Shutdown Repository.");
            
        } finally {mm.shutdown();}
    }
    
}
