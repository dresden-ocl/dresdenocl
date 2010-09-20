package tudresden.ocl20.testautomation.tools;


public class MiscHelper {

	public static String capitalizeFirstLetter(String value){
		if(value == null || value.isEmpty()){
			return value;
		}
		
		return value.substring(0,1).toUpperCase()+value.substring(1);
	}
}
