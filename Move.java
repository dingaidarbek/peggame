package peggame;

public class Move{
    private Location from;
    private Location to;

    public Move(Location from, Location to){
        this.from=from;
        this.to=to;
    }

    public Location getFrom(){return from;}
    public Location getTo(){return to;}

    public String toString(){
        return "Peg moved from " + from + " to " + to;
    }

    public boolean equals(Object o){
        if(!(o instanceof Move)){
            return false;
        } else {
            Move castedobject=(Move)(o);
            return (from.equals(castedobject.from)) && (to.equals(castedobject.to));
        }
    }
}
