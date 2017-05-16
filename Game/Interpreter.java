import java.util.*;
import java.lang.reflect.*;

public class Interpreter 
{
    private String outputSTR, input1, input2;
    private Map<String,Method> methods = new HashMap<String, Method>();
    private Player user;
    
    public Interpreter(Player player) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        user = player;
        //Player movement methods
        methods.put("NAME", Player.class.getMethod("getName"));
        methods.put("NORTH", Player.class.getMethod("moveNorth"));
        methods.put("N", Player.class.getMethod("moveNorth"));
        methods.put("SOUTH", Player.class.getMethod("moveSouth"));
        methods.put("S", Player.class.getMethod("moveSouth"));
        methods.put("EAST", Player.class.getMethod("moveEast"));
        methods.put("E", Player.class.getMethod("moveEast"));
        methods.put("WEST", Player.class.getMethod("moveWest"));
        methods.put("W", Player.class.getMethod("moveWest"));
        methods.put("UP", Player.class.getMethod("moveUp"));
        methods.put("DOWN", Player.class.getMethod("moveDown"));
        
        //Player Item managment methods
        methods.put("INVENTORY", Player.class.getMethod("getInventory"));
        methods.put("TAKE", Player.class.getMethod("addItem", Item.class));
        methods.put("DROP", Player.class.getMethod("dropItem", String.class));
        methods.put("INSPECT", Item.class.getMethod("getDescription"));
        methods.put("UNLOCK", Player.class.getMethod("unlock", String.class));
        methods.put("EAT", Player.class.getMethod("heal", Item.class));
        methods.put("DRINK", Player.class.getMethod("heal", Item.class));
        methods.put("EQUIP", Player.class.getMethod("equip", String.class));
        
        //Player Health
        methods.put("DIAGNOSE", Player.class.getMethod("getHealth"));
        
        //Room Methods
        methods.put("LOOK", Room.class.getMethod("getDescription"));
        
        //Combat
        methods.put("ATTACK", Player.class.getMethod("attack", Enemy.class));
    
    }
    
    public String actionPerformed(String input, Room cRoom) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        String split = " ";
        int index;
        
        index = input.indexOf(split);
        if(index == -1)
            input1 = input;
            
        else
        {
            input1 = input.substring(0, index);
            input2 = input.substring(index + 1);
        }
        
        if(input1.toUpperCase().equals("MOVE"))
        {
            try
            {
                //cRoom = user.getMap()[user.getX()][user.getY() -1][user.getZ()];
                return (String) methods.get(input2.toUpperCase()).invoke(user);
                //return input2;
            }
            catch(NullPointerException e)
            {
                return "You want to move where?";
            }
            
            catch(InvocationTargetException e)
            {
                return e.getCause().toString();
            }
        }
        
        else if(input1.toUpperCase().equals("TAKE"))
        {
            int item = -1;
            for(int k = 0; k < cRoom.getItems().size(); k++)
            {
                if(input2.toUpperCase().equals(cRoom.getItems().get(k).getName()))
                {
                    item = k;
                }
            }
            
            if(item != -1)
            {
                methods.get("TAKE").invoke(user, cRoom.getItems().get(item));
                
                cRoom.removeItem(cRoom.getItems().get(item));
                //Change Description
                return "Taken";
            }
            else
                return "You see no such thing";
        }
        
        else if(input1.toUpperCase().equals("DROP"))
        {
            int item = -1;
            for(int k = 0; k < user.getItems().size(); k++)
            {
                if(input2.toUpperCase().equals(user.getItems().get(k).getName()))
                {
                    item = k;
                }
            }
            
            if(item != -1)
            {
                methods.get("DROP").invoke(user, input2);
                cRoom.addItem(user.getItems().get(item));
                return "Dropped";
            }
            else
                return "You hold no such thing";
            }
            
        else if(input1.toUpperCase().equals("DIAGNOSE"))
        {
            return (String)methods.get("DIAGNOSE").invoke(user);
        }
        
        else if(input1.toUpperCase().equals("LOOK"))
        {
            return (String)methods.get("LOOK").invoke(cRoom);
        }
        
        else if(input1.toUpperCase().equals("ATTACK"))
        {
            if(cRoom.getEnemy() == null || input2.toUpperCase().equals(cRoom.getEnemy().getName()) == false)
            {
                return "Why would you want to attack that?";
            }
            return (String)methods.get("ATTACK").invoke(user, cRoom.getEnemy());
        }
        
        else if(input1.toUpperCase().equals("INSPECT"))
        {
            Item thing = null;
            
            for(int k = 0; k < user.getItems().size(); k++)
            {
                if( input2.toUpperCase().equals(user.getItems().get(k).getName() ))
                {
                    thing = user.getItems().get(k);
                    break;
                }
                }
        
            if(thing == null)
            {
                for(int k = 0; k< cRoom.getItems().size(); k++)
                {
                    if( input2.toUpperCase().equals(cRoom.getItems().get(k).getName() ))
                        thing = cRoom.getItems().get(k);
                    }
                }
                
            if(thing == null)
            {
                return "Inspect what?";
            }
            
            else
            {
                return thing.getDescription();
            }
        }
        
        else if(input1.toUpperCase().equals("UNLOCK"))
        {
            return (String)methods.get("UNLOCK").invoke(user,input2);
        }
        
        else if(input1.toUpperCase().equals("EAT") || input1.toUpperCase().equals("DRINK"))
        {
            int i = user.findItem(input2);
            Item health;
            
            if(i != -1)
            {
                health = user.getItems().get(i);
                
                return (String)methods.get("EAT").invoke(user, health) + "\n" + user.removeItem(input2);
            }
            else
                return "You have no such item";
        }
        
        else if(input1.toUpperCase().equals("EQUIP"))
        {
            return (String)methods.get("EQUIP").invoke(user, input2);
        }
        
        else if(input1.toUpperCase().equals("INVENTORY"))
        {
            return (String)methods.get("INVENTORY").invoke(user);
        }
        
        else
            return "What?";
            
    }
}
