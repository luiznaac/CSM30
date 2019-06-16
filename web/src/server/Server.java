package server;

import model.CGNE;
import model.G;
import model.H;
import model.HttpContainer;
import model.ProcessedImage;

public class Server {

    public static void main(String[] args) {
      float[] f = processSignal();
      saveImage(f);
    }
    
    private static void saveImage(float[] f) {
      ProcessedImage pi = new ProcessedImage(f);
      pi.saveImage();
    }
    
    private static void startHttp() {
      HttpContainer http = new HttpContainer();
    }
    
    private static float[] processSignal() {
      Float convergence = Float.parseFloat("1E-4");
      Runtime runtime = Runtime.getRuntime();
      int mb = 1024*1024;
      H.initialize();
      G g = new G("g-3.txt");
      
      CGNE cgne = new CGNE(g.getG());
      
      int i = 0;
      Float res = (float)0;
      
      while((res = cgne.iterate()) > convergence && i < 1000) {
        System.out.println(i + " " + res + " " + ((runtime.totalMemory() - runtime.freeMemory()) / mb));
        i++;
      }

      System.out.println("##### Heap utilization statistics [MB] #####");
      System.out.println("Used Memory:" 
        + (runtime.totalMemory() - runtime.freeMemory()) / mb);
      System.out.println("Total Memory:" + runtime.totalMemory() / mb);
      System.out.println("Max Memory:" + runtime.maxMemory() / mb);
      
      return cgne.getF().toArray();
    }
    
}
