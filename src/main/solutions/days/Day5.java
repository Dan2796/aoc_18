package main.solutions.days;
import java.util.*;

class Day5 extends Day{
    Day5(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 5;
    }

    Polymer polymer = new Polymer();
    @Override
    void parseInput() {
        String stringPolymer = input.next();
        for (int i = 0; i < stringPolymer.length(); i++) {
            polymer.add(stringPolymer.charAt(i));
        }

    }

    @Override
    Integer getSolutionPart1() {
        return polymer.count();
    }

    @Override
    Integer getSolutionPart2() {
        int shortest = polymer.size();
        for (int i = 65; i <= 90; i++)
            shortest = Math.min(polymer.removeAndCount(i), shortest);
        return shortest;
    }
}

class Polymer extends ArrayList<Character> {
    public int count() {
        Polymer new_polymer = new Polymer();
        new_polymer.addAll(this);
        int i = 0;
        while (i < new_polymer.size() - 1) {
            if (Math.abs(new_polymer.get(i) - new_polymer.get(i + 1)) == 32) {
                new_polymer.remove(i);
                new_polymer.remove(i);
                i--;
                if (i > -1)
                    i--;
            }
            i++;
        }
        return new_polymer.size();
    }
    public Polymer removeCharacter(int toRemove) {
        Polymer new_polymer = new Polymer();
        for (char letter: this) {
            if (letter != toRemove & letter != toRemove + 32)
                new_polymer.add(letter);
        }
        return new_polymer;
    }
    public int removeAndCount(int toRemove) {
        return this.removeCharacter(toRemove).count();
    }
}
