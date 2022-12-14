package main.solutions.days;
import java.util.*;

class Day4 extends Day{
    Day4(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 4;
    }

    ArrayList<String> rawInput = new ArrayList<>();
    AllShifts nightShifts = new AllShifts();
    NightShift nextShift;
    @Override
    void parseInput() {
        while (input.hasNext()) {
            rawInput.add(input.nextLine());
        }
        Collections.sort(rawInput);
        nextShift = new NightShift(rawInput.get(0));
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

    }

    @Override
    Integer getSolutionPart1() {
        String sleepiestGuard =  nightShifts.findSleepiestGuard();
        int sleepiestMinute = nightShifts.findSleepiestMinute(sleepiestGuard)[0];
        return Integer.parseInt(sleepiestGuard) * sleepiestMinute;
    }

    @Override
    Integer getSolutionPart2() {
        return nightShifts.findSleepiestGuardMinuteCombo();
    }
}

class AllShifts extends ArrayList<NightShift> {
    public HashSet<String> getAllGuards() {
        HashSet<String> allGuards = new HashSet<>();
        for (NightShift shift : this) {
            allGuards.add(shift.getGuard());
        }
        return allGuards;
    }
    public AllShifts getGuardShifts(String guardId) {
        AllShifts guardShifts = new AllShifts();
        for (NightShift shift : this) {
            if (shift.getGuard().equals(guardId)) {
                guardShifts.add(shift);
            }
        }
        return guardShifts;
    }
    public int getGuardSleepTotal(String guardId) {
        Integer guardSleepTime = 0;
        for (NightShift shift : this.getGuardShifts(guardId)) {
            guardSleepTime += shift.getTotalAsleep();
        }
        return guardSleepTime;
    }
    public String findSleepiestGuard() {
        int longestSleepTotal = 0;
        String sleepiestGuard = "0";
        for (String guard : this.getAllGuards()) {
            if (getGuardSleepTotal(guard) > longestSleepTotal) {
                longestSleepTotal = getGuardSleepTotal(guard);
                sleepiestGuard = guard;
            }
        }
        return sleepiestGuard;
    }
    public int[] findSleepiestMinute(String guardId) {
        int sleepiestMinute = 0;
        int numberOfTimesAsleep = 0;
        for (int i = 0; i <= 59; i++) {
            int timesAsleep = 0;
            for  (NightShift shift : this.getGuardShifts(guardId)) {
                timesAsleep += shift.checkAsleepAtMinute(i);
            }
            if (timesAsleep > numberOfTimesAsleep) {
                sleepiestMinute = i;
                numberOfTimesAsleep = timesAsleep;
            }
        }
        return new int[] {sleepiestMinute, numberOfTimesAsleep};
    }
    public int findSleepiestGuardMinuteCombo() {
        int sleepiestMinute = 0;
        int numberOfTimesAsleep = 0;
        String sleepiestGuardOnMinute = "0";
        for (String guard: this.getAllGuards()) {
            if (this.findSleepiestMinute(guard)[1] > numberOfTimesAsleep) {
                sleepiestMinute = this.findSleepiestMinute(guard)[0];
                numberOfTimesAsleep = this.findSleepiestMinute(guard)[1];
                sleepiestGuardOnMinute = guard;
            }
        }
        return Integer.parseInt(sleepiestGuardOnMinute) * sleepiestMinute;
    }
}
class NightShift {
    private String date, guard;
    private HashMap sleepCalendar = new HashMap<Integer, Integer>();
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
            System.out.print((sleepCalendar.get(i)).getClass());
        }
        System.out.print("\n");
    }
    public Integer getTotalAsleep() {
        int totalAsleep = 0;
        for (int i = 0; i <= 59; i++) {
            totalAsleep += (int) sleepCalendar.get(i);
        }
        return totalAsleep;
    }
    public int checkAsleepAtMinute(int minute) {
        return (int) sleepCalendar.get(minute);
    }
}
