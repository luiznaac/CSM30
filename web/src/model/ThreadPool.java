package model;

import java.util.ArrayList;

public class ThreadPool {

  private static ArrayList<CGNEThread> threads;
  private static int next_id;
  
  static {
    threads = new ArrayList<CGNEThread>();
    next_id = 0;
  }
  
  public static int registerThread(CGNEThread cgne_thread) {
    threads.add(cgne_thread);
    next_id++;
    return next_id;
  }
  
  public static void checkoutThread(CGNEThread cgne_thread) {
    threads.remove(cgne_thread);
  }
  
}
