/*
 * TextualCSTBuilder.java
 *
 * Created on 25. Juni 2004, 11:57
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Contact: <konermann@itikko.net>
 *
 * This file is part of the OCL2.0 parser and compiler libraries
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.parser.util;

import tudresden.ocl20.parser.sablecc.analysis.*;
import tudresden.ocl20.parser.sablecc.node.Start;
import tudresden.ocl20.parser.sablecc.node.Node;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Ansgar Konermann
 * @version
 */
public class TextualCSTBuilder extends DepthFirstAdapter {
    
    private Start root;
    
    /** Creates new VisualCSTBuilder */
    public TextualCSTBuilder() { }
    
    public void inStart(Start node) {
        root = node;
        defaultIn(root);
    }
    
    public void defaultIn(Node node) {
        String text = node.getClass().toString();
        text = text.substring(text.lastIndexOf(".")+1);
        
        if ( text.startsWith("A")) text = text.substring(1);
        
        DefaultMutableTreeNode dmtn = 
            new DefaultMutableTreeNode( text + " \"" + node.toString() + "\"");
        setIn(node, dmtn);         
    }
    
    public void defaultOut(Node node) {
        Node parent = node.parent();
        if ( parent != null ) {
            DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) getIn(parent);
            dmtn.add((DefaultMutableTreeNode) getIn(node));
        }
    }
    
    public DefaultMutableTreeNode getRootNode() {
        return (DefaultMutableTreeNode) getIn(root);
    }
}
