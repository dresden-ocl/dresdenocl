/*
 * GraphicalCSTBuilder.java
 *
 * Created on 19. Juli 2004, 16:34
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
import tudresden.ocl20.parser.sablecc.node.*;

import org.jgraph.graph.*;
import org.jgraph.JGraph;

import java.util.*;
import java.lang.reflect.*;

/**
 *
 * @author Ansgar Konermann
 * @version
 */
public class GraphicalCSTBuilder extends AnalysisAdapter {
    
    private int edgeCount = 0;
    
    // private Start root;
    
    private List rootCellList = new LinkedList();                   // root cells of graph
    private GraphModel graphModel = new DefaultGraphModel();        // graph's data model
    private ConnectionSet graphConnections = new ConnectionSet();   // graph's connections

    private static JGraphTreeBuilder jgtb = null;
    
    private JGraph graph = null;
    
    private static class NodeDescriptor {
        private DefaultPort port;
        private String name;
        
        public NodeDescriptor(DefaultPort port, String name) {
            this.port = port;
            this.name = name;
        }
        
        public DefaultPort getPort() { return this.port; }
        public String getName() { return this.name; }
    }
  
   
    /** Creates new VisualCSTBuilder */
    public GraphicalCSTBuilder(JGraph g) { 
        graph = g;
        jgtb = new JGraphTreeBuilder(graph.getGraphics());
    }
    
    // defaultIn is called for caseXXX methods of production rules
//    public void defaultIn(Node node) {
//        Class clazz = node.getClass();
//        String qualifiedClazzName = clazz.getName();
//        System.out.println("defaultIn(" + node.getClass().getName() + ")");
//        String clazzName = qualifiedClazzName.substring(qualifiedClazzName.lastIndexOf(".")+1);
//        if ( ! clazz.getSuperclass().getName().endsWith(".Token") ) {            
//            // this node is not a token, so we have to create a node during
//            // defaultIn, since defaultCase is never called by DepthFirstAdapter
//            // in this case.
//            
//            System.out.println("defaultIn: calling defaultCase() for " + node.getClass().getName() +
//                ", superclass is " + clazz.getSuperclass().getName());
//            // we do this by calling defaultCase explicitly
//            defaultCase(node);
//            HashMap map = (HashMap) getIn(node);
//            Object port = map.get("port");
//            String portOid = ( port == null) ? "<null>" : port.toString();
//            System.out.println("defaultIn: returned from defaultCase() for " + node.getClass().getName() + ", port OID is " + portOid);
//        } else {
//            System.out.println("defaultIn: skipping defaultCase() for " + node.getClass().getName());
//        }
//    }

    private void descend(Node parent, DefaultPort nodePort, Node child) {
        // System.out.println("applying analysis to child of class " + parent.getClass().getName() );
        child.apply(this);
        
        // create edge from parent to child
        HashMap childInMap = (HashMap) getIn(child);
        if ( childInMap == null ) {
            throw new RuntimeException("Child did not create IN map: " + child.getClass().getName() );
        }
        DefaultPort childPort = (DefaultPort) childInMap.get("port");                    
        String childName = (String) childInMap.get("name");

        jgtb.createEdge(childName + " (" + edgeCount + ")", nodePort, childPort);
        edgeCount++;                    
    }

    
    public void defaultCase(Node node) {
        
        Class clazz = node.getClass();
        String qualifiedClazzName = clazz.getName();
        String clazzName = qualifiedClazzName.substring(qualifiedClazzName.lastIndexOf(".")+1);
        
        Class superClazz = clazz.getSuperclass();
        String qualifiedSuperClazzName = superClazz.getName();
        String superClazzName = qualifiedSuperClazzName.substring(qualifiedSuperClazzName.lastIndexOf(".")+1);
        
        // for any inner node, create port for inner node
        // for any token, create port for token (leaf node)
        // System.out.println("Creating port for " + clazzName + " (super: " + superClazzName + ")");
        
        // create port for current node
        DefaultPort thisPort = null;
        char firstChar = clazzName.charAt(0);
        char firstSuperChar = superClazzName.charAt(0);
        
        String nodeName = clazzName;
        //     normal alternative                   start symbol
        if ( ( firstChar == 'A' ) || superClazzName.equals("Node") ) {
            thisPort = jgtb.createNonterminalCSTNode(node.toString());
            // nodeName = clazzName;
            //        normal token                          EOF token
        } else if ( ( firstChar == 'T' ) || superClazzName.equals("Token") ) {
            String nodeText = ((Token)node).getText();
            if ( nodeText.length() == 0 ) {
                nodeText = "<" + clazzName + ">-Token";
            }
            thisPort = jgtb.createTerminalCSTNode( nodeText );
            // nodeName = clazzName;
        } else {
            thisPort = jgtb.createErroneousCSTNode( "<unknown>" );
            // nodeName = clazzName;
        }
        
        // create IN map for current node
        HashMap map = (HashMap) getIn(node);
        if ( map == null ) {
            // System.out.println("Creating IN map for " + clazzName );
            map = new HashMap(2);
            setIn(node, map);
        }
        // add port to IN map
        map.put("port", thisPort);
        map.put("name", nodeName);
        
        //     normal alternative                   start symbol
        if ( ( firstChar == 'A' ) || superClazzName.equals("Node") )  {            
            // we're handling a production alternative, descend into children
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++ ) {
                Method curMethod = declaredMethods[i];
                String methName = curMethod.getName();
                if ( methName.startsWith("get") ) {
                    // System.out.println("Descending into child branch via \"" + methName + "\"");
                    Object childObject = null;
                    try {
                        childObject = curMethod.invoke(node, new Object[] {});
                    } catch ( IllegalAccessException iae ) {
                        iae.printStackTrace();
                    } catch ( InvocationTargetException ite ) {
                        ite.printStackTrace();
                    }
                    if ( childObject != null ) {
                        Class childClazz = childObject.getClass();
                        String childClazzName = childClazz.getName();
                        Node child = null;
                        // the next line depends heavily on SableCC's AST framework
                        // creation rules. See a Node subclass with list constructs
                        // for an example (e. g. AOclFile.java)
                        if ( childClazzName.endsWith("LinkedList") ) {
                            // "child" object is actually a list of children, use iterator
                            List children = (List) childObject;
                            Iterator childIt = children.iterator();
                            while ( childIt.hasNext() ) {
                                child = (Node) childIt.next();
                                if ( child != null ) {
                                    descend(node, thisPort, child);
                                }
                            }
                        } else {
                            // child is not a list, cast to Node and apply this Switch
                            child = (Node) childObject;
                            if ( child != null ) {
                                descend(node, thisPort, child);
                            }
                        }
                    } else {
                        // System.out.println(methName + "returned <null>, no descent performed");
                    }
                }
            }
        }
    }
        
        
