import java.util.Scanner;
import java.util.ArrayList;
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
        ArrayList<ArrayList<Integer>> claimedPartsOfCloth = new ArrayList<>();
        ArrayList<ArrayList<Integer>> usedPartsOfCloth = new ArrayList<>();
        int counter = 1;
        while (input.hasNext()) {
            System.out.println("Checking line" + counter);
            counter++;
            //claims.add(new Claim(input.nextLine()));
            //ArrayList<int[]> test = claims.get(claims.size() - 1).getAllCoords();
            ArrayList<ArrayList<Integer>> claimCoords = new Claim(input.nextLine()).getAllCoords();
            for ( ArrayList<Integer> claim : claimCoords ) {
                if (claimedPartsOfCloth.contains(claim)){
                    if (!usedPartsOfCloth.contains(claim)){
                        usedPartsOfCloth.add(claim);
                    }
                } else {
                    claimedPartsOfCloth.add(claim);
                }
            }
        }
        System.out.println("Solution to part 1: " + usedPartsOfCloth.size());

    }
}

class Claim {
    final private int topMargin, leftMargin, width, height;
    Claim(String claim){
        String[] claimString = claim.split(" ");
        leftMargin = Integer.parseInt(claimString[2].replace(":", "").split(",")[0]);
        topMargin = Integer.parseInt(claimString[2].replace(":", "").split(",")[1]);
        width = Integer.parseInt(claimString[3].split("x")[0]);
        height = Integer.parseInt(claimString[3].split("x")[1]);
    }
    public int getTopMargin(){ return topMargin; }
    public int getLeftMargin(){ return leftMargin; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    // not using an array list for the coords not an int[] because need to use contains function and this doesn't
    // work when trying to compare int[] (ArrayList doesn't override the equals method)
    public ArrayList<ArrayList<Integer>> getAllCoords(){
        ArrayList<ArrayList<Integer>> allCoords = new ArrayList<>();
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                ArrayList<Integer> thisCoord = new ArrayList<>();
                thisCoord.add(j + this.getLeftMargin());
                thisCoord.add(i + this.getTopMargin());
                allCoords.add(thisCoord);
            }
        }
        return allCoords;
    }
}
