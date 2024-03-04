package peggame;
/**
 * Enumeration class that contains possible game state of The Peg Game.
 * 
 * This enum class contains a constructor that takes one parameter;
 * @param description the description of each state of the game.
 * 
 * 
*/

public enum GameState {
    NOT_STARTED("Not Started. No moves have been made yet!"),
    IN_PROGRESS("In Progress. At least one move has been made and game has not ended yet."),
    STALEMATE("Stalemate. Two or more pegs are remaining on the board, but there are no more valid moves."),
    WON("Victory!");

    private String description;

    public String getDescription(){return description;}

    GameState(String description){
        this.description=description;
    }
    
    public String toString(){
        return description;
    }
}