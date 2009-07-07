package tudresden.ocl20.pivot.testsuite.runner;

import java.util.List;

import junit.framework.Test;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Dialog to select the tests and test suites to be run.
 * 
 * @author Michael Thiele
 * 
 */
public class SelectTestSuitesDialog extends TitleAreaDialog {

	private Table table;

	private List<Test> testsToBeExecuted;

	public SelectTestSuitesDialog(Shell parentShell, List<Test> allTests) {

		super(parentShell);

		this.testsToBeExecuted = allTests;

	}

	/*
	 * Code has been adapted from the LanGems project.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;

		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite.setFont(parent.getFont());

		this.setTitle("Dresden OCL2 for Eclipse test suite");
		this.setMessage("Please select the tests or test suites to be executed.");

		ScrolledComposite sc = new ScrolledComposite(composite, SWT.BORDER);
		sc.setSize(500, 400);
		table =
				new Table(sc, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK
						| SWT.V_SCROLL);
		sc.setContent(table);
		table.setSize(500, 400);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		table.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

				widgetSelected(e);

			}

			// check and un-check test suites is handled here
			public void widgetSelected(SelectionEvent e) {

				if (e.detail == SWT.CHECK) {
					TableItem ti = ((TableItem) e.item);
					if (e.item instanceof TableItem) {
						if (ti.getGrayed()) {
							ti.setGrayed(true);
							ti.setChecked(true);
							return;
						}
					}

					if (e.item.getData() instanceof Test) {
						Test test = (Test) e.item.getData();
						if (ti.getChecked()) {
							testsToBeExecuted.add(test);
						}
						else {
							testsToBeExecuted.remove(test);
						}

					}
				}

			}

		});

		// columns of table
		String[] titles = { "Test Suite" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);

		}

		// table is filled
		for (Test test : testsToBeExecuted) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, test.toString());
			item.setData(test);
			item.setChecked(true);
			item.setGrayed(false);
		}

		// optic sugar
		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}

		return parent;
	}

	/**
	 * Returns all tests that are selected.
	 * 
	 * @return all tests that are selected.
	 */
	public List<Test> getTestsToBeExecuted() {

		return testsToBeExecuted;
	}
}
