public class Grid
{
        // Direction constants - copied from Ship.java
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;

    private static final String HEADER_COLS = "  1 2 3 4 5 6 7 8 9 10";
    private static final String HEADER_ROWS = "ABCDEFGHIJ";
    private static final String[] STATUS_STRINGS = {"-", "X", "O"};

    private Location[][] grid;


    // Create a new Grid. Initialize each Location in the grid
    // to be a new Location object.
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        
        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int col = 0; col < NUM_COLS; col++)
            {
                grid[row][col] = new Location();
            }
        }
    }

    /**
    * This method can be called on your own grid. To add a ship
    * we will go to the ships location and mark a true value
    * in every location that the ship takes up.
    */
    public void addShip(Ship myShip)
    {
        int row, col;
        int length = myShip.getLength();
        System.out.println("Add " + myShip);
        
        row = myShip.getRow();
        col = myShip.getCol();


        for (int i=0; i < length; i++)
        {
            if (myShip.getDirection() == VERTICAL)
            {
                grid[row+i][col].setShip(true);
                System.out.println("ship at (" + (row+i) + ", " + col + ")");
            } else {
                grid[row][col+i].setShip(true);
                System.out.println("ship at (" + row + ", " + (col+i) + ")");            
            }
        }
    }

    
    // Check if specified row & col are on the legal grid
    public boolean inBounds(int row, int col)
    {
        return row >= 0 && row < numRows() && col >= 0 && col < numCols();
    }
    
    /*
     * Check if this ship can be placed at the specified (in Ship s) location & direction
     *   1) ship does not extend out of grid, based on size, orientation and location
     *   2) there is not another ship overlapping the new ship coordinates.
     */
    public boolean legalShipLocation(Ship s)
    {
        int row = s.getRow();
        int col = s.getCol();
        
        if(s.getDirection() == VERTICAL)
        {
            for(int i = row; i < row + s.getLength(); i++)
            {
                if(!inBounds(i, col) || hasShip(i, col))
                {
                    return false;
                }
            }
        }
        else
        {
            for(int i = col; i < col + s.getLength(); i++)
            {
                if(!inBounds(row, i) || hasShip(row, i))
                {
                    return false;
                }
            }
        }
        return true;
    }

    // Mark a hit in this location by calling the markHit method
    // on the Location object.  
    public void markHit(int row, int col)
    {
        grid[row][col].markHit();
    }

    // Mark a miss on this location.    
    public void markMiss(int row, int col)
    {
        grid[row][col].markMiss();
    }

    // Set the status of this location object.
    public void setStatus(int row, int col, int status)
    {
        grid[row][col].setStatus(status);
    }

    // Get the status of this location in the grid  
    public int getStatus(int row, int col)
    {
        return grid[row][col].getStatus();
    }

    // Return whether or not this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col)    
    {
        return !grid[row][col].isUnguessed();
    }
    // Set whether or not there is a ship at this location to the val   
    public void setShip(int row, int col, boolean val)
    {
        grid[row][col].setShip(val);
    }

    // Return whether or not there is a ship here   
    public boolean hasShip(int row, int col)
    {
        return grid[row][col].hasShip();
    }


    // Get the Location object at this row and column position
    public Location get(int row, int col)
    {
        return grid[row][col];
    }

    // Return the number of rows in the Grid
    public int numRows()
    {
        return grid.length;
    }

    // Return the number of columns in the grid
    public int numCols()
    {
        return grid[0].length;
    }

    // Print the Grid status including a header at the top
    // that shows the columns 1-10 as well as letters across
    // the side for A-J
    // If there is no guess print a -
    // If it was a miss print a O
    // If it was a hit, print an X
    // A sample print out would look something like this:
    // 
    //   1 2 3 4 5 6 7 8 9 10 
    // A - - - - - - - - - - 
    // B - - - - - - - - - - 
    // C - - - O - - - - - - 
    // D - O - - - - - - - - 
    // E - X - - - - - - - - 
    // F - X - - - - - - - - 
    // G - X - - - - - - - - 
    // H - O - - - - - - - - 
    // I - - - - - - - - - - 
    // J - - - - - - - - - - 
    public void printStatus()
    {
        System.out.println(HEADER_COLS);
        for(int row = 0; row < NUM_ROWS; row++)
        {
            System.out.print(HEADER_ROWS.charAt(row) + " ");
            
            for(int col = 0; col < NUM_COLS; col++)
            {
                System.out.print(STATUS_STRINGS[getStatus(row, col)] + " ");
            }
            System.out.println();
        }
    }
    
    // Print the grid and whether there is a ship at each location.
    // If there is no ship, you will print a - and if there is a
    // ship you will print a X. You can find out if there was a ship
    // by calling the hasShip method.
    //
    //   1 2 3 4 5 6 7 8 9 10 
    // A - - - - - - - - - - 
    // B - X - - - - - - - - 
    // C - X - - - - - - - - 
    // D - - - - - - - - - - 
    // E X X X - - - - - - - 
    // F - - - - - - - - - - 
    // G - - - - - - - - - - 
    // H - - - X X X X - X - 
    // I - - - - - - - - X - 
    // J - - - - - - - - X - 
    public void printShips()
    {
        System.out.println(HEADER_COLS);
        for(int row = 0; row < NUM_ROWS; row++)
        {
            System.out.print(HEADER_ROWS.charAt(row) + " ");
            
            for(int col = 0; col < NUM_COLS; col++)
            {
                if(hasShip(row, col))
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
