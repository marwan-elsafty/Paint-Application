package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import model.Shape;
import view.modes.CanvasMode;

public class Line extends Shape {

	private int length;

	private boolean calculatedLength;

	public Line() {
		super();

		calculatedLength = false;
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D castedCanvas = (Graphics2D) canvas;

		castedCanvas.setStroke(getStroke());

		castedCanvas.setColor(getStrokeColor());
		if (getStartPosition() != null && getEndPosition() != null) {
			castedCanvas.drawLine(getStartPosition().x, getStartPosition().y, getEndPosition().x, getEndPosition().y);
		}
	}

	@Override
	public boolean contains(Point selectedPoint) {
		Line2D newLine = new Line2D.Double(getStartPosition().getX(), getStartPosition().getY(),
				getEndPosition().getX(), getEndPosition().getY());
		if (newLine.contains((Point2D) selectedPoint))
			return true;
		else
			return false;
	}

	@Override
	public Point generateShapeOrigin() {
		return null;
	}

	/*
	 * extra methods for facilitation
	 */

	public int getLength() {
		if (!calculatedLength) {
			this.length = calculateLength();
			this.calculatedLength = true;
		}
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int calculateLength() {
		double deltaX = getEndPosition().x - getStartPosition().x;
		double deltaY = getEndPosition().y - getStartPosition().y;

		Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		return this.length;
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.LINE;
	}

	@Override
	public String getShapeName() {
		return "Line";
	}
}
