package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;

import model.Shape;
import model.ShapesConstants;
import view.modes.CanvasMode;

public class Rectangle extends Shape {

	private int length;
	private int width;

	private boolean calculatedLength;
	private boolean calculatedWidth;
	private boolean calculatedOrigin;

	public Rectangle() {
		super();

		length = ShapesConstants.LENGTH;
		width = ShapesConstants.WIDTH;

		calculatedLength = false;
		calculatedWidth = false;

		calculatedOrigin = false;
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D castedCanvas = (Graphics2D) canvas;

		castedCanvas.setStroke(getStroke());
		castedCanvas.setColor(getStrokeColor());

		if (!calculatedOrigin) {
			castedCanvas.drawRect(Math.min(getStartPosition().x, getEndPosition().x),
					Math.min(getStartPosition().y, getEndPosition().y), getWidth(), getLength());
		}
		if (calculatedOrigin) {
			castedCanvas.drawRect(generateShapeOrigin().x, generateShapeOrigin().y, getWidth(), getLength());
		}
		if (getFillColor() != null) {
			castedCanvas.setColor(getFillColor());
			castedCanvas.fillRect(generateShapeOrigin().x, generateShapeOrigin().y, getWidth(), getLength());
		}
	}

	@Override
	public boolean contains(Point selectedPoint) {
		java.awt.Rectangle newRectangle = new java.awt.Rectangle(generateShapeOrigin().x, generateShapeOrigin().y,
				getWidth(), getLength());
		if (newRectangle.contains(selectedPoint))
			return true;
		else
			return false;
	}

	@Override
	public Point generateShapeOrigin() {
		if (calculatedOrigin)
			return getStartPosition();
		else {
			int MinX = Math.min(getStartPosition().x, getEndPosition().x);
			int MinY = Math.min(getStartPosition().y, getEndPosition().y);

			int MaxX = Math.max(getStartPosition().x, getEndPosition().x);
			int MaxY = Math.max(getStartPosition().y, getEndPosition().y);

			Point newStartPoint = new Point(MinX, MinY);
			this.setStartPosition(newStartPoint);

			Point newEndPoint = new Point(MaxX, MaxY);
			this.setEndPosition(newEndPoint);

			calculatedOrigin = true;

			return this.getStartPosition();
		}

	}

	/*
	 * extra methods for facilitation
	 */

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		if (!calculatedLength) {
			this.length = calculateLength();
			calculatedLength = true;
		}
		return this.length;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		if (!calculatedWidth) {
			this.width = calculateWidth();
			calculatedWidth = true;
		}
		return this.width;
	}

	private int calculateWidth() {
		return Math.abs(getStartPosition().x - getEndPosition().x);
	}

	private int calculateLength() {
		return Math.abs(getStartPosition().y - getEndPosition().y);
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.RECTANGLE;
	}

	@Override
	public String getShapeName() {
		return "Rectangle";
	}
}
