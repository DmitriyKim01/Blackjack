// Helper static class for: BlackJack | Logic
//This class is responsible for the panels printing.
// All the methods have the same logic:
// 1. Call and store PanelBuilder method
// 2. Print the result

public class Panel {
    // Prints n amount of panels with message displayed: Player n
    public static void showAllPlayersPanel(int playersAmount){
        char[][] result = PanelBuilder.makeAllPlayersPanel(playersAmount);
        printResult(result);
    }

    // Prints n amount of panels with the all bets options displayed
    public static void showAllBetsPanel(Player[] players){
        char[][] result = PanelBuilder.makeAllBetsPanel(players);
        printResult(result);
    }

    // Prints all players cards on hand
    public static void showAllPlayersHandPanel(Player[] players){
        char[][] result = PanelBuilder.makeAllPlayersHandPanel(players);
        printResult(result);
    }

    // Prints n amount of panels with players name and score displayed
    public static void showAllPlayersScore(Player[] players){
        char[][] result = PanelBuilder.makeAllPlayersScorePanel(players);
        printResult(result);
    }

    // Prints n amount of players with the winner message
    public static void showAllWinnersPanel(Player[] players, Dealer dealer, int[] returnCodes, int index){
        char[][] result = PanelBuilder.makeAllWinnersWindow(players, dealer, returnCodes, index);
        printResult(result);
    }

    // Prints 1 panel with current player options and n amount of panels players waiting
    public static void showPlayersMovePanel(Player[] players, int currentPlayer){
        char[][] result = PanelBuilder.makeAllPlayersOptionsPanel(players, currentPlayer);
        printResult(result);
    }
    // Prints a panel with "dealer" and dealer's score
    public static void showDealerScorePanel(Dealer dealer){
        char[][] result = PanelBuilder.makeDealerScorePanel(dealer);
        printResult(result);
    }

    // Prints a panel with "dealer" and dealer's score, but the score is calculated only for the first card
    public static void showDealerHiddenScorePanel(Dealer dealer){
        char[][] result = PanelBuilder.makeDealerHiddenScorePanel(dealer);
        printResult(result);
    }

    // Prints n amount of panels with the message: "Dealer reveals cards"
    public static void showDealerRevealsCardsPanel(Player[] players){
        char[][] result = PanelBuilder.makeAllDealerRevealsMessagePanel(players);
        printResult(result);
    }

    // Prints dealers cards on hand. 1 card is open and 1 is hidden
    public static void showDealerHiddenHand(Dealer dealer){
        char[][] result = PanelBuilder.makeDealerHiddenHandPanel(dealer);
        printResult(result);
    }
    // Prints dealer's cards on hand
    public static void showDealerHand(Dealer dealer){
        char[][] result = PanelBuilder.makeDealerHandPanel(dealer);
        printResult(result);
    }

    private static void printResult(char[][] panel){
        for (char[] row : panel) {
            for (char character : row) {
                System.out.print(character);
            }
            System.out.println();
        }
        System.out.println();
    }
}
