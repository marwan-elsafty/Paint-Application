package controller.commander.commands;

import controller.commander.Change;
import model.Shape;

public class UpdateShape implements Change {

	Shape shape;

	public UpdateShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

}
