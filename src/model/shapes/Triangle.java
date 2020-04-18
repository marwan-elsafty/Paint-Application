package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import model.Shape;
import view.modes.CanvasMode;

public class Triangle extends Shape {

	private boolean differenceX, differenceY;
	private boolean calculatedOrigin;

	int diffrenceX, diffrenceY;
	int[] x;
	int[] y;

	boolean boolX, boolY;

	public Triangle() {
		super();

		calculatedOrigin = false;

		differenceX = false;
		differenceY = false;

		boolX = false;
		boolY = false;

		x = new int[3];
		y = new int[3];
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D castedCanvas = (Graphics2D) canvas;

		castedCanvas.setStroke(getStroke());
		castedCanvas.setColor(getStrokeColor());

		if (calculatedOrigin)
			castedCanvas.drawPolygon(getX(), getY(), 3);

		if (!calculatedOrigin) {
			int difference = getStartPosition().x - getEndPosition().x;

			int[] x = { getStartPosition().x, getStartPosition().x + difference, getEndPosition().x };
			int[] y = { getStartPosition().y, getEndPosition().y, getEndPosition().y };
			castedCanvas.drawPolygon(x, y, 3);
		}
		if (getFillColor() != null) {
			castedCanvas.setColor(getFillColor());
			castedCanvas.fillPolygon(getX(), getY(), 3);
		}
	}

	@Override
	public boolean contains(Point selectedPoint) {
		Polygon newTriangle = new Polygon(getX(), getY(), 3);
		if (newTriangle.contains(selectedPoint))
			return true;
		else
			return false;
	}

	@Override
	public Point generateShapeOrigin() {
		if (calculatedOrigin)
			return this.getStartPosition();
		else {
			int x = Math.min(getStartPosition().x, getEndPosition().x);
			int y = Math.min(getStartPosition().y, getEndPosition().y);

			Point newPoint = new Point(x, y);
			this.setStartPosition(newPoint);

			calculatedOrigin = true;

			return newPoint;
		}
	}

	/*
	 * extra methods for facilitation
	 */

	public int[] getX() {
		if (!boolX) {
			int diffrenceX = calculateDiffrenceX();

			this.x[0] = generateShapeOrigin().x;
			this.x[1] = generateShapeOrigin().x - diffrenceX;
			this.x[2] = generateShapeOrigin().x + diffrenceX;

			boolX = true;
		}
		return this.x;
	}

	public int[] getY() {
		if (!boolY) {
			int diffrenceY = calculateDiffrenceY();

			y[0] = generateShapeOrigin().y;
			y[1] = generateShapeOrigin().y - diffrenceY;
			y[2] = generateShapeOrigin().y - diffrenceY;
			boolY = true;
		}
		return this.y;
	}

	public void setX(int[] x) {
		this.x = x;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public int calculateDiffrenceX() {
		if (differenceX)
			return diffrenceX;
		else {
			diffrenceX = getStartPosition().x - getEndPosition().x;
			differenceX = true;
			return diffrenceX;
		}
	}

	public int calculateDiffrenceY() {
		if (differenceY)
			return diffrenceY;
		else {
			diffrenceY = getStartPosition().y - getEndPosition().y;
			differenceY = true;
			return diffrenceY;
		}
	}

	public void setTrue() {
		calculatedOrigin = true;
		
		differenceX = true;
		differenceY = true;
		
		boolX = true;
		boolY = true;
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.TRIANGLE;
	}

	@Override
	public String getShapeName() {
		return "Triangle";
	}
}
