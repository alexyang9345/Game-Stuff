import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class GameGui
{
    public GameGui(Player player, Interpreter translate) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        //End slate
        JFrame end = new JFrame();
        end.setSize(1300,700);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel endP = new JPanel();
        JTextArea endT = new JTextArea(30,30);
        endT.setEditable(false);
        endT.setLineWrap(true);
        endT.setWrapStyleWord(true);
        endP.add(endT);
        
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
        JTextArea outputBox = new JTextArea("Welcome\nYou slowly awaken after a nasty fall. Pieces of a broken ladder lie all around you. As you gather your bearings," + player.getCurrentRoom().getDescription(), 30, 30);
        outputBox.setEditable(false);
        outputBox.setLineWrap(true);
        outputBox.setWrapStyleWord(true);
        
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
                    
                    if(player.getCurrentRoom().getEnemy() != null)
                    {
                        if(player.getCurrentRoom().getEnemy().lifeStatus() == true)
                        {
                            outputBox.append("\n" + player.getCurrentRoom().getEnemy().attack(player));
                        }
                    }
                    inputBox.setText("");
                }
                    if(player.getX() == 5 && player.getY() == 0 && player.getZ() == 1 || player.getHealthInt() <= 0)
                    {
                        //f.setVisible(false);
                        if(player.getHealthInt() <= 0)
                        {
                            endT.setText("Unfortunately, you have died...");
                        }
                        else
                        {
                        int value = 0;
                        for(int k = 0; k < player.getItems().size(); k++)
                        {
                            if(player.getItems().get(k) != null)
                            {
                                value += player.getItems().get(k).getValue();
                            }
                        }
                        
                        endT.setText(player.getCurrentRoom().getDescription() +"\nYou survived!\n" + "You collected loot worth " + value + " gold!\n" + "QuestCo values your services!");
                        }
                        end.add(endP);
                        end.setVisible(true);
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
}
