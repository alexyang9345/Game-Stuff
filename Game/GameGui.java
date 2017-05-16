import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class GameGui
{
    public GameGui(Player player, Interpreter translate) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        //Sets up frame
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setSize(1300,700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Sets up panel and layout
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Sets up input/output areas
        JTextField inputBox = new JTextField(30);
        JTextArea outputBox = new JTextArea("Welcome", 30, 30);
        outputBox.setEditable(false);
        outputBox.setLineWrap(true);
        
                
        JLabel ch = new JLabel();
        JLabel name = new JLabel(player.getName());
        JLabel e = new JLabel();
        
        ch.setIcon(new ImageIcon("images/player_damaged_0.jpg"));
        e.setIcon(new ImageIcon("images/hallway.jpg"));
        
        //Makes output area scrollable
        JScrollPane scroll = new JScrollPane(outputBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //Scrolls text area to bottom
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener()
        {  
            public void adjustmentValueChanged(AdjustmentEvent e) 
            {  
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
            }
         });

        //Collects user input
        inputBox.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String input = inputBox.getText();
                if(input.equals("") == false)
                {
                    
                    try
                    {
                        outputBox.append("\n" + translate.actionPerformed(input, player.getCurrentRoom()));
                    }
                    catch(NoSuchMethodException e1)
                    {
                        throw new RuntimeException(e1);
                    }
                    catch(IllegalAccessException e2)
                    {
                        throw new RuntimeException(e2);
                    }
                    catch(InvocationTargetException e3)
                    {
                        throw new RuntimeException(e3);
                    }
                    inputBox.setText("");
                }
            }
        });
        
               c.gridx = 1;
        c.gridy = 0;
        p.add(scroll,c);
        
        c.gridx = 1;
        c.gridy = 1;
        p.add(inputBox,c);
        
        f.add(p);
        f.setVisible(true);
    }
    
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        Room[][][] map = new Room[1][1][1];
        Player player = new Player("Jeff", map);
        Interpreter test = new Interpreter(player);
        new GameGui(player, test);
    }
}
