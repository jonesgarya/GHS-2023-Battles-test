public class Battleship extends ConsoleProgram
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int NUM_SHIPS = 5;

    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    
    // Row & col constants
    private static final char MAX_ROW = 'J';
    private static final int MAX_COL = 10;
    

    public void run()
    {
        System.out.println("=======================\nWelcome to Battle Ship\n=======================");
        
        Player human = new Player();
        Player computer = new Player();
        
        setUpShips(human, computer);
        
        readLine("Hit enter to start guessing.");
        
        boolean gameOver = true;
        
        while(!gameOver)
        {
            // gameOver = playRound(human, computer);
        }
        
        if(human.hasWon())
        {
            System.out.println("You won!");
        }
        else
        {
            System.out.println("You lost!");
        }
        
        System.out.println("Thanks for playing!");
    }

    public void setUpShips(Player human, Player computer)
    {
        System.out.println("Human, place your ships.");
        initializeShipsByPlayer(human);

        System.out.println("Human has placed their ships.");
        human.printMyShips();
        
        readLine("Hit enter for the computer to choose their ship locations.");
        computer.initializeShipsRandomly();
        
        System.out.println("The computer has placed their ships.");
        computer.printMyShips();
    }

    public void initializeShipsByPlayer(Player player)
    {
       
        for(int i = 0; i < NUM_SHIPS; i++)
        {
            readLine("Hit enter to place your next ship.");
            System.out.println("Your current ships.");
            player.printMyShips();
 
            int length = SHIP_LENGTHS[i];
            Ship ship = new Ship(length);
            
            while(true)
            {
                // Ask user for row, column, direction.
                System.out.println("Place ship #" + i + " of length " + length);
                String rowInput = readLine("Enter row letter as starting (upper-left) position (ex. C): ");
                int    colInput = readInt("Enter col number as starting (upper-left) position as col letter (ex. 3): ");
                String dirInput = readLine("Enter ship orientation: (h)orizontal or (v)ertical: ");

                // grab row letter as first character of user input
                // convert letter to 0-9 matching A-J, so A=0, B=1, etc.
                rowInput.toUpperCase();
                char rowLetter = rowInput.charAt(0);
                int row = (int) rowLetter - 65;

                // grab column number, all except first char of user input
                int col = colInput;

                // convert user direction string to ship direction
                int dir = UNSET;
                String dirLetter = dirInput.substring(0,1);
                if (dirLetter.equalsIgnoreCase("h"))
                {
                    dir = HORIZONTAL;
                }
                else if (dirLetter.equalsIgnoreCase("v")) 
                {
                    dir = VERTICAL;
                }

                if (row == UNSET || col == UNSET || dir == UNSET) 
                {
                    System.out.println("Invalid input for ship location or direction. Try again!!");
                    continue;
                }

                ship.setDirection(dir);
                ship.setLocation(row, col);
                if(player.getGrid().legalShipLocation(ship))
                {
                    break;
                }
            }
            
            player.addShip(ship);
        }
    }
}
