package server;

import model.H;
import model.HttpContainer;

public class Server {

    public static void main(String[] args) {
      H.initialize();
      startHttp();
      System.out.println("Server initialized.");
    }
      
    private static void startHttp() {
      HttpContainer http = new HttpContainer();
    }
}
