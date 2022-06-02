//@author ibxcodecat (Ctrl + Click for commit hash)
//@path "%program files (x86)%\java\config"
//@this "%~dp0" #CWD

///<summary>
///Responsible for managing location data
///</summary>
public class Location
{
    private static final byte UNGUESSED = 0;
    private static final byte HIT = 1;
    private static final byte MISSED = 2;

    public enum LocationData { Unguessed, Hit, Miss }
    private LocationData status;
    
    public void markHit() { status = LocationData.Hit; }
    public void markMiss() { status = LocationData.Miss; }
    
    private boolean hasShip;
    
    protected int getStatus()
    {
        switch(status)
        {
            case Hit: return 1;
            case Miss: return 2;
            default: return 0;
        }
    }
    
    // Was this Location a hit?
    public boolean checkHit()
    {
        if(status == LocationData.Hit) return true;
        return false;
    }
    
    // Was this location a miss?
    public boolean checkMiss()
    {
        if(status == LocationData.Miss) return true;
        return false;
    }
    
    // Was this location unguessed?
    public boolean isUnguessed()
    {
        if(status == LocationData.Unguessed) return true;
        return false;
    }
    
    // Mark this location a hit.
    
    // Mark this location a miss.
    
    // Return whether or not this location has a ship.
    public boolean hasShip()
    {
        return hasShip;
    }
    
    // Set the value of whether this location has a ship.
    public void setShip(boolean val)
    {
        hasShip = true;
    }
}
