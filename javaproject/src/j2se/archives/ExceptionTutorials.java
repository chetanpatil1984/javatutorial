/*
 * Class: ExceptionTutorials Created on Apr 6, 2013 (c) Chetan Subhash Patil,
 * unpublished work, All use, disclosure, and/or reproduction of this material
 * is prohibited unless authorized in writing. All Rights Reserved. Rights in
 * this program belong to: Chetan Subhash Patil
 */
package j2se.archives;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExceptionTutorials implements IExceptionTutorials
{
  
  // main can be final, can be overridden, cmd line arg starts with parameters
  public static void main(String[] str) throws ClassNotFoundException
  {
    System.out.println("Main");
    new ExceptionTutorials().method1();
  }
  
  // constructor can throw exception
  public ExceptionTutorials() throws ClassNotFoundException
  {
    throw new ClassNotFoundException();
  }
  
  @Override
  public void method()
  {
    
  }
  
  public void method1()
  {
    // can have empty try catch block
    try
    {
      
    }
    // j2se 7 feature
    catch (Exception | Error e)
    {
      // suppresed exception
      e.getSuppressed();
    }
    System.out.println("Hurray1");
    try
    {
      this.method2();
      System.out.println("Hurray3");
    }
    // Need to either handle exception or propogate exception, only finally
    // will not suffice
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    // can catch a error too but not a good practise
    catch (Error e)
    {
      
    }
    catch (Throwable e)
    {
      
    }
    // finally should be at the end of all catch
    finally
    {
      
    }
    // Perfectly valid without catch clause, when try is empty or has method
    // which throws RTE
    // try
    // {
    // }
    // finally
    // {
    // }
    System.out.println("Hurray2");
    // just like runtime exception need not specify
    throw new Error();
  }
  
  // Make throw different exception which is higher in hierarchy
  void method2() throws Exception
  {
    throw new ClassNotFoundException();
  }
  
  // method may thow multiple exception
  void method3() throws ClassNotFoundException, InterruptedException
  {
    throw new ClassNotFoundException();
  }
  
  // cannot throw exception if superclass doesn't declare
  // public void method6() throws FileNotFoundException
  // {
  //
  
  void method5() throws ClassNotFoundException
  {
    throw new ClassNotFoundException();
  }
}

class ExceptionTutorials1 extends ExceptionTutorials
{
  // even static method is overriden, however usually we associate static
  // method with class name
  // and do not show it as overriden
  public static void main(String[] str)
  {
    System.out.println("Main1");
    java.nio.charset.Charset charset = java.nio.charset.StandardCharsets.US_ASCII;
    java.nio.file.Path outputFilePath = null;
    try
    {
      outputFilePath = java.nio.file.Paths.get(new URI("name"));
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }
    // j2se 7 feature try with resources - AutoClosable interface
    try (java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset, null);)
    {
      
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * Constructs a new <tt>Main1</tt>.
   */
  public ExceptionTutorials1() throws ClassNotFoundException
  {
    super();
  }
  
  // overriding method cannot throw exption higher in hierarchy that is
  // defined by super class
  @Override
  void method2() throws ClassNotFoundException
  {
    
  }
  
  // overriding method may chose not to throw exception at all even if super
  // class
  // method is throwing, can throw differnt exception.
  @Override
  void method3()
  {
  }
  
  // Sub class cannot throw exception which is not thrown by superclass or
  // not in hierarchy of class.
  // void method5() throws FileNotFoundException
  // {
  // }
}

interface IExceptionTutorials
{
  // interface method can declare exception in its signature
  void method() throws Exception;
  
  // void method6();
}