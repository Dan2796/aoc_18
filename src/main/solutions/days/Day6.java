package main.solutions.days;
import main.solutions.structures.*;
import java.util.*;
class Day6 extends Day{
    // note that the distance comparison variable differs for whether using example
    // or not, so this is sorted in the constructor.
    final private int DistanceForP2;
    Day6(boolean actual) {
        super(actual);
        DistanceForP2 = actual ? 10000 : 32;
    }

    @Override
    int getDay() {
        return 6;
    }

    CoordSet safeOrDanger = new CoordSet();
    CoordSet innerSpace = new CoordSet();
    HashMap<String, Integer> maxMins;
    CoordSet outline = new CoordSet();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            safeOrDanger.add(new Coord(input.nextLine()));
        }
        maxMins = safeOrDanger.getMaxMinXYValues();
        outline = safeOrDanger.getOutline(maxMins);
        innerSpace = safeOrDanger.fillInCoords(maxMins);
    }

    @Override
    Integer getSolutionPart1() {
        CoordSet infiniteCoords = new CoordSet();
        for (Coord coord: outline) {
            Coord theClosest = coord.findClosestManhattan(safeOrDanger);
            infiniteCoords.add(theClosest);
        }

        HashMap<Coord, Coord> innerSpaceWithClosest = new HashMap<>();
        for (Coord coord: innerSpace) {
            Coord theClosest = coord.findClosestManhattan(safeOrDanger);
            innerSpaceWithClosest.put(coord, theClosest);
        }

        HashMap<Coord, Integer> areaSizes = new HashMap<>();
        for (Coord key: innerSpaceWithClosest.values()) {
            areaSizes.put(key, areaSizes.get(key) == null ? 1 : areaSizes.get(key) + 1);
        }

        int maxArea = 0;
        for (Coord key: areaSizes.keySet()) {
            if (!infiniteCoords.contains(key)) {
                maxArea = Math.max(maxArea, areaSizes.get(key));
            }
        }
        return maxArea;
    }

    // Note that part 2 only works if the area fits within the extremes.
    // Would need an expanded maxmin if using a very large distance limit with a small grid
    @Override
    Integer getSolutionPart2() {
        int areaSize = 0;
        for (Coord coord: innerSpace) {
            if (coord.checkTotalManhattenDistance(safeOrDanger) < DistanceForP2) {
                areaSize++;
            }
        }
        return areaSize;
    }
}
