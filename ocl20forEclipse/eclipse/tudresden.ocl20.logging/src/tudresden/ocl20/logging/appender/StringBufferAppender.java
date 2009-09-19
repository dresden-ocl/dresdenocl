package tudresden.ocl20.logging.appender;

import java.io.StringWriter;

import org.apache.log4j.WriterAppender;

public class StringBufferAppender extends WriterAppender {

	public static StringWriter stringWriter = new StringWriter();
	
	public StringBufferAppender() {
		
		setWriter(stringWriter);
	}
	
	@Override
	public synchronized void close() {
	
		stringWriter = new StringWriter();
		super.close();
	}
	
	public static String getMessages() {
		return stringWriter.toString();
	}

}
