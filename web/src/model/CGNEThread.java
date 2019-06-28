package model;

import java.sql.Timestamp;

public class CGNEThread implements Runnable {

  private Float convergence = Float.parseFloat("1E-4");
  private CGNE cgne;
  private int thread_id;
  private Timestamp initialized_time;
  private String username;

  public CGNEThread(CGNE cgne, String username) {
    initialized_time = new Timestamp(System.currentTimeMillis());
    GlobalLock.lock();
    thread_id = ThreadPool.registerThread(this);
    GlobalLock.unlock();
    this.cgne = cgne;
    this.username = username;
  }

  @Override
  public void run() {
    System.out.println("THREAD " + thread_id + ": Initialized.");
    int i = 0;

    while (cgne.iterate() > convergence && i < 20) {
      i++;
      System.out.println("THREAD " + thread_id + ": Iteration number " + i);
    }

    System.out.println("THREAD " + thread_id + ": Saving image.");

    float[] f = cgne.getF().toArray();

    GlobalLock.lockImage();
    ProcessedImage pi = new ProcessedImage(f);
    pi.saveImage(username, i, initialized_time);
    ThreadPool.checkoutThread(this);
    GlobalLock.unlockImage();

    System.out.println("THREAD " + thread_id + ": Terminated.");
  }

}
