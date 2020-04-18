package model.drawingbuffer.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadFromXML implements Readable {

	@Override
	public void readFromFile(String filePath) throws IOException {
		  String[] array = new String[20];
//	        Shape shape;
//	        ArrayList<Shape> shapesList = new ArrayList<>();
	        int j = 0;
	        SAXReader saxReader = new SAXReader();
	        try {
	            Document document = saxReader.read(new FileReader(filePath));
	            Element root = document.getRootElement();
	            for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
	                Element element = it.next();
	                for (Iterator<Attribute> i = element.attributeIterator(); i.hasNext(); ) {
	                    Attribute attribute = i.next();
	                    System.out.println(attribute.getValue());
	                    array[j++] = attribute.getValue();
	                }
	                j = 0;
//	                shape = Factory.detectMode(canvasMode);
//	                shape.setColor(Color.web(array[1]));
//	                shape.setStroke(Double.parseDouble(array[2]));
//	                shape.setFillColor(Color.web(array[3]));
//	                ((Shape) shape).setName(array[0]);
//	                shape.setStartPoint(new Point2D(Double.parseDouble(array[4]), Double.parseDouble(array[5])));
//	                shape.setEndPoint(new Point2D(Double.parseDouble(array[6]), Double.parseDouble(array[7])));
//	                
//	                if (shape instanceof Circle) {
//	                    ((Circle) shape).setCenterPoint(new Point(Double.parseDouble(array[8]), Double.parseDouble(array[9])));
//	                    ((Circle) shape).setRadius(Double.parseDouble(array[10]));
//	                    
//	                } else if (shape instanceof Ellipse) {
//	                    ((Ellipse) shape).setCenterPoint(new Point(Double.parseDouble(array[8]), Double.parseDouble(array[9])));
//	                    ((Ellipse) shape).setRadius(new Point(Double.parseDouble(array[10]), Double.parseDouble(array[11])));
//	                }
//	                Application.getController().getShapesArrayList().add(shape);
	            }
	        } catch (DocumentException | FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
}
