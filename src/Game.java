import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Card> deck;

    private List<Card> playerHand;
    private List<Card> dealerHand;

    private Random random;

    public Game() {
        deck = new ArrayList<>();

        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        random = new Random();

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

    public void dealCards() {
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
    }

    public void printPlayerHand() {
        for (Card card : playerHand) {
            System.out.println(card);
        }
    }

    public void printDealerHand() {
        for (Card card : dealerHand) {
            System.out.println(card);
        }
    }

    public void printCards() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }
}
