import java.util.*;
public class Enemy
{
    private String name, description; 
    private boolean isAlive;
    private int health;
    private int attack;
    public Enemy(String name, int health, int attack, String desc)
    {
        this.name = name; 
        description = desc;
        this.health = health;
        this.attack = attack;
        isAlive = true;
    }
    
    public String attack(Player player)
    {
        int miss = (int)(Math.random()*5);
        if (miss != 1)
        {
            int damage = -1 * (int)(Math.random() * ((this.attack + 10)-(this.attack - 10)));
            player.changeHealth(damage);
            return (this.name + " hits you with a powerful blow!");
        }
        else
        {
            return (this.name + " attacked you but missed. ");
        }
    }
    
    public void takeDamage(int damage)
    {
        description = "An injured " + name.toLowerCase() + " stands clutching its wounds";
        health -= damage;
        
        if(health <= 0)
        {
            description = "The " + name.toLowerCase() + " lies dead on the ground";
            isAlive = false;
        }
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getHealth()
    {
        return this.health;
    }
    
    public boolean lifeStatus()
    {
        return isAlive;
    }
}
