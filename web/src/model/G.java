package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jblas.FloatMatrix;

public class G {
  
  private static FloatMatrix g;
  
  public G(String path) {
    g = new FloatMatrix(loadG(path));
  }
  
  public FloatMatrix getG() {
    return g;
  }
  
  private float[][] loadG(String path) {
    try {
      FileReader reader = new FileReader(path);
      BufferedReader br = new BufferedReader(reader);
      String line;
      String[] splitted;
      float[][] g = new float[50816][1];
      int i = 0;
      int j;
      
      while ((line = br.readLine()) != null) {
        splitted = line.split(",");
        j = 0;
        
        for(String s : splitted) {
          g[i][j] = Float.parseFloat(s);
          j++;
        }
        
        i++;
      }
      
      br.close();
      
      return g;
    } 
    catch (IOException e) {
        e.printStackTrace();
        return null;
    }
  }

}
