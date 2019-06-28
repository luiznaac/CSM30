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
  
  public static void load(HttpExchange request) throws IOException {
    HashMap<String, String> parameters = RequestManipulator.parameters(request);
    JSONObject attributes = RequestManipulator.attributes(request);
    
    Image image = new Image(parameters, attributes);
    
    ResponseManipulator.sendImage(request, image.load());
  }
  
  public static void test(HttpExchange request) throws IOException {
    RequestManipulator.image(request);    
    ResponseManipulator.send(request, "teste");
  }
  
  public static void loadSignal(HttpExchange request) throws IOException {
    new Thread() {
      @Override
      public void run() {
        HashMap<String, String> parameters = RequestManipulator.parameters(request);
        JSONObject attributes = RequestManipulator.attributes(request);
        ResponseManipulator.send(request, "Received.");
        
        Image.processSignal((String)attributes.get("signal"), (String)attributes.get("username"));
      }
    }.start();
  }
  
  public static void listImages(HttpExchange request) {
    new Thread() {
      @Override
      public void run() {
        HashMap<String, String> parameters = RequestManipulator.parameters(request);
        JSONObject attributes = RequestManipulator.attributes(request);
        String list = Image.listImages((String)attributes.get("username"));
        
        ResponseManipulator.send(request, list);
      }
    }.start();
  }
  
  public static void getImage(HttpExchange request) {
    new Thread() {
      @Override
      public void run() {
        HashMap<String, String> parameters = RequestManipulator.parameters(request);
        JSONObject attributes = RequestManipulator.attributes(request);
        ResponseManipulator.sendImage(request, Image.load((Integer)attributes.get("id")));
      }
    }.start();
  }

}
