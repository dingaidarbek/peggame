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
        return "Peg moves from " + from + " to " + to;
    }

    public boolean equals(Object o){
        if(!(o instanceof Move)){
            return false;
        }
        Move castedobject = (Move)(o);
        return ((this.from.equals(castedobject.getFrom())) && (this.to.equals(castedobject.getTo())));
    }
}
