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
import java.lang.reflect.Modifier;
import tudresden.ocl.codegen.CodeFragment;

/**
   Implements a modifying java parser.
   This means, the input stream is continuesly written
   into an output stream, and may be modified before writing.

   The parser recognizes java meta information only,
   which is anything outside method bodies and attribute 
   inizializers.

   To use the parser, provide an implemention of the 
   InjectionConsumer interface to the constructor.
   @see InjectionConsumer
*/
public final class Injector
{
  private Reader input;
  private Writer output;
  private InjectionConsumer consumer;

  private StringBuffer buf=new StringBuffer();

  private boolean do_block=false;
  private boolean start_block=false;
  private boolean collect_when_blocking=false;
  private StringBuffer collector=new StringBuffer();
  
  private String doccomment=null;

  private JavaFile javafile=new JavaFile();

  /**
     Constructs a new java parser.
     @parameter input 
        the input stream to be parsed.
     @parameter output 
        the target, where the modified input stream is written to.
        May be null, if only reading is desired.
     @parameter consumer 
        an implementation of InjectionConsumer,
        listening to parsed elements of the input stream.
     @see InjectionConsumer
  */
  public Injector(Reader input, Writer output, InjectionConsumer consumer)
  {
    this.input=input;
    this.output=output;
    this.consumer=consumer;
  }

  private char outbuf;
  private boolean outbufvalid=false;

