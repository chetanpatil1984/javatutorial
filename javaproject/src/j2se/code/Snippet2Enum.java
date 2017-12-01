/*
 * Class: Snippet2Enum
 * 
 * Created on Dec 1, 2017
 * 
 * (c) Chetan Subhash Patil, unpublished work,
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Chetan Subhash Patil
 */
package j2se.code;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import j2se.Main.ErrorCode;

public class Snippet2Enum
{
  
  public enum ErrorCode { ANALYTICS, OTHERS};
  public static ErrorCode errorCode = ErrorCode.ANALYTICS; 
  
  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException
  {
    switch(errorCode)
    {
      case ANALYTICS:
        System.out.println("tata");
        break;
      default:
        System.out.println("hello");
    }
    
  }
  
}
