package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jblas.FloatMatrix;

public class H {
  
  private static FloatMatrix h;
  
  public static void initialize() {
    h = new FloatMatrix(loadH());
  }
  
  public static FloatMatrix getH() {
    return h;
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
      
      br.close();
      
      return h;
    } 
    catch (IOException e) {
        e.printStackTrace();
        return null;
    }
  }

}
