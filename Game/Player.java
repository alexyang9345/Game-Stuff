import java.util.*;
import java.lang.reflect.*;

public class Player
{
    private String name;
    private int x,y,z; 
    private boolean isAlive;
    private int health; 
    private int maxHealth;
    private Item equippedItem;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private Room[][][] map;
    private int inventoryMax;
    private Room currentRoom;
    private int attack;
    //private Weapon eqWep;
    
    public Player(String user, Room[][][] rooms)
    {
        name = user;    x = 4; y = 12; z = 1;
        isAlive = true;
        equippedItem = null;
        health = 100; maxHealth = 100;
        inventoryMax = 15;
        map = rooms;
        currentRoom = map[4][12][1];
        attack = 1;
        //eqWep = null;
        
        for(int k = 0; k < 15; k++)
        {
            inventory.add(null);
        }
        
        //Add some if statements for easter egg user names
    }
    
    //Position Methods
    //CHANGE ALL SO THEY RETURN A STATUS STRING
    
    public String moveNorth()
    {
        if(currentRoom.canMoveNorth())
        {
            this.y = this.y - 1;
            setCurrentRoom();
            return "\n" + currentRoom.getDescription();
        }
        else
            return "You cannot go that way...";
    }
    
    public String moveSouth()
    {
        if(currentRoom.canMoveSouth())
        {
            y += 1;
            setCurrentRoom();
            return "\n" + currentRoom.getDescription();
        }
        else
            return "You cannot go that way...";
    }
    
    public String moveEast()
    {
        if(currentRoom.canMoveEast())
        {
            x += 1;
            setCurrentRoom();
            return "\n" + currentRoom.getDescription();
        }
        else
            return "You cannot go that way...";
    }
    
    public String moveWest()
    {
        if(currentRoom.canMoveWest())
        {
            x -= 1;
            setCurrentRoom();
            return "\n" + currentRoom.getDescription();
        }
        else
            return "You cannot go that way...";
    }
    
    public String moveUp()
    {
        if(currentRoom.canMoveUp())
        {
            z += 1;
            setCurrentRoom();
            return "\n" + currentRoom.getDescription();
        }
        else
            return "You cannot go that way...";
    }
    
    public String moveDown()
    {
        if(currentRoom.canMoveDown())
        {
            z -= 1;
            setCurrentRoom();
            return "\n" + currentRoom.getDescription();
        }
        else
            return "You cannot go that way...";
    }
    
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
    
    public String getPosition()
    {
        return "X: " + x + "\tY: " + y + "\tZ: " + z;
    }
    
    public void setX(int c)
    {
        x = c;
    }
    
    public void setY(int c)
    {
        y = c;
    }
    
