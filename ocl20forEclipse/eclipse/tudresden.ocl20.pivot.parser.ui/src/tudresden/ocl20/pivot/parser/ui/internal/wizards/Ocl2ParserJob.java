package tudresden.ocl20.pivot.parser.ui.internal.wizards;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIUtility;
import tudresden.ocl20.pivot.ocl2parser.parser.Ocl2Parser;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.ui.ParserUIPlugin;
import tudresden.ocl20.pivot.parser.ui.internal.ParserUIMessages;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class Ocl2ParserJob extends Job {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = ParserUIPlugin
			.getLogger(Ocl2ParserJob.class);

	/** The {@link IModel} for that {@link Constraint}s shall be parsed. */
	private IModel model;

	/** The {@link URL} representing the {@link File} that shall be parsed. */
	private URL url;

	/**
	 * <p>
	 * Creates a new {@link Ocl2ParserJob}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} for that {@link Constraint}s shall be
	 *            parsed.
	 * @param pathToParse
	 *            The {@link URL} of the file that shall be parsed.
	 */
	public Ocl2ParserJob(IModel model, URL pathToParse) {
		super("Parsing file ...");

		this.model = model;
		this.url = pathToParse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {

		IStatus result;
		IOclParser parser;

		parser = Ocl2Parser.INSTANCE;

		/* Try to parse the selected OCL file. */
		try {
			monitor
					.beginTask("Parse file " + this.url.toString() + " ...",
							100);

			parser.doParse(this.model, new InputStreamReader(this.url
					.openStream()));
			monitor.worked(99);

			/* Activate the Model Browser View. */
			ModelBusUIUtility.setActiveView(ModelBusUIPlugin.MODELS_VIEW_ID);

			monitor.worked(1);
			monitor.done();

			result = new Status(IStatus.OK, ParserUIPlugin.ID,
					"Parsing finished successfully.");
		}

		/* Probably display an exception. */
		catch (ParseException e) {

			String msg;
			msg = ParserUIMessages.ParseOCLWizard_ErrorOccuredDuringParsing;

			if (e.getMessage() != null) {
				msg += e.getMessage();
			}

			else {
				msg += ParserUIMessages.ParseOCLWizard_CheckLog;
			}

			/* Log the error. */
			LOGGER.error(msg, e);

			result = new Status(IStatus.ERROR, ParserUIPlugin.ID, msg);
		}

		catch (MalformedURLException e) {

			String msg;
			msg = ParserUIMessages.ParseOCLWizard_UnexpectedError;

			/* Log the error. */
			LOGGER.error(msg, e);

			result = new Status(IStatus.ERROR, ParserUIPlugin.ID, msg);
		}

		catch (IllegalStateException e) {

			String msg;
			msg = ParserUIMessages.ParseOCLWizard_UnexpectedError;

			/* Log the error. */
			LOGGER.error(msg, e);

			result = new Status(IStatus.ERROR, ParserUIPlugin.ID, msg);
		}

		catch (IOException e) {

			String msg;
			msg = ParserUIMessages.ParseOCLWizard_UnexpectedError;

			/* Log the error. */
			LOGGER.error(msg, e);

			result = new Status(IStatus.ERROR, ParserUIPlugin.ID, msg);
		}

		return result;
	}
}
