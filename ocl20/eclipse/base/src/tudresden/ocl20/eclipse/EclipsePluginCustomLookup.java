/*
 * Created on 10.11.2004
 */
package tudresden.ocl20.eclipse;

import org.netbeans.lib.jmi.mapping.*;
import org.netbeans.lib.jmi.util.Logger;
import org.netbeans.lib.jmi.xmi.*;
import org.netbeans.mdr.NBMDRManagerImpl;
import org.netbeans.mdr.NBMDRepositoryImpl;
import org.openide.ErrorManager;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 * This class replaces the default lookup of MDR which
 * searches through the classpath with a lookup that
 * searches through all the elements within the JARs
 * associated with an Eclipse plugin (and only the plugin).
 * It has to be used to allow access to MDR in the
 * context of Eclipse - since the default MDR lookup does
 * not take into account the special nature of the
 * classloaders used by Eclipse this custom implementation
 * is necessary.
 * 
 * The following steps are required to use MDR in Eclipse:
 * <ul>
 *  <li> Include all the required MDR jars in the build path
 *   of the plugin. Note that mdr.jar must not be included
 *   since it causes problems when MDR is not used from
 *   within Netbeans.
 *  <li> Include all MDR jars (except for mdr.jar) in the
 *    plugin.xml file.
 *  <li> Make this class the default lookup class by setting
 *    the system property "org.openide.util.Lookup" has to be
 *    set to the name of this class.
 *  <li> Since the standard constructor of EclipsePluginCustomLookup
 *    will be called when the default lookup is inistantiated,
 *    the information needed by the lookup service must be stored
 *    as system properties as well. In this case, this means that
 *    the pluginID of the plugin which should be searched by this 
 *    lookup service must be given as another system property, i.e.
 *    "omega.util.mdr.EclipsePluginCustomLookup.pluginID"
 *  <li> Now the MDR classes can be used they would be in a stand-alone
 *    scenario.
 * </ul>
 * 
 * Small Print: <i>If you want to try this class, you may do
 *  so but some feedback would be nice. Also, it comes as
 *  is and I take no responsibility for any problems
 *  incurred by its use. The class has been designed and
 *  tested with MDR only but should also work with other
 *  NetBeans modules. Since the lookup service is limited
 *  to a single plugin's space but is set globally via a
 *  system property, there are most likely going to be
 *  problems if more than one Eclipse plugin uses a lookup
 *  service of this kind. Anybody willing to modify this
 *  class for a more general scope is welcome to do so.</i>
 * 
 * Since this implementation is somewhat beta yet so to
 * speak, it has the option to write tons of debug info
 * is setDebug == true.
 * 
 * @author Ralf Gitzel (Lehrstuhl für Wirtschaftsinformatik III,
 *         University of Mannheim)
 * @author Helpful comments by Martin Matula, David Strupl,
 *         and Chris Laffra
 */
public class EclipsePluginCustomLookup extends Lookup {

	private ProxyLookup delegate = null;

	private boolean inited = false;

	private void called() {
		if(!inited) {
			inited = true;
			delegate = new ProxyLookup(new Lookup[] {
				Lookups.singleton(new XMISaxReaderImpl()),
				Lookups.singleton(new XMIWriterImpl()),
				Lookups.singleton(new XmiDtdProducer()),
				Lookups.singleton(new JMIMapperImpl()),
				Lookups.singleton(new JMIMapperCFImpl()),
				Lookups.singleton(new ReaderFactory()),
				Lookups.singleton(new WriterFactory()),
				Lookups.singleton(new ConsumerFactory()),
				Lookups.singleton(new ProducerFactory()),
				Lookups.singleton(new Logger()),
				Lookups.singleton(new NBMDRManagerImpl()),
				Lookups.singleton(new NBMDRepositoryImpl())
			});			
		}
	}

	public Object lookup(Class clazz) {
		called();
		Object ret = delegate.lookup(clazz);
		return ret;
	}


	public Lookup.Result lookup(Lookup.Template template) {
		called();
		Lookup.Result ret = delegate.lookup(template);
		return ret;
	}
}