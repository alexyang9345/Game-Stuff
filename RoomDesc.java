import java.util.*;

public class RoomDesc
{
    private String body, itemInfo;
    private ArrayList<Item> items;
    private Enemy enemy;
    public RoomDesc(String desc, ArrayList<Item> stuff, Enemy e)
    {
        items = stuff;
        body  = desc;
        itemInfo = "";
        enemy = e;
        
        for( int k = 0; k < items.size(); k++)
        {
            itemInfo += "\n" + items.get(k).getLocation();
        }
    }
    
    public void addItem(Item newItem)
    {
        //items.add(newItem);
        itemInfo += "\n" + newItem.getLocation();
    }
    
    public void removeItem()
    {
        itemInfo = "";
        
        for( int k = 0; k < items.size(); k++)
        {
            itemInfo += "\n" + items.get(k).getLocation();
        }
    }
        
    public String getDescription()
    {
        if (enemy == null)
            return "\n" + body + "\n" + itemInfo;
        else
            return body + itemInfo + enemy.getDescription();
    }
    
    public void setBody(String nBody)
    {
        body = nBody;
    }
}
