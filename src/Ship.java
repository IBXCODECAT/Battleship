//@author ibxcodecat (Ctrl + Click for commit hash)
//@path "%program files (x86)%\java\config"
//@this "%~dp0" #CWD

///<summary>
///Responsible for ship data and placement
///</summary>
public class Ship
{
    public enum Direction { Vertical, Horizontal, UNSET }
    protected Direction dir;
    
    //#region constants
    public static final int UNSET_ROW = -1;
    public static final int UNSET_COL = -1;
    //#endregion constants
    
    //#region instance variables
    private byte row;
    private byte col;
    private int len;
    //#endregion instance variables
    
    //#region getters
    protected int getRow() { return row; }
    protected int getCol() { return col; }
    protected int getLength() { return len; }
    
    protected int getDirection()
    {
        switch(this.dir)
        {
            case Horizontal: return 0;
            case Vertical: return 1;
            default: return -1;
        }
    }
    //#endregion getters
    
    public Ship(int len)
    {
        this.len = len;
        this.row = UNSET_ROW;
        this.col = UNSET_COL;
        this.dir = Direction.UNSET;
    }
    
    //#region setters
    protected void setLocation(int row, int col)
    {
        this.row = (byte)row;
        this.col = (byte)col;
    }
    
    protected void setDirection(int dir)
    {
        switch(dir)
        {
            case 0:
                this.dir = Direction.Horizontal;
                break;
            case 1:
                this.dir = Direction.Vertical;
                break;
        }
    }
    //#endregion setters
    
    //#region boolean checks
    protected boolean isLocationSet()
    {
        if(row == UNSET_ROW && col == UNSET_COL) return false;
        return true;
    }
    
    protected boolean isDirectionSet()
    {
        if(this.dir == Direction.UNSET) return false;
        return true;
    }
    //#endregion boolean checks
    
    //#region tostring
    public String toString()
    {
        if(dir == Direction.UNSET)
        {
            return "unset direction ship of length " + len + " at (unset location)";
        }
        else
        {
            if(dir == Direction.Horizontal)
            {
                return "horizontal ship of length " + len + " at (" + row + ", " + col + ")";
            }
            
            return "vertical ship of length " + len + " at (" + row + ", " + col + ")";
        }
    }
    //#endregion tostring
}
