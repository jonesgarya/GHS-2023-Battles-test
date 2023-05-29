public class Player
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int NUM_SHIPS = 5;
    private static final int MAX_HITS = computeMaxHits();

    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    
    
    private Grid myGrid;
    private Grid guessGrid;
    private Ship[] myShips;
    private int numShips;
    private int totalHits;
    private boolean winner;
    
    public Player()
    {
        myGrid = new Grid();
        guessGrid = new Grid();
        myShips = new Ship[NUM_SHIPS];
        numShips = 0;
        totalHits = 0;
        winner = false;
    }

    private static int computeMaxHits()
    {
        int sum = 0;
        for(int l : SHIP_LENGTHS)
        {
            sum += l;
        }
        return sum;
    }
    
    
    public void initializeShipsRandomly()
    {
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            int length = SHIP_LENGTHS[i];
            int dir;
            int row;
            int col;
            Ship ship = new Ship(length);
            
            while(true)
            {
                dir = Randomizer.nextInt(HORIZONTAL, VERTICAL);
                row = Randomizer.nextInt(0, myGrid.numRows() - 1);
                col = Randomizer.nextInt(0, myGrid.numCols() - 1);
                ship.setDirection(dir);
                ship.setLocation(row, col);
                if(myGrid.legalShipLocation(ship))
                {
                    break;
                }
            }
            
            addShip(ship);
        }
    }
    
    public void addShip(Ship s)
    {
        if (numShips < NUM_SHIPS)
        {
            myGrid.addShip(s);
            myShips[numShips] = s;
            numShips++;
        }
    }

    public void markGuess(int row, int col, boolean val)
    {
        guessGrid.setShip(row, col, val);
        if(val)
        {
            guessGrid.markHit(row, col);
            if (totalHits++ == MAX_HITS)
            {
                winner = true;
            }
        }
        else
        {
            guessGrid.markMiss(row, col);
        }

    }

    /*
     * Takes in an oppnent guess for a row, col location.
     * Records the guess, and returns a boolean indicating
     * whether the guess was a hit.
     */
    public boolean recordOpponentGuess(int row, int col)
    {
        if(myGrid.hasShip(row, col))
        {
            myGrid.markHit(row, col);
        }
        else
        {
            myGrid.markMiss(row, col);
        }
        return myGrid.hasShip(row, col);
    }


    public void printMyShips()
    {
        myGrid.printShips();
    }

    public boolean hasWon()
    {
        return winner;
    }
    
    public void printOpponentGuesses()
    {
        myGrid.printStatus();
    }
    
    public void printMyGuesses()
    {
        guessGrid.printStatus();
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        myGrid.addShip(s);
        
    }

}
