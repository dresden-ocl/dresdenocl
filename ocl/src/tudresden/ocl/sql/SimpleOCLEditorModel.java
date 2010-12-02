/*
Copyright (C) 2001  Sten Loecher

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

package tudresden.ocl.sql;

import java.util.*;
import java.io.*;
import tudresden.ocl.gui.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.check.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.gui.events.*;
import javax.swing.event.*;

/**
 * A simple implementation of the OCLEditorModel interface to serve the requirements of the OCL2SQL tool.
 * @author  Sten Loecher
 */
public class SimpleOCLEditorModel extends java.lang.Object implements tudresden.ocl.gui.OCLEditorModel, java.io.Serializable {
    
    /**
     *  To control serialization.
     */
    static final long serialVersionUID = -3597215846362973565l;    
    
    /**
     *  List of all constraints.
     *  @element-type ConstraintRepresentation
     */
    private List theConstraints;
    
    /**
     *  List of listeners.
     */
    private EventListenerList theEventListeners;
    
    /**
     * A ModelFacade for typechecking.
     */
    private ModelFacade theModelFacade = null;
    
    /** 
     *  Creates new SimpleOCLEditorModel.
     */
    public SimpleOCLEditorModel() {
        theConstraints = new ArrayList();
        theEventListeners = new EventListenerList();
    }
    
    /**
     *  Add a fresh constraint to the model.
     */
    public void addConstraint() {
        int pos = theConstraints.size();
        theConstraints.add(new SimpleConstraintRepresentation("unnamedConstraint", "context ", pos));
        fireConstraintAdded();
    }
    
    /**
     *  Add a listener to be informed of changes in the model.
     *
     *  @param ccl the new listener
     */
    public void addConstraintChangeListener(ConstraintChangeListener ccl) {
        theEventListeners.add(ConstraintChangeListener.class, ccl);
    }
    
    /**
     *  Return the constraint with the specified index.
     *
     *  @param nIdx the index of the constraint to be returned. 0 <= nIdx < {@link #getConstraintCount}
     */
    public ConstraintRepresentation getConstraintAt(int nIdx) {
        return (ConstraintRepresentation)theConstraints.get(nIdx);
    }
    
    /**
     *  Return the number of constraints in this model.
     */
    public int getConstraintCount() {
        return theConstraints.size();
    }
    
    /**
     *  Remove the specified constraint from the model.
     *
     *  @param nIdx the index of the constraint to be removed. 0 <= nIdx < {@link #getConstraintCount}
     */
    public void removeConstraintAt(int nIdx) {
        ConstraintRepresentation cr = (ConstraintRepresentation)theConstraints.remove(nIdx);
        fireConstraintRemoved(cr, nIdx);
    }
    
    /**
     *  Remove a listener to be informed of changes in the model.
     *
     *  @param ccl the listener to be removed
     */
    public void removeConstraintChangeListener(ConstraintChangeListener ccl) {
        theEventListeners.remove(ConstraintChangeListener.class, ccl);
    }
    
