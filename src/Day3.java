import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day3 {
    public static void main(String[] args) {
        File file = new File("input_files/day_3.txt");
        //File file = new File("input_files/example_input_day_3.txt");
        Scanner input;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        //ArrayList<Claim> claims = new ArrayList<>();
        Set<ArrayList<Integer>> claimedPartsOfCloth = new HashSet<>();
        Set<ArrayList<Integer>> usedPartsOfCloth = new HashSet<>();
        while (input.hasNext())
            new Claim(input.nextLine()).coordsAdd(claimedPartsOfCloth, usedPartsOfCloth);
        System.out.println("Solution to part 1: " + usedPartsOfCloth.size());

        // Reset input for read through
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        String answerP2 = "not found";
        while (input.hasNext()) {
            answerP2 = new Claim(input.nextLine()).checkClaimed(usedPartsOfCloth);
            if (!answerP2.equals("not found"))
                break;
        }
        System.out.println("Solution to part 2: " + answerP2);
    }
}

class Claim {
    final private String id;
    final private int topMargin, leftMargin, width, height;

    Claim(String claim) {
        String[] claimString = claim.split(" ");
        id = claimString[0].replace("#", "");
        leftMargin = Integer.parseInt(claimString[2].replace(":", "").split(",")[0]);
        topMargin = Integer.parseInt(claimString[2].replace(":", "").split(",")[1]);
        width = Integer.parseInt(claimString[3].split("x")[0]);
        height = Integer.parseInt(claimString[3].split("x")[1]);
    }

    public String getid() {
        return id;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // note using an array list for the coords not an int[] because need to use contains function and this doesn't
    // work when trying to compare int[] (ArrayList doesn't override the equals method)
    public void coordsAdd(Set<ArrayList<Integer>> claimedPartsOfCloth, Set<ArrayList<Integer>> usedPartsOfCloth) {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                ArrayList<Integer> thisCoord = new ArrayList<>();
                thisCoord.add(j + this.getLeftMargin());
                thisCoord.add(i + this.getTopMargin());
                if (claimedPartsOfCloth.contains(thisCoord))
                    usedPartsOfCloth.add(thisCoord);
                else
                    claimedPartsOfCloth.add(thisCoord);
            }
        }
    }

    public String checkClaimed(Set<ArrayList<Integer>> usedPartsOfCloth) {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                ArrayList<Integer> thisCoord = new ArrayList<>();
                thisCoord.add(j + this.getLeftMargin());
                thisCoord.add(i + this.getTopMargin());
                if (usedPartsOfCloth.contains(thisCoord))
                    return "not found";
            }
        }
        // only returns if none of the coords are in the used set
        return this.getid();
    }
}
