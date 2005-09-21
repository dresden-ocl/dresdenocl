/*
 * JGraphTreeBuilder.java
 *
 * Created on 19. Juli 2004, 16:37
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

package tudresden.ocl20.core.parser.util;

// import org._3pq.jgrapht.graph.*;
import org.jgraph.graph.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.*;
import java.awt.Font;
import java.awt.geom.Rectangle2D;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * Utility class supporting creation of JGraph tree models for visualization
 * of syntax trees.
 *
 * @author Ansgar Konermann
 * @version
 */
public class JGraphTreeBuilder extends Object {
    
    private List rootCells = new LinkedList();                  // root cells of graph
    private int rootCellCount = 0;
    private Map attributes = new HashMap();
    private GraphModel model = new DefaultGraphModel();         // graph's data model
    private ConnectionSet connections = new ConnectionSet();    // graph's connections
    
    private Font nodeFont = null;
    private Font terminalNodeFont = null;
    private Font erroneousNodeFont = null;
    private Font edgeFont = null;
    private Color darkGreen = Color.decode("#009900");
    private Color darkRed = Color.decode("#990000");
    private Graphics graphics = null;
    private FontRenderContext frc = null;

    
    /** Creates new JGraphTreeBuilder */
    public JGraphTreeBuilder(Graphics g) { 
        nodeFont = new Font("Helvetica", Font.PLAIN, 12);
        terminalNodeFont = new Font("Helvetica", Font.BOLD, 12);
        erroneousNodeFont = new Font("Helvetica", Font.PLAIN, 12);        
        edgeFont = new Font("Serif", Font.PLAIN, 10);
        
        graphics = g;
        Graphics2D g2d = (Graphics2D) g;
        frc = g2d.getFontRenderContext();
    }
    
    public List getRootCells() { return rootCells; }
    public int countRootCells() { return rootCellCount; }
    public Map getAttributes() { return attributes; }
    public GraphModel getModel() { return model; }
    public ConnectionSet getConnections() { return connections; }
    
    
//    private static DirectedMultigraph createDirectedMultigraph() {
//        return new DirectedMultigraph();
//    }    
    
    public DefaultPort createNonterminalCSTNode(String label) {
        // create vertex
        DefaultGraphCell graphCell = new DefaultGraphCell(label);
        rootCells.add(graphCell);
        rootCellCount++;

        // create vertex attributes
        AttributeMap cellAttributes = model.createAttributes();
        attributes.put(graphCell, cellAttributes);
        
        // @@TODO@@: adapt coordinates to contain label string
        // completely
        
        
        TextLayout tl = new TextLayout(label, nodeFont, frc);
        Rectangle2D textBounds = tl.getBounds();
        Rectangle2D cellBounds = cellAttributes.createRect(10000 + rootCellCount * 10, 10000 + rootCellCount * 10, textBounds.getWidth() + 16, textBounds.getHeight() + 10);
        GraphConstants.setBounds(cellAttributes, cellBounds);

        // set black border
        GraphConstants.setBorderColor(cellAttributes, Color.DARK_GRAY);
        GraphConstants.setBackground(cellAttributes, Color.WHITE);
        GraphConstants.setForeground(cellAttributes, Color.BLUE);
        GraphConstants.setOpaque(cellAttributes, true);
        GraphConstants.setFont(cellAttributes, nodeFont);

        // add a port
        DefaultPort cellPort = new DefaultPort();
        graphCell.add(cellPort);
        
        return cellPort;
    }
    
    public DefaultPort createTerminalCSTNode(String label) {
        // create vertex
        DefaultGraphCell graphCell = new DefaultGraphCell(label);
        rootCells.add(graphCell);
        rootCellCount++;

        // create vertex attributes
        AttributeMap cellAttributes = model.createAttributes();
        attributes.put(graphCell, cellAttributes);
        
        TextLayout tl = new TextLayout(label, terminalNodeFont, frc);
        Rectangle2D textBounds = tl.getBounds();
        Rectangle2D cellBounds = cellAttributes.createRect(10000 + rootCellCount * 10, 10000 + rootCellCount * 10, textBounds.getWidth() + 16, textBounds.getHeight() + 10);
        GraphConstants.setBounds(cellAttributes, cellBounds);

        // set black border
        GraphConstants.setBorderColor(cellAttributes, Color.YELLOW);
        GraphConstants.setBackground(cellAttributes, darkGreen);
        GraphConstants.setForeground(cellAttributes, Color.WHITE);
        GraphConstants.setOpaque(cellAttributes, true);
        GraphConstants.setFont(cellAttributes, nodeFont);

        // add a port
        DefaultPort cellPort = new DefaultPort();
        graphCell.add(cellPort);
        
        return cellPort;
    }
    
    public DefaultPort createErroneousCSTNode(String label) {
        // create vertex
        DefaultGraphCell graphCell = new DefaultGraphCell(label);
        rootCells.add(graphCell);
        rootCellCount++;

        // create vertex attributes
        AttributeMap cellAttributes = model.createAttributes();
        attributes.put(graphCell, cellAttributes);
        
        TextLayout tl = new TextLayout(label, erroneousNodeFont, frc);
        Rectangle2D textBounds = tl.getBounds();
        Rectangle2D cellBounds = cellAttributes.createRect(10000 + rootCellCount * 10, 10000 + rootCellCount * 10, textBounds.getWidth() + 16, textBounds.getHeight() + 10);
        GraphConstants.setBounds(cellAttributes, cellBounds);

        // set black border
        GraphConstants.setBorderColor(cellAttributes, Color.WHITE);
        GraphConstants.setBackground(cellAttributes, darkRed);
        GraphConstants.setForeground(cellAttributes, Color.YELLOW);
        GraphConstants.setOpaque(cellAttributes, true);
        GraphConstants.setFont(cellAttributes, nodeFont);

        // add a port
        DefaultPort cellPort = new DefaultPort();
        graphCell.add(cellPort);
        
        return cellPort;
    }
    
    public DefaultEdge createEdge(Object value, DefaultPort source, DefaultPort target) {
        // create edge
        DefaultEdge edge = new DefaultEdge();
        // create edge attributes
        AttributeMap edgeAttrib = model.createAttributes();
        attributes.put(edge, edgeAttrib);

        GraphConstants.setValue(edgeAttrib, value);
        int arrow = GraphConstants.ARROW_CLASSIC;
        GraphConstants.setLineEnd(edgeAttrib, arrow);
        GraphConstants.setEndFill(edgeAttrib, true);
        GraphConstants.setBendable(edgeAttrib, true);
        GraphConstants.setEndSize(edgeAttrib, 10);
        GraphConstants.setLabelAlongEdge(edgeAttrib, true);
        GraphConstants.setFont(edgeAttrib, edgeFont);
        GraphConstants.setForeground(edgeAttrib, Color.BLUE);
        GraphConstants.setLineColor(edgeAttrib, Color.YELLOW);
        
        // connect source and target ports
        rootCells.add(edge);
        rootCellCount++;
        connections.connect(edge, source, target);
        
        return edge;
    }

    
}
