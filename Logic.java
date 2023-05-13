// Helper static class for: BlackJack
// This class is responsible for all the players/dealer/deck logical manipulations.
// It contains 4 main game cycles
// Cycle 1: Define players stage
// Cycle 2: Betting stage
// Cycle 3: Playing round
// Cycle 4: Post game stage (Defining the winners)
// Cycle 5: Redefine players (Check if someone is out from the game)
public class Logic {

//Cycle 1:
    // This method takes players amount and creates an array of players
    public static Player[] definePlayers(){
        int playersAmount = Scan.takePlayersAmount();
        Player[] players = new Player[playersAmount];
        for (int i = 0; i < players.length; i++){
            Screen.showBlackJack();
            Screen.showWhatIsYourNameQuestion();
            Panel.showAllPlayersPanel(players.length);
            String name = Scan.takeName(i);
            players[i] = new Player(name);
        }
        return players;
    }

//Cycle 2:
    // This method runs the betting stage cycle:
    public static void startBettingStage(Player[] players){
        for (int i = 0; i < players.length; i++) {
            Screen.showPlaceYourBet();
            Screen.showDealer();
            Panel.showAllBetsPanel(players);
            players[i].setBet(i, Scan.takeBet(i, players[i]));
            players[i].setBalance(players[i].getBet());
        }
        Screen.showPressEnter();
        Screen.showDealer();
        Panel.showAllBetsPanel(players);
        Scan.enter(0);
    }

//Cycle 3:
    // This method runs the actual playing stage cycle:
    public static void startPlayingRound(Player[] players, Dealer dealer, Deck deck){
        deck.shuffle();
        dealer.takeCard(deck);
        dealer.takeCard(deck);
        for (Player player : players){
            player.refreshCards();
            player.takeCard(deck);
            player.takeCard(deck);
        }
        for (int i = 0; i < players.length; i++) {
            do {
                Panel.showDealerHiddenScorePanel(dealer);
                Screen.showDealer();
                Panel.showDealerHiddenHand(dealer);
                Panel.showPlayersMovePanel(players, i);
                Panel.showAllPlayersHandPanel(players);
                players[i].setMove(Scan.takeNextMove(i));
                Logic.evaluateMove(players[i], dealer, deck, i);
            } while (players[i].isAbleToMove());
        }
        Panel.showDealerHiddenScorePanel(dealer);
        Screen.showDealer();
        Panel.showDealerHiddenHand(dealer);
        Panel.showAllPlayersScore(players);
        Panel.showAllPlayersHandPanel(players);
        Scan.enter(0);
    }

//Cycle 4:
    // This method displays the post game stage cycle
    public static void startCheckForWinnersStage(Player[] players, Dealer dealer, Deck deck){
        Panel.showDealerScorePanel(dealer);
        Screen.showDealer();
        Panel.showDealerHand(dealer);
        Panel.showDealerRevealsCardsPanel(players);
        Panel.showAllPlayersHandPanel(players);
        Scan.enter(0);
        while (dealer.calculateScore() < 17){
            dealer.takeCard(deck);
            Panel.showDealerScorePanel(dealer);
            Screen.showDealer();
            Panel.showDealerHand(dealer);
            Panel.showDealerRevealsCardsPanel(players);
            Panel.showAllPlayersHandPanel(players);
            Scan.enter(0);
        }
        int[] returnCodes = new int[players.length];
        for (int j = 0; j < returnCodes.length; j++){
            returnCodes[j] = Logic.getWinnerReturnCode(players[j], dealer);
        }
        Panel.showDealerScorePanel(dealer);
        Screen.showDealer();
        Panel.showDealerHand(dealer);
        Panel.showAllWinnersPanel(players, dealer, returnCodes, players.length-1);
        Panel.showAllPlayersHandPanel(players);
        Logic.calculateBalanceAllPlayers(players, returnCodes);
        Scan.enter(0);
    }

//Cycle 5:
    // This method redefines players based on their balance. If the balance is negative, the player is out
    public static Player[] redefinePlayers(Player[] players){
        Player[] newPlayers = new Player[Logic.countForEligeblePlayers(players)];
        int counter = 0;
        for (Player player : players) {
            if (player.isEligibleToPlay()) {
                newPlayers[counter] = player;
                counter++;
            }
        }
        return newPlayers;
    }


// Helper methods:
    // This method counts for the eligeble players to play
    private static int countForEligeblePlayers(Player[] players){
        int counter = 0;
        for (Player player : players){
            if (player.isEligibleToPlay()){
                counter++;
            }
        }
        return counter;
    }
    // This method calculates balance of all players in the array
    private static void calculateBalanceAllPlayers(Player[] players, int[] returnCodes){
        for (int i = 0; i < players.length; i++){
            players[i].calculateBalance(returnCodes[i]);
        }
    }
    // This method evaluates player move. There are three options: stand, hit, double
    private static void evaluateMove(Player player, Dealer dealer, Deck deck, int index){
        if (dealer.calculateScore() == 21){
            player.setMove("stand");
        }
        else if (player.getMove().equalsIgnoreCase("hit") || player.getMove().equalsIgnoreCase("1")) {
            player.takeCard(deck);
        }
        else if (player.getMove().equalsIgnoreCase("double")|| player.getMove().equalsIgnoreCase("3")) {
            player.setBalance(player.getBet());
            player.setBet(player.getBet() * 2, index);
            player.takeCard(deck);
            player.setMove("stand");
        }
        else{
            player.setMove("stand");
        }
    }
    // This method checks for the winner in the game: 0 means player lost, 1 means player won, -1 means a tie
    private static int getWinnerReturnCode(Player player, Dealer dealer){
        int returnCode;
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();
        boolean playerLoose = (!player.isAlive() || dealerScore > playerScore && dealer.isAlive());
        boolean playerWin = (player.isAlive() && dealerScore < playerScore || !dealer.isAlive() && player.isAlive());
        if (playerLoose){
            returnCode = 0;
        }
        else if (playerWin){
            returnCode = 1;
        }
        else{
            returnCode = -1;
        }
        return  returnCode;
    }
}
