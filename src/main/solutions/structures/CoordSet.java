package main.solutions.structures;
import java.util.*;

public class CoordSet extends HashSet<Coord> {
    public CoordSet() {
    }

    public void printCoordSet() {
        for (Coord coord: this) {
            System.out.println(coord.toString());
        }
    }

    public HashMap<String, Integer> getFourCorners() {
        HashMap<String, Integer> maxMins = new HashMap<>();
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Coord coord: this){
            minX = Math.min(coord.getX(), minX);
            maxX = Math.max(coord.getX(), maxX);
            minY = Math.min(coord.getY(), minY);
            maxY = Math.max(coord.getY(), maxY);
        }
        maxMins.put("minX", minX);
        maxMins.put("maxX", maxX);
        maxMins.put("minY", minY);
        maxMins.put("maxY", maxY);
        return maxMins;
    }

    // Note that the set isn't removing duplicates because of equals() issue, so have to avoid them in the code below
    // by shortening the horizontal lines
    public CoordSet getOutline() {
        HashMap<String, Integer> maxMins = this.getFourCorners();
        CoordSet fullSet = new CoordSet();
        for (int i = maxMins.get("minX") + 1; i < maxMins.get("maxX"); i++) {
            fullSet.add(new Coord(i, maxMins.get("minY")));
        }
        for (int i = maxMins.get("minX") + 1; i < maxMins.get("maxX"); i++) {
            fullSet.add(new Coord(i, maxMins.get("maxY")));
        }
        for (int i = maxMins.get("minY"); i <= maxMins.get("maxY"); i++) {
            fullSet.add(new Coord(i, maxMins.get("minX")));
        }
        for (int i = maxMins.get("minY"); i <= maxMins.get("maxY"); i++) {
            fullSet.add(new Coord(i, maxMins.get("maxX")));
        }
        return fullSet;
    }

    // take set of coords and fill in area within their perimeter
    public CoordSet fillInCoords() {
        HashMap<String, Integer> maxMins = this.getFourCorners();
        CoordSet fullSet = new CoordSet();
        for (int i = maxMins.get("minX") + 1; i < maxMins.get("maxX"); i++) {
            for (int j = maxMins.get("minY") + 1; j < maxMins.get("maxY"); j++) {
                fullSet.add(new Coord(i, j));
            }
        }
        return fullSet;
    }
}
