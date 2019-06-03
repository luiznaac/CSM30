package model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.json.JSONObject;

public class Image {
  
  private HashMap<String, String> parameters;
  private JSONObject attributes;
  
  public Image() {
    
  }
  
  public Image(HashMap<String, String> parameters, JSONObject attributes) {
    this.parameters = parameters;
    this.attributes = attributes;
  }

  public String save() {
    String[] raw_bytes = ((String)attributes.getString("image")).split(",");
    byte[] image_data = new byte[raw_bytes.length];
    
    for(int i = 0 ; i < raw_bytes.length ; i++) {
      image_data[i] = (byte)Integer.parseInt(raw_bytes[i]);
    }
    
    System.out.println("caiiss");
    
    try {
      FileOutputStream output_stream = new FileOutputStream("received_image.png");
      output_stream.write(image_data);
      output_stream.close();
      return "Received.";
    }
    catch(Exception e) {
      e.printStackTrace();
      return "Something went wrong.";
    }
  }
  
  public File load() {
    try {
      File file = new File("test2.jpg");
      return file;
    }  
    catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public String test() {
    return "ok.";
  }
}
