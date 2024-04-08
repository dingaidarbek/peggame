package PEGGAME;

/**
 * Location class is used to locate a specific row and column on The Peg Game Board;
 * it contains a constructor that takes two parameters to specify the location.
 * @param row an int variable to specify a row on the board
 * @param col an int variable to specify a column on the board
 */
public class Location {
    private int row;
    private int col;

    public Location(int row, int col){
        this.row=row;
        this.col=col;
    } 

    public int getRow(){return row;}
    public int getCol(){return col;}

    public String toString(){
        return "(row" + row + ", col" + col + ")";
    }

    public boolean equals(Object o){
        if(!(o instanceof Location)){
            return false;
        }
        Location castedobject=(Location)(o);
        return ((this.row == castedobject.getRow()) && (this.col == castedobject.getCol()));
    }
       
      

}