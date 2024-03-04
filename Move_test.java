package peggame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Move_test {
    @Test
    public void test_Move(){
        // setup
        Location from = new Location(4, 5);
        Location to = new Location(6, 7);

        // invoke 
        Move move = new Move(from, to);
        
        // analyze 
        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());
        assertEquals("The peg moved from to ", move.toString()); // making sure that toString method returns the expected representation
    }

    @Test
    public void testEquals(){
        // setup
        Location from_1 = new Location(4, 5);
        Location to_1 = new Location(6, 7);

        // invoke 
        Move move_1 = new Move(from_1, to_1);

        // setup
        Location from_2 = new Location(4, 5);
        Location to_2 = new Location(6, 7);

        // invoke
        Move move_2 = new Move(from_2, to_2);

        // analyze
        assertTrue(move_1.equals(move_2)); // making sure that the equals method works corectly for move objects with the same location

        // setup
        Location from_3 = new Location(1, 2);
        Location to_3 = new Location(3, 4);

        // invoke 
        Move move_3 = new Move(from_3, to_3);
        // analyze
        assertFalse(move_1.equals(move_3)); // making sure that the equals method works corectly for move objects with different location
    }
}
