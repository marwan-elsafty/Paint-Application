package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import model.Shape;
import model.ShapesConstants;
import view.modes.CanvasMode;

public class Ellipse extends Shape {

	private int minorAxe;
	private int majorAxe;

	private boolean calculatedMinor;
	private boolean calculatedMajor;
	private boolean calculatedOrigin;

	public Ellipse() {
		super();

		this.minorAxe = ShapesConstants.MINOR_AXE;
		this.majorAxe = ShapesConstants.MAJOR_AXE;

		this.calculatedMinor = false;
		this.calculatedMajor = false;
		this.calculatedOrigin = false;
	}

	@Override
	public void draw(Object canvas) {
		Graphics2D castedCanvas = (Graphics2D) canvas;

		castedCanvas.setStroke(getStroke());
		castedCanvas.setColor(getStrokeColor());

		if (getStartPosition() != null && getEndPosition() != null) {
			castedCanvas.setColor(this.getStrokeColor());
			if (!calculatedOrigin) {
				castedCanvas.drawOval(Math.min(getStartPosition().x, getEndPosition().x),
						Math.min(getStartPosition().y, getEndPosition().y), getMajorAxe(), getMinorAxe());
			}
			if (calculatedOrigin) {
				castedCanvas.drawOval(generateShapeOrigin().x, generateShapeOrigin().y, getMajorAxe(), getMinorAxe());
			}
			if (getFillColor() != null) {
				castedCanvas.setColor(this.getFillColor());
				castedCanvas.fillOval(generateShapeOrigin().x, generateShapeOrigin().y, getMajorAxe(), getMinorAxe());
			}
		}
	}

	@Override
	public boolean contains(Point selectedPoint) {
		Ellipse2D.Double newEllipse = new Ellipse2D.Double(generateShapeOrigin().getX(), generateShapeOrigin().getY(),
				(double) getMajorAxe(), (double) getMinorAxe());
		if (newEllipse.contains((Point2D) selectedPoint))
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

			this.calculatedOrigin = true;

			return newPoint;
		}

	}

	/*
	 * extra methods for facilitation
	 */

	public void setMajorAxe(int majorAxe) {
		this.majorAxe = majorAxe;
	}

	public int getMajorAxe() {
		if (!calculatedMajor) {
			this.majorAxe = calculateMajorAxe();
			this.calculatedMajor = true;
		}
		return this.majorAxe;
	}

	public void setMinorAxe(int minorAxe) {
		this.minorAxe = minorAxe;
	}

	public int getMinorAxe() {
		if (!calculatedMinor) {
			this.minorAxe = calculateMinorAxe();
			this.calculatedMinor = true;
		}

		return this.minorAxe;
	}

	private int calculateMajorAxe() {
		return Math.abs(getStartPosition().x - getEndPosition().x);
	}

	private int calculateMinorAxe() {
		return Math.abs(getStartPosition().y - getEndPosition().y);
	}

	@Override
	public CanvasMode getShapeType() {
		return CanvasMode.ELLIPSE;
	}

	@Override
	public String getShapeName() {
		return "Ellipse";
	}
}
