/*
Copyright (C) 2001  Ralf Wiebicke
 
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

package tudresden.ocl.injection.test;

import java.util.*;
import junit.framework.*;
import tudresden.ocl.injection.Injector;

public class TestExtractDocParagraphs extends TestCase
{
	public TestExtractDocParagraphs(String name)
	{
		super(name);
	}
	
	Map map;
	
	public void setUp() throws Exception
	{
		super.setUp();
		map = new HashMap();
	}
	
	
	private void compute(final String doccomment)
	{
		assertTrue(map.isEmpty());
		map = Injector.extractDocParagraphs("/**"+doccomment+"*/");
	}
	
	private void assertIt(final String tagname, final String[] content)
	{
		final Object value = map.remove(tagname);
		assertEquals("map was "+map.toString(), Arrays.asList(content), value);
	}
	
	private void assertIt(final String tagname, final String content)
	{
		final Object value = map.remove(tagname);
		assertEquals("map was "+map.toString(), content, value);
	}
	
	public void testIt() throws Exception
	{
		compute("");
		
		compute("  @tag zappel nappel");
		assertIt("tag", "zappel nappel");
		
		compute("  * @tag zappel nappel");
		assertIt("tag", "zappel nappel");
		
		compute("  * @tag zappel @tag nappel");
		assertIt("tag", "zappel @tag nappel");
		
		compute("  * @tag zappel * nappel");
		assertIt("tag", "zappel * nappel");
		
		compute(
		"  \n"+
		" * Sappel zappel\n"+
		" * @tag1 mops hops\n"+
		" * @tag1 klop tops\n"+
		" * schnoppel\n"+
		" * hoppel\n"+
		" * @tag2 fips zaps\n");
		assertIt("tag1", new String[]
		{"mops hops ","klop tops  schnoppel  hoppel "});
		assertIt("tag2", "fips zaps");
		
		compute(
		"  \n"+
		"  Sappel zappel\n"+
		"  @tag1 mops hops\n"+
		"  @tag1 klop tops\n"+
		"  schnoppel\n"+
		"  hoppel\n"+
		"  @tag2 fips zaps\n");
		assertIt("tag1", new String[]
		{"mops hops","klop tops schnoppel hoppel"});
		assertIt("tag2", "fips zaps");
		
		compute(
		"\n"+
		"   * @invariant zapp1:\n"+
		"   * zapp2\n"+
		"	  * \n"+
		"   * @invariant zapp10:\n"+
		"   * zapp11\n"+
		"   ");
		assertIt("invariant", new String[]
		{
			"zapp1:  zapp2  ",
			"zapp10:  zapp11",
		});
		
		assertTrue(map.isEmpty());
	}
	
}
