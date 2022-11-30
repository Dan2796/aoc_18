import java.util.*;
import java.io.*;
public class parseInput {
    public static String readInputToString(boolean actual, int day) {
        File file;
        if (actual) {
            file = new File("input_files/day_" + day + ".txt");
        }
        else {
            file = new File("input_files/example_input_day_" + day + ".txt");
        }
    Scanner input;
    try {
        input = new Scanner(file);
    } catch (FileNotFoundException ex) {
        throw new RuntimeException();
    }
    return input.next();
    }
}
