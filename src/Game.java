import java.util.*;

public class Game {
    private List<Card> deck;
    private Hand playerHand;
    private Hand dealerHand;

    private final Random random;
    private final Scanner scanner;

    private boolean hideDealerCard = true;
    public boolean gameOver = false;
    private String status;

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";

    public Game() {
        deck = new ArrayList<>();
        playerHand = new Hand();
        dealerHand = new Hand();

        random = new Random();
        scanner = new Scanner(System.in);

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public Card drawCard() {
        int index = random.nextInt(deck.size());
        return deck.remove(index);
    }

    public void dealCard(Hand hand) {
        Card card = drawCard();
        hand.cards.add(card);
        hand.cardsTotal += card.getBlackjackValue();
    }

    public void dealCards() {
        dealCard(playerHand);
        dealCard(dealerHand);
        dealCard(playerHand);
        dealCard(dealerHand);

        if (playerHand.cardsTotal == 21) {
            status = BLUE + "BlackJack!" + RESET;
            gameOver = true;
        }
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printPlayerHand() {
        System.out.println("========== PLAYER HAND ==========\n");
        for (Card card : playerHand.cards) {
            System.out.println(card);
        }
        System.out.println("\n-------- TOTAL VALUE: " + playerHand.cardsTotal + " --------\n");
    }

    public void printDealerHand() {
        System.out.println("========== DEALER HAND ==========\n");
        for (int i = 0; i < dealerHand.cards.size(); i++) {
            if (hideDealerCard && i == 1) {
                System.out.println("    ***** of *");
            } else {
                Card card = dealerHand.cards.get(i);
                System.out.println(card);
            }
        }
        System.out.println("\n-------- TOTAL VALUE: " + (hideDealerCard ? "**" : dealerHand.cardsTotal) + " --------\n");
    }

    private boolean validateInput(String input) {
        List<String> answers = List.of("h", "s");
        return answers.contains(input);
    }

    private void hit() {
        dealCard(playerHand);
        if (playerHand.cardsTotal > 21) {
            status = YELLOW + "Bust!" + RESET;
            gameOver = true;
        } else if (playerHand.cardsTotal == 21) {
            status = BLUE + "BlackJack!" + RESET;
            gameOver = true;
        }
    }

    private void stand() {
        this.hideDealerCard = false;
        while (dealerHand.cardsTotal < playerHand.cardsTotal) {
            dealCard(dealerHand);

        }
        if (dealerHand.cardsTotal.equals(playerHand.cardsTotal)) {
            status = YELLOW + "Push!" + RESET;
        } else if (dealerHand.cardsTotal <= 21) {
            status = RED + "Dealer Wins!" + RESET;
        } else {
            status = GREEN + "Player Wins!" + RESET;
        }
        gameOver = true;
    }

    public void promptOperation() {
        System.out.print("\n(H)it or (S)tand? ");

        String input = scanner.nextLine();
        boolean valid = this.validateInput(input);

        if (valid) {
            if (input.equals("h")) {
                this.hit();
            }
            if (input.equals("s")) {
                this.stand();
            }
        } else {
            System.out.println("Enter valid input!");
            promptOperation();
        }
    }

    public void printGameStatus() {
        System.out.println(status);
    }
}
