package utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.json.JSONObject;
import com.sun.net.httpserver.Headers;
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
    JSONObject attributes = new JSONObject();
    InputStreamReader isr = null;
    
    try {
      isr = new InputStreamReader(request.getRequestBody(), "utf-8");
      BufferedReader br = new BufferedReader(isr);
      String raw_attributes = "";
      String line;
      
      while((line = br.readLine()) != null) 
        raw_attributes += line;
      
      if(raw_attributes.length() > 0)
        attributes = new JSONObject(raw_attributes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return attributes;
  }
  
  public static void image(HttpExchange request) {
    try {
      Headers responseHeaders = request.getResponseHeaders();
      responseHeaders.set("Content-Type", "application/png");
      request.sendResponseHeaders(200, 0);
      
      InputStream inputStream = request.getRequestBody();
      byte[] buffer = new byte[4096];
      int lengthRead;
      FileOutputStream fileOutputStream = new FileOutputStream("cassio_received.jpg");

      while ((lengthRead = inputStream.read(buffer, 0, 4096)) > 0)
      {
          fileOutputStream.write(buffer, 0, lengthRead);
      }

      fileOutputStream.close();

      request.getResponseBody().close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}

