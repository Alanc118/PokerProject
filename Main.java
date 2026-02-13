import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

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

        String[] lines = fileData.split("\n");

        int[][] allHands = new int[lines.length][5];
        int[] bets = new int[lines.length];

        int indexCount = 0;

        for (String line : lines) {

            if (line.length() > 0) {

                String[] parts = line.split("\\|");
                String[] cards = parts[0].split(",");
                int bet = Integer.parseInt(parts[1]);

                int[] value = new int[5];

                for (int i = 0; i < 5; i++) {
                    value[i] = hand.cardValue(cards[i].replace(" ", ""));
                }

                allHands[indexCount] = value;
                bets[indexCount] = bet;

                hand.cardHands(value);

                indexCount++;
            }

        }
        System.out.println(hand.calculateTotalBid(allHands, bets));
        hand.printResults();
    }
}