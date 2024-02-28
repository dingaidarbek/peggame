package peggame;

public class PegGameException extends java.lang.Exception{

   private String message;

   public String getMessage(){return message;}

    public PegGameException(String message){
        this.message=message;
    }

    
}
