package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import model.Shape;
import model.ShapesConstants;
import view.modes.CanvasMode;

public class Square extends Shape {

	private int length;

	private boolean calculatedLength;
	private boolean calculatedOrigin;

	public Square() {
		super();

		this.length = ShapesConstants.LENGTH;

		calculatedLength = false;
		calculatedOrigin = false;
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D castedCanvas = (Graphics2D) canvas;

		castedCanvas.setStroke(getStroke());
		castedCanvas.setColor(getStrokeColor());

		if (!calculatedOrigin) {
			castedCanvas.drawRect(Math.min(getStartPosition().x, getEndPosition().x),
					Math.min(getStartPosition().y, getEndPosition().y), getLength(), getLength());
		}
		if (calculatedOrigin) {
			castedCanvas.drawRect(generateShapeOrigin().x, generateShapeOrigin().y, getLength(), getLength());
		}
		if (getFillColor() != null) {
			castedCanvas.setColor(getFillColor());
			castedCanvas.fillRect(generateShapeOrigin().x, generateShapeOrigin().y, getLength(), getLength());
		}
	}

	@Override
	public boolean contains(Point selectedPoint) {
		Rectangle newSquare = new Rectangle(generateShapeOrigin().x, generateShapeOrigin().y, getLength(), getLength());
		if (newSquare.contains(selectedPoint))
			return true;

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

	private int calculateLength() {
		return Math.abs(getStartPosition().x - getEndPosition().x);
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.SQUARE;
	}

	@Override
	public String getShapeName() {
		return "Square";
	}
}