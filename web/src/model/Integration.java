package model;

import java.util.HashMap;

import org.json.JSONObject;

public class Integration {
  
  private HashMap<String, String> parameters;
  private JSONObject attributes;
  
  public Integration(HashMap<String, String> parameters, JSONObject attributes) {
    this.parameters = parameters;
    this.attributes = attributes;
  }

  public String integrate() {
    String response = "Successful.";
    
    try {
      response += "\n"
                + "date: " + (parameters.containsKey("date") ? parameters.get("date") : "") + "\n"
                + "username: " + (attributes.has("username") ? attributes.get("username") : "") + "\n"
                + "password: " + (attributes.has("password") ? attributes.get("password") : "");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
    return response;
  }
}
