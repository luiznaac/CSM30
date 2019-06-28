package model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.json.JSONObject;

import database.DatabaseConnector;

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
  
  public static File load(int id) {
    try {
      String path = DatabaseConnector.getPathForId(id);
      File file = new File(path);
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
  
  public static void processSignal(String signal, String username) {
    G g = new G(signal);
    CGNE cgne = new CGNE(g.getG());
    Runnable runCgne = new CGNEThread(cgne, username);
    Thread tCgne = new Thread(runCgne);
    tCgne.start();
  }
  
  public static String listImages(String username) {
    return DatabaseConnector.getImagesForUsername(username);
  }
  
}
