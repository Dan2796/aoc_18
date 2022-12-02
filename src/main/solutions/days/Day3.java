package main.solutions.days;
import java.util.*;

class Day3 extends Day{
    Day3(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 3;
    }

    ArrayList<String> parsedInput = new ArrayList<>();
    @Override
    void parseInput() {
        while (input.hasNext())
            parsedInput.add(input.nextLine());
    }

    HashSet<ArrayList<Integer>> claimedPartsOfCloth = new HashSet<>();
    HashSet<ArrayList<Integer>> usedPartsOfCloth = new HashSet<>();
    @Override
    Integer getSolutionPart1() {
        for (String claimLine: parsedInput)
            new Claim(claimLine).coordsAdd(claimedPartsOfCloth, usedPartsOfCloth);
        return usedPartsOfCloth.size();
    }

    String answerP2;
    @Override
    String getSolutionPart2() {
        for (String claimLine: parsedInput) {
            answerP2 = new Claim(claimLine).checkClaimed(usedPartsOfCloth);
            if (!answerP2.equals("not found"))
                break;
        }
        return answerP2;
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
