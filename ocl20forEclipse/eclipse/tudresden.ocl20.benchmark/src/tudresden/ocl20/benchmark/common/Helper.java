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
	
	/**
	 * Sets the value to each element of the array
	 * 
	 */
	public static void setArrayElements(int[] array, int value)
	{
		for(int i=0; i<array.length; ++i){
			array[i] = value;
		}
	}

}
