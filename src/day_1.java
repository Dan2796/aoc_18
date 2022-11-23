import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class day_1 {
    public static void main(String[] args) {
      File file = new File("input_files/day_1.txt");
      //File file = new File("input_files/example_input_day_1.txt");
      Scanner input;
      try {
          input = new Scanner(file);
      } catch ( FileNotFoundException ex) {
          throw new RuntimeException(ex);
      }
      ArrayList<FrequencyChange> parsedInput = new ArrayList<>();
      while (input.hasNext()) {
          String nextInput = input.next();
          FrequencyChange nextFrequencyChange = new FrequencyChange(nextInput);
          parsedInput.add(nextFrequencyChange);
      }

      int currentFrequency = 0;
      for (int i = 0; i < parsedInput.size(); i++) {
          //parsedInput.get(i).printFrequencyChange();
          if (parsedInput.get(i).getSign() == '+'){
              currentFrequency += parsedInput.get(i).getQuantity();
          }
          if (parsedInput.get(i).getSign() == '-'){
              currentFrequency -= parsedInput.get(i).getQuantity();
          }
      }
      System.out.println("Solution to part 1: " + currentFrequency);

      // reset counter for part 2
      currentFrequency = 0;
      ArrayList<Integer> seenBefore = new ArrayList<>();
      seenBefore.add(0);
      boolean foundAnswer = false;
      while (foundAnswer == false) {
          for (int i = 0; i < parsedInput.size(); i++) {
              if (parsedInput.get(i).getSign() == '+') {
                  currentFrequency += parsedInput.get(i).getQuantity();
              }
              if (parsedInput.get(i).getSign() == '-') {
                  currentFrequency -= parsedInput.get(i).getQuantity();
              }
              if (seenBefore.contains(currentFrequency)) {
                  foundAnswer = true;
                  break;
              }
              //System.out.println(currentFrequency);
              seenBefore.add(currentFrequency);
          }
      }
      System.out.println("Solution to part 2: " + currentFrequency);
    }
}

class FrequencyChange {
    private char sign;
    private int quantity;
    FrequencyChange(String bothInputs) {
        this.sign = bothInputs.charAt(0);
        this.quantity = Integer.parseInt(bothInputs.substring(1));
    }
    FrequencyChange(char sign, int quantity) {
        this.sign = sign;
        this.quantity = quantity;
    }
    char getSign() {
        return this.sign;
    }
    int getQuantity() {
        return this.quantity;
    }
    void setSign(char sign) {
        this.sign = sign;
    }
    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    void printFrequencyChange() {
        System.out.println("Sign is " + this.sign + " and quantity is " + this.quantity);
    }
}

