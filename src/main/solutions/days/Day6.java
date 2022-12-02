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
        Coord test1 = new Coord("1, 3");
        Coord test2 = new Coord("1, 3");
        System.out.println(test1.equals(test2));
        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());
    }

    CoordMap innerSpaceWithClosest = new CoordMap();
    CoordSet infiniteCoords = new CoordSet();
    HashMap<Coord, Integer> areaSizes = new HashMap<>();
    @Override
    Integer getSolutionPart1() {
        //System.out.println(outline.size());
        for (Coord coord: outline) {
            //System.out.println("In the outline" + coord.toString());
            infiniteCoords.add(coord.findClosestManhattan(safeOrDanger));
        }
        for (Coord coord: innerSpace) {
            innerSpaceWithClosest.put(coord, coord.findClosestManhattan(safeOrDanger));
        }

        for (Coord coord: outline) {
            //System.out.println(coord.toString());
        }
        for (Coord key: innerSpaceWithClosest.values()) {
            areaSizes.put(key, areaSizes.get(key) == null ? 0 : areaSizes.get(key) + 1);
        }
        int maxArea = 0;
        for (Coord key: areaSizes.keySet()) {
            if (key != null)
              //System.out.println(key.toString() + areaSizes.get(key));
            //System.out.println(areaSizes.get(key));
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
