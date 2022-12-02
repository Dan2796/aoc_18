package main.solutions.days;
import java.util.*;
class Day1 extends Day{
    Day1(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 1;
    }

    ArrayList<Instruction> parsedInput = new ArrayList<>();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            String nextInput = input.next();
            Instruction instruction = new Instruction(nextInput);
            parsedInput.add(instruction);
        }
    }

    @Override
    Integer getSolutionPart1() {
        FrequencyTracker part1Tracker = new FrequencyTracker();
        for (Instruction instruction : parsedInput)
            part1Tracker.changeByInstruction(instruction);
        return part1Tracker.getFrequency();
    }

    @Override
    Integer getSolutionPart2() {
        FrequencyTracker part2Tracker = new FrequencyTracker();
        ArrayList<Integer> seenBefore = new ArrayList<>();
        seenBefore.add(0);
        boolean foundAnswer = false;
        while (!foundAnswer) {
            for ( Instruction instruction : parsedInput ) {
                part2Tracker.changeByInstruction(instruction);
                if (seenBefore.contains(part2Tracker.getFrequency())) {
                    foundAnswer = true;
                    break;
                }
                seenBefore.add(part2Tracker.getFrequency());
            }
        }
        return part2Tracker.getFrequency();
    }
}

class FrequencyTracker{
    private int currentFrequency = 0;
    FrequencyTracker(){}
    public void changeByInstruction(Instruction instruction){
        if (instruction.getSign() == '+') {
            currentFrequency += instruction.getQuantity();
        }
        if (instruction.getSign() == '-') {
            currentFrequency -= instruction.getQuantity();
        }
    }
    public int getFrequency(){
        return currentFrequency;
    }
}
class Instruction {
    final private char sign;
    final private int quantity;
    Instruction(String bothInputs) {
        this.sign = bothInputs.charAt(0);
        this.quantity = Integer.parseInt(bothInputs.substring(1));
    }
    char getSign() {
        return this.sign;
    }
    int getQuantity() {
        return this.quantity;
    }
    // for use during testing:
    void printInstruction() {
        System.out.println("Sign is " + this.sign + " and quantity is " + this.quantity);
    }
}