    protected void fireConstraintRemoved (ConstraintRepresentation mc, int nIdx) {
        // Guaranteed to return a non-null array
        Object[] listeners = theEventListeners.getListenerList();
        ConstraintChangeEvent cce = null;
      
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ConstraintChangeListener.class) {
                // Lazily create the event:
                if (cce == null) {
                    cce = new ConstraintChangeEvent(this,
                                                    nIdx,
                                                    new SimpleConstraintRepresentation(mc),
                                                    null);
                }
                ((ConstraintChangeListener) listeners[i + 1]).constraintRemoved(cce);
            }
        }
    }

    protected void fireConstraintAdded() {
        // Guaranteed to return a non-null array
        Object[] listeners = theEventListeners.getListenerList();
        ConstraintChangeEvent cce = null;
      
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ConstraintChangeListener.class) {
                // Lazily create the event:
                if (cce == null) {
                    int nIdx = theConstraints.size() - 1;
                    cce = new ConstraintChangeEvent(this,
                                                    nIdx,
                                                    null,
                                                    (ConstraintRepresentation)theConstraints.get(nIdx));
                }
                ((ConstraintChangeListener) listeners[i + 1]).constraintAdded (cce);
            }
        }
    }
    
    protected void fireConstraintDataChanged(int nIdx, ConstraintRepresentation crOld, ConstraintRepresentation crNew) {
        // Guaranteed to return a non-null array
        Object[] listeners = theEventListeners.getListenerList();
        ConstraintChangeEvent cce = null;
      
         // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ConstraintChangeListener.class) {
                // Lazily create the event:
                if (cce == null) {
                    cce = new ConstraintChangeEvent(this,
                                                    nIdx,
                                                    new SimpleConstraintRepresentation(crOld, nIdx),
                                                    new SimpleConstraintRepresentation(crNew, nIdx));
                }
                ((ConstraintChangeListener) listeners[i + 1]).constraintDataChanged (cce);
            }
        }
    }
          
    protected void fireConstraintNameChanged(int nIdx, ConstraintRepresentation crOld, ConstraintRepresentation crNew) {
        // Guaranteed to return a non-null array
        Object[] listeners = theEventListeners.getListenerList();
        ConstraintChangeEvent cce = null;
      
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ConstraintChangeListener.class) {
                // Lazily create the event:
                if (cce == null) {
                    cce = new ConstraintChangeEvent(this,
                                                    nIdx,
                                                    new SimpleConstraintRepresentation(crOld, nIdx),
                                                    new SimpleConstraintRepresentation(crNew, nIdx));
                }
                ((ConstraintChangeListener) listeners[i + 1]).constraintNameChanged (cce);
            }
        }
    }
    
    /**
     *  Implementation of the Serialization interface to avoid the serialization of the event listeners.
     */
    private void writeObject(java.io.ObjectOutputStream out) 
    throws java.io.IOException {
        out.writeObject(theConstraints);
    }
    
    /**
     *  Implementation of the Serialization interface to avoid the serialization of the event listeners.
     */
    private void readObject(java.io.ObjectInputStream in)
    throws java.io.IOException, java.lang.ClassNotFoundException {
        theConstraints = (List)in.readObject();
        theEventListeners = new EventListenerList();
    }
    
   /**
    *  A ModelFacade for the OCL compiler. If no ModelFacade will be specified,
    *  no typechecking will be done.
    */
    public void setModelFacade(ModelFacade mf) {
        theModelFacade = mf;
    }
    
    /**
     * A simple implementation of the ConstraintRepresentation interface.
     * @author  Sten Loecher
     */
    public class SimpleConstraintRepresentation extends java.lang.Object implements tudresden.ocl.gui.ConstraintRepresentation, java.io.Serializable {
    
        /**
         *  To control serialization.
         */
        static final long serialVersionUID = 8437477795159630258l;
        
        /**
         *  The actual constraint will be stored directly into String objects.
         */
        String constraintName = null;
        String constraintData = null;
        int posInModel = -1;

        /** 
         *  Creates new SimpleConstraintRepresentation. 
         */
        public SimpleConstraintRepresentation() {
        }
    
        /** 
         *  Creates new SimpleConstraintRepresentation. 
         */ 
        public SimpleConstraintRepresentation(ConstraintRepresentation ce) {
            constraintName = ce.getName();
            constraintData = ce.getData();
        }
    
        /**
         *  Creates new SimpleConstraintRepresentation. 
         */
        public SimpleConstraintRepresentation(String sName, String sData, int posInModel) {
            constraintName = sName;
            constraintData = sData;
            this.posInModel = posInModel;
        }
    
        /**
         *  Creates new SimpleConstraintRepresentation. 
         */
        public SimpleConstraintRepresentation(ConstraintRepresentation cr, int posInModel) {
            constraintName = new String(cr.getName());
            constraintData = new String(cr.getData());
            this.posInModel = posInModel;
        }
        
        /**
         * Get the constraint's body text.
         */
        public String getData() {
            if (constraintName == null) {
                return "context " + getName();
            } else {
                return constraintData;
            }
        }
    
        /**
         * Get the constraint's name.
         */
        public String getName() {
            if (constraintName == null) {
                return "newConstraint";
            } else {
                return constraintName;
            }
        }
    
 
        public void setData(String sData, EditingUtilities euHelper) 
        throws IllegalStateException, OclParserException, OclTypeException { 
            try {
                euHelper.parseAndCheckConstraint(sData, theModelFacade);
            } catch(IOException e) {
                System.err.println(e.toString());
            }
            
            ConstraintRepresentation crOld = new SimpleConstraintRepresentation((ConstraintRepresentation)this, posInModel);
            constraintData = sData;
            fireConstraintDataChanged(posInModel, crOld, (ConstraintRepresentation)this);
        }
          
        public void setName (String sName,
                             EditingUtilities euHelper)
        throws IllegalStateException, IllegalArgumentException {

            if (!euHelper.isValidConstraintName (sName)) {
              throw new IllegalArgumentException ("Please specify a valid name.");
            }
            
            ConstraintRepresentation crOld = new SimpleConstraintRepresentation((ConstraintRepresentation)this, posInModel);
            constraintName = sName;
            fireConstraintNameChanged(posInModel, crOld, (ConstraintRepresentation)this);
        }
    }
}
