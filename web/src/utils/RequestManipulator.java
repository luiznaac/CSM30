package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;

public class RequestManipulator {

  public static HashMap<String, String> parameters(HttpExchange request) {
    HashMap<String, String> parameters = new HashMap<>();
    String raw_paramaters = request.getRequestURI().getQuery();
    
    if(raw_paramaters != null) {
      String[] pair_parameters = raw_paramaters.split("&");
      
      for(String pair : pair_parameters) {
        String key = pair.split("=")[0];
        String value = pair.split("=")[1];
        parameters.put(key, value);
      }
    }
    
    return parameters;
  }

  public static JSONObject attributes(HttpExchange request) {
    JSONObject attributes = null;
    InputStreamReader isr = null;
    
    try {
      isr = new InputStreamReader(request.getRequestBody(), "utf-8");
      BufferedReader br = new BufferedReader(isr);
      String raw_attributes = br.readLine();
      
      attributes = new JSONObject(raw_attributes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return attributes;
  }
}
