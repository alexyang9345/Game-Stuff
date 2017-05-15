import java.util.*;
import java.lang.reflect.*;

public class Room
{
   private int x,y,z;
   private String name;
   private RoomDesc description;
   private boolean north, south, east, west, up, down;
   private ArrayList<Item> roomItems = new ArrayList<Item>();
   private Enemy enemy;
   private String key;
   
   //Different constructors for # of items in room initially, max of four
   public Room(int cx, int cy, int cz, String names, String body, boolean n, boolean s, boolean e, boolean w,
               boolean u, boolean d, ArrayList<Item> stuff, Enemy en, String k)
   {
       x = cx; y = cy; z = cz;
       name = names;
       north = n; south = s; east = e; west = w;
       up = u; down = d;
       roomItems = stuff;
       enemy = en;
       key = k;
       description = new RoomDesc(body, roomItems, enemy);
    }
   
   //Possible movement methods
   
   public boolean canMoveNorth()
   {
       return north;
   }
   
   public boolean canMoveSouth()
   {
       return south;
   }
   
   public boolean canMoveEast()
   {
       return east;   
   }
   
   public boolean canMoveWest()
   {
       return west;
   }
    
    public boolean canMoveUp()
    {
        return up;
    }
    
    public boolean canMoveDown()
    {
        return down;
    }
    
    public void open(String direction)
    {
        if(direction.toUpperCase().equals("NORTH") || direction.toUpperCase().equals("N"))
            north = true;
            
        else if(direction.toUpperCase().equals("SOUTH") || direction.toUpperCase().equals("S"))
            south = true;
            
        else if(direction.toUpperCase().equals("EAST") || direction.toUpperCase().equals("E"))
            east = true;
            
        else if(direction.toUpperCase().equals("WEST") || direction.toUpperCase().equals("W"))
            west = true;
            
        else if(direction.toUpperCase().equals("DOWN") || direction.toUpperCase().equals("D"))
            down = true;
            
        else
            System.out.println("ERROR: INVALID DIRECTION, NO KEY FOUND");
        }
    //Get Room Coordinates
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getZ()
    {
        return z;
    }
    
    //Room Description / room item status
    public String getDescription()
    {
        return description.getDescription();
    }
    
    public void setDescription(String newDesc)
    {
        description.setBody(newDesc);
    }
    
    public void addItem(Item item)
    {
        roomItems.add(item);
        description.addItem(item);
    }
    
    public void removeItem(Item item)
    {
        int index = -1;
        for(int k = 0; k < roomItems.size(); k++)
        {
            if(item.getName().equals(roomItems.get(k).getName()))
            {
                index = k;
                break;
            }
        }
        if(index != -1)
        {
            roomItems.remove(index);
            description.removeItem();
        }
    }
    
    public ArrayList<Item> getItems()
    {
        return roomItems;
    }
    
    public Enemy getEnemy()
    {
        return enemy;
    }
    
    public String getKey()
    {
        return key;
    }
}
