package tudresden.ocl20.pivot.examples.simple.constraints.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import tudresden.ocl20.pivot.examples.simple.Person;
import tudresden.ocl20.pivot.examples.simple.Student;

/**
 * <p>
 * Contains a test case to test the code generation for the first invariant of
 * the simple example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class InvTest {

	/**
	 * <p>
	 * A test case to test the code generation for the first invariant of the
	 * simple example.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please note, that this test case will fail, if no instrumentation
	 * code for the invariant has been generated!</strong>
	 * </p>
	 */
	@Test
	public void Inv1Test() {

		Person person1;
		Person person2;

		person1 = new Student();
		person1.setAge(18);

		person2 = new Student();

		/* Try to set a negative age. */
		try {
			person2.setAge(-3);
			fail("An expected exception was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}
}
