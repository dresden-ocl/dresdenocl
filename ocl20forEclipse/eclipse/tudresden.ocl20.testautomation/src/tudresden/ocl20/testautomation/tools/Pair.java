package tudresden.ocl20.testautomation.tools;

public class Pair<S, T> {

	public S left;
	public T right;

	public Pair(S left, T right) {

		this.left = left;
		this.right = right;
	}

	public static class StringPair extends Pair<String, String> {

		public StringPair(String left, String right) {

			super(left, right);
		}

	}

}
