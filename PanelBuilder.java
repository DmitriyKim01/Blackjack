// Helper static class for: Panel
// This class is responsible for all the panels creation logic.
// There are 3 types of methods:
// Public  Type 1: makeAll... returns a single big array of all panels stored horizontally.
// Private Type 2: makePlayer... returns a single panel with a special message related to player
// Private or Public Type 3: makeDealer... returns a single panel with a special message related to dealer
public class PanelBuilder {

//**********************************************************************************************************************

    // Makes an array of panels using makeSinglePlayerPanel method
    public static char[][] makeAllPlayersPanel(int playersAmount){
        char[][][] arrayOfWindows = new char[playersAmount][][];
        for (int i = 0; i < arrayOfWindows.length; i++){
            arrayOfWindows[i] = makePlayerPanel(i+1);
        }
        int maxColumns = arrayOfWindows[0].length;
        int maxRows = arrayOfWindows[0][0].length*playersAmount;
        char[][] result = new char[maxColumns][maxRows];
        int counter = 0;

        for (char[][] arrayOfWindow : arrayOfWindows) {
            for (int j = 0; j < arrayOfWindow.length; j++) {
                for (int k = 0; k < arrayOfWindow[j].length; k++) {
                    result[j][k + counter] = arrayOfWindow[j][k];
                }
            }
            counter += arrayOfWindow[0].length;
        }
        return result;
    }
    // Helper method of makeAllPlayersPanel(). Makes a single panel with "Player n" message
    private static char[][] makePlayerPanel(int index) {
        // Stage 1
        // Create a sing window:
        int leftIndentation = 20;
        int columns = 5;
        int rows = 40+leftIndentation;
        char[][] window = new char[columns][rows];
        String question = "Player " + index;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (j < leftIndentation){
                    window[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    window[i][j] = '-';
                }
                else if (j == leftIndentation || j == rows-1){
                    window[i][j] = '|';
                }
                else if (i == 2 && j == (rows - question.length()+leftIndentation) / 2) {
                    for (int k = 0; k < question.length(); k++){
                        window[i][j+k] = question.charAt(k);
                    }
                    j += question.length()-1;
                }
                else {
                    window[i][j] = ' ';
                }
            }
        }
        return window;
    }

