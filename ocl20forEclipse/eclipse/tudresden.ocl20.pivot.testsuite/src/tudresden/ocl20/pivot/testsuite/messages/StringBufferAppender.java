package tudresden.ocl20.pivot.testsuite.messages;

import java.io.StringWriter;

import org.apache.log4j.WriterAppender;

public class StringBufferAppender extends WriterAppender {

	public StringBufferAppender() {

		setWriter(new StringWriter());
	}
	
	public String getMessages() {
		return this.qw.toString();
	}

}
