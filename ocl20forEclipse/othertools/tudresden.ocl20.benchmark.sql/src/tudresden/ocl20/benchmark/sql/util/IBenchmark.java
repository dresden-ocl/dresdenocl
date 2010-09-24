package tudresden.ocl20.benchmark.sql.util;

import java.util.List;

public interface IBenchmark {
	
	public void init(List<String> performer);
	
	public List<String> getPerformer();
		
	public void run(String constraint,List<String> performer);
	
	public void clean();
	
	public List<String> getConstraints();


}
