import java.util.Arrays;
import java.io.File;

public class Poker {
    File f = new File("src/data");
    private int fiveKind = 0;
    private int fourKind = 0;
    private int fullHouse = 0;
    private int threeKind = 0;
    private int twoPair = 0;
    private int onePair = 0;
    private int highCard = 0;
    private int numberOfHands = 0;
    private int[] hand = new int[5];
    private int totalBet = 0;
    private final String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

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

    public String getPokerCombo(int[] hand) {
        int[] cardAmounts = new int[13];
        int numOfPairs = 0;
        int numOfTriples = 0;
        int numOfFours = 0;
        int numOfFives = 0;

        for (int cardIndex : hand) {
            cardAmounts[cardIndex]++;
        }

        for (int i = 0; i < cardAmounts.length; i++) {
            if (cardAmounts[i] == 2) {
                numOfPairs++;
            } else if (cardAmounts[i] == 3) {
                numOfTriples++;
            } else if (cardAmounts[i] == 4) {
                numOfFours++;
            } else if (cardAmounts[i] == 5) {
                numOfFives++;
            }
        }

        if (numOfFives == 1) return "fiveOfAKind";
        else if (numOfFours == 1) return "fourOfAKind";
        else if (numOfTriples == 1 && numOfPairs == 1) return "fullHouse";
        else if (numOfTriples == 1) return "threeOfAKind";
        else if (numOfPairs == 2) return "twoPair";
        else if (numOfPairs == 1) return "onePair";
        else return "highCard";
    }

    public int[] getHighestValuesInCombo(int[] hand) {
        int[] cardAmounts = new int[13];
        for (int card : hand) cardAmounts[card]++;

        int[] values = new int[5];
        int index = 0;

        String combo = getPokerCombo(hand);
        int targetCount = switch (combo) {
            case "fiveOfAKind" -> 5;
            case "fourOfAKind" -> 4;
            case "fullHouse" -> 3;
            case "threeOfAKind" -> 3;
            case "twoPair" -> 2;
            case "onePair" -> 2;
            default -> 1;
        };
        for (int i = 12; i >= 0; i--) {
            if (cardAmounts[i] == targetCount) {
                for (int j = 0; j < cardAmounts[i]; j++) {
                    values[index++] = i;
                }
            }
        }
        if (combo.equals("fullHouse")) {
            for (int i = 12; i >= 0; i--) {
                if (cardAmounts[i] == 2) {
                    for (int j = 0; j < cardAmounts[i]; j++) {
                        values[index++] = i;
                    }
                }
            }
        }
        for (int i = 12; i >= 0 && index < 5; i--) {
            if (cardAmounts[i] > 0 && cardAmounts[i] != targetCount &&
                    !(combo.equals("fullHouse") && cardAmounts[i] == 2)) {
                for (int j = 0; j < cardAmounts[i] && index < 5; j++) {
                    values[index++] = i;
                }
            }
        }

        return values;
    }

    public int[] cardHands(int[] hand) {
        int[] cardAmount = new int[13];
        String combo = this.getPokerCombo(hand);

        if (combo.equals("fiveOfAKind")) fiveKind++;
        else if (combo.equals("fourOfAKind")) fourKind++;
        else if (combo.equals("fullHouse")) fullHouse++;
        else if (combo.equals("threeOfAKind")) threeKind++;
        else if (combo.equals("twoPair")) twoPair++;
        else if (combo.equals("onePair")) onePair++;
        else highCard++;
        numberOfHands = fiveKind + fourKind + fullHouse + threeKind + twoPair + onePair + highCard;
        return cardAmount;
    }
    public int rankHand(int handIndex, int[][] allHands) {
        int[] hand = allHands[handIndex];
        int[] currentValues = getHighestValuesInCombo(hand);
        int strength = getComboStrength(getPokerCombo(hand));

        int rank = 1;
        for (int i = 0; i < allHands.length; i++) {
            if (i == handIndex) continue;
            int otherStrength = getComboStrength(getPokerCombo(allHands[i]));
            int[] otherValues = getHighestValuesInCombo(allHands[i]);
            if (otherStrength < strength) {
                rank++;
            } else if (otherStrength == strength) {
                for (int j = 0; j < 5; j++) {
                    if (otherValues[j] < currentValues[j]) {
                        rank++;
                        break;
                    } else if (otherValues[j] > currentValues[j]) {
                        break;
                    }
                }
            }
        }

        return rank;
    }

    public int calculateTotalBid(int[][] allHands, int[] bids) {
        int total = 0;
        for (int i = 0; i < allHands.length; i++) {
            int rank = rankHand(i, allHands);
            total += rank * bids[i];
        }
        return total;
    }

    private int getComboStrength(String combo) {
        return switch (combo) {
            case "fiveOfAKind" -> 6;
            case "fourOfAKind" -> 5;
            case "fullHouse" -> 4;
            case "threeOfAKind" -> 3;
            case "twoPair" -> 2;
            case "onePair" -> 1;
            default -> 0;  // highCard
        };
    }

    public int getTotalBet() {
        return this.totalBet;
    }

    public void printTotalBet() {
        System.out.println(totalBet);
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
}