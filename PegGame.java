package peggame;

import java.util.Collection;

public interface PegGame {
    public Collection<Move> getPossibleMoves();
    public GameState getGameState();
    public void makeMove(Move move) throws PegGameException;
    // public void setGameState(GameState stalemate);
    public int pegsLeft();
    public void setGameState(GameState inProgress);
}
     