//        
//        System.out.println("defaultCase(" + node.getClass().getName() + ")");
//        // initialize current node's "in" map if necessary
//        
//        if ( map == null ) {
//            System.out.println("Creating IN map for " + node.getClass().getName());
//            map = new HashMap(2);
//            setIn(node, map);
//        }
//        
//        Node parent = node.parent();
//        HashMap parentMap = null;
//        LinkedList childList = null;
//        if ( parent != null ) {
//            // add this node to set of child nodes in graph
//            parentMap = (HashMap) getIn(parent);
//            if ( parentMap == null ) {
//                // create new map if not already existing
//                parentMap = new HashMap(1);
//                setIn(parent, parentMap);
//            }
//            childList = (LinkedList) parentMap.get("children");
//            if ( childList == null ) {
//                childList = new LinkedList();
//                parentMap.put("children", childList);
//            }
//        }
//        
//        // create graph port according to node type
//        Class nodeClazz = node.getClass();
//        String nodeText = node.getClass().toString();
//        nodeText = nodeText.substring(nodeText.lastIndexOf(".")+1);
//
//        char firstChar = nodeText.charAt(0);
//        String childName = "<unknown>";
//        if ( firstChar == 'A' ) childName = "alt: " + nodeText.substring(1);
//        if ( firstChar == 'T' ) childName = "tok: \"" + nodeText.substring(1) + "\"";
//        DefaultPort thisPort = null;
//        switch ( firstChar ) {
//            case 'A':
//                thisPort = jgtb.createNonterminalCSTNode(node.toString());
//                break;
//            case 'T':
//                thisPort = jgtb.createTerminalCSTNode(((Token)node).getText());
//                break;
//        }
//        if ( childList != null ) {
//            // add port to map of children
//            childList.add(new NodeDescriptor(thisPort, childName));
//        }
//        // add port to in-map of current node
//        map.put("port", thisPort);
//    }
    
//    public void defaultOut(Node node) {
//        HashMap map = (HashMap) getIn(node);
//        if ( map == null ) {
//            System.out.println("defaultOut: no IN-map for " + node.getClass().getName());
//            // no input map => no edges needed
//            return;
//        }
//        LinkedList childList = (LinkedList) map.get("children");
//        if ( childList == null ) {
//            // no children => no edges needed
//            System.out.println("defaultOut: no CHILD map for " + node.getClass().getName());
//            return;
//        }
//        DefaultPort thisPort = (DefaultPort) map.get("port");
//        if ( thisPort == null ) {
//            throw new RuntimeException("Node without port in defaultOut: " + node.getClass().getName());
//        }
//        Iterator it = childList.iterator();
//        while ( it.hasNext() ) {
//            NodeDescriptor descr = (NodeDescriptor) it.next();
//            String childName = descr.getName();
//            DefaultPort childPort = descr.getPort();
//            
//            jgtb.createEdge(childName + " (" + edgeCount + ")", thisPort, childPort);
//            edgeCount++;
//        }
//    }
//        
//        
        
        
//            DefaultPort nodePort = (DefaultPort) getIn(node);
//            jgtb.createEdge("TSimpleName" + " (" + edgeCount + ")", nodePort, tokenPort);
//            edgeCount++;
//                
//        
//        setIn(node, thisPort);
//            
//        Node parent = node.parent();
//        if ( parent != null ) {
//            DefaultPort parentPort = (DefaultPort) getIn(parent);
//            // DefaultPort thisPort = (DefaultPort) getIn(node);
//                    
//            // String text = node.getClass().toString();
//            // text = text.substring(text.lastIndexOf(".")+1);        
//            // if ( text.startsWith("A")) text = text.substring(1);
//            
//            jgtb.createEdge(text + " (" + edgeCount + ")", parentPort, thisPort);
//            edgeCount++;
//        }
//    }
    
    
//    public void outASimpleIdentifierCs(ASimpleIdentifierCs node) {        
//        TSimpleName token = node.getSimpleName();
//        if ( node != null ) {
//            DefaultPort tokenPort = jgtb.createTerminalCSTNode(token.getText());
//            DefaultPort nodePort = (DefaultPort) getIn(node);
//            jgtb.createEdge("TSimpleName" + " (" + edgeCount + ")", nodePort, tokenPort);
//            edgeCount++;
//        }
//    }        
    
    public JGraphTreeBuilder getBuilder() {
        return jgtb;
    }
}
