package peggame;

public enum GameState {
    NOT_STARTED("No moves have been made yet!"),
    IN_PROGRESS("at least one move has been made and game has not ended yet."),
    STALEMATE("two or more pegs remain on the board, but there are no more valid moves."),
    WON("the game has been played to completion and ended in victory. There is one peg remaining on the board.");

    private String description;

    public String getDescription(){return description;}

    GameState(String description){
        this.description=description;
    }
    
    public String toString(){
        return description;
    }
}