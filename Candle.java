import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

import packages.Fire;

public class Candle extends Applet implements ActionListener {
    private Color BackgroundColor = new Color(255, 255, 255);
    Graphics g;
    int delay = 1000;
    private Button LightCandle = new Button("Light Candle");
    private Button BlowCandle = new Button("Blow Candle");

    ActionListener taskPerformer = new ActionListener() {
        Fire fire = new Fire();

        public void actionPerformed(ActionEvent evt) {
            fire.paint(g);
        }
    };

    private Timer timer = new Timer(delay, taskPerformer);

    public void init() {
        this.setSize(900, 900);

        add(LightCandle);
        add(BlowCandle);

        this.setBackground(BackgroundColor);

        LightCandle.setBackground(BackgroundColor);
        BlowCandle.setBackground(BackgroundColor);

        LightCandle.addActionListener(this);
        BlowCandle.addActionListener(this);

        g = getGraphics();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        g.setColor(Color.blue);
        g.fillRect(250, 600, 400, 300);
    }

    public void actionPerformed(ActionEvent e) {
        g.setColor(Color.blue);
        if (e.getSource() == BlowCandle) {
            g.clearRect(250, 200, 400, 400);
            paint(g);
            timer.stop();
        } else if (e.getSource() == LightCandle) {
            g.setColor(BackgroundColor);
            g.fillRect(0,0, getWidth(), getHeight());
            g.setColor(Color.blue);
            g.fillRect(250, 600, 400, 300);
            timer.start();
        }
    }
}
