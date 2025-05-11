import java.sql.SQLOutput;
import java.util.*;

public class Game {
    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;

    private Random random;
    private Scanner scanner;
    private boolean hideDealerCard = true;

    public Game() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        random = new Random();
        scanner = new Scanner(System.in);

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    public Card drawCard() {
        int index = random.nextInt(deck.size());
        return deck.remove(index);
    }

    public void dealCards() {
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
    }

    public void printPlayerHand() {
        System.out.println("========== PLAYER HAND ==========");
        int total = 0;
        for (Card card : playerHand) {
            total += card.getBlackjackValue();
            System.out.println(card);
        }
        System.out.println("---------- TOTAL VALUE: " + total + " ----------\n");
    }

    public void printDealerHand() {
        System.out.println("========== DEALER HAND ==========");
        int total = 0;
        for (int i = 0; i < dealerHand.size(); i++) {

            if (hideDealerCard && i == 1) {
                System.out.println("***** of ***** * (Value: Unknown)");
            } else {
                Card card = dealerHand.get(i);
                total += card.getBlackjackValue();
                System.out.println(card);
            }
        }
        System.out.println("---------- TOTAL VALUE: " + total + " ----------\n");
    }

    public void printCards() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }

    private boolean validateInput(String input) {
        List<String> answers = List.of("h", "s");
        return answers.contains(input);
    }

    private void showDealerCard() {
        this.hideDealerCard = false;
    }

    private void hit() {
        playerHand.add(drawCard());
    }

    private void stand() {
    }

    public void promptOperation() {
        System.out.print("You wanna (H)it or (S)tand? ");

        String input = scanner.nextLine();

        boolean valid = this.validateInput(input);

        if (valid) {
            if (input.equals("h")) {
                this.hit();
            }
            if (input.equals("s")) {
                this.stand();
            }
        }
        this.showDealerCard();
    }

    public void play() {
        System.out.println("Playing...");
    }
}