  public final char read() throws IOException, EndException
  {
    int c=input.read();

    if(output!=null&&!do_block&&outbufvalid)
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
    //System.out.println("  collector: >"+s+"<");
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
      {
        if(output!=null)
          output.write(outbuf);
      }
      outbufvalid=false;
    }
  }

  private void write(String s) throws IOException
  {
    if(output!=null)
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

  /**
     Parses a method body or an attribute initializer, 
     depending on the parameter.
     For method bodys, the input stream must be directly behind 
     the first opening curly bracket of the body.
     For attribute initializers, the input stream must be directly 
     behind the '='.
     @return 
        the delimiter, which terminated the attribute initializer
        (';' or ',') or '}' for methods.
  */
  private char parseBody(boolean attribute) 
    throws IOException, EndException, InjectorParseException
  {
    //System.out.println("    body("+(attribute?"attribute":"method")+")");

    int bracketdepth=( attribute ? 0 : 1 );
    while(true)
    {
      switch(read())
      {
      case '/': readComment(); break;
      case '{': case '(':
        bracketdepth++; 
        break;
      case '}': case ')':
        bracketdepth--;
        if(bracketdepth==0 && !attribute)
          return '}';
        if(bracketdepth<0)
          throw new InjectorParseException("';' expected.");
        break;
      case ';': 
        // dont have to test for "attribute" here
        // since then the test in the '}' branch would have
        // already terminated the loop
        if(bracketdepth==0)
          return ';';
        break;
      case ',':
        if(bracketdepth==0)
          return ',';
        break;
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

  /**
     Parses a class feature. May be an attribute, a method or a inner
     class. May even be a normal class, in this case parent==null.
     @parameter parent the class that contains the class feature
                       if null, there is no containing class, and 
                       the feature must be a class itself.
  */
  private JavaFeature[] parseFeature(JavaClass parent)
    throws IOException, EndException, InjectorParseException
  {
    return parseFeature(parent, buf.toString());
  }

  /**
     The same as parseFeature(JavaClass) but the first token has
     already been fetched from the input stream.
     @parameter bufs the first token of the class feature.
     @see #parseFeature(JavaClass)
  */
  private JavaFeature[] parseFeature(JavaClass parent, String bufs)
    throws IOException, EndException, InjectorParseException
  {
    int modifiers=0;

    while(true)
    {
      //System.out.println("bufs >"+bufs+"<");
      if("public".equals(bufs))
        modifiers|=Modifier.PUBLIC;
      else if("protected".equals(bufs))
        modifiers|=Modifier.PROTECTED;
      else if("private".equals(bufs))
        modifiers|=Modifier.PRIVATE;
      else if("static".equals(bufs))
        modifiers|=Modifier.STATIC;
      else if("final".equals(bufs))
        modifiers|=Modifier.FINAL;
      else if("synchronized".equals(bufs))
        modifiers|=Modifier.SYNCHRONIZED;
      else if("volatile".equals(bufs))
        modifiers|=Modifier.VOLATILE;
      else if("transient".equals(bufs))
        modifiers|=Modifier.TRANSIENT;
      else if("native".equals(bufs))
        modifiers|=Modifier.NATIVE;
      else if("abstract".equals(bufs))
        modifiers|=Modifier.ABSTRACT;
      else if("interface".equals(bufs))
      {
        modifiers|=Modifier.INTERFACE;
        JavaClass[] jcarray={ parseClass(parent, modifiers) };
        return jcarray;
      }
      else if("class".equals(bufs))
      {
        JavaClass[] jcarray={ parseClass(parent, modifiers) };
        return jcarray;
      }
      else
      {
        if(parent==null)
          throw new InjectorParseException("'class' or 'interface' expected.");
        break;
      }
    
      char c=readToken();
      if(c!='\0')
      {
        if(parent==null)
          throw new InjectorParseException("'class' or 'interface' expected.");
        else
        {
          if( c=='{' && modifiers==Modifier.STATIC )
          {
            // this is a static initializer
            if(collect_when_blocking)
              write(getCollector());
            flushOutbuf();
            parseBody(false);
            scheduleBlock(true);
            doccomment=null;
            return new JavaClass[0];
          }
          else
          {
            throw new InjectorParseException("modifier expected.");
          }
        }
      }
      bufs=buf.toString();
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
        if(!parent.getName().equals(featurename))
          throw new InjectorParseException("constructor '"+featurename+" must have the classes name '"+parent.getName()+'\'');
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


    if(c=='(') // it's a method/constructor
    {
      JavaBehaviour jb=
        (featuretype==null) ?
        (JavaBehaviour)new JavaConstructor(parent, modifiers, featurename) :
        new JavaMethod(parent, modifiers, featuretype, featurename, position_name_end);
      jb.setLastParameterStart(collector.length()+1);
      parseBehaviour(jb);
      JavaFeature[] jbarray={jb};
      return jbarray;
    }
    else // it's an attribute
    {
      JavaAttribute ja=
        new JavaAttribute(parent, modifiers, featuretype, featurename);
      return parseAttribute(ja, c);
    }
  }

  private void parseBehaviour(JavaBehaviour jb)
    throws IOException, EndException, InjectorParseException
  {
    char c=readToken();
    // parsing parameter list
    while(true)
    {
      String parametertype;
      if(c==')')
      {
        jb.setLastParameterEnd(collector.length());
        break;
      }
      else if(c=='\0')
      {
        parametertype=buf.toString();
        if("final".equals(parametertype))
        {
          c=readToken();
          if(c=='\0')
            parametertype=buf.toString();
          else
            throw new InjectorParseException("parameter type expected.");
        }
      }
      else
        throw new InjectorParseException("')' expected.");
      c=readToken();
      if(c!='\0')
        throw new InjectorParseException("parameter name expected.");
      //System.out.println("addParameter("+parametertype+", "+buf.toString()+")");
      jb.addParameter(parametertype, buf.toString());
      c=readToken();
      if(c==',')
      {
        jb.setLastParameterStart(collector.length());
        c=readToken();
        continue;
      }
      else if(c==')')
      {
        jb.setLastParameterEnd(collector.length());
        break;
      }
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
          jb.setLiteral(getCollector());
          consumer.onBehaviourHeader(jb);
        }
        parseBody(false);
        flushOutbuf();
        break ti;
      case ';':
        if(collect_when_blocking)
        {
          jb.setLiteral(getCollector());
          consumer.onBehaviourHeader(jb);
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
              jb.addThrowable(buf.toString());
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
      //jb.print(System.out);
    }
  }

  private JavaAttribute[] parseAttribute(JavaAttribute ja, char c)
    throws IOException, EndException, InjectorParseException
  {
    consumer.onAttributeHeader(ja);
    
    final ArrayList commaSeparatedAttributes=new ArrayList();
    commaSeparatedAttributes.add(ja);
    //if(!do_block) ja.print(System.out);

    while(true)
    {
      
      switch(c)
      {
      case ';': 
        if(collect_when_blocking)
          write(getCollector());
        flushOutbuf();
        if(do_block)
          getCollector();
        JavaAttribute[] jaarray=
          new JavaAttribute[commaSeparatedAttributes.size()];
        commaSeparatedAttributes.toArray(jaarray);
        return jaarray;
      case ',':
        c=readToken();
        if(c!='\0')
          throw new InjectorParseException("attribute name expected.");
        ja=new JavaAttribute(ja, buf.toString());
        commaSeparatedAttributes.add(ja);
        //if(!do_block) ja.print(System.out);
        c=readToken();
        break;
      case '=':
        if(collect_when_blocking)
          write(getCollector());
        c=parseBody(true);
        flushOutbuf();
        break;
      default:
        throw new InjectorParseException("';', '=' or ',' expected.");
      }
    }
  }

  private JavaClass parseClass(JavaClass parent, int modifiers)
    throws IOException, EndException, InjectorParseException
  {
    if(readToken()!='\0')
      throw new InjectorParseException("class name expected.");
    String classname=buf.toString();
    //System.out.println("class ("+Modifier.toString(modifiers)+") >"+classname+"<");

    JavaClass jc=
      new JavaClass(javafile, parent, modifiers, classname);
    //cc.print(System.out);

    consumer.onClass(jc);
    
    if(collect_when_blocking)
      write(getCollector());
    if(do_block)
      getCollector();

    while(readToken()!='{')
      ;

    scheduleBlock(true);
    ml: while(true)
    {
      switch(readToken())
      {
      case '}':
        getCollector();
        break ml;
      case 'c':
        if(comment.startsWith("/**"))
        {
          doccomment=comment;
          //System.out.println("doccomment: "+doccomment);
          scheduleBlock(consumer.onDocComment(doccomment));
        }
        else
        {
          //System.out.println("comment: "+comment);
          write(comment);
          scheduleBlock(true);
        }
        break;
      case '\0':
        JavaFeature[] jfarray=parseFeature(jc);
        for(int i=0; i<jfarray.length; i++)
          consumer.onClassFeature(jfarray[i], doccomment);
        doccomment=null;
        scheduleBlock(true);
        break;
      case ';': 
        // javac (but not jikes) accepts semicolons on class level, 
        // so do we.
        getCollector();
        break;
      case '{':
        // this is an object initializer as defined
        // in Java Language Specification D.1.3
        if(collect_when_blocking)
          write(getCollector());
        flushOutbuf();
        parseBody(false);
        scheduleBlock(true);
        break;
      default:
        throw new InjectorParseException("class member expected.");
      }
    }
    
    consumer.onClassEnd(jc);
    return jc;
  }

  public void parseFile() throws IOException, InjectorParseException
  {
    try
    {
      char c;
      while(true)
      {
        try
        {
          c=readToken();
        }
        catch(EndException e)
        {
          return;
        }
        
        if(c=='\0')
        {
          String bufs=buf.toString();
          if("package".equals(bufs))
          {
            c=readToken();
            if(c!='\0')
              throw new InjectorParseException("package name expected.");
            javafile.setPackage(buf.toString());
            consumer.onPackage(javafile);
            //System.out.println("package >"+buf.toString()+"<");
            c=readToken();
            if(c!=';')
              throw new InjectorParseException("';' expected.");
          }
          else if("import".equals(bufs))
          {
            c=readToken();
            if(c!='\0')
              throw new InjectorParseException("class name expected.");
            String importstring=buf.toString();
            //System.out.println("import >"+importstring+"<");
            javafile.addImport(importstring);
            consumer.onImport(importstring);
            c=readToken();
            if(c!=';')
              throw new InjectorParseException("';' expected.");
          }
          else
            parseFeature(null, bufs); // null says, its a top-level class
        }
        else
        {
          //System.out.println("bufc >"+c+"<");
        }
      }
    }
    catch(EndException e) 
    {
      throw new InjectorParseException("Unexpected End-of-File.");
    }
  }

  private class EndException extends Exception
  {
    public EndException()
    {}
  }

  /**
     @parameter tagname the tag name without the '@' prefix
     @return the first word following the tag
  */
  public final static String findDocTag(String doccomment, String tagname)
  {
    String s='@'+tagname+' ';
    int start=doccomment.indexOf(s);
    if(start<0)
      return null;
    start+=s.length();
    
    int end;
    li: for(end=start; end<doccomment.length(); end++)
    {
      switch(doccomment.charAt(end))
      {
      case ' ':
      case '\n':
      case '\r':
      case '*':
        break li;
      }
    }
    String result=doccomment.substring(start, end).trim();
    //System.out.println("doctag:>"+tagname+"< >"+doccomment.substring(start, end)+"<");
    return result;
  }

  /**
     @parameter tagname the tag name without the '@' prefix
     @return the paragraphs following the tag
  */
  public final static String[] extractDocParagraphs(String doccomment, String tagname)
  {
    ArrayList result=new ArrayList();
    
    int start=doccomment.indexOf('@');
    while(true)
    {
      if(start<0)
        break;
      start++;
      
      if(!doccomment.regionMatches(start, tagname, 0, tagname.length()))
      {
        start=doccomment.indexOf('@', start);
        continue;
      }
      start+=tagname.length();
      
      if(doccomment.charAt(start)!=' ')
      {
        start=doccomment.indexOf('@', start);
        continue;
      }
      start++;

      int end=doccomment.indexOf('@', start);
      if(end<0)
      {
        result.add(doccomment.substring(start, doccomment.length()-("*/".length())));
        break;
      }
      else
      {
        result.add(doccomment.substring(start, end));
        start=end;
      }
    }
    
    String[] resultarray=new String[result.size()];
    resultarray=(String[])(result.toArray(resultarray));
    //System.out.println("docparagraphs:>"+tagname+"< >"+result+"<");
    return resultarray;
  }

}
