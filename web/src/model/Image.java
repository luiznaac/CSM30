package model;

import java.io.FileOutputStream;
import java.util.HashMap;
import org.json.JSONObject;

public class Image {
  
  private HashMap<String, String> parameters;
  private JSONObject attributes;
  
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
  
}
