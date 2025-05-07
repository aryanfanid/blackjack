//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game blackjack = new Game();
//        blackjack.printCards();

        blackjack.dealCards();
        blackjack.printPlayerHand();
        blackjack.printDealerHand();

    }
}