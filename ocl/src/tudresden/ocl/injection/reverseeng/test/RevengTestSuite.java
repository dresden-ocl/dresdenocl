/*
Copyright (C) 2000  Steffen Zschaler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

/*
 * RevengTestSuite.java
 *
 * Created on 19. Oktober 2000, 14:08
 */
 
package tudresden.ocl.injection.reverseeng.test;

import junit.framework.*;
import junit.swingui.*;

import java.io.*;
import java.util.*;

import tudresden.ocl.injection.*;
import tudresden.ocl.injection.reverseeng.*;
import tudresden.ocl.test.*;

/** 
  * Testsuite testing the vital parts of the reverseeng stuff.
  *
  * @author  sz9
  * @version 1.0
  */
public class RevengTestSuite extends TestCase {

  protected InputStream m_isSource;
  protected Reader m_rSource;

  protected InputStream m_isSource2;
  protected Reader m_rSource2;
  
  /** Creates new RevengTestSuite */
  public RevengTestSuite (String sName) {
    super (sName);
  }
  
  protected void setUp() throws IOException {
    // Setup files to be handled by the tests
    m_isSource = getClass().getResourceAsStream ("ShortExample.java");
    m_rSource = new BufferedReader (new InputStreamReader (m_isSource));

    m_isSource2 = getClass().getResourceAsStream ("ShortExample.java");
    m_rSource2 = new BufferedReader (new InputStreamReader (m_isSource2));
    
  }
  
  protected void tearDown() {
    try {
      m_rSource.close();
      m_isSource.close();

      m_rSource2.close();
      m_isSource2.close();
    }
    catch (IOException ioe) {}
  }

  public void testAnalysis() throws Exception {
    AnalysisConsumer ac = new AnalysisConsumer ("ShortExample.java");
    
    new Injector (m_rSource, null, ac).parseFile();
    
    String sCompare = "[Collection<null> m_llCollectionTester at comment ID 2, Collection<null> m_alCollectionTesterNoComment at comment ID 3, Map<null -> null> m_mMapTester at comment ID 4, Map<null -> null> m_hmMapTesterNoComment at comment ID 5, Collection<null> m_sBag at comment ID 6]";
    
    assertEquals (ac.getAllFeatures().toString(), sCompare);
  }
  
  public void testSaving() throws Exception {
    // First read in file
    AnalysisConsumer ac = new AnalysisConsumer ("ShortExample.java");
    
    new Injector (m_rSource, null, ac).parseFile();
    
    // Now manipulate one feature
    List lFeatures = ac.getAllFeatures();
    
    CollectionDescriptor cdWithComments = (CollectionDescriptor) lFeatures.get (0);
    CollectionDescriptor cdNoComments = (CollectionDescriptor) lFeatures.get (1);
    
    cdWithComments.setElementType ("CommentCollectionElementType");
    cdNoComments.setElementType ("NoCommentCollectionElementType");
    
    // Now test saving...
    IndentAwareWriter iaw = new IndentAwareWriter (new FileWriter("ShortExample.java.bak"));
    new Injector (m_rSource2, iaw, new FileSaveConsumer (iaw, ac)).parseFile();
    iaw.close();
    iaw = null;
			
    // For comparison in testSave
		Diff.diff(new DiffSource(getClass().getResource("ShortExample.testmodified")), new DiffSource(new File("ShortExample.java.bak")));
    
  }
  
  public static Test suite() {
    return new TestSuite (RevengTestSuite.class);
  }
  
  public static void main (String[] args) {
    TestRunner tr = new junit.swingui.TestRunner();
    tr.main (new String[] {RevengTestSuite.class.getName()});
  }
}