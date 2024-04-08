package PEGGAME;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Location_test {
    @Test
    public void testToString(){
        // setup
        int row = 1;
        int col = 6;
        Location location = new Location(row, col);

        // invoke
        String locationString = location.toString();

        // analyze 
        String expectedString = " The peg is at row, " + row +  " ." +  "The peg is at coloumn, " + col;
        assertEquals(expectedString, locationString);
    }
    
    @Test
    public void testEquals(){
        // setup 
        int row_1 = 1;
        int col_1 = 6;
        Location location_1 = new Location(row_1, col_1);

        int row_2 = 1;
        int col_2 = 6;
        Location location_2 = new Location(row_2, col_2); // the first two locations should be equal

        int row_3 = 3;
        int col_3 = 5;
        Location location_3 = new Location(row_3, col_3);

        // invoke
        boolean Equal = location_1.equals(location_2);
        boolean Equal_1 = location_2.equals(location_3);

        // analyze 
        assertTrue(Equal);
        assertFalse(Equal_1);
    }

}
