package tudresden.ocl20.pivot.tracer.model;

public class TracerTree {
    private TracerNode root;

    public TracerTree(TracerNode root) {
	this.root = root;
    }

    public TracerNode getRootElement() {
	return root;
    }
}
