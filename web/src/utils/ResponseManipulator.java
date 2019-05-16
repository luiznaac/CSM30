package utils;

import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class ResponseManipulator {

  public static void send(HttpExchange request, String response) {
    try {
      request.sendResponseHeaders(200, response.length());
      OutputStream os = request.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}
