/**
 * Package where all the code belongs
 */
package code;

import java.io.File;
import java.io.IOException;
import java.util.Map;

// Note that constructors cannot be synchronized — using the synchronized
// keyword with a constructor is a syntax error. Synchronizing constructors
// doesn't make sense, because only the thread that creates an object should
// have access to it while it is being constructed.
class ThreadA extends Thread
{
    @Override
    public void run()
    {
        System.out.println("ThreadA running");
        this.sleepMethod();
    }
    
    private void sleepMethod()
    {
        
        // sleep does not releases lock (if inside synchronized method/block --
        // i.e has acquired monitor)
        // Sleep can be interrupted, t.interrupt()
        try
        {
            // sleep for 10 seconds
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Sleep was interrupted");
        }
        // If the thread has started, and was not in a sleep() or wait() method,
        // then calling interrupt() on it does not throw an exception. Instead
        // it sets the thread's interrupted flag. This flag can then be used as
        // the thread's stop flag.
        
        // clears interrupted flag
        // if (Thread.interrupted())
        // {
        // System.out.println("I was interrupted!");
        // e.printStackTrace();
        // }
        // Does not clear interrupted flag
        // if(Thread.currentThread().isInterrupted())
        // {
        // System.out.println("I was interrupted!");
        // e.printStackTrace();
        // }
    }
}

class ThreadB implements Runnable
{
    // synchronizes on class object i.e. this.getClass()
    // Static and object synchronized methods can run concurrent.
    private synchronized static void staticSynchronizedMethod()
    {
    }
    
    // The resource itself can be used as locking object to lock on
    // private List sharedList = new LinkedList();
    
    // The lock/monitor used for synchronization
    private Object lock = new Object();
    
    private synchronized void blockSynchronization() throws InterruptedException
    {
        // block synchronization.
        // Can have synchronization inside synchronization
        synchronized (this.lock)
        {
            while (true)// some contion
            {
                // wait can be called outside from outside synchronized block
                // however throws IllegalMonitorStateException
                // No way to find if its because of notify or timed out
                this.wait(1000);
            }
        }
    }
    
    // synchronizes on 'this' object itself
    private synchronized void methodSynchronization() throws InterruptedException
    {
        // wait can be interrupted, wait releases acquired lock
        // wrong way of doing - lock acquired for this object and waiting on
        // lock object
        // Will throw IllegalMonitorStateException as it has not acquired lock
        this.lock.wait();
    }
    
    private void notifyMethod()
    {
        // acquire lock and then notify
        synchronized (this.lock)
        {
            // set some condition to true to inform waiting thread
            this.lock.notifyAll();
            // this.lock.notify();
        }
    }
    
    @Override
    public void run()
    {
        System.out.println("ThreadB running");
    }
}

/**
 * @author chetpati
 */
public class ThreadTutorial
{
    
    /**
     * Thread.sleep() sends the current thread into the "Not Runnable" state for
     * some amount of time. The thread keeps the monitors it has aquired -- i.e.
     * if the thread is currently in a synchronized block or method no other
     * thread can enter this block or method. If another thread calls
     * t.interrupt() it will wake up the sleeping thread. Note that sleep is a
     * static method, which means that it always affects the current thread (the
     * one that is executing the sleep method). A common mistake is to call
     * t.sleep() where t is a different thread; even then, it is the current
     * thread that will sleep, not the t thread. t.suspend() is deprecated.
     * Using it is possible to halt a thread other than the current thread. A
     * suspended thread keeps all its monitors and since this state is not
     * interruptable it is deadlock prone. object.wait() sends the current
     * thread into the "Not Runnable" state, like sleep(), but with a twist.
     * Wait is called on a object, not a thread; we call this object the
     * "lock object." Before lock.wait() is called, the current thread must
     * synchronize on the lock object; wait() then releases this lock, and adds
     * the thread to the "wait list" associated with the lock. Later, another
     * thread can synchronize on the same lock object and call lock.notify().
     * This wakes up the original, waiting thread. Basically, wait()/notify() is
     * like sleep()/interrupt(), only the active thread does not need a direct
     * pointer to the sleeping thread, but only to the shared lock object.
     */
    
    /**
     * A thread state. A thread can be in one of the following states: NEW A
     * thread that has not yet started is in this state. RUNNABLE A thread
     * executing in the Java virtual machine is in this state. BLOCKED A thread
     * that is blocked waiting for a monitor lock is in this state. WAITING A
     * thread that is waiting indefinitely for another thread to perform a
     * particular action is in this state. TIMED_WAITING A thread that is
     * waiting for another thread to perform an action for up to a specified
     * waiting time is in this state. TERMINATED A thread that has exited is in
     * this state. A thread can be in only one state at a given point in time.
     * These states are virtual machine states which do not reflect any
     * operating system thread states.
     */
    
    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ThreadTutorial main = new ThreadTutorial();
        main.createProcess();
        main.createThread();
    }
    
    // To prevent memory consistency issue
    // Due to thread (local) cache, a write from one thread may not reflect in
    // other
    // Read of volatile variable that was modified by other thread reflects the
    // changes
    private volatile int sharedResource;
    
    // Immutable objects (shared resource donot require synchronization)
    
    // 1. Don't provide "setter" methods — methods that modify fields or objects
    // referred to by fields.
    // 2. Make all fields final and private.
    // 3. Don't allow subclasses to override methods. The simplest way to do
    // this is to
    // declare the class as final. A more sophisticated approach is to make the
    // constructor
    // private and construct instances in factory methods.
    // 4. If the instance fields include references to mutable objects, don't
    // allow those objects
    // to be changed:
    // 4.1. Don't provide methods that modify the mutable objects.
    // 4.2. Don't share references to the mutable objects. Never store
    // references to external,
    // mutable objects passed to the constructor; if necessary, create copies,
    // and store
    // references to the copies. Similarly, create copies of your internal
    // mutable objects
    // when necessary to avoid returning the originals in your methods.
    
    private void createProcess() throws IOException
    {
        ProcessBuilder pb = new ProcessBuilder("mspaint");
        // set the process builder's working directory by passing the File to
        // the directory() method
        pb.directory(new File("."));
        // add and remove environment variables
        Map<String, String> env = pb.environment();
        env.put("var1", "value");
        env.remove("var3");
        // start the sub process
        Process p = pb.start();
        // gets the output stream of sub process as input stream.
        p.getInputStream();
        // kill the process
        p.destroy();
        
        // older way before java 1.5
        Runtime r = Runtime.getRuntime();
        Process p1 = r.exec("mspaint");
    }
    
    private void createThread() throws InterruptedException
    {
        // start threadA
        Thread th1 = new ThreadA();
        th1.start();
        // start threadB
        Thread th2 = new Thread(new ThreadB());
        th2.start();
        // current thread (Thread.currentThread())waits until th2 thread is
        // finished executing
        // never say (eternal wait)-- Thread.currentThread().join();
        // throws interrupted exception if interrupted while waiting
        th2.join();
        // Sleep so that Thread A and Thread B may run
        Thread.sleep(2000);
        // interrupt thread A which is sleeping for 10 seconds
        th1.interrupt();
    }
}
