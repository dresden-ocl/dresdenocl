package tudresden.ocl.test.royloy;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.lang.reflect.*;

public abstract class RLObject implements Serializable {

  static ArrayList allObjects=new ArrayList();
  static File popFile;

  static int idCounter=0;

  int id;

  public RLObject() {
    allObjects.add(this);
    id=idCounter++;
  }

  public String toXString() {
    String ret=getClass().getName()+"@"+getID();
    Field[] fields=getClass().getFields();
    for (int i=0; i<fields.length; i++) {
      try {
        if (Modifier.isStatic(fields[i].getModifiers()) || fields[i].getName().equals("id")) {
        } else {
          Object o=fields[i].get(this);
          if (o instanceof RLObject) {
            RLObject rlo=(RLObject) o;
            ret+="<"+fields[i].getName()+"=@"+rlo.getID()+">";
          } else if (o instanceof Vector) {
            ret+="<"+fields[i].getName()+"=[";
            Enumeration enum=((Vector)o).elements();
            while (enum.hasMoreElements()) {
              Object next=enum.nextElement();
              if (next instanceof RLObject) {
                ret+=((RLObject)next).getID();
              } else {
                ret+=next.toString();
              }
              if (enum.hasMoreElements()) ret+=",";
            }
            ret+="]>";
          } else {
            ret+="<"+fields[i].getName()+"="+o+">";
          }
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return ret;
  }

  public abstract boolean assertTrue();

  public int getID() {
    return id;
  }

  public static void assertAll() {
    report("--- starting assertAll ---");
    Iterator iter=allObjects.iterator();
    while (iter.hasNext()) {
      RLObject o=(RLObject) iter.next();
      report("testing "+o.getClass().getName()+" "+o.getID());
      boolean b=false;
      try {
        b=o.assertTrue();
      } catch (Exception e) {
        e.printStackTrace();
      }
      report(b?"passed":"- FAILED -");
    }
    report("finished assertAll");
  }

  public static RLObject getObjectWithID(String id) {
    try {
      int searchid=Integer.parseInt(id);
      Iterator iter=allObjects.iterator();
      RLObject found=null;
      while (iter.hasNext() && found==null) {
        RLObject next=(RLObject)iter.next();
        if (next.getID()==searchid) {
          found=next;
        }
      }
      return found;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public static void report(String s) {
    System.out.println(s);
  }

  public static void loadPopFile() {
    if (popFile!=null) {
      try {
        ObjectInputStream oi=new ObjectInputStream(new FileInputStream(popFile));
        allObjects=(ArrayList) oi.readObject();
        oi.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      report("no PopFile set");
    }
    setIDCounter();
  }

  static void setIDCounter() {
    Iterator iter=allObjects.iterator();
    while (iter.hasNext()) {
      RLObject rlo=(RLObject)iter.next();
      idCounter = idCounter>rlo.getID() ? idCounter : rlo.getID()+1;
    }
  }

  public static void storePopFile() {
    if (popFile!=null) {
      try {
        ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(popFile));
        oo.writeObject(allObjects);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      report("no PopFile set");
    }
  }

  public static void main(String[] args) {
    CustomerCard.COLOR_GOLD=2;
    CustomerCard.COLOR_SILVER=1;
    tudresden.ocl.lib.Ocl.setNameAdapter(new tudresden.ocl.lib.ArgoNameAdapter());
    if (args.length>0) {
      popFile=new File(args[0]);
      loadPopFile();
    }
    Frame f=new RLFrame();
    f.setVisible(true);
  }
}
