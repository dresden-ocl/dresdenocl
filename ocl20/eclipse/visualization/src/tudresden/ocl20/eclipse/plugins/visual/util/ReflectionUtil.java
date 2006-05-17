package tudresden.ocl20.eclipse.plugins.visual.util;

import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {
	public boolean instanceOf(Object o, Class c) {
		if (getSupers(null, o.getClass()).contains(c))
			return true;
		else
			return false;
	}

	public boolean superOf(Class c, Class s) {
		if (getSupers(null, c).contains(s))
			return true;
		else
			return false;
	}

	public List<Class> getSupers(List<Class> l, Class c) {
		if (l == null)
			l = new ArrayList<Class>();
		if (c.getSuperclass() != null)
			if (c.getSuperclass().equals(Object.class)) {
				if (!l.contains(Object.class))
					l.add(Object.class);
				return l;
			} else {
				if (!l.contains(c.getSuperclass()))
					l.add(c.getSuperclass());
				getSupers(l, c.getSuperclass());
			}
		Class[] interfaces = c.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			if (!l.contains(interfaces[i]))
				l.add(interfaces[i]);
			getSupers(l, interfaces[i]);
		}
		return l;
	}

}
