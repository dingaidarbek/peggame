package peggame;

public enum GameState {
    NOT_STARTED("No moves have been made yet!"),
    IN_PROGRESS("At least one move has been made and game has not ended yet."),
    STALEMATE("Two or more pegs are remaining on the board, but there are no more valid moves."),
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