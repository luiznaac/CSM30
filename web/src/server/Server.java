package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

import controller.ImageController;
import controller.IntegrationController;

public class Server {

    public static void main(String[] args) throws IOException {
      HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);

      server.createContext("/integration", IntegrationController::integrate);
      server.createContext("/image/save", ImageController::save);
      server.createContext("/image/load", ImageController::load);
      server.createContext("/image/test", ImageController::test);
      server.start();
    } 
}
