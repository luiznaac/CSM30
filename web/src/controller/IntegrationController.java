package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    InputStreamReader isr = new InputStreamReader(request.getRequestBody(), "utf-8");
    BufferedReader br = new BufferedReader(isr);
    
    Integration integration = new Integration(parameters, attributes);

    ResponseManipulator.send(request, integration.integrate());
  }

}