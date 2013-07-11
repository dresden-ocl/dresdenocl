package resource.package01;

public class TestClass {

	/** Used to test recursion. */
	@SuppressWarnings("unused")
	public TestClass parent;

	private int integer = 10;

	private static int staticInteger = 10;

	public int getInteger() {
		return integer;
	}

	public static int getStaticInteger() {
		return staticInteger;
	}

	public boolean isInteger(int integer) {
		return integer == this.integer;
	}

	public boolean isIntegerBetween(int lower, int upper) {
		return this.integer > lower && this.integer < upper;
	}
}
