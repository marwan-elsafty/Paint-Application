package controller.commander.commands;

import controller.commander.Change;
import controller.commander.ChangeInvoker;
import controller.commander.ChangeMode;
import model.Shape;

public class AddShape implements Change {

	Shape shape;

	public AddShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void undo() {
		this.shape.setVisible(false);

		ChangeInvoker.getChangeModeHistory().add(ChangeMode.ADD);
	}

	@Override
	public void redo() {
		this.shape.setVisible(true);

		ChangeInvoker.getChangeModeHistory().add(ChangeMode.ADD);
	}
}
