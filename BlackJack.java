// Application class
// This class is responsible for the actual game cycles

public class BlackJack {
	public static void main(String[] args) {
		Screen.showWelcomeToBlackJack();
		Screen.showCredentials();
		Scan.enter(0);
		Screen.showBlackJack();
		Screen.showPlayersAmountQuestion();
		Player[] players = Logic.definePlayers();
		do{
			Logic.startBettingStage(players);
			Dealer dealer = new Dealer();
			Deck deck = new Deck();
			Logic.startPlayingRound(players, dealer, deck);
			Logic.startCheckForWinnersStage(players, dealer, deck);
			players = Logic.redefinePlayers(players);
		}while (players.length != 0);
		Screen.showBlackJack();
		Screen.showDealer();
		Screen.showThanksForPlaying();
	}
}

// Assumptions:
// 0. The game is running full screen only (otherwise you can encounter visual issues). If it's still buggy even on fullscreen, turn off the text wrap in your terminal or decrease font size or zoom out.
// 1. Min players 2 ... Max players 4
// 2. Player's name should be between 2 and 30 characters
// 3. No split option
// 4. Player can't choose Ace value. Instead, Ace is 11 by default and if player overflow, it automatically changes to 1
// 5. Ace value can be changed only once
// 6. Player have only 3 options: hit (1), stand (2), double (3)
// 7. DO NOT PRESS (4) during the playing stage
// 8. There are only 6 types of bets: | 5 | 25 | 50 | 100 | 250 | 500 |
// 9. Player's initial balance is always 5000
// 10. Player can press (q) or (quit) anytime during the game