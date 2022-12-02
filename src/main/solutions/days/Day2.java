package main.solutions.days;
import java.util.*;

class Day2 extends Day{
    Day2(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 2;
    }

    ArrayList<char[]> boxes = new ArrayList<>();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            boxes.add(input.next().toCharArray());
        }

    }


    @Override
    Integer getSolutionPart1() {
        Counter p1counter = new Counter();
        for ( char[] boxid : boxes ) {
            p1counter.counterIncrement(boxid);
        }

        return p1counter.getChecksum();
    }

    @Override
    String getSolutionPart2() {
        for ( char[] i : boxes ) {
            for ( char[] j : boxes ) {
                if(boxMatch(i, j) != "Not found")
                    return(boxMatch(i, j));
            }
        }
        return "Could not find";
    }

    static String boxMatch(char[] box1, char[] box2) {
        int not_matching = 0;
        for (int i = 0; i < box1.length; i++) {
            if (box1[i] != box2[i])
                not_matching++;
        }
        if (not_matching == 1) {
            StringBuilder solution = new StringBuilder();
            for (int i = 0; i < box1.length; i++) {
                if (box1[i] == box2[i]) {
                    solution.append(box1[i]);
                }
            }
            return solution.toString();
        }
        return "Not found";
    }
}

class Counter{
    private int doubles = 0;
    private int triples = 0;
    Counter(){}
    public void counterIncrement(char[] boxID) {
        ArrayList<Character> appearsOnce = new ArrayList<>();
        ArrayList<Character> appearsTwice = new ArrayList<>();
        ArrayList<Character> appearsThrice = new ArrayList<>();
        ArrayList<Character> appearsMany = new ArrayList<>();
        for ( char letter : boxID ) {
            if (appearsOnce.contains(letter)) {
                appearsTwice.add(letter);
                appearsOnce.remove(Character.valueOf(letter));
            } else if (appearsTwice.contains(letter)) {
                appearsThrice.add(letter);
                appearsTwice.remove(Character.valueOf(letter));
            } else if (appearsThrice.contains(letter)) {
                appearsMany.add(letter);
                appearsThrice.remove(Character.valueOf(letter));
            } else if (!appearsMany.contains(letter)){
                appearsOnce.add(letter);
            }
        }

        if (!appearsTwice.isEmpty())
            doubles++;
        if (!appearsThrice.isEmpty())
            triples++;
    }
    public int getChecksum(){
        return doubles * triples;
    }

}
