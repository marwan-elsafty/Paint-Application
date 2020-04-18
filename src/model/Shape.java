package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Map;

import view.modes.CanvasMode;

public abstract class Shape implements Drawable, Cloneable {

	private Point startPosition;
	private Point endPosition;

	private transient Color strokeColor;
	private transient Color fillColor;

	private Stroke stroke;

	private Map<String, Integer> properties;

	private boolean visible;

	public Shape() {
		strokeColor = ShapesConstants.STROKE_COLOR;
		fillColor = ShapesConstants.FILL_COLOR;

		visible = true;
	}

	@Override
	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}

	@Override
	public Point getStartPosition() {
		return this.startPosition;
	}

	@Override
	public void setEndPosition(Point endPosition) {
		this.endPosition = endPosition;
	}

	@Override
	public Point getEndPosition() {
		return this.endPosition;
	}

	@Override
	public void setProperties(Map<String, Integer> properties) {
		this.properties = properties;
	}

	@Override
	public Map<String, Integer> getProperties() {
		return this.properties;
	}

	@Override
	public void setStrokeColor(Color color) {
		this.strokeColor = color;
	}

	@Override
	public java.awt.Color getStrokeColor() {
		return this.strokeColor;
	}

	@Override
	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	@Override
	public java.awt.Color getFillColor() {
		return this.fillColor;
	}

	@Override
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	@Override
	public Stroke getStroke() {
		return this.stroke;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return (Shape) super.clone();
	}

	/*
	 * 
	 */

	public abstract boolean contains(Point selectedPoint);

	public abstract Point generateShapeOrigin();

	public abstract CanvasMode getShapeType();

	public abstract String getShapeName();
}
