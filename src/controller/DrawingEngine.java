package controller;

import model.Shape;

public interface DrawingEngine {
	/**
	 * redraw all shapes on the canvas
	 * 
	 * @param canvas
	 */
	public void refresh(Object shape);

	public void addShape(Shape shape);

	public void removeShape(Shape shape);

	public void updateShape(Shape oldShape, Shape newShape);

	/**
	 * @return created shapes objects
	 */
	public Shape[] getShapes();

	/**
	 * use the file extension to determine the type or throw runtime exception when
	 * unexpected extension
	 */
	public void save(String path);

	public void load(String path);

}
