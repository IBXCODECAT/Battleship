//@author ibxcodecat (Ctrl + Click for commit hash)
//@path "%program files (x86)%\java\config"
//@this "%~dp0" #CWD

///<summary>
///Responsible for managing a grid of locations
///</summary>
public class Grid
{
    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    private Location[][] grid = new Location[NUM_ROWS][NUM_COLS];
    
    public Grid()
    {
        for(byte x = 0; x < NUM_ROWS; x++)
        {
            for(byte y = 0; y < NUM_COLS; y++)
            {
                grid[x][y] = new Location();
            }
        }
    }
    
    //#region getters
    protected Location get(int x, int y) { return grid[x][y]; }
    protected int getStatus(int x, int y) { return grid[x][y].getStatus(); }
    protected int numRows() { return NUM_ROWS; }
    protected int numCols() { return NUM_COLS; }
    //#endregion getters
    
    //#region setters
    protected void markHit(int x, int y) { grid[x][y].markHit(); }
    protected void markMiss(int x, int y) { grid[x][y].markMiss(); }
    
    protected void setShip(int x, int y, boolean val)
    {
        grid[x][y].setShip(val);
    }
    //#endregion setters
    
    //#region checkers
    protected boolean hasShip(int x, int y)
    {
        return grid[x][y].hasShip();
    }
    
    protected boolean alreadyGuessed(int x, int y)
    {
        return !grid[x][y].isUnguessed();
    }
    //#endregion checkers
    
    //#region helper methods
    
    protected void printStatus()
    {
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
        StringBuilder output = new StringBuilder(100);
        
        output.append("  ");
        
        for(int x = 0; x <= NUM_ROWS; x++)
        {
            if(x != 0) output.append(letters[x - 1] + " ");
            
            for(int y = 0; y < NUM_COLS; y++)
            {
                if(x == 0)
                {
                    output.append(y + 1);
                    
                    if(y != NUM_COLS - 1)
                    {
                        output.append(" ");
                    }
                }
                else 
                {
                    if(alreadyGuessed(x-1, y))
                    {
                        if(hasShip(x-1, y))
                        {
                            output.append("X ");
                        }
                        else
                        {
                            output.append("O ");
                        }
                    }
                    else
                    {
                        output.append("- ");
                    }
                }
            }
            
            output.append("\n");
        }
        
        System.out.println(output);
    }
    
    protected void printShips()
    {
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
        StringBuilder output = new StringBuilder(100);
        
        output.append("  ");
        
        for(int x = 0; x <= NUM_ROWS; x++)
        {
            if(x != 0) output.append(letters[x - 1] + " ");
            
            for(int y = 0; y < NUM_COLS; y++)
            {
                if(x == 0)
                {
                    output.append(y + 1);
                    
                    if(y != NUM_COLS - 1)
                    {
                        output.append(" ");
                    }
                }
                else 
                {
                    if(hasShip(x-1, y))
                    {
                        output.append("X ");
                    }
                    else
                    {
                        output.append("- ");
                    }
                }
            }
            
            output.append("\n");
        }
        
        System.out.println(output);
    }
    
    /**
     * This method can be called on your own grid. To add a ship
     * we will go to the ships location and mark a true value
     * in every location that the ship takes up.
     */
    public void addShip(Ship s)
    {
        boolean dir = s.getDirection() == 1;
        
        int len = s.getLength();
        
        for(int i = 0; i < len; i++)
        {
            if(dir)
                setShip(s.getRow() + i, s.getCol(), true);
            else
                setShip(s.getRow(), s.getCol() + i, true);
        }
    }
    //#endregion helper methods
}
