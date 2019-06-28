package model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe utilizada globalmente pelas threads para
 * facilitar a sincronização na escrita/leitura dos dados
 * 
 * @author Luiz
 */
public class GlobalLock {

  private static Lock h_lock;
  private static Lock image_lock;
  
  static {
    h_lock = new ReentrantLock();
    image_lock = new ReentrantLock();
  }
  
  public static void lock() {
    h_lock.lock();
  }
  
  public static void unlock() {
    h_lock.unlock();
  }
  
  public static void lockImage() {
    image_lock.lock();
  }
  
  public static void unlockImage() {
    image_lock.unlock();
  }
  
}
