// Question 5 and 6

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import userdefined.Second;

class test {
    public String print(){
        return "This is test class";
    }
}

public class applet extends Applet {
    private Color BackgroundColor = new Color(255, 255, 255);

    public void init(){
        this.setSize(900, 900);
		this.setBackground(BackgroundColor);
    }

    public void paint(Graphics g){
        test t = new test();
        Second s = new Second();


        Font NewFont = new Font("Arial", 4, 50);
		g.setFont(NewFont);

        // Print using default package
        g.drawString(t.print(), 100, 100);

        // Print using user-defined package
        g.drawString(s.printsecond(), 100, 200);
    }
}
