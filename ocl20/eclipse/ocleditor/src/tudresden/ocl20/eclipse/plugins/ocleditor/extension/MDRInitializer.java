package tudresden.ocl20.eclipse.plugins.ocleditor.extension;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;

import tudresden.ocl20.eclipse.extensionpoint.IMDRInitializer;
import tudresden.ocl20.eclipse.plugins.ocleditor.OCLEditorControler;

public class MDRInitializer implements IMDRInitializer
{
	public MDRInitializer()
	{}
	
	public void mdrInitialized(String directory)
	{
		OCLEditorControler.getInstance().mdrInitialized(directory);
	}
}
