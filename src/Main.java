import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game blackjack = new Game();

        blackjack.dealCards();
        while (true) {
            blackjack.printPlayerHand();
            blackjack.printDealerHand();

            blackjack.promptOperation();

            blackjack.play();
        }
//        blackjack.closeScanner();
    }
}