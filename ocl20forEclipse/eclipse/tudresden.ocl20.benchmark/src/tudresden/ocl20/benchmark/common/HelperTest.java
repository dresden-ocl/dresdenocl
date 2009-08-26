package tudresden.ocl20.benchmark.common;

import org.junit.Test;
import org.junit.Assert;

public class HelperTest {
	
	@Test
	public void testGetFileNameFromPathWithExt()
	{
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("path/to/someFile.xyz"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("path\\to\\someFile.xyz"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("someFile.xyz"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("\\someFile.xyz"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("/someFile.xyz"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("/path/someFile.xyz"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("\\path\\someFile.xyz"));
		
	}
	
	@Test 
	public void testGetFileNameFromPathWithoutExt()
	{
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("path/to/someFile"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("path\\to\\someFile"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("someFile"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("\\someFile"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("/someFile"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("/path/someFile"));
		Assert.assertEquals("someFile", Helper.getFileNameFromPath("\\path\\someFile"));
		
	}

}
