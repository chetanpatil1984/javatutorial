/*
 * Class: Main Created on Nov 16, 2017 (c) Chetan Subhash Patil, unpublished
 * work, All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing. All Rights Reserved. Rights in this program
 * belong to: Chetan Subhash Patil
 */
package j2se;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import j2se.code.Snippet1ThreadGroup;

/**
 * @author chetpati
 */
public class Main1 implements Callable
{
  public enum LBACHeaders
  {
    
  TRUE_CLIENT_IP("True-Client-IP"),
  X_FORWARDED_FOR("X-Forwarded-For");
  
  private String value;
  
  private LBACHeaders(String value)
  {
    this.value = value;
  }
  
  @Override
  public String toString() {
      return this.value;
  }

  }
  
  public enum ErrorCode { ANALYTICS, OTHERS};
  public static ErrorCode errorCode = ErrorCode.ANALYTICS; 
  
  public static final Pattern ILLEGAL_PARAMETER_CHARACTERS_PATTERN = Pattern.compile("^.*[~!#$%^&*+`|\"\'\\<>?/\\\\]+.*$");
  
  ExecutorService service;
  
  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException
  {  
    
    System.out.println(allowScheduleReport(3));
    
    if(Main1.ILLEGAL_PARAMETER_CHARACTERS_PATTERN.matcher(" ").matches())
    {
      System.out.println("yes");
    }
    else
    {
      System.out.println("no");
    }
    
    switch(errorCode)
    {
      case ANALYTICS:
        System.out.println("tata");
        break;
      default:
        System.out.println("hello");
    }
    
    Executors.newCachedThreadPool().submit(new Main1());
    
    Test t = new Test();
    if(t instanceof Test)
    {
      System.out.println("true");
    }
    else
    {
      System.out.println("false");
    }
    
    System.out.println(LBACHeaders.TRUE_CLIENT_IP);
    
    Map<LBACHeaders,String> map = new Hashtable<LBACHeaders,String>();
    map.put(LBACHeaders.TRUE_CLIENT_IP, "test");
    map.get("test");
    
    Map<LBACHeaders   ,String> ht = new Hashtable<LBACHeaders,String>();
    ht.put(LBACHeaders.TRUE_CLIENT_IP, "hello");
    System.out.println(ht.toString());
    

    try
    {
      readConfig();
      initResource();
    }
    catch(FileNotFoundException ex)
    {
      System.out.println("Tango 3");
    }
  }
   
  static void initResource()
  {
    boolean exception = false;
    try
    {
      System.out.println("Tango 1");
      throw new Exception();
    }
    catch(Exception ex)
    {
      exception = true;
      throw new RuntimeException(ex);
    }
    finally
    {
      if(exception)
      {
        destroy();
        System.out.println("Tango 2");
      }
    }
  }
  
  static void destroy()
  {
    throw new ArithmeticException();
  }
  
  static void readConfig() throws FileNotFoundException
  {
    boolean error = false;
    if(error)
    {
      throw new FileNotFoundException();
    }
  }

  @Override
  public Object call() throws Exception
  {
    System.out.println("Calling test");
    Test.test();
    return null;
  }
  
  public static boolean allowScheduleReport(int permission)
  {
    int s = 4096;
    return (permission & s) != 0;
  }
  

  
}

class Test
{
  static
  {
    waitTime();
  }
  
  static void waitTime()
  {
    try
    {
      System.out.println("static started");
      Thread.sleep(30000);
      System.out.println("static finished");
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  
  static void test()
  {
    System.out.println("executed");
  }
  
}



