package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameState_test {
    @Test
    public void GameStateDescriptions(){ //testing the descriptions of our enum GameState
        // setup
        // these are the expected outputs 
        String NOT_STARTED_Description = "No moves have been made yet!";
        String IN_PROGRESS_Description = "at least one move has been made and game has not ended yet.";
        String STALEMATE_Description = "two or more pegs remain on the board, but there are no more valid moves.";
        String WON_Description = "the game has been played to completion and ended in victory. There is one peg remaining on the board.";
        
        // invoke
        // in this step we are getting the actual descriptions from GameState to test them 
        String not_started_Description = GameState.NOT_STARTED.getDescription(); 
        String in_progress_Description = GameState.IN_PROGRESS.getDescription();
        String stalemate_Description = GameState.STALEMATE.getDescription();
        String won_Description = GameState.WON.getDescription();
        
        // analyze 
        // testing to see if the expected objects are equal to the actual objects
        assertEquals(NOT_STARTED_Description, not_started_Description);
        assertEquals(IN_PROGRESS_Description, in_progress_Description);
        assertEquals(STALEMATE_Description, stalemate_Description);
        assertEquals(WON_Description, won_Description);
    }
}
