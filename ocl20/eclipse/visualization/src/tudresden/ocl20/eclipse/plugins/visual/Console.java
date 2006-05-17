package tudresden.ocl20.eclipse.plugins.visual;

import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Helper class for using the Eclipse console.
 *
 *
 */
public class Console {
  private MessageConsole myConsole;
 
  private MessageConsoleStream stream;
  
  private static Console instance=null;
 
  private Console() {
    myConsole = new MessageConsole("Model Visualization", null);
    ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ myConsole }); ;
    ConsolePlugin.getDefault().getConsoleManager().showConsoleView(myConsole);
    stream = myConsole.newMessageStream();
  }
  
  public static Console getInstance(){
	  if (instance==null) instance=new Console();
	  return instance;
  }
 
  /**
   * @return Color
   */
  public Color getColor() {
    return stream.getColor();
  }
  /**
   * @return Console
   */
  public MessageConsole getConsole() {
    return stream.getConsole();
  }
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  public int hashCode() {
    return stream.hashCode();
  }
  /**
   * @param message
   */
  public void print(String message) {
    stream.print(message);
  }
  /**
   * 
   */
  public void println() {
    stream.println();
  }
  /**
   * @param message
   */
  public void println(String message) {
    stream.println(message);
  }
  /**
   * @param color
   */
  public void setColor(Color color) {
    stream.setColor(color);
  }
}