    public void setZ(int c)
    {
        z = c;
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
 
    public void setCurrentRoom()
    {
        boolean outOfBounds = false;
        currentRoom = map[x][y][z];
        
        if(outOfBounds)
            System.out.println("Error: Player out of bounds!");
        }
        
    public String unlock(String direction)
    {
        if(currentRoom.getKey() == null)
            return "You can't be serious...";
            
        else
        {
            int index = findItem(currentRoom.getKey());
            
            if(index != -1)
            {
                currentRoom.open(direction);
                return inventory.get(index).use();
            }
            
            else
                return "You don't have the necessary item to get through";
            }
        }
        
    //Player Status methods
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public boolean checkLife()
    {
        if( health > 0 )
            return true;    
        else
            return false;
    }
    
    public String getHealth()
    {
        if(health == 100)
            return "You are perfectly healthy!";
        
        else if( health >= 80 )
            return "You are a bit banged up, but feeling ok!";
            
        else if(health >= 60)
            return "You are in quite a bit of pain, but nothing that isn't manageable!";
            
        else if(health >= 40)
            return "You won't last much longer if you keep this up...";
            
        else if(health >= 20)
            return "You are greivously wounded...";
            
        else if(health > 0)
            return "You are on the verge of death...";
            
        else
            return "You seem to be rather dead...";
    }
    
    public String heal(Item item)
    {
        if(item.getHealing() > 0)
            return item.use() + "\n" + this.changeHealth(item.getHealing());
            
        else
            return "You can't do that...";
        }
    
    public int getHealthInt()
    {
        return health;
    }
    
    public String changeHealth(int change)
    {
        int previous = health;
        if( (health + change) > maxHealth)
        {
            change = maxHealth - health;
            health += change;
        }
        else
            health += change;
            
        if(health <= 0)
        {   
            isAlive = false;
            return "You are dead";
        }
            
        if(health >= previous)
            return "You feel much better";
            
        else
            return "That hurt";
    }
    
    //Inventory Management
    
    public String getInventory()
    {
        String gubs = "Inventory - ";
        int emptySpaces = inventory.size();
        
        for (int k = 0; k < inventory.size(); k++)
        {
            if( inventory.get(k) != null )
            {
                emptySpaces --;
                gubs += "\n" + inventory.get(k).getName();
            }
        }
        
        gubs += "\n" + emptySpaces + "/" + inventory.size();
        return gubs;
    }
    
    //ADD CHECK FOR IF ITEM EVEN EXISTS
    public String addItem(Item item)
    {
        boolean isFull = true;
        int index = -1;
        String status;
        
        for(int k = 0; k < inventory.size(); k++)
        {
            if(inventory.get(k) == null)
            {   isFull = false;
                index = k;
                break;
            }
            }
            
        //If inventory is not full, add the item    
        if( isFull == false && index != -1)
        {   inventory.set(index, item);
            status = item.getName() + "taken";
        }
            
        else
            status = "Bag is full...";
            
        return status;
    }
    
    public int findItem(String Iname)
    {
        boolean found = false;
        int index = 0;
        
        for(int k = 0; k < inventory.size(); k++)
        {
            if( Iname.toUpperCase().equals(inventory.get(k).getName().toUpperCase()))
            {
                found = true;
                index = k;
                break;
            }
        }
        
        if(found)
            return index;
            
        else
            return -1;
                
    }
    
    public String removeItem(String Iname)
    {
        String status;
        int index = findItem(Iname);
        
        if(index != -1)
        {
            status = inventory.get(index).getName() + " removed!";
            inventory.remove(index);
            inventory.add(null);
            return status;
        }
        
        else
        {
            status = "You have no such item...";
            return status;
        }
    }
    
    public String dropItem(String Iname)
    {
        String status;
        int index = findItem(Iname);
        
        if(index != -1)
        {
            status = inventory.get(index).getName() + " dropped!";
            currentRoom.addItem(inventory.get(index));
            inventory.remove(index);
            inventory.add(null);
            return status;
        }
        
        else
        {
            status = Iname + " not found...";
            return status;
        }
    }
    
    public ArrayList<Item> getItems()
    {
        return inventory;
    }
    
    public String equip(String Iname)
    {
        int index = findItem(Iname);
        
        if(index != -1)
        {
            if(inventory.get(index).getAttack() > 0)
            {
                equippedItem = inventory.get(index);
                return "Equipped";
            }
            
            else
                return "You can't equip that";    
        }
        
        else
            return "You want to equip what?";
    }
    
    //Combat
    public String Attack(Enemy enemy)
    {
        int miss = (int)(Math.random()*5);
        if (miss != 1)
        {
            int damage = -1 * (int)(Math.random() * ((this.attack + 10)-(this.attack - 10)));
            enemy.changeHealth(damage);
            return ("You attack " + enemy.getName() + " for " + damage + " damage.");
        }
        else
        {
            return ("You attack the " +  enemy.getName() + " but miss.");
        }
    }
    
    public String attack(Enemy e)
    {
        return "yes";
    }
    
    public Room[][][] getMap()
    {
        return map;
    }
        
}
