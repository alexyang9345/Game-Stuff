import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
public class EndScreen_Test
{
    public static void main (String args [])
    {
        /**JFrame end = new JFrame();
        end.setSize(1300,700);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel endP = new JPanel();
        ///** 
        JTextArea endT = new JTextArea(30,30);
        endT.setEditable(false);
        endT.setLineWrap(true);
        
        int value = 0;
        Room[][][] map = new Room[7][13][2];
        Player player = new Player(map);
        for(int k = 0; k < player.getItems().size(); k++)
        {
            if(player.getItems().get(k) != null)
            {
                value += player.getItems().get(k).getValue();
            }
        }                
        endT.setText(player.getCurrentRoom().getDescription() +"\nGame Over!\n" + "You collected loot worth " + 10 + " gold!\n" + "QuestCo values your services, and recommends that you don't die next time!");
        end.add(endP);
        endP.add(endT);
        end.setVisible(true);
        /**
        endT.setText("HERE ARE WHERE THE STATS SHOULD BE :D");
        endT.setWrapStyleWord(true);
        endP.add(endT);
        
        endP.add(new JLabel(new ImageIcon("images/gameover_1.jpg")));
        
        end.add(endP);
        end.setVisible(true);
        **/
        JFrame title = new JFrame();
        title.setSize(1300,700);
        title.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        JPanel titleP = new JPanel();
        titleP.add(new JLabel(new ImageIcon("images/title_screen.jpg")));
        title.add(titleP);
        title.setVisible(true);
    }
}
