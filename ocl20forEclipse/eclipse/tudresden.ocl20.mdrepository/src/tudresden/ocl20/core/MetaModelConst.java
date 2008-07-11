/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.core;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// TODO merge with the orginal MetaModelConst !!!
/**
 * This class provides some constants for OCL, MOF and UML metamodel.
 * @author  Stefan Ocke
 */
public class MetaModelConst {
    
    /**
     * The package prefix of the Common-OCL JMI-Interfaces.
     */
    public static final String OCLPCKGPRFX = "tudresden.ocl20.core.jmi";
    
    /**
     * The name of the CommonModel package.
     */
    public static final String COMMONMODELPCKG = "CommonModel";
    
    /**
     * The name of the Proxies package.
     */
    public static final String PROXYPCKG = "Proxies";
    
    /**
     * The name of the Adapters package.
     */
    public static final String ADAPTERPCKG = "Adapters";
    
    /**
     * The name of the Common-OCL package.
     */
    public static final String OCLPCKG = "OCL";
    
    /**
     * The name of the Types package.
     */
    public static final String TYPESPCKG = "Types";
    
    /**
     * The name of the Expressions package.
     */
    public static final String EXPPCKG = "Expressions";
    
    //    public static final String UML13 = "UML13";
    //    public static final String UML13XMI = "UML1.3Metamodel/01-12-02_Diff.xml";
    //    public static final String UML14 = "UML14";
    //    public static final String UML14XMI = "UML1.4Metamodel/01-02-15_Diff.xml";
    
    /**
     * The name of the OCL + UML 1.5 metamodel.
     */
    public static final String UML15 = "UML15";
    
    /**
     * The path for the XMI document of the UML 1.5 metamodel.
     */
    public static final String UML15XMI = "UML1.5Metamodel/ActionUML_Interchange_Metamodel_FTF_nb_Diff.xml";
    
    /**
     * The package prefix for the JMI interfaces for UML 1.5 and for UML1.5-OCL.
     */
    public static final String UML15PCKGPRFX = "tudresden.ocl20.core.jmi.uml15";
    
    /**
     * The path for the XMI document containing the Common-OCL package and the UML1.5-OCL package (with meta-proxies).
     */
    public static final String UML15OCLXMI = "OCLMetamodel/UML15OCL.xml";
    
    /**
     * The package of the UML metamodel that should be instantiated in order to load UML models.
     */
    public static final String UMLPCKG = "UML";
    
    /**
     * The name of the OCL + MOF 1.4 metamodel.
     */
    public static final String MOF14 = "MOF14";
    
    /**
     * The path for the XMI document of the MOF 1.4 metamodel.
     */
    public static final String MOF14XMI = "MOFMetamodel/01-10-08.xml";
    
    /**
     * The package prefix for the JMI interfaces for MOF 1.4 and for MOF1.4-OCL.
     */
    public static final String MOF14PCKGPRFX = "tudresden.ocl20.core.jmi.mof14";
    
    /**
     * The path for the XMI document containing the Common-OCL package and the MOF1.4-OCL package (with meta-proxies).
     */
    public static final String MOF14OCLXMI = "OCLMetamodel/MOF14OCL.xml";
    
    /**
     * The package of the MOF  that should be instantiated.
     */
    public static final String MODELPCKG = "Model";
    
    //public static final String OCLXMI = "OCLMetamodel/OclMetamodelCommon.xml";
    
    /**
     * The directory for the generated JMI interfaces.
     */
    public static final String GENERATEDJMIDIR = "generatedJMI";
    
    /**
     * The directory for XMI documents that are the results of the metamodel integration.
     */
    public static final String METAMODELSWITHOCLDIR = "MetamodelsWithOcl";

	//summarizes the constants for MOF 1.4 an UML 1.5
    private static Map<String, MetaModelInfo> metaModelInfos;
    
    // added by Christian Wende for CWM
    public static final String CWM = "CWM";

    public static final String CWMXMI = "cwm/CWM-MOF.xmi";

    public static final String CWMPCKG = "Relational";

    public static final String CWMPCKGPRFX = "tudresden.ocl20.core.jmi.cwm";

    // added end
    
    /**
     * Summarizes the constants for a metamodel that is subject to integration with OCL.
     */
    public static class MetaModelInfo{
        
        /**
         * The name of the metamodel.
         */
        public String name;
        
        /**
         * The path for the XMI document of metamodel (prior to integration with OCL).
         */
        public String xmi;
        
        /**
         * The path for the XMI document containing the Common-OCL package and the specific OCL package (with meta-proxies and adapters).
         */
        public String oclxmi;
        
        /**
         * The package of the metamodel that shoulb be instantiated to create models.
         */
        public String packageToInstantiate;
        
        /**
         * The package prefix for the JMI interfaces of the metamodel.
         */
        public String pckgPrefix;
        
        private MetaModelInfo(String name, String oclxmi, String xmi, String packageToInstantiate){
            this(name, oclxmi, xmi, packageToInstantiate, null);
        }
        
        private MetaModelInfo(String name, String oclxmi, String xmi, String packageToInstantiate, String pckgPrefix){
            this.name = name;
            this.xmi = xmi;
            this.oclxmi = oclxmi;
            this.packageToInstantiate = packageToInstantiate;
            this.pckgPrefix = pckgPrefix;
        }
        
        /** Returns the path to the XMI document that represents the integrated metamodel.
         */
        public URL getIntegratedXmiPath(){
            try{
                URL outputDir = ClassLoader.getSystemClassLoader().getResource(MetaModelConst.METAMODELSWITHOCLDIR+java.io.File.separatorChar);
                return new URL(outputDir, name+"_plus_OCLMetamodel.xml");
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        
        /**
         *The name of the specialized OCL package is always derived from the name of the metamodel.
         *For "MOF14", the specialized OCL package is "MOF14OCL".
         *For "UML15", the specialized OCL package is "UML15OCL".
         */
        public String getSpecializedOclName(){
            return name+MetaModelConst.OCLPCKG;
        }
    }
    
    static{
        metaModelInfos =  new HashMap<String, MetaModelInfo>();
        metaModelInfos.put(MOF14, new MetaModelInfo(MOF14, MOF14OCLXMI, MOF14XMI, MODELPCKG, MOF14PCKGPRFX));
        metaModelInfos.put(UML15, new MetaModelInfo(UML15, UML15OCLXMI, UML15XMI, UMLPCKG, UML15PCKGPRFX));
        // added by Christian Wende for CWM
        metaModelInfos.put(CWM, new MetaModelInfo(CWM, null, CWMXMI, CWMPCKG, CWMPCKGPRFX));
    }
    
    /** Gets a structure comprising all constants for a metamodel that is subject to integration with OCL.
     * @param metamodelname should be one of {@link #MOF14 MOF14}, {@link #UML15 UML15}
     */
    public static MetaModelInfo getMMInfo(String metamodelname){
        return (MetaModelInfo)metaModelInfos.get(metamodelname);
    }
    
    public static String getMetamodelForRefPackage(String pkg) {
    	for(MetaModelInfo info : metaModelInfos.values()) {
    		if(info.packageToInstantiate.equals(pkg)) {
    			return info.name;
    		}
    	}
    	return null;
    }
    
    private MetaModelConst() {
    }
}
