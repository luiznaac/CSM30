package utils;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;

public class ResponseManipulator {

  public static void send(HttpExchange request, String response) {
    try {
      request.sendResponseHeaders(200, response.length());
      OutputStream outputStream = request.getResponseBody();
      outputStream.write(response.getBytes());
      outputStream.close();
      request.getResponseBody().close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void sendImage(HttpExchange request, File file) {
    try {
      request.sendResponseHeaders(200, file.length());
      OutputStream outputStream = request.getResponseBody();
      Files.copy(file.toPath(), outputStream);
      outputStream.close();
      request.getResponseBody().close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}
