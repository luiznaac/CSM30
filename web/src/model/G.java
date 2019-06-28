package model;

import org.jblas.FloatMatrix;

public class G {
  
  private static FloatMatrix g;
  
  public G(String signal) {
    g = new FloatMatrix(loadG(signal));
  }
  
  public FloatMatrix getG() {
    return g;
  }
  
  private float[][] loadG(String signal) {
      float[][] g = new float[50816][1];
      String[] splitted = signal.split(",");
      int i = 0;  
      
      for(String s : splitted) {
        g[i][0] = Float.parseFloat(s);
        i++;
      }
      
      return g;
  }
  
  private float[][] treatGain(float[][] g_gain) {
    float[][] g_treated = new float[50816][1];
    int j = 0;
    
    for(int i = 0 ; i < 50816 ; i++) {
      if(i%64 == 0)
        j++;
      g_treated[i][1] = g_gain[i][0] - (float)j;
    }
    
    return g_treated;
  }

}
