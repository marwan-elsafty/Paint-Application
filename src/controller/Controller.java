package controller;

import java.awt.Graphics;
import java.util.ArrayList;

import controller.commander.Change;
import controller.commander.ChangeInvoker;
import controller.commander.ChangeMode;
import model.Factory;
import model.Shape;
import model.main.Application;

public class Controller implements DrawingEngine, Change {

	private ArrayList<Shape> shapes;

	private boolean onRedo;
	private boolean onUndo;

	private ChangeMode changeMode;

	private Controller() {
		shapes = new ArrayList<Shape>();

		onRedo = false;
		onUndo = false;

		changeMode = ChangeMode.NO_CHANGE;
	}

	public boolean isOnRedo() {
		return onRedo;
	}

	public void setOnRedo() {
		this.onRedo = true;
		this.onUndo = false;
	}

	public boolean isOnUndo() {
		return onUndo;
	}

	public void setOnUndo() {
		this.onUndo = true;
		this.onRedo = false;
	}

	public ChangeMode getChangeMode() {
		return this.changeMode;
	}

	public void setChangeMode(ChangeMode changeMode) {
		this.changeMode = changeMode;
	}

	public ArrayList<Shape> getShapesArrayList() {
		return shapes;
	}

	@Override
	public void refresh(Object canvas) {
		Graphics castedCanvas = (Graphics) canvas;
		for (Shape shapeToDraw : shapes)
			shapeToDraw.draw(castedCanvas);
	}

	@Override
	public void addShape(Shape shape) {
		shapes.add(shape);

		ChangeInvoker.resetChangeCount();
		ChangeInvoker.getChangeModeHistory().add(ChangeMode.ADD);
	}

	@Override
	public void removeShape(Shape shape) {
		shape.setVisible(false);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		shapes.set(shapes.indexOf(oldShape), newShape);
	}

	@Override
	public Shape[] getShapes() {
		Shape[] newArray = new Shape[shapes.size()];
		return shapes.toArray(newArray);
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		setOnUndo();

		int LAST = ChangeInvoker.getChangeModeHistory().size() - 1;

		new ChangeInvoker(Factory.detectMode(ChangeInvoker.getChangeModeHistory().get(LAST))).invoke();
		Application.getMainFrame().getCanvas().repaint();
	}

	@Override
	public void redo() {
		setOnRedo();

		int LAST = ChangeInvoker.getChangeModeHistory().size() - 1;

		new ChangeInvoker(Factory.detectMode(ChangeInvoker.getChangeModeHistory().get(LAST))).invoke();
		Application.getMainFrame().getCanvas().repaint();
	}

	/**
	 * singleton class
	 */
	private static Controller instance = null;

	public static Controller getInstance() {
		synchronized (Controller.class) {
			if (instance == null)
				instance = new Controller();
		}
		return instance;
	}
}
