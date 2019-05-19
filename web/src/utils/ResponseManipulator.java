package utils;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;

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
  
  public static void sendImage(HttpExchange request, File file) {
    try {
      OutputStream outputStream = request.getResponseBody();
      request.sendResponseHeaders(200, file.length());
      Files.copy(file.toPath(), outputStream);
      outputStream.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}
