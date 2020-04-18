package controller.commander.commands;

import controller.commander.Change;
import controller.commander.ChangeInvoker;
import controller.commander.ChangeMode;
import model.Shape;

public class RemoveShape implements Change {

	Shape shape;

	public RemoveShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void undo() {
		this.shape.setVisible(true);

		ChangeInvoker.getChangeModeHistory().add(ChangeMode.REMOVE);
	}

	@Override
	public void redo() {
		this.shape.setVisible(false);

		ChangeInvoker.getChangeModeHistory().add(ChangeMode.REMOVE);
	}
}
