package Example;

import java.lang.RuntimeException;

public class MuffinException extends RuntimeException{

   public MuffinException() {}

//Constructor that accepts a message
   public MuffinException(String message) {
      super(message);
   }
}
