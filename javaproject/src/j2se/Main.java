/*
 * Class: Main Created on Nov 16, 2017 (c) Chetan Subhash Patil, unpublished
 * work, All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing. All Rights Reserved. Rights in this program
 * belong to: Chetan Subhash Patil
 */
package j2se;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import j2se.code.Snippet1ThreadGroup;

/**
 * @author chetpati
 */
public class Main extends Thread
{
  
  private static ThreadGroup tg1 = new ThreadGroup("threadgroup");
  private static ThreadGroup tg2 = new ThreadGroup("threadgroup");
  
  public Main(String string)
  {
    super(string);
  }
  
  public Main(ThreadGroup tg, String string)
  {
    super(tg,string);
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    Thread t1 = new Snippet1ThreadGroup(tg1, "thread");
    Thread t2 = new Snippet1ThreadGroup(tg2, "thread");
    t1.start();
    t2.start();
  }
  
  public void run()
  {
    while(true)
    {
      System.out.println(Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getThreadGroup().activeCount() +  " " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
    }
  }
  
  
}


class Task implements Callable<List<String>>
{
  
  public List<String> call() throws IOException
  {
    return Arrays.asList("Apple","Oranges");
  }
  
}



