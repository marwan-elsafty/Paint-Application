package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import model.Shape;
import model.ShapesConstants;
import view.modes.CanvasMode;

public class Circle extends Shape {

	private int radius;

	private boolean calculatedRadius;
	private boolean calculatedOrigin;

	public Circle() {
		super();

		this.radius = ShapesConstants.RADIUS;

		calculatedRadius = false;
		calculatedOrigin = false;
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D castedCanvas = (Graphics2D) canvas;

		castedCanvas.setStroke(getStroke());
		castedCanvas.setColor(getStrokeColor());

		if (getStartPosition() != null && getEndPosition() != null) {
			castedCanvas.setColor(getStrokeColor());
			if (!calculatedOrigin) {
				castedCanvas.drawOval(Math.min(getStartPosition().x, getEndPosition().x),
						Math.min(getStartPosition().y, getEndPosition().y), getRadius(), getRadius());
			}
			if (calculatedOrigin) {
				castedCanvas.drawOval(generateShapeOrigin().x, generateShapeOrigin().y, getRadius(), getRadius());
			}
			if (getFillColor() != null) {
				castedCanvas.setColor(getFillColor());
				castedCanvas.fillOval(generateShapeOrigin().x, generateShapeOrigin().y, getRadius(), getRadius());
			}
		}
	}

	@Override
	public boolean contains(Point selectedPoint) {
		Ellipse2D.Double newCircle = new Ellipse2D.Double(generateShapeOrigin().getX(), generateShapeOrigin().getY(),
				(double) getRadius(), (double) getRadius());
		if (newCircle.contains((Point2D) selectedPoint))
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

	public int getRadius() {
		if (!calculatedRadius) {
			this.radius = calculateRadius();
			this.calculatedRadius = true;
		}
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	private int calculateRadius() {
		this.radius = Math.abs(getStartPosition().x - getEndPosition().x);
		return this.radius;
	}

	public Point getCenter() {
		Ellipse2D.Double newCircle = new Ellipse2D.Double(generateShapeOrigin().getX(), generateShapeOrigin().getY(),
				(double) getRadius(), (double) getRadius());

		Point newPoint = new Point();
		newPoint.setLocation(newCircle.getCenterX(), newCircle.getCenterY());

		return newPoint;
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.CIRCLE;
	}

	@Override
	public String getShapeName() {
		return "Circle";
	}
}
