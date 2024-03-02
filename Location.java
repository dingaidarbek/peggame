package peggame;

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