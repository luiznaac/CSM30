package controller;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Image;
import utils.RequestManipulator;
import utils.ResponseManipulator;

public class ImageController implements HttpHandler {
  
  @Override
  public void handle(HttpExchange arg0) throws IOException {
  }
  
  public static void save(HttpExchange request) throws IOException {
    HashMap<String, String> parameters = RequestManipulator.parameters(request);
    JSONObject attributes = RequestManipulator.attributes(request);
    
    Image image = new Image(parameters, attributes);
    
    ResponseManipulator.send(request, image.save());
  }

}
