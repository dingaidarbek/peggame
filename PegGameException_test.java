package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PegGameException_test{
    @Test
    public void test_PegGameException(){
        // setup
        String message = "Testing message";

        // invoke 
        // testing the constructor in PegGameexception class
        PegGameException exception = new PegGameException(message);

        // analyze
        assertEquals(message, exception.getMessage());
    }
}