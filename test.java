import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {
	public void init() {
		this.setSize(600, 600);
		Button ZoomIn = new Button("Zoom in");
		add(ZoomIn);

		Button ZoomOut = new Button("Zoom Out");
		add(ZoomOut);

		ZoomIn.addActionListener(this);
		ZoomOut.addActionListener(this);
	}

	public void paint(Graphics g) {

		// Set origin
		int originX=(getX()+getWidth())/2;
		int originY=(getY()+getHeight())/2;
		
		int unit = 40;
		int count = 50;

		g.setColor(Color.red);
		Font stringFont = new Font("Arial", 4, 18);
		g.setFont(stringFont);
		
		// Set x-axis
		g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
		
		// Set y-axis
		g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
		
		g.setColor(Color.black);
		g.drawString("0", originX, originY);

		int positive = 1;
		int negative = -1;

		while(count >= 0){
			// Set x
			g.drawLine(getWidth()/2 + unit,0,getWidth()/2 + unit,getHeight());
			g.drawLine(getWidth()/2 - unit,0,getWidth()/2 - unit,getHeight());

			// Set y
			g.drawLine(0,getHeight()/2 + unit,getWidth(),getHeight()/2 + unit);
			g.drawLine(0,getHeight()/2 - unit,getWidth(),getHeight()/2 - unit);

			// Set x-axis coordinates
			g.drawString(String.valueOf(positive), originX + unit, originY);
			g.drawString(String.valueOf(negative), originX, originY + unit);

			// Set y-axis coordinates
			g.drawString(String.valueOf(positive), originX, originY - unit);
			g.drawString(String.valueOf(negative), originX - unit, originY);

			unit += 40;
			positive++;
			negative--;
			count--;
		}
	}

	public void actionPerformed(ActionEvent e){
		System.out.println("Button 1 was pressed");
		// if (e.getSource() == ){}
	}
}