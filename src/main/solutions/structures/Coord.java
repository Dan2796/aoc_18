package main.solutions.structures;

public class Coord {
    final int x, y;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
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
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

    // Override hashing and equals methods so can use hashset.contains
    @Override
    public int hashCode() {
        return this.getX() * 33 + this.getY();
    }
    public boolean equals(Coord otherCoord) {
        if (this.getX() == otherCoord.getX() && this.getY() == otherCoord.getY()) {
            return true;
        } else {
            return false;
        }
    }


    public int getManhattan(Coord otherCoord){
        return Math.abs(this.x - otherCoord.getX()) + Math.abs(this.y - otherCoord.getY());
    }
    // returns -2 as an error or -1 for overlapping closeness
    public Coord findClosestManhattan(CoordSet coordSet) {
        Coord closest = null;
        int minDistance = Integer.MAX_VALUE;
        int numberAtMin = 1;
        for (Coord coord: coordSet) {
            int distance = this.getManhattan(coord);
            // including the point itself automatically
            if (distance == 0) {
                //System.out.println("Found you" + coord.toString());
                return coord;
            } else if (distance < minDistance) {
                minDistance = distance;
                numberAtMin += 1;
                closest = coord;
            } else if (distance == minDistance)  {
                closest = null;
            }
        }
        //System.out.println("We will return " + closest + " for coordinate" + this.toString());
        return closest;
    }
}
