import java.util.*;
public class Enemy
{
    private String name; 
    private boolean isAlive;
    private int health;
    private int maxHealth;
    private int attack;
    public Enemy(String name, int health, int attack)
    {
        this.name = name;    
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
        isAlive = true;
        System.out.println("A wild " + this.name + " has appeared!");
    }
    public String Attack(Player player)
    {
        int miss = (int)(Math.random()*5);
        if (miss != 1)
        {
            int damage = -1 * (int)(Math.random() * ((this.attack + 10)-(this.attack - 10)));
            player.changeHealth(damage);
            return (this.name + " attacked you for " + damage + " damage.");
        }
        else
        {
            return (this.name + " attacked you but missed. ");
        }
    }
    public int changeHealth(int change)
    {
        health += change;  
        return health;
    }
    public int getHealth()
    {
        return this.health;
    }
    public String getName()
    {
        return this.name;
    }
    public String getDescription()
    {
        String display = "Name: " + this.name;
        display += "\nHealth: " + this.health + "/" + this.maxHealth;
        display += "\nAttack: " + this.attack;
        return display;
    }
    /**
    //this is for the player class so that the player can attack enemies too.
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
    **/
}


