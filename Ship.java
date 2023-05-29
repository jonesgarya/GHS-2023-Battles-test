public class Ship
{
    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    private static final String[] directionNames = {"unset direction", "horizontal", "vertical"};
    private static final String rowNames = "ABCDEFGHIJKLMNOP";

    // Instance variables
    private int row;        // row location of the ship
    private int col;        // column location of the ship
    private int length;     // length of the ship
    private int direction;  // orientation of the ship (horz, vert)

    // Constructor. Create a ship and set the length.
    public Ship(int length) 
    {
        this.length = length;
        this.row = UNSET;
        this.col = UNSET;
        this.direction = UNSET;
    }

    // Has the location been initialized
    public boolean isLocationSet() 
    {
        return (this.row != UNSET) && (this.col != UNSET);
    }

    // Has the direction been initialized
    public boolean isDirectionSet() 
    {
        return this.direction != UNSET;
    }

    // Set the location of the ship 
    public void setLocation(int row, int col) 
    {
        this.row = row;
        this.col = col;
        // System.out.println(locationToString());
    }

    // Set the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    // Getter for the row value
    public int getRow()
    {
        return this.row;
    }

    // Getter for the column value
    public int getCol()
    {
        return this.col;
    }

    // Getter for the length of the ship
    public int getLength()
    {
        return this.length;
    }

    // Getter for the direction
    public int getDirection()
    {
        return this.direction;
    }

    // Helper method to get a string value from the direction
    private String directionToString()
    {
        return (directionNames[this.direction + 1]);
    }

    private char rowToLetter(int rowNum)
    {
        return rowNames.charAt(rowNum);
    }
    
    private int letterToRow(char rowLetter)
    {
        return (int) rowLetter - 64;
    }
    
    // Helper method to get a string value for the location (row, col)
    private String locationToString()
    {
        if(isLocationSet())
        {
            return "(" + rowToLetter(this.row) + ", " + (this.col+1) + ")";
        }
        else
        {
            return "(unset location)";
        }
    }


    // toString value for this Ship
    public String toString()
    {
        return directionToString() + " ship of length " + this.length + " at " + this.locationToString(); 

    }

}
