/*
Copyright (C) 2000  Ralf Wiebicke

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.injection;

import java.io.*;
import java.util.*;
import tudresden.ocl.codegen.CodeFragment;

public class Injector
{
  private Reader input;
  private Writer output;
  private Hashtable codefragments;

  private StringBuffer buf=new StringBuffer();

  private boolean do_block=false;
  private boolean start_block=false;
  private boolean collect_when_blocking=false;
  private StringBuffer collector=new StringBuffer();

  public Injector(Reader input, Writer output, Hashtable codefragments)
  {
    this.input=input;
    this.output=output;
    this.codefragments=codefragments;
  }

  private char outbuf;
  private boolean outbufvalid=false;

  public final char read() throws IOException, EndException
  {
    int c=input.read();

    if(!do_block&&outbufvalid)
      output.write(outbuf);

    if(c>=0)
    {
      if(do_block&&collect_when_blocking)
        collector.append(outbuf);
      outbuf=(char)c;
      outbufvalid=true;
      return(char)c;
    }
    else
      throw new EndException();
  }

  private void scheduleBlock(boolean collect_when_blocking)
  {
    if(do_block||collector.length()>0)
      throw new IllegalArgumentException();
    start_block=true;
    this.collect_when_blocking=collect_when_blocking;
  }

  private String getCollector()
  {
    do_block=false;
    start_block=false;
    String s=collector.toString();
    collector.setLength(0);
    return s;
  }

  private void flushOutbuf() throws IOException
  {
    if(outbufvalid)
    {
      if(do_block)
      {
        if(collect_when_blocking)
          collector.append(outbuf);
      }
      else
        output.write(outbuf);
      outbufvalid=false;
    }
  }

  private void write(String s) throws IOException
  {
    output.write(s);
  }

  private boolean readComment() throws IOException, EndException
  {
    switch(read())
    {
    case '*':
      if(read()=='*')
      {
        // definitly a doc comment, see Java Lang. Spec. 3.7.
        //System.out.println("this is a '/** .. */' doc-comment");
      }
      //System.out.println("this is a '/* .. */' comment");
      do
        do
          ;
        while(read()!='*');
      while(read()!='/');
      break;
    case '/':
      //System.out.println("this is a '//' comment");
      do
        ;
      while(read()!='\n');
      break;
    default:
      return false;
    }
    return true;
  }

  private char tokenBuf='\0';
  private String commentBuf=null;
  private String comment=null;

  /**
     Splits the character stream into tokens.
     This tokenizer works only outside of method bodys.
     @return '\0' for multiple character token in buf,
             'c' for comment token in comment,
             else for single character token.
  */
  private char readToken() throws IOException, EndException
  {
    char c;

    if(tokenBuf!='\0')
    {
      c=tokenBuf;
      tokenBuf='\0';
      //System.out.println("<<"+c+">>");
      return c;
    }

    if(commentBuf!=null)
    {
      comment=commentBuf;
      commentBuf=null;
      //System.out.println("<<"+comment+">>");
      return 'c';
    }

    buf.setLength(0);

    while(true)
    {
      switch(c=read())
      {
      case '/':
        boolean commentcollector=false;
        if(!do_block&&start_block)
        {
          do_block=true;
          commentcollector=true;
        }
        readComment();
        if(commentcollector)
          flushOutbuf();
        if(buf.length()>0)
        {
          if(commentcollector)
            commentBuf=getCollector();
          //System.out.println("<"+buf+">");
          return '\0';
        }
        if(commentcollector)
        {
          comment=getCollector();
          //System.out.println("<<"+comment+">>");
          return 'c';
        }
        break;
      case ' ':
      case '\t':
      case '\n':
      case '\r':
        if(buf.length()>0)
        {
          //System.out.println("<"+buf+">");
          return '\0';
        }
        break;
      case '{':
      case '}':
      case '(':
      case ')':
      case ';':
      case '=':
      case ',':
        if(buf.length()>0)
        {
          tokenBuf=c;
          //System.out.println("<"+buf+">");
          return '\0';
        }
        //System.out.println("<<"+c+">>");
        return c;
      default:
        if(!do_block&&start_block)
          do_block=true;
        buf.append(c);
        break;
      }
    }
  }

  private void parseMethodBody() throws IOException, EndException
  {
    //System.out.println("    body");
    for(int bracketdepth=1; bracketdepth>0; )
    {
      switch(read())
      {
      case '/': readComment(); break;
      case '{': bracketdepth++; break;
      case '}': bracketdepth--; break;
      // ignore brackets inside of literal String's
      case '"':
        il: while(true)
        {
          switch(read())
          {
          case '"': break il;
          case '\\': read(); break; // ignore escaped characters
          }
        }
        break;
      // ignore brackets inside of literal characters
      case '\'':
        il: while(true)
        {
          switch(read())
          {
          case '\'': break il;
          case '\\': read(); break; // ignore escaped characters
          }
        }
        break;
      }
    }
  }

  private ClassFeature parseFeature()
    throws IOException, EndException, InjectorParseException
  {
    int modifiers=0;

    while(true)
    {
      String bufs=buf.toString();
      if("public".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.PUBLIC;
      else if("protected".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.PROTECTED;
      else if("private".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.PRIVATE;
      else if("static".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.STATIC;
      else if("final".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.FINAL;
      else if("synchronized".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.SYNCHRONIZED;
      else if("volatile".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.VOLATILE;
      else if("transient".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.TRANSIENT;
      else if("native".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.NATIVE;
      else if("abstract".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.ABSTRACT;
      else
        break;
      if(readToken()!='\0')
        throw new InjectorParseException("modifier expected.");
    }
    String featuretype=buf.toString();
    String featurename;

    int position_name_end=collector.length();

    char c=readToken();

    if(c!='\0')
    {
      if(c=='(') // it's a constructor !
      {
        featurename=featuretype;
        featuretype=null;
      }
      else
        throw new InjectorParseException("'(' expected.");
    }
    else
    {
      featurename=buf.toString();
      position_name_end=collector.length();
      c=readToken();
    }

    ClassFeature cf=
      new ClassFeature(modifiers, featuretype, featurename, position_name_end);

    if(c=='(') // it's a method/constructor
    {
      cf.setMethod();
      c=readToken();
      // parsing parameter list
      while(true)
      {
        String parametertype;
        if(c==')')
          break;
        else if(c=='\0')
          parametertype=buf.toString();
        else
          throw new InjectorParseException("')' expected.");
        c=readToken();
        if(c!='\0')
          throw new InjectorParseException("parameter name expected.");
        cf.addParameter(parametertype, buf.toString());
        c=readToken();
        if(c==',')
        {
          c=readToken();
          continue;
        }
        else if(c==')')
          break;
        else
          throw new InjectorParseException("')' expected.");
      }
      // parsing throws clauses
      c=readToken();
      ti:while(true)
      {
        switch(c)
        {
        case '{':
          if(collect_when_blocking)
          {
            cf.setLiteral(getCollector());
            if(cf.isConstructor())
              write(cf.getNotWrappedLiteral());
            else
              write(cf.getWrappedLiteral());
          }
          parseMethodBody();
          flushOutbuf();
          break ti;
        case ';':
          if(collect_when_blocking)
          {
            cf.setLiteral(getCollector());
            if(cf.isConstructor())
              write(cf.getNotWrappedLiteral());
            else
              write(cf.getWrappedLiteral());
          }
          flushOutbuf();
          break ti;
        case '\0':
          if(buf.toString().equals("throws"))
          {
            do
            {
              c=readToken();
              if(c=='\0')
                cf.addThrowable(buf.toString());
              else
                throw new InjectorParseException("class name expected.");
              c=readToken();
            }
            while(c==',');
          }
          else
            throw new InjectorParseException("'throws' expected.");
          break;
        default:
          throw new InjectorParseException("'{' expected.");
        }
      }
      if(do_block)
        getCollector();
      else
      {
        //cf.print(System.out);
      }
      return cf;
    }
    else // it's a attribute
    {
      while(c!=';') // TODO
        c=readToken();
      cf.setLiteral(getCollector());
      write(cf.getLiteral());
      //cf.print(System.out);
      return cf;
    }
  }

  private String last_element_type=null;

  private void parseClass(String bufs)
    throws IOException, EndException, InjectorParseException
  {
    boolean insertWrappers=true;

    int modifiers=0;
    while(true)
    {
      //System.out.println("bufs >"+bufs+"<");
      if("public".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.PUBLIC;
      else if("protected".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.PROTECTED;
      else if("private".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.PRIVATE;
      else if("static".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.STATIC;
      else if("final".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.FINAL;
      else if("abstract".equals(bufs))
        modifiers|=java.lang.reflect.Modifier.ABSTRACT;
      else if("interface".equals(bufs))
      {
        modifiers|=java.lang.reflect.Modifier.INTERFACE;
        break;
      }
      else if("class".equals(bufs))
        break;
      else
        throw new InjectorParseException("'class' expected.");

      if(readToken()!='\0')
        throw new InjectorParseException("'class' expected.");
      bufs=buf.toString();
    }
    if(readToken()!='\0')
      throw new InjectorParseException("class name expected.");
    String classname=buf.toString();
    //System.out.println("class ("+java.lang.reflect.Modifier.toString(modifiers)+") >"+classname+"<");

    while(readToken()!='{')
      ;

    Vector cfs=(insertWrappers ? null : new Vector());
    Vector ets=new Vector();
    scheduleBlock(true);
    boolean discardnextfeature=false;
    ml: while(true)
    {
      switch(readToken())
      {
      case '}':
        getCollector();
        break ml;
      case 'c':
        //System.out.println("comment: "+comment);
        if(comment.startsWith("/**"))
        {
          if(ClassFeature.OCL_AUTHOR.equals(findDocTag(comment, "author")))
          {
            discardnextfeature=true;
            scheduleBlock(false);
          }
          else
          {
            last_element_type=findDocTag(comment, "element-type");
            write(comment);
            scheduleBlock(true);
          }
        }
        else
        {
          write(comment);
          scheduleBlock(true);
        }
        break;
      case '\0':
        ClassFeature cf=parseFeature();
        if(cf.isMethod()&&!cf.isConstructor()&&!discardnextfeature)
        {
          if(insertWrappers)
          {
            cf.writeWrapper(output);
          }
          else
            cfs.addElement(cf);
        }
        if(last_element_type!=null)
        {
          if(!cf.isMethod())
          {
            cf.setElementType(last_element_type);
            ets.addElement(cf);
          }
          last_element_type=null;
        }
        discardnextfeature=false;
        scheduleBlock(true);
        break;
      default:
        throw new InjectorParseException("class member expected.");
      }
    }

    if(!insertWrappers)
      for(int i=0; i<cfs.size(); i++)
        ((ClassFeature)cfs.elementAt(i)).writeWrapper(output);

    writeInvariants(output, ets, classname);
  }

  public static final String INV_METHOD="checkOclInvariants";

  public final void writeInvariants(Writer o, Vector ets, String classname) throws IOException
  {
    o.write("/**\n    A method for checking ocl invariants.\n    Generated automatically, DO NOT CHANGE!\n      @author ");
    o.write(ClassFeature.OCL_AUTHOR);
    o.write("\n  */private final void ");
    o.write(INV_METHOD);
    o.write("()\n  {\n");
    for(int i=0; i<ets.size(); i++)
      ((ClassFeature)(ets.elementAt(i))).writeElementChecker(o);
    SortedFragments sf=(SortedFragments)(codefragments.get(classname));
    if(sf!=null)
    {
      Vector v=sf.inv;
      for(Enumeration e=v.elements(); e.hasMoreElements(); )
      {
        CodeFragment cf=(CodeFragment)(e.nextElement());
        o.write(cf.getCode());
        o.write("    if(!");
        o.write(cf.getResultVariable());
        o.write(".isTrue())\n      throw new RuntimeException(\"ocl constraint ");
        o.write(cf.getName());
        o.write(" violated\");\n");
      }
    }
    o.write("}");
  }

  public void parseFile() throws IOException, InjectorParseException
  {
    String packageString=null;

    try
    {
      char c;
      while(true)
      {
        c=readToken();
        if(c=='\0')
        {
          String bufs=buf.toString();
          if("package".equals(bufs))
          {
            if(packageString!=null)
              throw new InjectorParseException("more than one package statement.");
            c=readToken();
            if(c!='\0')
              throw new InjectorParseException("package name expected.");
            packageString=buf.toString();
            //System.out.println("package >"+packageString+"<");
            c=readToken();
            if(c!=';')
              throw new InjectorParseException("';' expected.");
          }
          else if("import".equals(bufs))
          {
            c=readToken();
            if(c!='\0')
              throw new InjectorParseException("class name expected.");
            //System.out.println("import >"+buf.toString()+"<");
            c=readToken();
            if(c!=';')
              throw new InjectorParseException("';' expected.");
          }
          else
            parseClass(bufs);
        }
        else
        {
          //System.out.println("bufc >"+c+"<");
        }
      }
    }
    catch(EndException e) {};
  }

  public static void inject(File inputfile, File outputfile, Hashtable codefragments)
    throws IOException, InjectorParseException
  {
    //System.out.println("injecting from "+inputfile+" to "+outputfile);

    if(outputfile.exists())
    {
      if(inputfile.getCanonicalPath().equals(outputfile.getCanonicalPath()))
        throw new RuntimeException("error: input file and output file are the same.");
      if(!outputfile.isFile())
        throw new RuntimeException("error: output file is not a regular file.");
    }

    Reader input=null;
    Writer output=null;
    try
    {
      input =new InputStreamReader (new FileInputStream (inputfile));
      output=new OutputStreamWriter(new FileOutputStream(outputfile));
      (new Injector(input, output, codefragments)).parseFile();
      input.close();
      output.close();
    }
    catch(InjectorParseException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw e;
    }
    catch(IOException e)
    {
      if(input!=null)  input.close();
      if(output!=null) output.close();
      outputfile.delete();
      throw e;
    }
  }

  public static final String TEMPFILE_SUFFIX=".temp_oclinjection";

  public static void inject(File tobemodifiedfile, Hashtable codefragments)
    throws IOException, InjectorParseException
  {
    File outputfile=new File(tobemodifiedfile.getPath()+TEMPFILE_SUFFIX);
    inject(tobemodifiedfile, outputfile, codefragments);
    outputfile.renameTo(tobemodifiedfile);
  }

  class EndException extends Exception
  {
    public EndException()
    {}
  }

  /**
     parameter tagname the tag name without the '@' prefix
  */
  public final static String findDocTag(String doccomment, String tagname)
  {
    String s='@'+tagname+' ';
    int start=doccomment.indexOf(s);
    if(start<0)
      return null;
    start+=s.length();
    int end=doccomment.indexOf('\n', start);
    if(end<0)
      return null;
    //System.out.println("doctag:>"+tagname+"< >"+doccomment.substring(start, end)+"<");
    return doccomment.substring(start, end);
  }

}
