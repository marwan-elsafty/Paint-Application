package model;

import java.awt.Stroke;

public interface Drawable {
	
	public void setStartPosition(java.awt.Point position);
	public java.awt.Point getStartPosition();
	
	public void setEndPosition(java.awt.Point position);
	public java.awt.Point getEndPosition();

	public void setProperties(java.util.Map<String, Integer> properties);

	public java.util.Map<String, Integer> getProperties();

	public void setStrokeColor(java.awt.Color color);
	public java.awt.Color getStrokeColor();

	public void setFillColor(java.awt.Color color);
	public java.awt.Color getFillColor();
	
	public void setStroke(Stroke stroke);
	public Stroke getStroke();
	
	public boolean isVisible();
	public void setVisible(boolean visible);
	
	public void draw(Object canvas);

	public Object clone() throws CloneNotSupportedException;
}
