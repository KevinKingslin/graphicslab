import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Q3 extends Applet implements ActionListener {
	private Button ZoomIn = new Button("Zoom In");
	private Button ZoomOut = new Button("Zoom Out");
	private int unit = 40;

	
	public void init() {
		Color BackgroundColor = new Color(255, 255, 255);
		
		this.setSize(900, 900);
		this.setBackground(BackgroundColor);
		
		add(ZoomIn);
		add(ZoomOut);
		
		ZoomIn.addActionListener(this);
		ZoomOut.addActionListener(this);
	}
	
	public void paint(Graphics g) {
		
		int height = getHeight();
		int width = getWidth();
		
		// Set origin
		int originX = (getX() + width) / 2;
		int originY = (getY() + height) / 2;
		
		g.setColor(Color.red);
		Font stringFont = new Font("Arial", 4, 18);
		g.setFont(stringFont);
		
		// Set x-axis
		g.drawLine(0, height / 2, width, height / 2);
		
		// Set y-axis
		g.drawLine(width / 2, 0, width / 2, height);
		
		Color GridColor = new Color(100, 100, 100);
		g.setColor(GridColor);
		g.drawString("0", originX, originY);
		
		int coordinate = 1;
		
		for (int count = 1; count <= 50; count++, coordinate++) {
			// Set horizontal lines
			g.drawLine(width / 2 + (unit * count), 0, width / 2 + (unit * count), height);
			g.drawLine(width / 2 - (unit * count), 0, width / 2 - (unit * count), height);

			// Set vertical lines
			g.drawLine(0, height / 2 + (unit * count), width, height / 2 + (unit * count));
			g.drawLine(0, height / 2 - (unit * count), width, height / 2 - (unit * count));

			// Set x-axis coordinates
			g.drawString(String.valueOf(coordinate), originX + (unit * count), originY);
			g.drawString("-" + String.valueOf(coordinate), originX, originY + (unit * count));
			
			// Set y-axis coordinates
			g.drawString(String.valueOf(coordinate), originX, originY - (unit * count));
			g.drawString("-" + String.valueOf(coordinate), originX - (unit * count), originY);
		}
		
		// Plot points
		Color PointColor = new Color(255, 0, 0);

		plotpoint(3, 1, PointColor);
		plotpoint(3, 5, PointColor);
		plotpoint(5, 1, PointColor);
		plotpoint(5, 5, PointColor);
	}

	public void actionPerformed(ActionEvent e) {
		Graphics g = getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		if (e.getSource() == ZoomIn)
			unit += 20;
		else
			unit -= 20;
		paint(g);
	}

	public void plotpoint(int x, int y, Color C) {
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;
		Graphics g = getGraphics();
		g.setColor(C);

		int PointWidth = unit / 4;
		g.fillOval(originX + (x * unit) - PointWidth / 2, originY - (y * unit) - PointWidth / 2, PointWidth,
				PointWidth);
	}
}