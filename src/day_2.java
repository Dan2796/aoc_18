import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class day_2 {
    public static void main(String[] args) {
        File file = new File("input_files/day_2.txt");
        //File file = new File("input_files/example_input_day_2.txt");
        Scanner input;
        try {
            input = new Scanner(file);
        }
        catch ( FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        Counter p1counter = new Counter();
        while (input.hasNext()) {
            p1counter.counterIncrement(input.next().toCharArray());
        }
        System.out.println("Solution to part 1: " + p1counter.getChecksum());

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