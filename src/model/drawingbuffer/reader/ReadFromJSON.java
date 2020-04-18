package model.drawingbuffer.reader;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Factory;
import model.Shape;
import model.main.Application;
import view.modes.CanvasMode;

public class ReadFromJSON implements Readable {

	@Override
	public void readFromFile(String filePath) throws IOException {
		JSONParser parser = new JSONParser();
		
		Shape shape;
		
		int startX;
		int startY;
		int endX;
		int endY;
//		int centerX = 0;
//		int centerY = 0;
//		int radius = 0;
//		int radiusX = 0;
//		int radiusY = 0;
		
		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader(filePath));
			
			for (Object o : array) {
				JSONObject jsonObject = (JSONObject) o;
				
				CanvasMode type = (CanvasMode)jsonObject.get("type");
				
				Color strokeColor = (Color) jsonObject.get("color");
				Color fillColor = (Color) jsonObject.get("fillColor");
				
				Stroke stroke = (Stroke) jsonObject.get("strokeWidth");
				
				startX = (Integer) jsonObject.get("start-x");
				startY = (Integer) jsonObject.get("start-y");
				
				endX = (Integer) jsonObject.get("end-x");
				endY = (Integer) jsonObject.get("end-y");
				
//				if (name.equals("circle")) {
//					centerX = (Integer) jsonObject.get("center-x");
//					centerY = (Integer) jsonObject.get("center-y");
//					radius = (Integer) jsonObject.get("radius");
//				} else if (name.equals("ellipse")) {
//					centerX = (Integer) jsonObject.get("center-x");
//					centerY = (Integer) jsonObject.get("center-y");
//					radiusX = (Integer) jsonObject.get("radius-x");
//					radiusY = (Integer) jsonObject.get("radius-y");
//				}
				shape = Factory.detectMode(type);
				
				shape.setStrokeColor(strokeColor);
				shape.setFillColor(fillColor);
				
				shape.setStroke(stroke);
				
				shape.setStartPosition(new Point(startX, startY));
				shape.setEndPosition(new Point(endX, endY));
//				switch (name) {
//				case "circle":
//					((Circle) shape).setCenterPoint(new Point2D(centerX, centerY));
//					((Circle) shape).setRadius(radius);
//					break;
//				case "ellipse":
//					((Ellipse) shape).setCenterPoint(new Point2D(centerX, centerY));
//					((Ellipse) shape).setRadius(new Point2D(radiusX, radiusY));
//					break;
//				}
				Application.getController().getShapesArrayList().add(shape);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
