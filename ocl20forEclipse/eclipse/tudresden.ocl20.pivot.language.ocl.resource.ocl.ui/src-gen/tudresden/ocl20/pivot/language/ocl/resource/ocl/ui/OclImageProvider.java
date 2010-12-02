/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

/**
 * A provider class for all images that are required by the generated UI plug-in.
 * The default implementation load images from the bundle and caches them to make
 * sure each image is loaded at most once.
 */
public class OclImageProvider {
	
	public final static OclImageProvider INSTANCE = new OclImageProvider();
	
	private java.util.Map<String, org.eclipse.swt.graphics.Image> imageCache = new java.util.LinkedHashMap<String, org.eclipse.swt.graphics.Image>();
	
	/**
	 * Returns the image associated with the given key. The key can be either a path
	 * to an image file in the resource bundle or a shared image from
	 * org.eclipse.ui.ISharedImages.
	 */
	public org.eclipse.swt.graphics.Image getImage(String key) {
		if (key == null) {
			return null;
		}
		org.eclipse.swt.graphics.Image image = null;
		// try shared images
		try {
			java.lang.reflect.Field declaredField = org.eclipse.ui.ISharedImages.class.getDeclaredField(key);
			Object valueObject = declaredField.get(null);
			if (valueObject instanceof String) {
				String value = (String) valueObject;
				image = org.eclipse.ui.PlatformUI.getWorkbench().getSharedImages().getImage(value);
			}
		} catch (java.lang.SecurityException e) {
		} catch (java.lang.NoSuchFieldException e) {
		} catch (java.lang.IllegalArgumentException e) {
		} catch (java.lang.IllegalAccessException e) {
		}
		if (image != null) {
			return image;
		}
		
		// try cache
		if (imageCache.containsKey(key)) {
			return imageCache.get(key);
		}
		
		// try loading image from UI bundle
		org.eclipse.core.runtime.IPath path = new org.eclipse.core.runtime.Path(key);
		org.eclipse.jface.resource.ImageDescriptor desriptor = org.eclipse.jface.resource.ImageDescriptor.createFromURL(org.eclipse.core.runtime.FileLocator.find(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getBundle(), path, null));
		image = desriptor.createImage();
		if (image == null) {
			return null;
		}
		imageCache.put(key, image);
		return image;
	}
	
}
