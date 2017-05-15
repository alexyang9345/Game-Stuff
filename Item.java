import java.util.*;
import java.lang.reflect.*;

public class Item
{
    private String name, description, location;
    private int attackValue,healthValue, value;
    private String used;
    
    public Item(String n, String d, String l, int a, int healing, String u, int v)
    {
        name = n;
        description = d;
        location = l;
        attackValue = a;
        healthValue = healing;
        used = u;
        value = v;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public void setLocation(String nLoc)
    {
        location = nLoc;
    }
    
    public int getAttack()
    {
        return attackValue;
    }
    
    public int getHealing()
    {
        return healthValue;
    }
            
    public String use()
    {
        return used;
    }
    
    public int getValue()
    {
        return value;
    }
}
