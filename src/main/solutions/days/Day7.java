package main.solutions.days;
import java.util.*;

class Day7 extends Day{
    final private int WORKERNUMBER;
    final private int BASETIME;
    Day7(boolean actual) {
        super(actual);
        WORKERNUMBER = actual ? 5 : 2;
        BASETIME = actual ? 60 : 0;
    }

    @Override
    int getDay() {
        return 7;
    }

    // hashmap from step to a set of the other steps that need to be done first (ordered by key)
    TreeMap<Character, HashSet<Character>> orderConditions = new TreeMap<>();
    // need a part 2 version as a copy
    TreeMap<Character, HashSet<Character>> orderConditionsP2 = new TreeMap<>();
    // for part 2 need a map of the times for each step
    HashMap<Character, Integer> stepTimesLeft = new HashMap<>();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            String condition = input.nextLine();
            char doFirst = condition.charAt(5);
            char doAfter = condition.charAt(36);
            if (!orderConditions.containsKey(doAfter))
                orderConditions.put(doAfter, new HashSet());
            // add doFirst too to capture any that have no dependencies, e.g. A in the example
            if (!orderConditions.containsKey(doFirst))
                orderConditions.put(doFirst, new HashSet());
            orderConditions.get(doAfter).add(doFirst);
            // copy for part 2
            for (Character key: orderConditions.keySet()) {
                orderConditionsP2.put(key, orderConditions.get(key));
            }
            stepTimesLeft.put(doFirst, doFirst + BASETIME - 64);
            stepTimesLeft.put(doAfter, doAfter + BASETIME - 64);
        }
    }

    @Override
    StringBuilder getSolutionPart1() {
        StringBuilder correctOrder = new StringBuilder();
        HashSet<Character> completed = new HashSet<>();
        while(!orderConditions.isEmpty()) {
            for (Character key: orderConditions.keySet()) {
                if (completed.containsAll(orderConditions.get(key))) {
                    correctOrder.append(key);
                    completed.add(key);
                    orderConditions.remove(key);
                    break;
                }
            }
        }
        return correctOrder;
    }

    @Override
    Integer getSolutionPart2() {
        HashSet<Character> completed = new HashSet<>();
        int totalTimeWorked = 0;
        while(!orderConditionsP2.isEmpty()) {
            // Assign workers
            int workersLeft = WORKERNUMBER;
            int timeTillCheck = BASETIME + 1000;
            HashSet<Character> currentlyWorked = new HashSet<>();
            for (Character key: orderConditionsP2.keySet()) {
                if (!currentlyWorked.contains(orderConditionsP2.get(key))) {
                    if (completed.containsAll(orderConditionsP2.get(key))) {
                        if(workersLeft > 0) {
                            currentlyWorked.add(key);
                            timeTillCheck = Math.min(timeTillCheck, stepTimesLeft.get(key));
                            workersLeft--;
                        }
                    }
                }
            }
            // now go through worked ones, reduce time left on the task and mark done if so
            for (char step: currentlyWorked) {
                stepTimesLeft.put(step, stepTimesLeft.get(step) - timeTillCheck);
                if (stepTimesLeft.get(step) == 0) {
                    completed.add(step);
                    orderConditionsP2.remove(step);
                }
            }
            totalTimeWorked += timeTillCheck;
        }
        return totalTimeWorked;
    }
}
