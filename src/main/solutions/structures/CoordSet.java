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

    public HashMap<String, Integer> getMaxMinXYValues() {
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

    public CoordSet getOutline(HashMap<String, Integer> maxMins) {
        CoordSet outline = new CoordSet();
        // top horizontal line with corners
        for (int i = maxMins.get("minX"); i <= maxMins.get("maxX"); i++) {
            outline.add(new Coord(i, maxMins.get("minY")));
        }
        // bottom horizontal line with corners
        for (int i = maxMins.get("minX"); i <= maxMins.get("maxX"); i++) {
            outline.add(new Coord(i, maxMins.get("maxY")));
        }
        // left vertical line without corners
        for (int i = maxMins.get("minY") + 1; i < maxMins.get("maxY"); i++) {
            outline.add(new Coord(maxMins.get("minX"), i));
        }
        // right vertical line with corners
        for (int i = maxMins.get("minY") + 1; i < maxMins.get("maxY"); i++) {
            outline.add(new Coord(maxMins.get("maxX"), i));
        }
        return outline;
    }

    // take set of coords and fill in area within their perimeter
    public CoordSet fillInCoords(HashMap<String, Integer> maxMins) {
        CoordSet fullSet = new CoordSet();
        for (int i = maxMins.get("minX") + 1; i < maxMins.get("maxX"); i++) {
            for (int j = maxMins.get("minY") + 1; j < maxMins.get("maxY"); j++) {
                fullSet.add(new Coord(i, j));
            }
        }
        return fullSet;
    }
}