//**********************************************************************************************************************

    // Makes an array of panels of all player's bets
    public static char[][] makeAllBetsPanel(Player[] players){
        char[][][] arrayOfWindows = new char[players.length][][];
        for (int i = 0; i < arrayOfWindows.length; i++){
            arrayOfWindows[i] = PanelBuilder.makeBetPanel(players[i]);
        }
        int maxColumns = arrayOfWindows[0].length;
        int maxRows = arrayOfWindows[0][0].length*players.length;
        char[][] result = new char[maxColumns][maxRows];
        int counter = 0;

        for (char[][] arrayOfWindow : arrayOfWindows) {
            for (int j = 0; j < arrayOfWindow.length; j++) {
                for (int k = 0; k < arrayOfWindow[j].length; k++) {
                    result[j][k + counter] = arrayOfWindow[j][k];
                }
            }
            counter += arrayOfWindow[0].length;
        }
        return  result;
    }
    // Helper method of makeAllBetsPanel(). Makes a single panel of player's bet
    private static char[][] makeBetPanel(Player player){
        int leftIndentation = 20;
        int rows = 7;
        int columns = leftIndentation+40;
        char[][] window = new char[rows][columns];
        String name = player.getName().toUpperCase();
        String balance = "BALANCE: " + player.getBalance();
        String bets = "| 5 | 25 | 50 | 100 | 250 | 500 |";
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < columns; j++) {
                if (j < leftIndentation){
                    window[i][j] = ' ';
                }
                else if (i == 0 || i == rows-1){
                    window[i][j] = '-';
                }
                else if (j == leftIndentation || j == columns-1){
                    window[i][j] = '|';
                }
                else if (i == 2 && j == (columns - name.length()+leftIndentation) / 2){
                    for (int k = 0; k < name.length(); k++){
                        window[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (i == 3 && j == (columns - balance.length() + leftIndentation) / 2){
                    for (int k = 0; k < balance.length(); k++){
                        window[i][j+k] = balance.charAt(k);
                    }
                    j += balance.length()-1;
                }
                else if (i == 4 && j == (columns - bets.length() + leftIndentation) / 2){
                    for (int k = 0; k < bets.length(); k++){
                        window[i][j+k] = bets.charAt(k);
                    }
                    j += bets.length()-1;
                }
                else{
                    window[i][j] = ' ';
                }
            }
        }
        return window;
    }

//**********************************************************************************************************************

    // Makes an array of all players cards on hands
    public static char[][] makeAllPlayersHandPanel(Player[] players){
        int maxColumns = 0;
        int maxRows = 0;
        int rowIndentation = 0;

        for (int i = 0; i < players.length; i++){
            char[][] arrayOfPanels = PanelBuilder.makePlayerHandPanel(players[i]);
            if (maxColumns < arrayOfPanels.length){
                maxColumns = arrayOfPanels.length;
            }
            maxRows += arrayOfPanels[i].length;
        }

        char[][] result = new char[maxColumns][maxRows];
        for(int i =0; i < result.length; i++){
            for (int j = 0; j < result[i].length; j++){
                result[i][j] = ' ';
            }
        }

        for (Player player : players) {
            char[][] playerMatrix = PanelBuilder.makePlayerHandPanel(player);
            for (int i = 0; i < playerMatrix.length; i++) {
                for (int j = 0; j < playerMatrix[i].length; j++) {
                    result[i][j + rowIndentation] = playerMatrix[i][j];
                }
            }
            rowIndentation += playerMatrix[0].length;
        }
        return result;
    }
    // This method makes 2d array of player's cards on hands.
    private static char[][] makePlayerHandPanel(Player player){
        int leftIndentation  = 34 - player.getHand().length()*2;
        int rightIndentation = 16 - player.getHand().length()*2;
        int width = leftIndentation + 15;
        int height  = 9;
        int rows    = height + 2 * (player.getHand().length()-1);
        int columns = width  + 5 * (player.getHand().length()-1);

        char[][] result = new char[rows][columns+rightIndentation];

        PanelBuilder.makeLeftIndent          (result);
        PanelBuilder.makeTopSide             (result, player, leftIndentation, width);
        PanelBuilder.makeTopRightSide        (result, player, width);
        PanelBuilder.makeLeftColumns         (result, leftIndentation, width, height, rows, columns);
        PanelBuilder.makeRightColumn         (result, rows, columns, height);
        PanelBuilder.makeBottomSide          (result, leftIndentation, rows, columns, width);
        PanelBuilder.makeBottomLeftSide      (result, leftIndentation, height, rows);

        PanelBuilder.makeTopLeftCardIndex    (result, player, leftIndentation);
        PanelBuilder.makeMiddleCardIndex     (result, player, rows, columns);
        PanelBuilder.makeBottomLeftCardIndex (result, player, rows, columns);

        return result;
    }
    // Helper method for makePlayerHandPanel(). Fills all the array slots with white spaces
    private static void makeLeftIndent(char[][] cardPattern){
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                cardPattern[i][j] = ' ';
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Makes top lines of cards.
    private static void makeTopSide(char[][] cardPattern, Player player, int leftIndentation, int width){
        int leftMargin = 0;
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if (i % 2 == 0   && i < player.getHand().length() * 2 && j >= leftIndentation+leftMargin && j < width+leftMargin){
                    cardPattern[i][j] = '-';
                }
            }
            if (i % 2 == 0){
                leftMargin += 5;
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Makes top right lines of player's cards.
    private static void makeTopRightSide(char[][] cardPattern, Player player, int width){
        int leftMargin = 0;
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if (i % 2 == 1  &&  i <= player.getHand().length() * 2 && j == width-1+leftMargin-5){
                    cardPattern[i][j] = '|';
                }
            }
            if (i % 2 == 0){
                leftMargin += 5;
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Makes left columns of player's cards
    private static void makeLeftColumns(char[][] cardPattern, int leftIndentation, int width, int height, int rows, int columns){
        int leftMargin = 0;
        int bottomMargin = leftIndentation-1;
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if (i > height-2 && j < columns-width+leftIndentation && j <= bottomMargin){
                    cardPattern[i][j] = ' ';
                }
                else if ((j - leftIndentation) % 5 == 0 && j < leftIndentation+leftMargin && j < columns-12 && i != rows-1 && j > leftIndentation-1){
                    cardPattern[i][j] = '|';
                }
                if (i > height-2 && i % 2 == 0 && j == 0){
                    bottomMargin += 5;
                }
            }
            if (i % 2 == 0){
                leftMargin += 5;
            }
        }
    }

    // Helper method for makePlayerHandPanel(). Makes bottom left lines of player's cards
    private static void makeBottomLeftSide(char[][] cardPattern, int leftIndentation, int height, int rows){
        int rightMargin = 0;
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if (i >= height-1 && i % 2 == 0 && i != rows-1 && j < leftIndentation+rightMargin+5 && j >= leftIndentation + rightMargin){
                    cardPattern[i][j] = '-';
                }
            }
            if (i > height-2 && i % 2 == 1){
                rightMargin += 5;
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Makes right side of the player's last card
    private static void makeRightColumn(char[][] cardPattern, int rows, int columns, int height){
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if (j == columns-1 && i >= rows - height+1 && i < rows-1 ){
                    cardPattern[i][j] = '|';
                }
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Makes bottom side of the player's last card
    private static void makeBottomSide(char[][] cardPattern, int leftIndentation, int rows, int columns, int width){
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if ( i == rows-1 && j >= columns-width + leftIndentation && j < columns){
                    cardPattern[i][j] = '-';
                }
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Adds card number and suit in the top left corner of a last card
    private static void makeTopLeftCardIndex(char[][] cardPattern, Player player, int leftIndentation){
        int cardIndex = 0;
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if (i == cardIndex * 2 + 1 && j == 5*cardIndex + 2+leftIndentation){
                    // Case if 10:
                    if (player.getHand().getCard(cardIndex).toString().length() == 3){
                        cardPattern[i][j] = player.getHand().getCard(cardIndex).getNumber().charAt(0);
                        cardPattern[i][j+1] = player.getHand().getCard(cardIndex).getNumber().charAt(1);
                        cardPattern[i][j+2] = player.getHand().getCard(cardIndex).getSuit().charAt(0);
                        j += 2;
                    }
                    // Case if not 10:
                    else{
                        cardPattern[i][j] = player.getHand().getCard(cardIndex).getNumber().charAt(0);
                        cardPattern[i][j+1] = player.getHand().getCard(cardIndex).getSuit().charAt(0);
                        j++;
                    }
                    if (cardIndex < player.getHand().length()-1){
                        cardIndex++;
                    }
                }
            }
        }
    }
    // Helper method for makePlayerHandPanel(). Adds card suit in the middle of a last card
    private static void makeMiddleCardIndex(char[][] cardPattern, Player player, int rows, int columns){
        Card lastCard = player.getHand().getCard(player.getHand().length()-1);
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                if ( i == rows -5  && j == columns-8){
                    cardPattern[i][j] = lastCard.toString().charAt(lastCard.toString().length()-1);
                }
            }
        }
    }
    // Helper method for makePlayerHandPanel() method. Adds card number and suit in the bottom right corner of a last card
    private static void makeBottomLeftCardIndex(char[][] cardPattern, Player player, int rows, int columns){
        Card lastCard = player.getHand().getCard(player.getHand().length()-1);
        for (int i = 0; i < cardPattern.length; i++) {
            for (int j = 0; j < cardPattern[i].length; j++) {
                // Case if not 10:
                if (lastCard.toString().length() == 3 && j == columns-5 && i == rows - 2){
                    cardPattern[i][j] = lastCard.getNumber().charAt(0);
                    cardPattern[i][j+1] = lastCard.getNumber().charAt(1);
                    cardPattern[i][j+2] = lastCard.getSuit().charAt(0);
                    j += 2;
                }
                // Case if 10:
                else if (lastCard.toString().length() == 2 && j == columns-4 && i == rows - 2){
                    cardPattern[i][j] = lastCard.getNumber().charAt(0);
                    cardPattern[i][j+1] = lastCard.getSuit().charAt(0);
                    j++;
                }
            }
        }
    }

//**********************************************************************************************************************

    // Makes an arrays of panels with the all players score
    public static char[][] makeAllPlayersScorePanel(Player[] players){
        int maxColumns = makePlayerScorePanel(players[0]).length;
        int maxRows = makePlayerScorePanel(players[0])[0].length * players.length;
        int counter = 0;
        char[][] result = new char[maxColumns][maxRows];
        for (int i = 0; i < players.length; i++){
            char[][] tempArray = makePlayerScorePanel(players[i]);
            for (int j = 0; j < tempArray.length; j++){
                for (int k = 0; k < tempArray[j].length; k++){
                    result[j][k+counter] = tempArray[j][k];
                }
            }
            counter += tempArray[i].length;
        }
        return result;
    }
    // Helper method for makeAllPlayersScorePanel(). Makes a single panel of a player's score
    private static char[][] makePlayerScorePanel(Player player){
        int leftIndentation = 20;
        int rows = 4;
        int columns = leftIndentation+40;
        char[][] result = new char[rows][columns];
        String name = player.getName();
        String score = "Score: " + player.calculateScore();
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < columns; j++){
                if (j < leftIndentation){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == rows-1){
                    result[i][j] = '-';
                }
                else if (j == leftIndentation || j == columns-1){
                    result[i][j] = '|';
                }
                else if (j == leftIndentation+(columns - leftIndentation - name.length()) / 2 && i == 1){
                    for (int k = 0; k < name.length(); k++){
                        result[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (j == leftIndentation+(columns - leftIndentation - score.length()) / 2 && i == 2){
                    for (int k = 0; k < score.length(); k++){
                        result[i][j+k] = score.charAt(k);
                    }
                    j += score.length()-1;
                }
                else{
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }

//**********************************************************************************************************************

    // Makes an array of panels with all the winners.
    public static char[][] makeAllWinnersWindow(Player[] players, Dealer dealer, int[] returnCodes, int index){
        char[][][] arrayOfWindows = new char[players.length][][];
        for (int i = 0; i < players.length; i++){
            arrayOfWindows[i] = makeWinnerWindow(players[i], dealer, returnCodes[i]);
        }
        int maxColumns = arrayOfWindows[0].length;
        int maxRows = arrayOfWindows[0][0].length*players.length;
        char[][] result = new char[maxColumns][maxRows];
        int counter = 0;

        for (int i = 0; i < 1+index; i++){
            for (int j = 0; j < arrayOfWindows[i].length; j++){
                for (int k = 0; k < arrayOfWindows[i][j].length; k++){
                    result[j][k+counter] = arrayOfWindows[i][j][k];
                }
            }
            counter += arrayOfWindows[i][0].length;
        }
        return result;
    }
    // Helper method for makeAllWinnersWindow(). Makes a single panel for one winner
    private static char[][] makeWinnerWindow(Player player, Dealer dealer, int returnCode){
        int leftIndentation = 20;
        int columns = 5;
        int rows = leftIndentation+40;
        char[][] window = new char[columns][rows];
        String playerName = player.getName();
        String dealerName = "Dealer";
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();
        String playerVsDealer = playerName + " vs " + dealerName;
        String winnerMessage = setWinner(returnCode, player);
        String scoreComparison = setScore(returnCode, playerScore, dealerScore);

        for (int i = 0; i < columns; i ++){
            for (int j = 0; j < rows; j++) {
                if (j < leftIndentation){
                    window[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    window[i][j] = '-';
                }
                else if (j == leftIndentation || j == rows-1){
                    window[i][j] = '|';
                }
                else if (i == 1 && j == leftIndentation+(rows-leftIndentation-playerVsDealer.length()) /2 ){
                    for (int k = 0; k < playerVsDealer.length(); k++){
                        window[i][j+k] = playerVsDealer.charAt(k);
                    }
                    j += playerVsDealer.length()-1;
                }
                else if (i == 2 && j == leftIndentation+(rows-leftIndentation-scoreComparison.length()) / 2){
                    for (int k = 0; k < scoreComparison.length(); k++){
                        window[i][j+k] = scoreComparison.charAt(k);
                    }
                    j += scoreComparison.length()-1;
                }
                else if ( i == 3 && j == leftIndentation+(rows-leftIndentation-winnerMessage.length()) / 2){
                    for (int k = 0; k < winnerMessage.length(); k++){
                        window[i][j+k] = winnerMessage.charAt(k);
                    }
                    j += winnerMessage.length()-1;
                }
                else {
                    window[i][j] = ' ';
                }
            }
        }
        return window;
    }
    // Helper method for makeSingleWinnerWindow(). Return a string of a winner
    // Return codes: 0 means dealer won, 1 means player won, -1 means a tie
    private static String setWinner(int returnCode, Player player){
        if (returnCode == 0){
            return "DEALER WON!";
        }
        else if (returnCode == 1){
            return player.getName().toUpperCase() + " WON!";
        }
        else{
            return "IT'S A TIE!";
        }
    }
    // Helper method for makeSingleWinnerWindow(). Return a string of a winner
    // Return codes: 0 means dealer won, 1 means player won, -1 means a tie
    private static String setScore(int returnCode, int playerScore, int dealerScore){
        if (returnCode == 0){
            return playerScore + "    <    " + dealerScore;
        }
        else if (returnCode == 1){
            return playerScore + "    >    " + dealerScore;
        }
        else{
            return playerScore + "    =    " + dealerScore;
        }
    }

//**********************************************************************************************************************

    // Makes an array of panels that shows all player's options
    public static char[][] makeAllPlayersOptionsPanel(Player[] players, int currentPlayer){
        char[][][] array = new char[players.length][][];
        for (int i = 0; i < players.length; i++){
            if (currentPlayer == i){
                array[i] = makePlayerOptionsPanel(players[i]);
            }else{
                array[i] = makePlayerWaitingPanel(players[i]);
            }
        }
        int maxColumns = array[0].length;
        int maxRows = array[0][0].length * players.length;
        char[][] result = new char[maxColumns][maxRows];
        int counter = 0;
        for (char[][] chars : array) {
            for (int j = 0; j < chars.length; j++) {
                for (int k = 0; k < chars[j].length; k++) {
                    result[j][k + counter] = chars[j][k];
                }
            }
            counter += chars[0].length;
        }
        return result;
    }
    // Helper method of makeAllPlayersOptionsPanel(). Makes a single panel that shows n player is waiting
    private static char[][] makePlayerWaitingPanel(Player player){
        int leftIndentation = 20;
        int rightIndentation = 1;
        int rows = 6;
        int columns = leftIndentation+40+rightIndentation;
        char[][] result = new char[rows][columns];
        String name = player.getName()+ " waiting!";
        String score = "Score: " + player.calculateScore();
        for (int i = 0; i < result.length; i ++){
            for (int j = 0; j < result[i].length; j++){
                if (j < leftIndentation || j > columns){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == rows-1){
                    result[i][j] = '-';
                }
                else if (j == leftIndentation || j == columns-rightIndentation){
                    result[i][j] = '|';
                }
                else if (j == leftIndentation+(columns - leftIndentation - name.length()) / 2 && i == 2){
                    for (int k = 0; k < name.length(); k++){
                        result[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (j == leftIndentation+(columns - leftIndentation - score.length()) / 2 && i == 3){
                    for (int k = 0; k < score.length(); k++){
                        result[i][j+k] = score.charAt(k);
                    }
                    j += score.length()-1;
                }
                else{
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }
    // Helper method of makeAllPlayersOptionsPanel(). Makes a single panel that shows current player's options to move
    private static char[][] makePlayerOptionsPanel(Player player){
        int leftIndentation = 20;
        int rightIndent = 1;
        int rows = 6;
        int columns = leftIndentation+40;
        char[][] result = new char[rows][columns+rightIndent];
        String name = player.getName();
        String balance = "Balance: " + player.getBalance();
        String score = "Score: " + player.calculateScore();
        String options = "1. HIT  2. STAND  3. DOUBLE";
        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length; j++){
                if (j < leftIndentation){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == rows-1){
                    result[i][j] = '-';
                }
                else if (j == leftIndentation || j == columns){
                    result[i][j] = '|';
                }
                else if (i == 1 && j == leftIndentation+(columns-leftIndentation - name.length()) / 2){
                    for (int k = 0; k < name.length(); k++){
                        result[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (j == leftIndentation+(columns-leftIndentation - balance.length()) /2 && i == 2){
                    for (int k = 0; k < balance.length(); k++){
                        result[i][j+k] = balance.charAt(k);
                    }
                    j += balance.length()-1;
                }
                else if (i == 3 && j == leftIndentation+(columns - leftIndentation - score.length()) / 2){
                    for (int k = 0; k < score.length(); k++){
                        result[i][j+k] = score.charAt(k);
                    }
                    j += score.length()-1;
                }
                else if(i == 4 && j == leftIndentation+(columns - leftIndentation- options.length()) / 2){
                    for (int k = 0; k < options.length(); k++){
                        result[i][j+k] = options.charAt(k);
                    }
                    j += options.length()-1;
                }else{
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }

//**********************************************************************************************************************

    // Makes a panel with the dealer's score
    public static char[][] makeDealerScorePanel(Dealer dealer){
        int indent = 107;
        int columns = 4;
        int rows = 40+indent;
        char[][] result = new char[columns][rows];
        String name = "Dealer";
        String score = "Score: " + dealer.calculateScore();
        for (int i = 0; i < result.length; i ++){
            for (int j = 0; j < result[i].length; j++){
                if (j < indent){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    result[i][j] = '-';
                }
                else if (j == indent || j == rows-1){
                    result[i][j] = '|';
                }
                else if (j == indent+(rows-indent - name.length()) / 2 && i == 1){
                    for (int k = 0; k < name.length(); k++){
                        result[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (j == indent+(rows-indent - score.length()) / 2 && i == 2){
                    for (int k = 0; k < score.length(); k++){
                        result[i][j+k] = score.charAt(k);
                    }
                    j += score.length()-1;
                }
                else{
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }

//**********************************************************************************************************************

    // Makes a panel with the dealer's score, but only for the 1 card
    public static char[][] makeDealerHiddenScorePanel(Dealer dealer){
        int indent = 107;
        int columns = 4;
        int rows = 40+indent;
        char[][] result = new char[columns][rows];
        String name = "Dealer.";
        String score = "Score: " + dealer.calculateScoreFirstCard();
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (j < indent){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    result[i][j] = '-';
                }
                else if (j == indent || j == rows-1){
                    result[i][j] = '|';
                }
                else if (j == indent+(rows-indent - name.length()) / 2 && i == 1){
                    for (int k = 0; k < name.length(); k++){
                        result[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (j == indent+(rows-indent - score.length()) / 2 && i == 2){
                    for (int k = 0; k < score.length(); k++){
                        result[i][j+k] = score.charAt(k);
                    }
                    j += score.length()-1;
                }
                else{
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }

//**********************************************************************************************************************

    // Makes an array of panels of makeDealerRevealsMessagePanel()
    public static char[][] makeAllDealerRevealsMessagePanel(Player[] players) {
        char[][] window = makeDealerRevealsMessagePanel();
        char[][] result = new char[window.length][window[0].length*players.length];
        int counter = 0;

        for (int i = 0; i < players.length; i++){
            for (int j = 0; j < window.length; j++){
                for (int k = 0; k < window[j].length; k++){
                    result[j][k+counter] = window[j][k];
                }
            }
            counter += window[0].length;
        }
        return result;
    }
    // Helper method of makeAllDealerRevealsMessagePanel(). Makes a single panel with the message: "Dealer reveals his cards"
    private static char[][] makeDealerRevealsMessagePanel(){
        int indent = 20;
        int columns = 4;
        int rows = 40+indent;
        char[][] window = new char[columns][rows];
        String name = "Dealer reveals his cards";
        String message = "...";
        for (int i = 0; i < columns; i ++){
            for (int j = 0; j < rows; j++){
                if (j < indent){
                    window[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    window[i][j] = '-';
                }
                else if (j == indent || j == rows-1){
                    window[i][j] = '|';
                }
                else if (j == indent+(rows-indent - name.length()) / 2 && i == 1){
                    for (int k = 0; k < name.length(); k++){
                        window[i][j+k] = name.charAt(k);
                    }
                    j += name.length()-1;
                }
                else if (j == indent+(rows-indent - message.length()) / 2 && i == 2){
                    for (int k = 0; k < message.length(); k++){
                        window[i][j+k] = message.charAt(k);
                    }
                    j += message.length()-1;
                }
                else{
                    window[i][j] = ' ';
                }
            }
        }
        return window;
    }

//**********************************************************************************************************************

    // Makes an array of 2 dealer's cards: the first card and the hidden one
    public static char[][] makeDealerHiddenHandPanel(Dealer dealer){
        char[][][] arrayOfPanels = new char[2][][];
        arrayOfPanels[0] = makeDealerSingleCardPanel(dealer.getCard(0));
        arrayOfPanels[1] = makeDealerSingleHiddenCardPanel();
        int maxColumns = 9;
        int leftIndentation = 113;
        int maxRows = leftIndentation + arrayOfPanels[0][0].length + arrayOfPanels[1][0].length;
        int counter = 0;
        char[][] result = new char[maxColumns][maxRows];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = ' ';
            }
        }

        for (char[][] arrayOfPanel : arrayOfPanels) {
            for (int j = 0; j < arrayOfPanel.length; j++) {
                for (int k = 0; k < arrayOfPanel[j].length; k++) {
                    result[j][k + counter + leftIndentation] = arrayOfPanel[j][k];
                }
            }
            counter += arrayOfPanels[0][0].length;
        }
        return result;
    }
    // Makes a single hidden dealer's card
    private static char[][] makeDealerSingleHiddenCardPanel(){
        int indent = 3;
        int columns = 9;
        int rows = indent+15;
        char[][] result = new char[columns][rows];
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (j < indent){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    result[i][j] = '-';
                }
                else if (j == indent || j == rows-1){
                    result[i][j] = '|';
                }
                else{
                    result[i][j] = '#';
                }
            }
        }
        return result;
    }
    // Makes a single dealer's card.
    private static char[][] makeDealerSingleCardPanel(Card card){
        int indent = 1;
        int columns = 9;
        int rows = indent+15;
        char[][] result = new char[columns][rows];
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (j < indent){
                    result[i][j] = ' ';
                }
                else if (i == 0 || i == columns-1){
                    result[i][j] = '-';
                }
                else if (j == indent || j == rows-1){
                    result[i][j] = '|';
                }
                else if (j == indent + 2 && i == 1 && card.getNumber().length() == 1){
                    result[i][j] = card.getNumber().charAt(0);
                    result[i][j+1] = card.getSuit().charAt(0);
                    j += 1;
                }
                else if (j == indent + 2 && i == 1 && card.getNumber().length() == 2){
                    result[i][j] = card.getNumber().charAt(0);
                    result[i][j+1] = card.getNumber().charAt(1);
                    result[i][j+2] = card.getSuit().charAt(0);
                    j += 2;
                }
                else if (i == 4 && j == indent + 7){
                    result[i][j] = card.getSuit().charAt(0);
                }
                else if (i == columns-2 && j == indent + 11 && card.getNumber().length() == 1){
                    result[i][j] = card.getNumber().charAt(0);
                    result[i][j+1] = card.getSuit().charAt(0);
                    j += 1;
                }
                else if (i == columns-2 && j == indent + 10 && card.getNumber().length() == 2){
                    result[i][j] = card.getNumber().charAt(0);
                    result[i][j+1] = card.getNumber().charAt(1);
                    result[i][j+2] = card.getSuit().charAt(0);
                    j += 2;
                }
                else{
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }

//**********************************************************************************************************************

    // Makes an array of dealer's cards
    public static char[][] makeDealerHandPanel(Dealer dealer){
        char[][][] arrayOfPanels = new char[dealer.getHand().length()][][];
        for (int i = 0; i < dealer.getHand().length(); i++){
            arrayOfPanels[i] = makeDealerSingleCardPanel(dealer.getCard(i));
        }
        int maxColumns = 9;
        int leftIndentation = 133 - arrayOfPanels.length * 10;
        int maxRows = leftIndentation + arrayOfPanels[0][0].length * arrayOfPanels[0].length;
        int counter = 0;
        char[][] result = new char[maxColumns][maxRows];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = ' ';
            }
        }

        for (char[][] arrayOfPanel : arrayOfPanels) {
            for (int j = 0; j < arrayOfPanel.length; j++) {
                for (int k = 0; k < arrayOfPanel[j].length; k++) {
                    result[j][k + counter + leftIndentation] = arrayOfPanel[j][k];
                }
            }
            counter += arrayOfPanels[0][0].length;
        }
        return result;
    }
}





