package peggame;

import java.util.Collection;

/**An interface that contains five abstract methods*/
public interface PegGame {
    public Collection<Move> getPossibleMoves();
    public GameState getGameState();
    public void makeMove(Move move) throws PegGameException;
    public int pegsLeft();
    public void setGameState(GameState gameState);
}
     
