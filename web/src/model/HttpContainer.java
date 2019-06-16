package model;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import controller.ImageController;
import controller.IntegrationController;

public class HttpContainer {
  
  private HttpServer server;

  public HttpContainer() {
    try {
      instantiateServer();
      mapEndpoints();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  private void mapEndpoints() {
    server.createContext("/integration", IntegrationController::integrate);
    server.createContext("/image/save", ImageController::save);
    server.createContext("/image/load", ImageController::load);
    server.createContext("/image/test", ImageController::test);
    server.start();
  }
  
  private void instantiateServer() throws Exception {
      server = HttpServer.create(new InetSocketAddress(8500), 0);
  }
  
}
