package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
      long start_time = System.currentTimeMillis();
      float[][] h = loadH();
      printH(h);
      System.out.println(System.currentTimeMillis() - start_time);
      /*HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);

      server.createContext("/integration", IntegrationController::integrate);
      server.createContext("/image/save", ImageController::save);
      server.createContext("/image/load", ImageController::load);
      server.createContext("/image/test", ImageController::test);
      server.start();*/
    }
    
    private static void printH(float[][] h) {
      for(int i = 0 ; i < 50816 ; i++) {
        for(int j = 0 ; j < 3600 ; j++) {
          System.out.print(h[i][j]);
          if(j != 3599)
            System.out.print(",");
        }
        System.out.println("");
      }
    }
    
    private static float[][] loadH() {
      try {
        FileReader reader = new FileReader("H-1.txt");
        BufferedReader br = new BufferedReader(reader);
        String line;
        String[] splitted;
        float[][] h = new float[50816][3600];
        int i = 0;
        int j;
        
        while ((line = br.readLine()) != null) {
          splitted = line.split(",");
          j = 0;
          
          for(String s : splitted) {
            h[i][j] = Float.parseFloat(s);
            j++;
          }
          
          i++;
        }
        
        return h;
      } 
      catch (IOException e) {
          e.printStackTrace();
          return null;
      }
    }
}
