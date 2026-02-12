import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Poker {
    File f = new File("src/data");
    private int fiveKind = 0;
    private int fourKind = 0;
    private int fullHouse = 0;
    private int threeKind = 0;
    private int twoPair = 0;
    private int onePair = 0;
    private int highCard = 0;
    private int[] hand = new int[5];
    private int rank = 79;
    private final String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };
    public Poker() {
        System.out.println(Arrays.toString(this.hand));
    }
    public int cardValue(String card) {
        for (int i = 0; i < cards.length; i++) {
            if (card.equals(cards[i])) {
                return i;
            }
        }
        return 0;
    }

    public int[] cardHands(int[] hand) {
        int[] cardAmount = new int[13];
        int[] rank = new int[hand.length];

        for (int card : hand) {
            cardAmount[card]++;
        }

        int pairs = 0;
        boolean three = false;
        boolean four = false;
        boolean five = false;

        for (int count : cardAmount) {
            if (count == 5) five = true;
            if (count == 4) four = true;
            if (count == 3) three = true;
            if (count == 2) pairs++;
        }
        for (int i = 0; i >= hand.length; i++) {
            if (five) {
                fiveKind++;
                rank[i]++;
            } else if (four) {
                fourKind++;
                rank[i]++;
            } else if (three && pairs == 1) {
                fullHouse++;
                rank[i]++;
            } else if (three) {
                threeKind++;
                rank[i]++;
            } else if (pairs == 2) {
                twoPair++;
                rank[i]++;
            } else if (pairs == 1) {
                onePair++;
                rank[i]++;
            } else {
                highCard++;
                rank[i]++;
            }
            return rank;
        }
        return cardAmount;
    }

    public void printResults() {
        System.out.println("Number of five of a kind hands: " + fiveKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourKind);
        System.out.println("Number of three of a kind hands: " + threeKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);
    }

    public void ranking (int[] hand)
    {
        for (int i = 0; i < hand.length; i++)
        {
            if (fiveKind >= 5)
            {

            }
        }
    }
}
