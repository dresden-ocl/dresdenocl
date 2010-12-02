package tudresden.ocl20.eclipse.plugins.visual.views;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.eclipse.plugins.visual.Console;
import tudresden.ocl20.eclipse.plugins.visual.editparts.VisualEditPartFactory;
import tudresden.ocl20.eclipse.plugins.visual.editparts.VisualModelEditPart;
import tudresden.ocl20.eclipse.plugins.visual.exceptions.ModelFactoryException;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualModel;
import tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory;
import tudresden.ocl20.eclipse.plugins.visual.modelfactory.UmlOclModelFactory;


/**
 * Main view class. The change the ModelFactory simple change the corresponding line.
 * @author Kai-Uwe Gärtner
 *
 */
public class ASTView extends ViewPart {
	private ScrollingGraphicalViewer graphicalViewer;

	private Action action1;

	private Action action2;
	
	private ModelFactory modelFactory = new UmlOclModelFactory();
	
	private Object viewedContext=null;
	
	private int horizontalPadding=200;
	
	private int verticalPadding=100;


	/**
	 * The constructor.
	 */
	public ASTView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		graphicalViewer = new ScrollingGraphicalViewer();
		graphicalViewer.createControl(parent);
		ScalableRootEditPart root = new ScalableRootEditPart();
		graphicalViewer.setRootEditPart(root);
		graphicalViewer.setEditPartFactory(new VisualEditPartFactory());
		graphicalViewer.setEditDomain(new EditDomain());
		root.getFigure().setBackgroundColor(ColorConstants.white);

		drawGraph();
		makeActions();

		hookContextMenu();
		contributeToActionBars();
	}

	public void drawGraph() {
		Collection contexts = modelFactory.getContextList();
		if ((contexts != null) && (contexts.size() > 0)) {
			
			Object con;
			if (viewedContext==null) con= contexts.toArray()[0];
			else con=viewedContext;
			try{
			VisualModel vmodel;
					vmodel = modelFactory.getVisualModel(con);
					
			graphicalViewer.setContents(vmodel);
			ScalableRootEditPart root = (ScalableRootEditPart) graphicalViewer
					.getRootEditPart();

			ConnectionLayer cl = (ConnectionLayer) root
					.getLayer(LayerConstants.CONNECTION_LAYER);
			Map figToNodeMap = new HashMap();
			DirectedGraph dg = new DirectedGraph();
			List figureList = ((Figure) root.getLayer(
					LayerConstants.PRIMARY_LAYER).getChildren().toArray()[0])
					.getChildren();
			Iterator it = figureList.iterator();
			while (it.hasNext()) {
				IFigure figure = (IFigure) it.next();
				Node n = new Node();
				n.height = figure.getBounds().height;
				n.width = figure.getBounds().width;
				n.setPadding(new Insets(10, horizontalPadding, verticalPadding, 10));
				dg.nodes.add(n);
				figToNodeMap.put(figure, n);
			}
			it = cl.getChildren().iterator();
			while (it.hasNext()) {
				PolylineConnection pc = (PolylineConnection) it.next();
				Edge e = new Edge((Node) figToNodeMap.get(pc.getSourceAnchor()
						.getOwner()), (Node) figToNodeMap.get(pc
						.getTargetAnchor().getOwner()));
				dg.edges.add(e);
			}
			(new DirectedGraphLayout()).visit(dg);
			it = figureList.iterator();
			while (it.hasNext()) {
				IFigure figure = (IFigure) it.next();
				Rectangle r = new Rectangle(
						((Node) figToNodeMap.get(figure)).x,
						((Node) figToNodeMap.get(figure)).y, -1, -1);
				figure.setLocation(new Point(r.x, r.y));
				figure.setLocation(new Point(r.x, r.y));
			}
			cl.setConnectionRouter(new ShortestPathConnectionRouter(
					(IFigure) root.getLayer(LayerConstants.PRIMARY_LAYER)
							.getChildren().toArray()[0]));
			VisualModelEditPart mep = (VisualModelEditPart) root.getChildren()
					.toArray()[0];
			mep.refresh();
			it = mep.getChildren().iterator();
			while (it.hasNext()) {
				EditPart e = (EditPart) it.next();
				e.refresh();
			}
			}
			catch (ModelFactoryException e){
				Console.getInstance().println("Error creating model:"+e.getMessage());
				e.printStackTrace();
			}

		}
	}
	

	public void loadRessource(File file) {
		modelFactory.loadRessource(file);
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		Console.getInstance().println("Loaded file: "+file.getAbsolutePath());
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ASTView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(graphicalViewer.getControl());
		graphicalViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, graphicalViewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.removeAll();
		manager.add(action1);
		manager.add(action2);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		Collection contexts = modelFactory.getContextList();
		if ((contexts != null)) {
			Iterator it = contexts.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				Action action = new Action() {
					public void run() {
						String name=getText();
						Object o=modelFactory.getContextByDisplayName(name);
						viewedContext=o;
						drawGraph();
					}
				};
				action.setText(modelFactory.getContextDisplayName(o));
				action.setToolTipText("Choose context");
				manager.add(action);
			}
		}
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action2 = new ZoomOutAction(((ScalableRootEditPart) graphicalViewer
				.getRootEditPart()).getZoomManager());
		action2.setText("Zoom out");
		action2.setToolTipText("zooms out");
		action1 = new ZoomInAction(((ScalableRootEditPart) graphicalViewer
						.getRootEditPart()).getZoomManager());
		action1.setText("Zoom in");
		action1.setToolTipText("zooms in");
		
		
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(graphicalViewer.getControl().getShell(),
				"Sample View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		graphicalViewer.getControl().setFocus();
	}
}