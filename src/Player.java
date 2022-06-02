public class Player
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private Grid own = new Grid();
    private Grid opp = new Grid();
    
    private Ship[] ships = new Ship[5];
    private byte shipCount = 0;
    
    public Player() 
    {
        for (int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
    }
    
    public int getGridSize()
    {
        return own.numRows();
    }
    
    public void printMyShips() 
    {
        own.printShips();
    }
    
    public void printOpponentGuesses() 
    {
        opp.printStatus();
    }
    
    public void printMyGuesses() 
    {
        own.printStatus();
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        if(shipCount >= 5)
        {
            return;
        }
        
        shipCount++;
        s.setLocation(row, col);
        s.setDirection(direction);
        own.addShip(s);
    }
    
    public void recordOpponentGuess(int row, int col)
    {
        if (own.hasShip(row, col))
        {
            opp.markHit(row, col);
        }
        else opp.markMiss(row, col);
    }
    
    
}
