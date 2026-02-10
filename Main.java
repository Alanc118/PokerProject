import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Poker hand = new Poker();
    String fileData = "";

    try {
      File f = new File("src/data");
      Scanner s = new Scanner(f);

      while (s.hasNextLine()) {
        String line = s.nextLine();
        fileData += line + "\n";
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }

    System.out.println(fileData);
    String[] lines = fileData.split("\n");

    for (String line : lines) {
      if (line.length() > 0) {
        String[] parts = line.split("\\|");
        String[] cards = parts[0].split(",");

        int[] value = new int[5];

        for (int i = 0; i < 5; i++) {
          value[i] = hand.cardValue(cards[i].replace(" ", ""));
        }

        hand.fiveCard(value);
      }
    }
    hand.printResults();
  }
}
