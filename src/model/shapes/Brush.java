package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import model.Shape;
import model.ShapesConstants;
import view.modes.CanvasMode;

public class Brush extends Shape {

	private ArrayList<Point> nodes;

	private int nodeRadius;

	public Brush() {
		super();

		this.nodes = new ArrayList<>();

		this.nodeRadius = ShapesConstants.NODE_RADIUS;
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D g = (Graphics2D) canvas;

		for (int i = 1; i < nodes.size(); i++) {
			g.setStroke(getStroke());
			g.setColor(getStrokeColor());
			g.draw(new Line2D.Float(nodes.get(i - 1), nodes.get(i)));
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return (Brush) super.clone();
	}

	@Override
	public boolean contains(Point selectedPoint) {
		return false;
	}

	@Override
	public Point generateShapeOrigin() {
		return null;
	}

	/*
	 * extra methods for facilitation
	 */

	public ArrayList<Point> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Point> points) {
		this.nodes = points;
	}

	public int getNodeRadius() {
		return this.nodeRadius;
	}

	public void setNodeRadius(int nodeRadius) {
		this.nodeRadius = nodeRadius;
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.BRUSH;
	}

	@Override
	public String getShapeName() {
		return "Brush";
	}
}