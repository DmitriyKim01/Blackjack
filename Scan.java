import java.util.InputMismatchException;
import java.util.Scanner;
// Helper static class for: BlackJack | Logic
// This class is responsible for all the user inputs and its validation.
public class Scan {
//**********************************************************************************************************************
    // Takes and validates user input for the players amount
    public static int takePlayersAmount(){
        Scanner scan = new Scanner(System.in);
        makeIndentation(30);
        System.out.print("-> ");
        int amount = validatePlayersAmount();
        clearScreen();
        return amount;
    }
    // Helper method of takePLayersAmount(): Validates players amount
    private static int validatePlayersAmount(){
        Scanner scan = new Scanner(System.in);
        boolean isValidInput = false;
        int amount = 0;
        while(!isValidInput){
            try{
                amount = scan.nextInt();
                checkIfQuit(String.valueOf(amount));
                while(amount <= 1 || amount > 4){
                    System.out.println("Enter the valid number!");
                    makeIndentation(30);
                    System.out.print("-> ");
                    amount = scan.nextInt();
                }
                isValidInput = true;
            }
            catch(InputMismatchException e){
                makeIndentation(30);
                System.out.println("Enter a number!");
                makeIndentation(30);
                System.out.print("-> ");
                checkIfQuit(String.valueOf(scan.next()));
            }
        }
        return amount;
    }
//**********************************************************************************************************************
    // Takes and validates user input for the player's name
    public static String takeName(int indent){
        Scanner scan = new Scanner(System.in);
        indent = indent *60+23;
        makeIndentation(indent);
        System.out.print("-> ");
        String name = scan.nextLine();
        name = validateName(name, indent);
        clearScreen();
        return name;
    }
    // Helper method for takeName(). Validates palyer's name
    private static String validateName(String name, int indent){
        Scanner scan = new Scanner(System.in);
        String newName = name;
        checkIfQuit(name);
        while (newName.length() > 30 || newName.length() < 2){
            makeIndentation(indent);
            System.out.println("Your name is too short or too long!");
            makeIndentation(indent);
            System.out.print("Enter the name between 2 and 30 characters: ");
            newName = scan.nextLine();
        }
        return newName;
    }
//**********************************************************************************************************************
    // Takes and validates user input for the bet
    public static int takeBet(int index, Player player){
        Scanner scan = new Scanner(System.in);
        int indent = index * 60+23;
        makeIndentation(indent);
        System.out.print("-> ");
        int bet = validateBet(indent, player);
        clearScreen();
        return bet;
    }
    // Helper method for takeBet(). Validates bet
    private static int validateBet(int indent, Player player){
        Scanner scan = new Scanner(System.in);
        boolean isValidInput = false;
        int bet = 0;
        while(!isValidInput){
            try{
                bet = scan.nextInt();
                while(bet != 5 && bet != 25 && bet != 50 && bet != 100 && bet != 250 && bet != 500 || player.getBalance() < bet){
                    makeIndentation(indent);
                    System.out.println("Enter the valid bet!");
                    makeIndentation(indent);
                    System.out.print("-> ");
                    bet = scan.nextInt();
                }
                isValidInput = true;
            }
            catch(InputMismatchException e){
                makeIndentation(indent);
                System.out.println("Enter a number!");
                makeIndentation(indent);
                System.out.print("-> ");
                checkIfQuit(String.valueOf(scan.next()));
            }
        }
        return bet;
    }
//**********************************************************************************************************************
    // Takes and validates user's input for the move during the playing stage
    public static String takeNextMove(int index){
        Scanner scan = new Scanner(System.in);
        int indent = index * 60+23;
        makeIndentation(indent);
        System.out.print("Enter your next move: ");
        String nextMove = scan.nextLine();
        // Easter eggs
        rickRollEasterEgg(nextMove);
        amyEasterEgg(nextMove);
        nextMove = validateNextMove(nextMove, indent);
        clearScreen();
        return nextMove;
    }
    // Helper method for takeNextMove(). Validates player's next move
    private static String validateNextMove(String nextMove, int indent){
        Scanner scan = new Scanner(System.in);
        String move = nextMove;
        while ( !move.equalsIgnoreCase("hit")    &&
                !move.equalsIgnoreCase("stand")  &&
                !move.equalsIgnoreCase("double") &&
                !move.equalsIgnoreCase("1")      &&
                !move.equalsIgnoreCase("2")      &&
                !move.equalsIgnoreCase("3")){
            checkIfQuit(move);
            makeIndentation(indent);
            System.out.println("Invalid input!");
            makeIndentation(indent);
            System.out.print("Enter your next move: ");
            move = scan.nextLine();
        }
        return move;
    }
//**********************************************************************************************************************
    // Ask user to press enter
    public static void enter(int index){
        Scanner scan = new Scanner(System.in);
        int indent = index * 60+23;
        System.out.println();
        System.out.println();
        makeIndentation(indent);
        System.out.print("Press enter to continue...");
        checkIfQuit(scan.nextLine());
        clearScreen();
    }
//**********************************************************************************************************************
    // Helper methods:
    // Checks if user entered q or quit for quit. If he does, the program stops
    private static void checkIfQuit(String input){
        if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")){
            clearScreen();
            Screen.showBlackJack();
            Screen.showDealer();
            Screen.showThanksForPlaying();
            System.exit(0);
        }
    }
    // Clears the screen
    private static void clearScreen(){
        System.out.print("\033c");
    }
    // Makes indentation based on the parameter input
    private static void makeIndentation(int length){
        for (int i = 0; i < length; i++){
            System.out.print(" ");
        }
    }

    // Easter egg:
    private static void rickRollEasterEgg(String nextMove){
        if (nextMove.equalsIgnoreCase("4")){
            clearScreen();
            Screen.showLyric1();
            Screen.showRickRoll();
            Scan.enter(0);
            Screen.showLyric2();
            Screen.showRickRoll();
            Scan.enter(0);
            Screen.showLyric3();
            Screen.showRickRoll();
        }
    }
    // Easter egg:
    private static void amyEasterEgg(String nextMove){
        if (nextMove.equalsIgnoreCase("Amy")){
            clearScreen();
            Screen.showBlackJack();
            Screen.showAmySpecialRequest();
        }
    }
//**********************************************************************************************************************
}

