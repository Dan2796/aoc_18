import java.util.*;
import java.io.*;
import java.util.Collections;

public class Day4 {
    public static void main(String[] args) {
        //File file = new File("input_files/day_4.txt");
        File file = new File("input_files/example_input_day_4.txt");
        Scanner input;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException();
        }
        ArrayList<String> rawInput = new ArrayList<>();
        while (input.hasNext()) {
            rawInput.add(input.nextLine());
        }
        Collections.sort(rawInput);

        // process strings - open new shift each time hit a "begin shift line" after adding old one to the end
        ArrayList<NightShift> nightShifts = new ArrayList<>();
        NightShift nextShift = new NightShift(rawInput.get(0));
        for (String line: rawInput.subList(1, rawInput.size())) {
            String action = line.substring(line.indexOf("]") + 2);
            if (action.matches("(.*)begins shift(.*)")) {
                nightShifts.add(nextShift);
                nextShift = new NightShift(line);
            }
            if (action.matches("(.*)falls asleep(.*)")) {
                nextShift.addSleepTime(line);
            }
            if (action.matches("(.*)wakes up(.*)")) {
                nextShift.addWakeTime(line);
            }
        }
        // add last next shift item because list is ended
        nightShifts.add(nextShift);
        for (NightShift shift: nightShifts) {
            System.out.println("Date + " + shift.getDate());
            System.out.println("Guard on duty: " + shift.getGuard());
            shift.printSleepCalendar();
        }
    }
}

class NightShift {
    String date, guard;
    HashMap sleepCalendar = new HashMap<Integer, Integer>();
    // initialise with awake at all times

    NightShift(String beginShiftLine) {
        date = beginShiftLine.substring(beginShiftLine.indexOf("[") + 1, beginShiftLine.indexOf("]"));
        guard = beginShiftLine.substring(beginShiftLine.indexOf("#") + 1, beginShiftLine.indexOf('b') - 1);
        for (int i = 0; i <= 59; i++){
            sleepCalendar.put(i, 0);
        }
    }
    public void addSleepTime(String sleepLine) {
        int sleepTime = Integer.parseInt(sleepLine.substring(sleepLine.indexOf(" ") + 4, sleepLine.indexOf("]")));
        for (int i = sleepTime; i <= 59; i++) {
            sleepCalendar.put(i, 1);
        }
    }
    public void addWakeTime(String wakeLine) {
        int wakeupTime = Integer.parseInt(wakeLine.substring(wakeLine.indexOf(" ") + 4, wakeLine.indexOf("]")));
        for (int i = wakeupTime; i <= 59; i++) {
            sleepCalendar.put(i, 0);
        }
    }
    public String getDate() {
        return date;
    }
    public String getGuard() {
        return guard;
    }
    public void printSleepCalendar() {
        for (int i = 0; i <= 59; i++) {
            System.out.print(sleepCalendar.get(i));
        }
        System.out.print("\n");
    }
}
