public class Main {
    public static void main(String[] args) {
        while (true) {
            Game blackjack = new Game();

            blackjack.dealCards();
            while (true) {
                blackjack.clearConsole();
                blackjack.printPlayerHand();
                blackjack.printDealerHand();
                if (blackjack.gameOver) {
                    blackjack.printGameStatus();
                    break;
                }
                blackjack.promptOperation();
            }

            System.out.println("\nStarting a new game in 3 seconds...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}