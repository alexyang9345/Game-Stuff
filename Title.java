//Aaron Zeeb
//Title screen for the game
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
public class Title extends JApplet
{
    Image TitleScreen;
    public void init() {
        //grabs the image file from the folder
        TitleScreen=getImage(getDocumentBase(),"title.jpg");
    }

    public void paint(Graphics g) {
        //draws the title screen image
        g.drawImage(TitleScreen, 0, 0, this);
        
        //sets color for text
        g.setColor(Color.red);
        //sets the font, font type, and size of font
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        //draws the string with the given x,y coordinates
        g.drawString("QuestCo", 484, 384);
        //sets the font, font type, and size of font
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        //draws the string with the given x,y coordinates
        g.drawString("Created by Nick Litman, Aaron Zeeb, Alex Yang", 324, 410);

        //draws the string with the given x,y coordinates
        g.drawString("Press the Enter key to Play...", 424, 436);
        
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                 {"firstParameter",    "1-10",    "description of first parameter"},
                 {"status", "boolean", "description of second parameter"},
                 {"images",   "url",     "description of third parameter"}
        };
        return paramInfo;
    }
}
