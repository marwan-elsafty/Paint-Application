package model.drawingbuffer.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import model.Shape;
import model.main.Application;
import model.shapes.Circle;

public class WriteInXML implements Writable {

	public WriteInXML() {

	}

	@Override
	public void writeInFile(String filePath) throws IOException {
		ArrayList<Shape> shapesList = Application.getController().getShapesArrayList();

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("shapes");
		document.setRootElement(root);
		Element element;

		try {
			FileWriter file = new FileWriter(filePath);
			for (Shape aShapesList : shapesList) {
				element = document.getRootElement().addElement("element")
						.addAttribute("color", aShapesList.getStrokeColor().toString())
						.addAttribute("strokeWidth", aShapesList.getStroke() + "")
						.addAttribute("fillColor", aShapesList.getFillColor().toString())
						.addAttribute("start-x", aShapesList.getStartPosition().getX() + "")
						.addAttribute("start-y", aShapesList.getStartPosition().getY() + "")
						.addAttribute("end-x", aShapesList.getEndPosition().getX() + "")
						.addAttribute("end-y", aShapesList.getEndPosition().getY() + "");

				if (aShapesList instanceof Circle) {
					element.addAttribute("center-x", ((Circle) aShapesList).getCenter().getX() + "")
							.addAttribute("center-y", ((Circle) aShapesList).getCenter().getY() + "")
							.addAttribute("radius", ((Circle) aShapesList).getRadius() + "");
				}
			}
			document.write(file);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
