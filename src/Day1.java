import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day1 {
    public static void main(String[] args) {
      File file = new File("input_files/day_1.txt");
      //File file = new File("input_files/example_input_day_1.txt");
      Scanner input;
      try {
          input = new Scanner(file);
      } catch ( FileNotFoundException ex) {
          throw new RuntimeException(ex);
      }
      ArrayList<Instruction> parsedInput = new ArrayList<>();
      while (input.hasNext()) {
          String nextInput = input.next();
          Instruction instruction = new Instruction(nextInput);
          parsedInput.add(instruction);
      }

      FrequencyTracker part1Tracker = new FrequencyTracker();
      for (Instruction instruction : parsedInput)
          part1Tracker.changeByInstruction(instruction);
      System.out.println("Solution to part 1: " + part1Tracker.getFrequency());

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
      System.out.println("Solution to part 2: " + part2Tracker.getFrequency());
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

