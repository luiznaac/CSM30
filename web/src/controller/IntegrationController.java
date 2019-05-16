package controller;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Integration;
import utils.RequestManipulator;
import utils.ResponseManipulator;

public class IntegrationController implements HttpHandler {
  
  @Override
  public void handle(HttpExchange arg0) throws IOException {
  }
  
  public static void integrate(HttpExchange request) throws IOException {
    HashMap<String, String> parameters = RequestManipulator.parameters(request);
    JSONObject attributes = RequestManipulator.attributes(request);
    
    Integration integration = new Integration(parameters, attributes);

    ResponseManipulator.send(request, integration.integrate());
  }

}
