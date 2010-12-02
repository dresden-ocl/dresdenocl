package tudresden.ocl20.eclipse.extensionpoint;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

public interface IMDRInitializer
{
	public static final String ID = "tudresden.ocl20.eclipseplugin.initializeMDR";
	public abstract void mdrInitialized(String directory);	
}
