import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;

public class GameGui
{
    public GameGui(Player player, Interpreter translate) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        //Title slate
        Scanner scan = new Scanner(System.in);
        
        JFrame title = new JFrame();
        JPanel titleP = new JPanel();
        title.setSize(1300,700);
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleP.add(new JLabel(new ImageIcon("images/title_screen.jpg")));
        title.add(titleP);
        title.setVisible(true);
        try 
        {
            Thread.sleep(4000);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
        title.setVisible(false);
        
        //End slate
        
        JFrame end = new JFrame();
        end.setSize(1300,700);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel endP = new JPanel();
        JTextArea endT = new JTextArea(30,30);
        endT.setEditable(false);
        endT.setLineWrap(true);
        endT.setWrapStyleWord(true);

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
                            int value = 0;
                            for(int k = 0; k < player.getItems().size(); k++)
                            {
                                if(player.getItems().get(k) != null)
                                {
                                    value += player.getItems().get(k).getValue();
                                }
                            }                
                            
                            ///**
                            
                            //I don't know how to get the other stuff to stop displaying...
                            
                            endT.setText("HERE ARE WHERE THE STATS SHOULD BE :D");
                            endT.setWrapStyleWord(true);
                            endP.add(endT);
                            
                            end.removeAll();
                            endT.removeAll();
                            //endT.setText("");
                            endT.setText(player.getCurrentRoom().getDescription() +"\nGame Over!\n" + "You collected loot worth " + value + " gold!\n" + "QuestCo values your services, \nand recommends that you try harder next time!");
                            endP.add(endT);
                            //**/ #uncomment these two things to make just the gameover image appear by itself
                            endP.add(new JLabel(new ImageIcon("images/gameover.jpg")));
                            
                           
                            end.add(endP);
                            end.setVisible(true);
                            
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
                            /**
                             * same problem with not being able to delete previous messages
                             */
                            endP.add(new JLabel(new ImageIcon("images/win.jpg")));
                            ///**
                            endT.setText(player.getCurrentRoom().getDescription() +"\n\n\nCongratulations, you survived!\n" + "You collected loot worth " + value + " gold!\n" + "QuestCo values your services!");
                            endP.add(endT);
                            //**/ #uncomment these two things to make just the victory picture appear by itself
                            end.add(endP);
                            end.setVisible(true);
                            
                        }
                        
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
