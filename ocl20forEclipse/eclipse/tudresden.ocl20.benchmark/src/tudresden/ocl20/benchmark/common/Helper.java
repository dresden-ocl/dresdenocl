package tudresden.ocl20.benchmark.common;

public class Helper {
	
	/**
	 * Extracts Filename from a path WITHOUT extension
	 * 
	 */
	public static String getFileNameFromPath(String path)
	{
		path = path.substring(Math.max(path.lastIndexOf('\\'), path.lastIndexOf('/'))+1);
		
		// filename has extension?
		int dotIndex = path.lastIndexOf('.');
		if(dotIndex != -1){
			return path.substring(0, path.lastIndexOf('.'));
		}else{
			return path;
		}
	}

}
