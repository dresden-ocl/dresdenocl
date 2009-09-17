package tudresden.ocl20.pivot.testsuite.runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import tudresden.ocl20.pivot.testsuite.TestSuitePlugin;

/**
 * Dialog to select the tests and test suites to be run.
 * 
 * @author Michael Thiele
 * 
 */
public class SelectTestSuitesDialog extends TitleAreaDialog {

	private Table table;

	private List<Test> testsToBeExecuted;

	private boolean showWarnings;

	public SelectTestSuitesDialog(Shell parentShell, List<Test> allTests) {

		super(parentShell);

		this.testsToBeExecuted = allTests;

	}

	/*
	 * Code has been adapted from the LanGems project.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {

		final Preferences preferences =
				TestSuitePlugin.getDefault().getPluginPreferences();
		try {
			System.out.println(TestSuitePlugin.getDefault().getStateLocation()
					.append("showWarnings.pref").toFile());
			preferences.load(new FileInputStream(TestSuitePlugin.getDefault()
					.getStateLocation().append("showWarnings.pref").toFile()));
		} catch (FileNotFoundException e1) {
			// ignore
		} catch (IllegalStateException e1) {
			// ignore
		} catch (IOException e1) {
			// ignore
		}
		showWarnings = preferences.getBoolean(OCL2TestSuiteRunner.SHOW_WARNINGS);

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

		Composite selections = new Composite(composite, SWT.NONE);
		GridData buttonLD = new GridData(SWT.FILL);
		buttonLD.verticalAlignment = SWT.FILL;
		selections.setLayoutData(buttonLD);
		selections.setLayout(new GridLayout());

		Button selectAll = new Button(selections, SWT.NONE);
		selectAll.setText("Select all");
		GridData gd = new GridData(SWT.FILL);
		gd.horizontalAlignment = SWT.FILL;
		selectAll.setLayoutData(gd);
		selectAll.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

				widgetSelected(e);

			}

			public void widgetSelected(SelectionEvent e) {

				for (TableItem item : table.getItems()) {
					if (item.getChecked() == false) {
						item.setChecked(true);
						if (item.getData() instanceof Test) {
							testsToBeExecuted.add((Test) item.getData());
						}
					}
				}
			}

		});

		Button deselectAll = new Button(selections, SWT.NONE);
		deselectAll.setText("Deselect all");
		gd = new GridData(SWT.FILL);
		gd.horizontalAlignment = SWT.FILL;
		deselectAll.setLayoutData(gd);
		deselectAll.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

				widgetSelected(e);

			}

			public void widgetSelected(SelectionEvent e) {

				for (TableItem item : table.getItems()) {
					if (item.getChecked()) {
						item.setChecked(false);
						if (item.getData() instanceof Test) {
							testsToBeExecuted.remove((Test) item.getData());
						}
					}
				}
			}

		});

		Composite showWarningsComposite = new Composite(composite, SWT.NONE);
		GridData swcGridData = new GridData(SWT.FILL);
		swcGridData.verticalAlignment = SWT.FILL;
		showWarningsComposite.setLayoutData(swcGridData);
		showWarningsComposite.setLayout(new GridLayout());

		Button showNoWarningsButton = new Button(showWarningsComposite, SWT.CHECK);
		showNoWarningsButton.setText("show Warnings");
		showNoWarningsButton.setVisible(true);
		showNoWarningsButton.setSelection(showWarnings);

		showNoWarningsButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				showWarnings = !showWarnings;
				preferences.setValue(OCL2TestSuiteRunner.SHOW_WARNINGS, showWarnings);

			}

			public void widgetDefaultSelected(SelectionEvent e) {

				widgetSelected(e);

			}
		});

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
