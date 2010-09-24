package tudresden.ocl20.benchmark.sql.util;


public interface IPerformer {

	public void runAdd();

	public String sendQuery(String query) throws Exception;

	public void addQueryString(String oclString, String runningString);

	public void clean();

	public String getName();
	
}
