import java.util.Scanner;
public class TTT {

  char[][] box;
  String status;
  int gameMenu;
  Scanner input;
  Boolean onePlayer = false;
  Boolean twoPlayer = false;
  char playerOne;
  char playerTwo;
  char computer;
  int playerTurn = 1;
  int playerComputerTurn = 1;

  TTT() {
    box = new char[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        box[i][j] = ' ';
      }
    }
    input = new Scanner(System.in);
  }
  
  void restartGame() {
    box = new char[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        box[i][j] = ' ';
      }
    }
    status = "";
    onePlayer = false;
    twoPlayer = false;
    playerOne = ' ';
    playerTwo = ' ';
    playerTurn = 1;
    playerComputerTurn = 1;
  }
  
  void setGrid(int i, int j, char c) {
    box[i - 1][j - 1] = c;
    this.displayGrid();
  }

  void displayGrid() {
    System.out.println(" " + box[0][0] + " | " + box[0][1] + " | " + box[0][2] + " ");
    System.out.println("---+---+---");
    System.out.println(" " + box[1][0] + " | " + box[1][1] + " | " + box[1][2] + " ");
    System.out.println("---+---+---");
    System.out.println(" " + box[2][0] + " | " + box[2][1] + " | " + box[2][2] + " \n");
  }
  
  void displayMenu() {
    System.out.println("Game Menu");
    System.out.println("1. Main Menu");
    System.out.println("2. New Game");
    System.out.println("3. Quit");
    System.out.println("Selection: ");
    
    while(!input.hasNextInt()) {
      System.out.println("Invalid input! Please choose either 1, 2, or 3: ");
      input.next();
    }
    
    int temp = input.nextInt();
    while(temp > 3 || temp < 0) {
      System.out.println("Invalid input! Please choose either 1, 2, or 3: ");
      temp = input.nextInt();
    }
    
    this.setGameMenu(temp);
  }
  
  // game menu 1, 2, 3
  void setGameMenu(int i) {
    gameMenu = i;
    
    if (gameMenu == 3 || gameMenu == 1) {
      this.displayMenu();
    } else {
      this.mainMenu();
    } 
  }
  
  // gameMenu = 2 --> mainMenu()
  void mainMenu() {
    System.out.println("Main Menu");
    System.out.println("1. One Player");
    System.out.println("2. Two Player");
    System.out.println("3. Quit");
    System.out.println("Selection: ");
    
    while(!input.hasNextInt()) {
      System.out.println("Invalid input! Please choose either 1, 2, or 3: ");
      input.next();
    }
    
    int temp = input.nextInt();
    while(temp > 3 || temp < 0) {
      System.out.println("Invalid input! Please choose either 1, 2, or 3: ");
      temp = input.nextInt();
    }
    
    if (temp == 1) {
      onePlayer = true;
    } else if (temp == 2) {
      twoPlayer = true;
    } else {
      this.displayMenu();
    }
  }
  
  void startGame() {
    if (twoPlayer) {
      System.out.println("Player 1 - Select Between 'X' or 'O': ");
      char temp = input.next().charAt(0);
      
      while (temp != 'X' && temp != 'O') {
        System.out.println("Invalid input. Please choose either 'X' or 'O': ");
        temp = input.next().charAt(0);
      }
      
      if (temp == 'X') {
        playerOne = 'X';
        playerTwo = 'O';
      } else {
        playerOne = 'O';
        playerTwo = 'X';
      }
      
      System.out.println("Player 2 - You are playing as \'" + playerTwo + "\'");
      
      this.displayGrid();
      status = "neutral";
      
      while (status == "neutral") {
        if (playerTurn % 2 != 0) {
          System.out.println("Player 1 - Select row and column: ");
          int row = input.nextInt();
          int col = input.nextInt();
          
          while (row < 1 || row > 3 || col < 1 || col > 3) {
            System.out.println("Invalid Selection - Player 1 - Select row and column: ");
            row = input.nextInt();
            col = input.nextInt();
          }
          this.setGrid(row, col, playerOne);
        } else {
          System.out.println("Player 2 - Select row and column: ");
          int row = input.nextInt();
          int col = input.nextInt();
          while (row < 1 || row > 3 || col < 1 || col > 3) {
            System.out.println("Invalid Selection - Player 2 - Select row and column: ");
            row = input.nextInt();
            col = input.nextInt();
          }
          this.setGrid(row, col, playerTwo);
        }
        playerTurn++;
        winningCheck();
      }
      if (status == "wins") {
        if (playerTurn % 2 == 1) System.out.println("Player 2 wins");
        else System.out.println("Player 1 wins");
        this.restartGame();
        this.displayMenu();
        this.startGame();
      } else if (status == "draws") {
        System.out.println("It's a draw");
        this.restartGame();
        this.displayMenu();
        this.startGame();
      }
    }
    
    if (onePlayer) {
      System.out.println("Player 1 - Select Between 'X' or 'O': ");
      char temp = input.next().charAt(0);
      
      while (temp != 'X' && temp != 'O') {
        System.out.println("Invalid input. Please choose either 'X' or 'O': ");
        temp = input.next().charAt(0);
      }
      
      if (temp == 'X') {
        playerOne = 'X';
        computer = 'O';
      } else {
        playerOne = 'O';
        computer = 'X';
      }
      
      this.displayGrid();
      status = "neutral";
      
      while (status == "neutral") {
          if (playerComputerTurn % 2 != 0) {
           System.out.println("Player 1 - Select row and column: ");
          int row = input.nextInt();
          int col = input.nextInt();
          
          while (row < 1 || row > 3 || col < 1 || col > 3) {
            System.out.println("Invalid Selection - Player 1 - Select row and column: ");
            row = input.nextInt();
            col = input.nextInt();
          }
          this.setGrid(row, col, playerOne);
          } else {
            this.computerMove();
          }
          playerComputerTurn++;
          this.winningCheck();
      }
      if (status == "wins") {
        if (playerComputerTurn % 2 != 0) System.out.println("Computer wins");
        else System.out.println("Player 1 wins");
        this.restartGame();
        this.displayMenu();
        this.startGame();
      } else if (status == "draws") {
        System.out.println("It's a draw");
        this.restartGame();
        this.displayMenu();
        this.startGame();
      }
    }
  }
  
  void winningCheck() {
    if ( box[0][0] == box[0][1] && box[0][1] == box[0][2] && (box[0][1] == 'X' || box[0][1] == 'O') ) {
      status = "wins";
    } else if (box[0][0] == box[1][0] && box[1][0] == box[2][0] && (box[1][0] == 'X' || box[1][0] == 'O')) {
      status = "wins";
    } else if (box[0][2] == box[1][2] && box[1][2] == box[2][2] && (box[1][2] == 'X' || box[1][2] == 'O')) {
      status = "wins";
    } else if (box[2][0] == box[2][1] && box[2][1] == box[2][2] && (box[2][1] == 'X' || box[2][1] == 'O')) {
      status = "wins";
    } else if (box[0][1] == box[1][1] && box[1][1] == box[2][1] && (box[1][1] == 'X' || box[1][1] == 'O')) {
      status = "wins";
    } else if (box[1][0] == box[1][1] && box[1][1] == box[1][2] && (box[1][1] == 'X' || box[1][1] == 'O')) {
      status = "wins";
    } else if (box[0][0] == box[1][1] && box[1][1] == box[2][2] && (box[1][1] == 'X' || box[1][1] == 'O')) {
      status = "wins";
    } else if (box[0][2] == box[1][1] && box[1][1] == box[2][0] && (box[1][1] == 'X' || box[1][1] == 'O')) {
      status = "wins";
    } else if (box[0][0] != ' ' && box[0][1] != ' ' && box[0][2] != ' ' && box[1][0] != ' ' && box[1][1] != ' ' && box[1][2] != ' ' && box[2][0] != ' ' && box[2][1] != ' ' && box[2][2] != ' ') {
      status = "draws";
    } else {
      status = "neutral";
    }
  }
  
  void computerMove() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (box[i][j] == ' ') {
          this.setGrid(i+1, j+1, computer);
          return;
        } 
      }
    }
  }
}
