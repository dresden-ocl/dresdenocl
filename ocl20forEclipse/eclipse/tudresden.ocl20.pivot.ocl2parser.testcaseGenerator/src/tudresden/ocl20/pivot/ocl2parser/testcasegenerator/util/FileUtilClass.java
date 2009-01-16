package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtilClass {
	/**
	 * Copy a whole directory recursively to the <code>destDir</code> directory.
	 * Both parameters must denote a directory. If not, -1 will be returned and nothing happened.
	 * If an error occurred while copying the directory -2 will be returned, some actions
	 * maybe done. 0 will be returned if the copying process is successful.
	 * If <code>destDir</code> doesn't exist it will be created.
	 * @param srcDir the source directory
	 * @param destDir the destination directory
	 * @return -1 if one the parameters is a file; -2 if an error occurred while copying; 0 denotes success
	 */
	public static int copyDirectory(File srcDir, File destDir) {
		// If one of the input parameters is not a directory, we do nothing.
		if (!srcDir.isDirectory() || !destDir.isDirectory()) return -1;
		
		if (!destDir.exists()) destDir.mkdir();
		
		File[] srcFiles = srcDir.listFiles();
		
		for(int i = 0; i < srcFiles.length; i++) {
			File elementCopy = new File(destDir, srcFiles[i].getName());
			if (!srcFiles[i].isDirectory()) {
				if (copyFile(srcFiles[i], elementCopy) != 0) return -2;
			} else {
				FileUtilClass.copyDirectory(srcFiles[i], elementCopy);
			}
		}
		
		return 0;
	}
	
	/**
	 * Copies the file <code>srcFile</code> to <code>destFile</code>. If one of these
	 * files denote a directory -1 is given back. If an error occurred while copying
	 * the file -2 will be returned. If the copy-process succeed 0 will be returned.
	 * If <code>destFile</code> doesn't exist it will be created.
	 * @param srcFile the source file to be copied
	 * @param destFile the destination of the file
	 * @return -1, if one the parameter is a directory; -2 if an error occurred while copying; 0, if
	 * the copy process succeed
	 */
	public static int copyFile(File srcFile, File destFile) {
		// If one the input parameters is a directory we give -1 back.
		if (srcFile.isDirectory() || destFile.isDirectory()) return -1;
		
		try {
			destFile.createNewFile();
			
			InputStream input = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(destFile);
			
			byte[] content = new byte[4096];
			
			while (input.read(content) != -1) {
				out.write(content);
			}
			
			out.flush();
			input.close();
			out.close();
		}catch(IOException ex) {
			ex.printStackTrace();
			return -2;
		}
		
		return 0;
	}
}
