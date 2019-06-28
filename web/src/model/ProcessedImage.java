package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.imageio.ImageIO;

import database.DatabaseConnector;

public class ProcessedImage {

  private float[] f;
  private float min, max;
  private int imageNumber;
  
  public ProcessedImage(float[] f) {
    this.f = f;
    imageNumber = getNumberOfExistentImages() + 1;
    setMinAndMax();
  }
  
  private int getNumberOfExistentImages() {
    File dir = new File("images");

    File[] files = dir.listFiles();
    
    return files == null ? 0 : files.length;
  }
  
  public void saveImage(String username, int iterations, Timestamp start) {
    BufferedImage img = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
    
    for(int i = 0 ; i < 60 ; i++) {
      for(int j = 0 ; j < 60 ; j++) {
        int rgb = calculateGrey(i, j);
        img.setRGB(i, j, (new Color(rgb, rgb, rgb)).getRGB());
      }
    }
    
    try {
      String path = "images/image_" + imageNumber + ".bmp";
      ImageIO.write(img, "BMP", new File(path));
      DatabaseConnector.registerImage(username, path, iterations, start, new Timestamp(System.currentTimeMillis()));
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
