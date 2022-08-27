import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

import packages.*;

public class assignment extends Applet implements ActionListener {
	private Integer unit = 40;
	private Button ZoomIn = new Button("Zoom In");
	private Button ZoomOut = new Button("Zoom Out");

	private Button DDA = new Button("DDA");
	private Button Bresenham = new Button("Bresenham");
	private Button MidPoint = new Button("Mid-Point");

	private Color BackgroundColor = new Color(255, 255, 255);
	private Color GridColor = new Color(100, 100, 100);
	private Color PointColor = new Color(255, 0, 0);

	public void init() {
		this.setSize(900, 900);
		this.setBackground(BackgroundColor);
		setLayout(null);

		ZoomIn.setBounds(400, 0, 80, 40);
		ZoomOut.setBounds(490, 0, 80, 40);

		DDA.setBounds(800, 0, 100, 50);
		Bresenham.setBounds(800, 50, 100, 50);
		MidPoint.setBounds(800, 100, 100, 50);

		add(ZoomIn);
		add(ZoomOut);
		add(DDA);
		add(Bresenham);
		add(MidPoint);

		ZoomIn.addActionListener(this);
		ZoomOut.addActionListener(this);
		DDA.addActionListener(this);
		Bresenham.addActionListener(this);
		MidPoint.addActionListener(this);
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
	}

	public void actionPerformed(ActionEvent e) {
		DDA dda = new DDA();
		Bresenham bresenham = new Bresenham();
		Midpoint midpoint = new Midpoint();

		Graphics g = getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		if (e.getSource() == ZoomIn)
			unit += 10;

		else if (e.getSource() == ZoomOut)
			unit -= 10;

		paint(g);

		if (e.getSource() == DDA)
			dda.PlotLine(3, 4, 5, 8, g, PointColor);

		else if (e.getSource() == Bresenham)
			bresenham.PlotLine(3, 4, 5, 8, g, PointColor);

		else if (e.getSource() == MidPoint)
			midpoint.PlotLine(3, 4, 5, 8, g, PointColor);

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