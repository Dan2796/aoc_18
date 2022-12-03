package main.solutions.days;
import main.solutions.structures.*;
import java.util.*;
class Day6 extends Day{
    Day6(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 6;
    }

    CoordSet safeOrDanger = new CoordSet();
    CoordSet outline = new CoordSet();
    CoordSet innerSpace = new CoordSet();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            safeOrDanger.add(new Coord(input.nextLine()));
        }
        outline = safeOrDanger.getOutline();
        innerSpace = safeOrDanger.fillInCoords();
    }

    CoordMap innerSpaceWithClosest = new CoordMap();
    CoordSet infiniteCoords = new CoordSet();
    HashMap<Coord, Integer> areaSizes = new HashMap<>();
    @Override
    Integer getSolutionPart1() {
        for (Coord coord: outline) {
            Coord theClosest = coord.findClosestManhattan(safeOrDanger);
            infiniteCoords.add(theClosest);
        }
        for (Coord coord: innerSpace) {
            Coord theClosest = coord.findClosestManhattan(safeOrDanger);
            innerSpaceWithClosest.put(coord, theClosest);
            if (theClosest != null) {
                System.out.println(coord.toString() + " is closest to " + theClosest.toString());
            } else {
                System.out.println(coord.toString() + " is null.");
            }
        }

        for (Coord key: innerSpaceWithClosest.values()) {
            areaSizes.put(key, areaSizes.get(key) == null ? 0 : areaSizes.get(key) + 1);
        }
        int maxArea = 0;
        for (Coord key: areaSizes.keySet()) {
            if (key != null)
            if (!infiniteCoords.contains(key)) {
                maxArea = Math.max(maxArea, areaSizes.get(key));
            }
        }

        return maxArea;
    }

    @Override
    String getSolutionPart2() {
        return "tbd";
    }
}
