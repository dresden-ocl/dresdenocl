/*
Copyright (C) 2000  Steffen Zschaler

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

/*
 * ThreadPool.java
 *
 * Created on 14. August 2000, 13:45
 */
 
package tudresden.ocl.injection.reverseeng;

import java.util.*;

/** 
  * A pool of indistinguishable worker threads that can perform tasks.
  *
  * <p>A ThreadPool consists of a number of identical worker threads and a bag of tasks. Whenever a thread
  * finishes one task it grabs the next available task out of the bag and performs it. The ThreadPool
  * allows to add tasks to be performed, and to control the number of threads in the group.</p>
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class ThreadPool extends ThreadGroup {

  /**
    * Monitor synchronizing access to the thread count variables.
    */
  private transient Object m_oThreadCountLock = null;
  
  /**
    * The number of worker threads in this pool.
    */ 
  private int m_cThreads = 0;
  
  /**
    * The number of threads that need to be added (or removed if negative!).
    *
    * @see addThreads
    * @see removeThreads
    */
  private int m_cThreadsToAdd = 0;
  
  /**
    * The next worker thread ID.
    */
  private int m_nNextID = 0;
  
  /**
    * Monitor synchronizing access to the bag of tasks.
    */
  private transient Object m_oTasksLock = null;
  
  /**
    * The bag of tasks implemented as a list.
    */
  private List m_lrTasks = new LinkedList();
  
  /** 
    * Creates new ThreadPool with a default name and a default number of threads in the current 
    * thread group. The name is "ThreadPool". The initial number of threads is 10.
    */
  public ThreadPool () {
    this ("ThreadPool");
  }
  
  /** 
    * Creates new ThreadPool with a default number of threads in the current 
    * thread group. The initial number of threads is 20.
    */
  public ThreadPool (String sName) {
    this (sName, 20);
  }
  
  /** 
    * Creates new ThreadPool in the current 
    * thread group.
    *
    * @param sName the name of the ThreadPool.
    * @param cInitialThreads the initial number of worker threads.
    */
  public ThreadPool (String sName, int cInitialThreads) {
    this (sName, cInitialThreads, Thread.currentThread().getThreadGroup());
  }
  
  /** 
    * Creates new ThreadPool in the specified
    * thread group.
    *
    * @param sName the name of the ThreadPool.
    * @param cInitialThreads the initial number of worker threads.
    * @param tg the ThreadGroup in which the pool is to be created.
    */
  public ThreadPool (String sName, int cInitialThreads, ThreadGroup tg) {
    super (tg, sName);
    
    addThreads (cInitialThreads);
  }
  
  private synchronized Object getThreadCountLock() {
    if (m_oThreadCountLock == null) {
      m_oThreadCountLock = new Object();
    }
    
    return m_oThreadCountLock;
  }
  
  private synchronized Object getTasksLock() {
    if (m_oTasksLock == null) {
      m_oTasksLock = new Object();
    }
    
    return m_oTasksLock;
  }
  
  private static class PooledThread extends Thread {
    private int m_nID;
    private ThreadPool m_tp;
    
    public PooledThread (ThreadPool tp, int nID) {
      super (tp, "Pooled Thread " + nID);
      
      m_nID = nID;
      m_tp = tp;
    }
    
    public void run() {
      while (true) {
        Runnable r = m_tp.dequeueTask();
        
        try {
          r.run();
        }
        catch (Throwable t) {
          System.err.println("Expception caught in pooled thread: " + t);
          t.printStackTrace ();
        }
      }
    }
  }
  
  /**
    * Used internally by PooledThread to dequeue the next task.
    * 
    * <p>This can return in two cases:
    * <ol>
    * <li>If the calling thread needs to die because too many threads already exist, a ThreadDeath exception
    *     is raised.</li>
    * <li>If a non-null task is in the bag, it is removed from the bag and returned.</li>
    * </ol>
    */
  private Runnable dequeueTask() {
    synchronized (getTasksLock()) {
      while (true) {
        checkForDeath(); // May throw ThreadDeath
        
        if (m_lrTasks.size() > 0) {
          return (Runnable) m_lrTasks.remove (0);
        }
        else {
          try {
            getTasksLock().wait();
          }
          catch (InterruptedException ie) { // Ignore
          }
        }
      }
    }
  }
  
  /**
    * Add a task to the bag of tasks needing to be performed.
    *
    * @param rTask the task to be added.
    */
  public void addTask (Runnable rTask) {
    synchronized (getTasksLock()) {
      m_lrTasks.add (rTask);
      
      if (m_lrTasks.size() == 1) {
        // First task in bag
        getTasksLock().notifyAll();
      }
    }
  }
  
  /**
    * Add the specified number of threads to the pool of worker threads.
    *
    * @param cThreadsToAdd the number of threads to be added.
    */
  public void addThreads (int cThreadsToAdd) {
    synchronized (getThreadCountLock()) {
      m_cThreadsToAdd += cThreadsToAdd;
      
      for (; m_cThreadsToAdd > 0; m_cThreadsToAdd --) {
        new PooledThread(this, m_nNextID ++).start ();
        m_cThreads ++;
      }
    }
  }
  
  /**
    * Mark the specified number of threads to be needing to be removed.
    * 
    * <p>Note that the threads will be killed only after they completed the current task.</p>
    */
  public void removedThreads (int cThreadsToRemove) {
    synchronized (getThreadCountLock()) {
      m_cThreadsToAdd -= cThreadsToRemove;
    }
  }
  
  /**
    * Throws a ThreadDeath if there are still threads that need to die. Assumes, the calling thread 
    * was a pooled thread that will subsequently die because of the ThreadDeath event.
    */
  private void checkForDeath() {
    boolean fKill = false;
    
    synchronized (getThreadCountLock()) {
      fKill = (m_cThreadsToAdd < 0);
      
      if (fKill) {
        // Assume thread will die
        m_cThreadsToAdd ++;
      }
    }
    
    if (fKill) {
      throw new ThreadDeath();
    }
  }
}