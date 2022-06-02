import java.util.Scanner;
import java.util.HashMap;

public class Battleship extends ConsoleProgram
{
    //region constants
    protected final Scanner userInput = new Scanner(System.in);
    protected final Randomizer rng = new Randomizer();
    
    private final Player user = new Player();
    private final Player comp = new Player();
    //endregion
    
    //region protected/private globals
    protected enum ShipType { PatrolBoat, Destroyer, Submarine, BattleShip, Carrier };
    protected HashMap<ShipType, Byte> shipSize = new HashMap<ShipType, Byte>();
    protected HashMap<ShipType, Boolean> shipPlaced = new HashMap<ShipType, Boolean>();
    
    private ShipType Tship;
    //endregion 
    
    //region helper methods
    private void clearConsole()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
    private void suspendExec()
    { 
        System.out.println("Press Enter key to continue...");
        try { System.in.read(); } catch (Exception e) {}  
    }
    //endregion
    
    private void Setup()
    {
        shipSize.put(Tship.PatrolBoat, new Byte((byte)0x002));
        shipSize.put(Tship.Destroyer, new Byte((byte)0x003));
        shipSize.put(Tship.Submarine, new Byte((byte)0x003));
        shipSize.put(Tship.BattleShip, new Byte((byte)0x004));
        shipSize.put(Tship.Carrier, new Byte((byte)0x005));
        
        System.out.println("Welcome to battleship!");
        this.suspendExec();
        clearConsole();
        
        playerSetup();
        computerSetup();
    }
    
    private void playerSetup()
    {
        
        System.out.println("Let's place your ships!\n\n");
        user.printMyShips();
        
        while(true)
        {
            String selection = readLine("Enter the name of the ship to place\nPatrolBoat | Destroyer | Submarine | BattleShip | Carrier>>>");
        
            ShipType[] shipTypes = ShipType.values();
            
            for(ShipType type : shipTypes)
            {
                String shipStr = type.name();
                byte gridSize = (byte)user.getGridSize();
                
                if(shipStr.toLowerCase().equals(selection.toLowerCase()))
                {
                    boolean validCoords = false;
                    boolean validDir = false;
                    
                    byte dir = (byte)readInt("Enter ship direction (0 vertical | 1 Horizontal)>>>");
                    boolean orientation = false;
                    
                    while(validDir)
                    {
                        System.out.println("Invalid Direction | Expected (0/1)");
                        dir = (byte)readInt("Enter ship direction (0 vertical | 1 Horizontal)>>>");
                        
                        validDir = dir == 0 || dir == 1;
                        orientation = dir == 0;
                    }
                    
                    byte x = (byte)readInt("Enter X coordanate for '" + shipStr + "'>>>");
                    byte y = (byte)readInt("Enter y coordanate for '" + shipStr + "'>>>");
                    
                    byte size = Byte.valueOf(shipSize.get(type));
                    
                    validCoords = x <= (gridSize - size) && y <= (user.getGridSize() - size);   

                    while(!validCoords)
                    {
                        System.out.println("The loaction (" + x + "," + y + ") would place the '" + shipStr + "' out of bounds!");
                        
                        byte correctedX = (byte)((short)gridSize - size);
                        byte correctedY = (byte)((short)gridSize - size);
                        
                        boolean allowCorrection = false;
                        
                        if(orientation)
                        {
                            String correctionPermission = readLine("We can correct your ship to be at location (" + correctedX + "," + y + "). Would you like us to auto correct your ship placement(Y/N)?>>>");
                            allowCorrection = correctionPermission.toLowerCase().contains("y");
                        }
                        else
                        {
                            String correctionPermission = readLine("We can correct your ship to be at location (" + x + "," + correctedY + "). Would you like us to auto correct your ship placement(Y/N)?>>>");
                            allowCorrection = correctionPermission.toLowerCase().contains("y");
                        }
                        
                        if(allowCorrection)
                        {
                            if(orientation) x = correctedX;
                            else y = correctedY;
                            validCoords = true;
                        }
                        else
                        {
                            x = (byte)readInt("Enter X coordanate for '" + shipStr + "'>>>");
                            y = (byte)readInt("Enter y coordanate for '" + shipStr + "'>>>");
                            
                            size = shipSize.get(type);
                            
                            if(dir == 1) validCoords = x <= (user.getGridSize() - size);
                            else validCoords = y <= (user.getGridSize() - size);
                        }
                    }
                    
                    
                    

                    
                    
                    
                }
            }    
        }
    }
    
    private void computerSetup()
    {
        
    }
    
    
    
    public void run()
    {
        Setup();
        
        short turnNumber = 0;
        
        while(true)
        {
            if(turnNumber % 2 == 0)
            {
                user.printMyShips();
                this.suspendExec();
            }
            else
            {
                comp.printMyShips();
                this.suspendExec();
            }
            
            turnNumber++;
        }
    }
}
