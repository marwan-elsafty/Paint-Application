package view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import controller.commander.ChangeInvoker;
import controller.commander.ChangeMode;
import model.Factory;
import model.Shape;
import model.main.Application;
import model.shapes.Brush;
import model.shapes.Circle;
import model.shapes.Ellipse;
import model.shapes.Line;
import model.shapes.Rectangle;
import model.shapes.Square;
import model.shapes.Triangle;
import view.modes.CanvasMode;
import view.threads.CanvasMouseMotionThread;

public class CustomCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private Point startPosition;
	private Point endPosition;

	private Point newPosition;

	private Point selectedPosition;

	private Stroke lastStrokeUsed;

	private Brush brush;

	private boolean mouseReleased;

	private CustomCanvas() {
		startPosition = new Point();
		endPosition = new Point();

		newPosition = new Point();

		selectedPosition = new Point();

		brush = new Brush();

		mouseReleased = false;

		// monitors events on the drawing area of the frame
		addMouseListener(mouseHandler);

		addMouseMotionListener(mouseMotionHandler);
	}

	/**
	 * singleton class
	 */
	private static CustomCanvas instance = null;

	public static CustomCanvas getInstance() {
		synchronized (CustomCanvas.class) {
			if (instance == null)
				instance = new CustomCanvas();
		}
		return instance;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D guide = (Graphics2D) g;
		super.paint(g);
		guide.setStroke(new BasicStroke(3));
		// stores default stroke
		// to be used again for shapes
		// stroke is only changed while free drawing
		final Stroke defaultStroke = guide.getStroke();

		for (Shape shapeToDraw : Application.getController().getShapes()) {
			if (shapeToDraw.isVisible())
				shapeToDraw.draw(g);

			// reset stroke to reuse for guiding shapes
			guide.setStroke(defaultStroke);
		}

		/*
		 * free drawing
		 */
		if (Application.getMainFrame().getCanvasMode() == CanvasMode.BRUSH) {
			mouseReleased = false;

			guide.setColor(Application.getMainFrame().getStrokeColor());

			guide.setStroke(new BasicStroke(Application.getMainFrame().getBrushSize(), BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND));

			brush.setStrokeColor(Application.getMainFrame().getStrokeColor());
			brush.setStroke(guide.getStroke());

			for (int i = 1; i < brush.getNodes().size(); i++)
				while (mouseReleased == false) {
					guide.draw(new Line2D.Float(brush.getNodes().get(i - 1), brush.getNodes().get(i)));

					break;
				}

			// reset stroke to reuse for guiding shapes
			guide.setStroke(defaultStroke);
		}

		else if (Application.getMainFrame().getCanvasMode() == CanvasMode.ERASER) {
			mouseReleased = false;

			guide.setColor(Application.getMainFrame().getCanvas().getBackground());

			guide.setStroke(new BasicStroke(Application.getMainFrame().getEraserSize(), BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND));

			brush.setStrokeColor(Application.getMainFrame().getCanvas().getBackground());
			brush.setStroke(guide.getStroke());

			for (int i = 1; i < brush.getNodes().size(); i++)
				while (mouseReleased == false) {
					guide.draw(new Line2D.Float(brush.getNodes().get(i - 1), brush.getNodes().get(i)));

					break;
				}

			// reset stroke to reuse for guiding shapes
			guide.setStroke(defaultStroke);
		}

		/*
		 * guide shape when drawing
		 */
		else if (Application.getMainFrame().getCanvasMode() == CanvasMode.LINE
				|| Application.getMainFrame().getCanvasMode() == CanvasMode.CIRCLE
				|| Application.getMainFrame().getCanvasMode() == CanvasMode.ELLIPSE
				|| Application.getMainFrame().getCanvasMode() == CanvasMode.SQUARE
				|| Application.getMainFrame().getCanvasMode() == CanvasMode.RECTANGLE
				|| Application.getMainFrame().getCanvasMode() == CanvasMode.TRIANGLE) {
			if (startPosition != null && endPosition != null) {

				guide.setStroke(new BasicStroke(Application.getMainFrame().getStrokeWidth()));
				lastStrokeUsed = guide.getStroke();

				// makes the guide shape transparent
				guide.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));

				// make guide shape gray for professional look
				guide.setPaint(Color.DARK_GRAY);

				if (Application.getMainFrame().getCanvasMode() == CanvasMode.LINE) {
					g.drawLine(startPosition.x, startPosition.y, endPosition.x, endPosition.y);

				} else if (Application.getMainFrame().getCanvasMode() == CanvasMode.ELLIPSE) {
					g.drawOval(Math.min(startPosition.x, endPosition.x), Math.min(startPosition.y, endPosition.y),
							Math.abs(startPosition.x - endPosition.x), Math.abs(startPosition.y - endPosition.y));

				} else if (Application.getMainFrame().getCanvasMode() == CanvasMode.CIRCLE) {
					g.drawOval(Math.min(startPosition.x, endPosition.x), Math.min(startPosition.y, endPosition.y),
							Math.abs(startPosition.x - endPosition.x), Math.abs(startPosition.x - endPosition.x));

				} else if (Application.getMainFrame().getCanvasMode() == CanvasMode.RECTANGLE) {
					g.drawRect(Math.min(startPosition.x, endPosition.x), Math.min(startPosition.y, endPosition.y),
							Math.abs(startPosition.x - endPosition.x), Math.abs(startPosition.y - endPosition.y));

				} else if (Application.getMainFrame().getCanvasMode() == CanvasMode.SQUARE) {
					g.drawRect(Math.min(startPosition.x, endPosition.x), Math.min(startPosition.y, endPosition.y),
							Math.abs(startPosition.x - endPosition.x), Math.abs(startPosition.x - endPosition.x));

				} else if (Application.getMainFrame().getCanvasMode() == CanvasMode.TRIANGLE) {
					int diffX = startPosition.x - endPosition.x;
					int diffY = startPosition.y - endPosition.y;

					int[] x = { startPosition.x, startPosition.x - diffX, startPosition.x + diffX };
					int[] y = { startPosition.y, startPosition.y - diffY, startPosition.y - diffY };
					g.drawPolygon(x, y, 3);
				}
			}
		}

		/*
		 * guide shape when moving
		 */
		else if (Application.getMainFrame().getCanvasMode() == CanvasMode.MOVER) {
			// makes the guide shape transparent
			guide.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));

			// make guide shape gray for professional look
			guide.setPaint(Color.DARK_GRAY);

			boolean found = false;

			for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
				if (found)
					break;

				if (Application.getController().getShapesArrayList().get(i).contains(selectedPosition)) {
					found = true;

					g.setColor(Color.LIGHT_GRAY);

					if (Application.getController().getShapesArrayList().get(i) instanceof Square) {
						int length = ((Square) Application.getController().getShapesArrayList().get(i)).getLength();
						g.drawRect(newPosition.x, newPosition.y, length, length);

						if (Application.getController().getShapesArrayList().get(i).getFillColor() != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillRect(newPosition.x, newPosition.y, length, length);

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Rectangle) {
						int width = ((Rectangle) Application.getController().getShapesArrayList().get(i)).getWidth();
						int length = ((Rectangle) Application.getController().getShapesArrayList().get(i)).getLength();
						g.drawRect(newPosition.x, newPosition.y, width, length);

						if (Application.getController().getShapesArrayList().get(i).getFillColor() != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillRect(newPosition.x, newPosition.y, width, length);

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Circle) {
						int radius = ((Circle) Application.getController().getShapesArrayList().get(i)).getRadius();
						g.drawOval(newPosition.x, newPosition.y, radius, radius);

						if (Application.getController().getShapesArrayList().get(i).getFillColor() != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillOval(newPosition.x, newPosition.y, radius, radius);

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Ellipse) {
						int majorAxe = ((Ellipse) Application.getController().getShapesArrayList().get(i))
								.getMajorAxe();
						int minorAxe = ((Ellipse) Application.getController().getShapesArrayList().get(i))
								.getMinorAxe();
						g.drawOval(newPosition.x, newPosition.y, majorAxe, minorAxe);
						if (Application.getController().getShapesArrayList().get(i).getFillColor() != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillOval(newPosition.x, newPosition.y, majorAxe, minorAxe);

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Triangle) {
						int diffX = ((Triangle) Application.getController().getShapesArrayList().get(i))
								.calculateDiffrenceX();
						int diffY = ((Triangle) Application.getController().getShapesArrayList().get(i))
								.calculateDiffrenceY();
						int[] x = { newPosition.x, newPosition.x - diffX, newPosition.x + diffX };
						int[] y = { newPosition.y, newPosition.y - diffY, newPosition.y - diffY };
						g.drawPolygon(x, y, 3);

						if (Application.getController().getShapesArrayList().get(i).getFillColor() != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillPolygon(x, y, 3);

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Line) {
						// g.drawLine(newPosition.x, newPosition.y, endPosition.x, endPosition.y);
						// TODO LINE HERE
					}
				}
			}
		}

		/*
		 * guide when resizing
		 */
		else if (Application.getMainFrame().getCanvasMode() == CanvasMode.RESIZER) {
			Point start = new Point();

			// makes the guide shape transparent
			guide.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));

			// make guide shape gray for professional look
			guide.setPaint(Color.LIGHT_GRAY);

			boolean found = false;

			for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
				if (found)
					break;

				if (Application.getController().getShapesArrayList().get(i).contains(selectedPosition)) {
					found = true;
					if (Application.getController().getShapesArrayList().get(i) instanceof Square) {
						start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();

						g.drawRect(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.x - newPosition.x));

						if (Application.getController().getShapesArrayList().get(i) != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillRect(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.x - newPosition.x));

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Rectangle) {
						start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();

						g.drawRect(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.y - newPosition.y));

						if (Application.getController().getShapesArrayList().get(i) != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillRect(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.y - newPosition.y));

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Circle) {
						start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();

						g.drawOval(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.x - newPosition.x));

						if (Application.getController().getShapesArrayList().get(i) != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillOval(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.x - newPosition.x));

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Ellipse) {
						start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();

						g.drawOval(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.y - newPosition.y));

						if (Application.getController().getShapesArrayList().get(i) != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillOval(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.abs(start.x - newPosition.x), Math.abs(start.y - newPosition.y));

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Line) {
						start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();

						g.drawLine(Math.min(start.x, newPosition.x), Math.min(start.y, newPosition.y),
								Math.max(start.x, newPosition.x), Math.max(start.y, newPosition.y));

					} else if (Application.getController().getShapesArrayList().get(i) instanceof Triangle) {
						start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();
						int difference = start.x - newPosition.x;
						int[] x = { start.x, start.x + difference, newPosition.x };
						int[] y = { start.y, newPosition.y, newPosition.y };
						g.drawPolygon(x, y, 3);

						if (Application.getController().getShapesArrayList().get(i) != null)
							g.setColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						g.fillPolygon(x, y, 3);
					}
				}
			}
		}
		repaint();
	}

	private MouseListener mouseHandler = new MouseAdapter() {
		@Override
		public void mouseExited(MouseEvent mouseEvent) {
			Application.getMainFrame().lblHoveredX.setText("0");
			Application.getMainFrame().lblHoveredY.setText("0");
		}

		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			if (Application.getMainFrame().getCanvasMode() == CanvasMode.SELECTOR) {
				boolean found = false;

				Application.getMainFrame().getPropertiesPanel().getLabelShapeName().setText("Shape.");

				Application.getMainFrame().getPropertiesPanel().getTxtFieldLength().setText("-");
				Application.getMainFrame().getPropertiesPanel().getTxtFieldWidth().setText("-");
				Application.getMainFrame().getPropertiesPanel().getTxtFieldMajorAxe().setText("-");
				Application.getMainFrame().getPropertiesPanel().getTxtFieldMinorAxe().setText("-");
				Application.getMainFrame().getPropertiesPanel().getTxtFieldRadius().setText("-");
				Application.getMainFrame().getPropertiesPanel().getStrokeColorPreview().setBackground(Color.BLACK);
				Application.getMainFrame().getPropertiesPanel().getFillColorPreview().setBackground(Color.WHITE);

				for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
					if (found)
						break;

					Shape shapeToSelect = Application.getController().getShapesArrayList().get(i);
					if (shapeToSelect.contains(mouseEvent.getPoint())) {
						found = true;

						if (shapeToSelect instanceof Square) {
							Application.getMainFrame().getPropertiesPanel().getTxtFieldLength()
									.setText(((Square) shapeToSelect).getLength() + "");

						} else if (shapeToSelect instanceof Rectangle) {
							Application.getMainFrame().getPropertiesPanel().getTxtFieldLength()
									.setText(((Rectangle) shapeToSelect).getLength() + "");
							Application.getMainFrame().getPropertiesPanel().getTxtFieldWidth()
									.setText(((Rectangle) shapeToSelect).getWidth() + "");

						} else if (shapeToSelect instanceof Circle) {
							Application.getMainFrame().getPropertiesPanel().getTxtFieldRadius()
									.setText(((Circle) shapeToSelect).getRadius() + "");

						} else if (shapeToSelect instanceof Ellipse) {
							Application.getMainFrame().getPropertiesPanel().getTxtFieldMajorAxe()
									.setText(((Ellipse) shapeToSelect).getMajorAxe() + "");
							Application.getMainFrame().getPropertiesPanel().getTxtFieldMinorAxe()
									.setText(((Ellipse) shapeToSelect).getMinorAxe() + "");

						} else if (shapeToSelect instanceof Triangle) {

						} else if (shapeToSelect instanceof Line) {
							Application.getMainFrame().getPropertiesPanel().getTxtFieldLength()
									.setText(((Square) shapeToSelect).getLength() + "");
						} else if (shapeToSelect instanceof Brush) {

						} else {
							return;
						}

						if (shapeToSelect.getStrokeColor() == null)
							Application.getMainFrame().getPropertiesPanel().getStrokeColorPreview()
									.setBackground(Color.BLACK);
						else
							Application.getMainFrame().getPropertiesPanel().getStrokeColorPreview()
									.setBackground(shapeToSelect.getStrokeColor());

						if (shapeToSelect.getStrokeColor() == null)
							Application.getMainFrame().getPropertiesPanel().getFillColorPreview()
									.setBackground(Color.BLACK);
						else
							Application.getMainFrame().getPropertiesPanel().getFillColorPreview()
									.setBackground(shapeToSelect.getFillColor());

						Application.getMainFrame().getPropertiesPanel().getLabelShapeName()
								.setText(shapeToSelect.getShapeName() + ".");

					}
				}
			}

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.BRUSH)
				brush.getNodes().add(mouseEvent.getPoint());

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.ERASER)
				brush.getNodes().add(mouseEvent.getPoint());

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.LINE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.CIRCLE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.ELLIPSE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.SQUARE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.RECTANGLE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.TRIANGLE) {
				// when mouse is pressed get x and y position
				startPosition = mouseEvent.getPoint();
				endPosition = startPosition;
			}

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.MOVER
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.RESIZER) {
				// when mouse is pressed get x and y position
				selectedPosition = mouseEvent.getPoint();
			}

			/*
			 * remove shape
			 */
			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.REMOVER) {
				boolean found = false;

				for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
					if (found)
						break;

					Shape shapeToRemove = Application.getController().getShapesArrayList().get(i);
					if (shapeToRemove.contains(mouseEvent.getPoint())) {
						found = true;

						shapeToRemove.setVisible(false);

						ChangeInvoker.getChangeModeHistory().add(ChangeMode.REMOVE);
					}
				}
			}

			/*
			 * duplicate shape
			 */
			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.DUPLICATOR) {
				boolean found = false;

				for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
					if (found)
						break;

					Shape shapeToDuplicate = Application.getController().getShapesArrayList().get(i);
					if (shapeToDuplicate.contains(mouseEvent.getPoint())) {
						found = true;

						Shape newShape = Factory.detectMode(shapeToDuplicate.getShapeType());

						try {
							if (newShape instanceof Brush) {
								for (int j = 1; j < ((Brush) shapeToDuplicate).getNodes().size() + 1; j++) {
									Point newPoint = new Point(((Brush) shapeToDuplicate).getNodes().get(j - 1).x + 20,
											((Brush) shapeToDuplicate).getNodes().get(j - 1).y + 20);
									((Brush) newShape).getNodes().add(newPoint);
								}
								((Brush) newShape).setStroke(((Brush) shapeToDuplicate).getStroke());
							} else {
								if (newShape instanceof Square)
									newShape = (Square) shapeToDuplicate.clone();

								else if (newShape instanceof Rectangle)
									newShape = (Rectangle) shapeToDuplicate.clone();

								else if (newShape instanceof Circle)
									newShape = (Circle) shapeToDuplicate.clone();

								else if (newShape instanceof Ellipse)
									newShape = (Ellipse) shapeToDuplicate.clone();

								else if (newShape instanceof Triangle)
									newShape = (Triangle) shapeToDuplicate.clone();

								else if (newShape instanceof Line)
									newShape = (Line) shapeToDuplicate.clone();

								else
									return;

								newShape.setVisible(true);

								Point newStartPoint = new Point(shapeToDuplicate.getStartPosition().x + 10,
										shapeToDuplicate.getStartPosition().y + 10);
								newShape.setStartPosition(newStartPoint);

								Point newEndPoint = new Point(shapeToDuplicate.getEndPosition().x + 10,
										shapeToDuplicate.getEndPosition().y + 10);
								newShape.setEndPosition(newEndPoint);
							}

						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
							throw new RuntimeException();
						}

						Application.getController().getShapesArrayList().add(newShape);
						// ChangeInvoker.getChangeModeHistory().add(ChangeMode.);
					}
				}
			}
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			if (Application.getMainFrame().getCanvasMode() == CanvasMode.SELECTOR)
				return;

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.BRUSH) {
				mouseReleased = true;

				Brush newShape = brush;

				Application.getController().addShape(newShape);

				// references new brush
				// for re-usability
				brush = new Brush();
			}

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.ERASER) {
				mouseReleased = true;

				Brush newShape = brush;

				Application.getController().addShape(newShape);

				// references new brush
				// for re-usability
				brush = new Brush();
			}

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.LINE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.CIRCLE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.ELLIPSE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.SQUARE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.RECTANGLE
					|| Application.getMainFrame().getCanvasMode() == CanvasMode.TRIANGLE) {

				endPosition = mouseEvent.getPoint();

				Shape newShape = Factory.detectMode(Application.getMainFrame().getCanvasMode());
				newShape.setStartPosition(startPosition);
				newShape.setEndPosition(endPosition);
				newShape.setStrokeColor(Application.getMainFrame().getStrokeColor());
				newShape.setFillColor(Application.getMainFrame().getFillColor());
				newShape.setStroke(lastStrokeUsed);

				if (Application.getMainFrame().getCanvasMode() == CanvasMode.TRIANGLE) {
					int diffX = startPosition.x - endPosition.x;
					int diffY = startPosition.y - endPosition.y;

					int[] x = { startPosition.x, startPosition.x - diffX, startPosition.x + diffX };
					int[] y = { startPosition.y, startPosition.y - diffY, startPosition.y - diffY };

					((Triangle) newShape).setX(x);
					((Triangle) newShape).setX(y);
				}

				Application.getController().addShape(newShape);

				startPosition = null;
				endPosition = null;
			}

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.MOVER) {

				// Point modifiedSelectedPoint = new Point(mouseEvent.getXOnScreen() -
				// selectedPosition.x , mouseEvent.getYOnScreen() - selectedPosition.y );

				newPosition = mouseEvent.getPoint();

				boolean found = false;

				for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
					if (found)
						break;

					if (Application.getController().getShapesArrayList().get(i).contains(selectedPosition)) {
						found = true;
						Shape newShape = Application.getController().getShapesArrayList().get(i);

						newShape.setStartPosition(newPosition);

						if (Application.getController().getShapesArrayList().get(i) instanceof Triangle) {
							((Triangle) Application.getController().getShapesArrayList().get(i)).setTrue();

							int diffX = ((Triangle) Application.getController().getShapesArrayList().get(i))
									.calculateDiffrenceX();
							int diffY = ((Triangle) Application.getController().getShapesArrayList().get(i))
									.calculateDiffrenceY();
							int[] x = { newPosition.x, newPosition.x - diffX, newPosition.x + diffX };
							int[] y = { newPosition.y, newPosition.y - diffY, newPosition.y - diffY };

							((Triangle) Application.getController().getShapesArrayList().get(i)).setX(x);
							((Triangle) Application.getController().getShapesArrayList().get(i)).setY(y);
						}

						Application.getController().updateShape(Application.getController().getShapesArrayList().get(i),
								newShape);
					}
				}
			}

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.RESIZER) {
				newPosition = mouseEvent.getPoint();

				Shape newShape = null;
				boolean found = false;

				for (int i = Application.getController().getShapesArrayList().size() - 1; i >= 0; i--) {
					if (found)
						break;

					if (Application.getController().getShapesArrayList().get(i).contains(selectedPosition)) {
						found = true;

						newShape = Factory
								.detectMode(Application.getController().getShapesArrayList().get(i).getShapeType());

						// triangle has special coordinates
						if (newShape instanceof Triangle) {

							newShape = new Triangle();

							Point start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();
							int difference = start.x - newPosition.x;
							int[] x = { start.x, start.x + difference, newPosition.x };
							int[] y = { start.y, newPosition.y, newPosition.y };
							((Triangle) newShape).setX(x);
							((Triangle) newShape).setX(y);
						}

						Point start = Application.getController().getShapesArrayList().get(i).generateShapeOrigin();
						newShape.setStartPosition(start);
						newShape.setEndPosition(newPosition);
						newShape.setStroke(Application.getController().getShapesArrayList().get(i).getStroke());
						newShape.setStrokeColor(
								Application.getController().getShapesArrayList().get(i).getStrokeColor());
						newShape.setFillColor(Application.getController().getShapesArrayList().get(i).getFillColor());
						Application.getController().updateShape(Application.getController().getShapesArrayList().get(i),
								newShape);
						// Application.getController()
						// .updateShape(Application.getController().getShapesArrayList().get(i),
						// newShape);
					}
				}
			}
			startPosition = null;
			endPosition = null;

			repaint();
		}
	};

	private MouseMotionListener mouseMotionHandler = new MouseMotionAdapter() {
		@Override
		public void mouseMoved(MouseEvent mouseEvent) {
			Application.getMainFrame().lblHoveredX.setText(mouseEvent.getX() + "");
			Application.getMainFrame().lblHoveredY.setText(mouseEvent.getY() + "");
		}

		@Override
		public void mouseDragged(MouseEvent mouseEvent) {
			// starts thread
			// to show coordinates while dragging
			new CanvasMouseMotionThread().start();

			if (Application.getMainFrame().getCanvasMode() == CanvasMode.SELECTOR)
				return;

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.BRUSH)
				brush.getNodes().add(mouseEvent.getPoint());

			else if (Application.getMainFrame().getCanvasMode() == CanvasMode.ERASER)
				brush.getNodes().add(mouseEvent.getPoint());

			// get the final x and y position after the mouse is dragged
			endPosition = mouseEvent.getPoint();
			newPosition = mouseEvent.getPoint();

			repaint();
		}
	};
}
