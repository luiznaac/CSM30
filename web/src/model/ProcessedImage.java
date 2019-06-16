package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcessedImage {

  private float[] f;
  private float min, max;
  
  public ProcessedImage(float[] f) {
    this.f = f;
    setMinAndMax();
  }
  
  public void saveImage() {
    BufferedImage img = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
    
    for(int i = 0 ; i < 60 ; i++) {
      for(int j = 0 ; j < 60 ; j++) {
        int rgb = calculateGrey(i, j);
        img.setRGB(i, j, (new Color(rgb, rgb, rgb)).getRGB());
      }
    }
    
    try {
        ImageIO.write(img, "BMP", new File("test.bmp"));
    } catch (IOException e) {

    }
  }
  
  private int calculateGrey(int i, int j) {
    int index = i*60 + j;
    float value = f[index];
    
    int rgb = Math.round(((value-min)/(max-min)) * (float)255);
    
    return rgb;
  }
  
  private void setMinAndMax() {
    min = max = (float)0;
    for(int i = 0 ; i < 3600 ; i++) {
      if(f[i] < min) min = f[i];
      if(f[i] > max) max = f[i];
    }
  }
}
