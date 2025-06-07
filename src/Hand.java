import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> cards;
    public Integer cardsTotal;

    public Hand() {
        cards = new ArrayList<>();
        cardsTotal = 0;
    }
}
