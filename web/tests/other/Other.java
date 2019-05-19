package other;

import java.io.FileReader;
import java.util.Scanner;

public class Other {

  public static void main(String[] args) {
    try {
      Scanner in = new Scanner(new FileReader("google.png"));
      String file = "";
      while(in.hasNextLine())
        file += in.nextLine();
    } 
    catch(Exception e) {
      
    }
  }
  
}
