package model.drawingbuffer.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Shape;
import model.main.Application;
import model.shapes.Circle;
import model.shapes.Ellipse;

public class WriteInJSON implements Writable {

	public WriteInJSON() {

	}

	@SuppressWarnings("unchecked")
	public void writeInFile(String filePath) throws IOException {

		ArrayList<Shape> shapesList = Application.getController().getShapesArrayList();

		JSONArray list = new JSONArray();
		JSONObject JSONObject;

		try {
			FileWriter file = new FileWriter(filePath.toString());
			
			for (int i = 0; i < shapesList.size(); i++) {
				JSONObject = new JSONObject();
				
				JSONObject.put("type", shapesList.get(i).getShapeType());
				JSONObject.put("stroke", (shapesList.get(i).getStroke()));
				
				if(shapesList.get(i).getFillColor()==null) 
					JSONObject.put("strokeColor", null);
				else
					JSONObject.put("strokeColor", shapesList.get(i).getStrokeColor().toString());
				
				if(shapesList.get(i).getFillColor()==null) 
					JSONObject.put("fillColor", null);
				else
					JSONObject.put("fillColor", shapesList.get(i).getFillColor().toString());
				
				JSONObject.put("start-x", (shapesList.get(i)).getStartPosition().getX());
				JSONObject.put("start-y", shapesList.get(i).getStartPosition().getY());
				JSONObject.put("end-x", shapesList.get(i).getEndPosition().getX());
				JSONObject.put("end-y", shapesList.get(i).getEndPosition().getY());

				if (shapesList.get(i) instanceof Circle) {
					JSONObject.put("center-x", ((Circle) shapesList.get(i)).getCenter().getY());
					JSONObject.put("center-y", ((Circle) shapesList.get(i)).getCenter().getY());
					JSONObject.put("radius", ((Circle) shapesList.get(i)).getRadius());

				} else if (shapesList.get(i) instanceof Ellipse) {
					// JSONObject.put("center-x", ((Ellipse)shapesList.get(i)).getCenter().getY());
					// JSONObject.put("center-y",((Ellipse)shapesList.get(i)).getCenter().getY());
					// JSONObject.put("radius-x",((Ellipse)shapesList.get(i)).getRadius().getX());
					// JSONObject.put("radius-y",((Ellipse)shapesList.get(i)).getRadius().getY());
				}
				list.add(JSONObject);
				System.out.println(list);
			}
			list.writeJSONString(file);
//			file.flush();
			file.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
