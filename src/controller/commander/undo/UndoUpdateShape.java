package controller.commander.undo;

import controller.commander.Change;
import controller.commander.ChangeCommand;

public class UndoUpdateShape implements ChangeCommand {

	Change change;

	public UndoUpdateShape(Change change) {
		this.change = change;
	}

	@Override
	public void execute() {
		this.change.undo();
	}
}
