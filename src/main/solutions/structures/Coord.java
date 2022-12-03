package main.solutions.structures;
import java.util.*;

public class Coord {
    final int x, y;
    private int hashcode;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
        this.hashcode = Objects.hash(x, y);
    }
    public Coord(String commaSeparated) {
        String[] split = commaSeparated.split(", ");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    @Override
    public String toString() {
        if (this == null) {
            return "Null";
        } else {
            return "(" + this.getX() + ", " + this.getY() + ")";
        }
    }

    // Override hashing and equals methods so can use hashset.contains
    @Override
    public int hashCode() {
        return 1009 * this.getX() + this.getY();
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Coord)) {
            return false;
        }
        Coord coord_other = (Coord) other;
        return coord_other.getX() == this.getX() && coord_other.getY() == this.getY();
    }

    public int getManhattan(Coord otherCoord){
        return Math.abs(this.x - otherCoord.getX()) + Math.abs(this.y - otherCoord.getY());
    }
    // returns -2 as an error or -1 for overlapping closeness
    public Coord findClosestManhattan(CoordSet coordSet) {

        // automatically add in safe/dangerous areas
        if (coordSet.contains(this)) {
            return this;
        }
        Coord closest = null;
        int minDistance = Integer.MAX_VALUE;
        boolean many = false;
        for (Coord coord: coordSet) {
            int distance = this.getManhattan(coord);
            if (distance < minDistance) {
                minDistance = distance;
                many = false;
                closest = coord;
            } else if (distance == minDistance)  {
                many = true;
                closest = null;
            }
        }
        //System.out.println("We will return " + closest + " for coordinate" + this.toString());
        return closest;
    }
}
